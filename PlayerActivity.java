package com.ratingrocker.appwithmenu;
// TutorialApp
// Created by Spotify on 25/02/14.
// Copyright (c) 2014 Spotify. All rights reserved.
//TODO divide main page into fragments/ start by making fragments work on their own

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
//import android.os.Looper;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
//import android.widget.ExpandableListView;
// import android.widget.ScrollView;
//import android.widget.CheckBox;
//import android.widget.ExpandableListView;
//import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
//import android.widget.SeekBar;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;

//import android.widget.Toast;

//import android.widget.Toast;
//import android.support.v4.app.FragmentActivity;
import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Connectivity;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.Metadata;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlaybackState;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;


//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;


public class PlayerActivity extends Activity implements
        SpotifyPlayer.NotificationCallback, ConnectionStateCallback {


        // TODO: Replace with your client ID
        private static final String CLIENT_ID = "d570917696114c588cc8e1b302d801ed";
        // TODO: Replace with your redirect URI
        private static final String REDIRECT_URI = "rating-rocker-login://callback";

        // Request code that will be used to verify if the result comes from correct activity
        // Can be any integer
        private static final int REQUEST_CODE = 1337;
        private Player mPlayer;

        private TextView mMetadataText, freshView, chillView, partyView, studyView, mplaylist;
        private PlaybackState mCurrentPlaybackState;
        private BroadcastReceiver mNetworkStateReceiver;
        private static final String TAG = "Mymessages";
        private Metadata mMetadata;
        private ToggleButton playpause;
        //private CheckBox rap, alt, rock, funk, edm;
        //private SeekBar partyseek, chillseek, studyseek, freshseek;

        //private ArrayList queuelist;
        MyDBHandler db;
     /*Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                updateView();

            }
        };
*/
        private final Player.OperationCallback mOperationCallback = new Player.OperationCallback() {
            @Override
            public void onSuccess() {
                Log.i(TAG, "OK!");
            }

            @Override
            public void onError(Error error) {
                Log.i(TAG,"ERROR:" + error);
            }

        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.mainactivity3);
//

            mMetadataText = (TextView) findViewById(R.id.metadatas);
            mplaylist = (TextView) findViewById(R.id.metadata);

            db = new MyDBHandler(this);
            //
            //List<String> queuelist = new ArrayList<>();
            /*
            //queuelist.add("spotify:track:2f0PgPdLCFPU1eA50Q4yFr"); queuelist.add("spotify:track:5MJ5p2NBKQxpmjeHWcUQkH");queuelist.add("spotify:track:1pWE5VPu8OYgn23SyVMwOo");
            partyseek = (SeekBar) findViewById(R.id.partyseekBar);
            partyView = (TextView) findViewById(R.id.PartyvibevalText);
            chillseek = (SeekBar) findViewById(R.id.chillseekBar);
            chillView = (TextView) findViewById(R.id.chillvibevalText);
            studyseek = (SeekBar) findViewById(R.id.studyseekBar);
            studyView = (TextView) findViewById(R.id.studyvibevalText);
            freshseek = (SeekBar) findViewById(R.id.freshseekBar);
            freshView = (TextView) findViewById(R.id.freshnessvalText);
            rap = (CheckBox) findViewById(R.id.checkboxtextGenre1);
            alt = (CheckBox) findViewById(R.id.checkboxtextGenre2);
            rock = (CheckBox) findViewById(R.id.checkboxtextGenre3);
            funk = (CheckBox) findViewById(R.id.checkboxtextGenre4);
            edm = (CheckBox) findViewById(R.id.checkboxtextGenre5);
            //dropdown
            ///

*/
            AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID,
                    AuthenticationResponse.Type.TOKEN,
                    REDIRECT_URI);
            builder.setScopes(new String[]{"user-read-private", "streaming"});
            AuthenticationRequest request = builder.build();

            AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);

        }

        @Override
        protected void onResume() {
            super.onResume();
            // Set up the broadcast receiver for network events. Note that we also unregister
            // this receiver again in onPause().
            mNetworkStateReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (mPlayer != null) {
                        Connectivity connectivity = getNetworkConnectivity(getBaseContext());
                        mPlayer.setConnectivityStatus(mOperationCallback, connectivity);
                    }
                }
            };
            IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(mNetworkStateReceiver, filter);

            if (mPlayer != null) {
                mPlayer.addNotificationCallback(PlayerActivity.this);
                mPlayer.addConnectionStateCallback(PlayerActivity.this);
            }
        }
        private Connectivity getNetworkConnectivity(Context context) {
            ConnectivityManager connectivityManager;
            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnected()) {
                return Connectivity.fromNetworkType(activeNetwork.getType());
            } else {
                return Connectivity.OFFLINE;
            }
        }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
            super.onActivityResult(requestCode, resultCode, intent);

            // Check if result comes from the correct activity
            if (requestCode == REQUEST_CODE) {
                AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
                if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                    Config playerConfig = new Config(this, response.getAccessToken(), CLIENT_ID);
                    Spotify.getPlayer(playerConfig, this, new SpotifyPlayer.InitializationObserver() {
                        @Override
                        public void onInitialized(SpotifyPlayer spotifyPlayer) {
                            mPlayer = spotifyPlayer;
                            mPlayer.addConnectionStateCallback(PlayerActivity.this);
                            mPlayer.addNotificationCallback(PlayerActivity.this);

                        }
                        @Override
                        public void onError(Throwable throwable) {
                            Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
                        }
                    });
                }
            }
        }

    public void onPlaylistButtonClicked(View view){
            Intent i = new Intent(this, Main2Activity.class);
            startActivity(i);
        }
        public void onNewButtonClicked(View view){
        Intent i = new Intent(this, CreateNewPlaylist.class);
        startActivity(i);
    }
    public void onTrackButtonClicked(View view) {
        Intent i = new Intent(this, Main2Activity.class);
        startActivity(i);

        //mPlayer.playUri(mOperationCallback, "spotify:track:5glXTXNIkUMLIDJVMEBLFQ", 0, 0);

    }

        private void updateView() {
            /*mMetadata = mPlayer.getMetadata();
            partyseek.setProgress(10);
            partyView.setText("");
            chillseek.setProgress(10);
            studyseek.setProgress(10);
            freshseek.setProgress(10);
            chillView.setText("");
            studyView.setText("");
            freshView.setText("");
*/
            final ImageView coverArtView = (ImageView) findViewById(R.id.cover_art);
            if (mMetadata != null && mMetadata.currentTrack != null) {
                //final String durationStr = String.format(" (%dms)", mMetadata.currentTrack.durationMs);
                //mMetadataText.setText(mMetadata.contextName + "\n" + mMetadata.currentTrack.name + " - " + mMetadata.currentTrack.artistName + durationStr);
                mplaylist.setText(mMetadata.contextName);
                mMetadataText.setText( mMetadata.currentTrack.name + " - "
                        + mMetadata.currentTrack.artistName);
                Picasso.with(this)
                        .load(mMetadata.currentTrack.albumCoverWebUrl)
                        /*.transform(new Transformation() {
                            @Override
                            public Bitmap transform(Bitmap source) {
                                // really ugly darkening trick
                                final Bitmap copy = source.copy(source.getConfig(), true);
                                source.recycle();
                                final Canvas canvas = new Canvas(copy);
                                canvas.drawColor(0xbb000000);
                                return copy;
                            }

                            @Override
                            public String key() {
                                return "darken";
                            }
                        })
                        */
                        .into(coverArtView);
            } else {
                mMetadataText.setText("<nothing is playing>");
               // coverArtView.setBackground();
            }


            /*
            rap.setChecked(false);
            alt.setChecked(false);
            funk.setChecked(false);
            edm.setChecked(false);
            rock.setChecked(false);
            */
        }
        /* Communication with fragments*/


//TODO thiw won't work until everything is loaded onto this activity page
    //Todo create a menu
    //Will set up everything first then redo layout to mach new setup






     /*Commands for playback */




        public void onAlbumButtonClicked(View view) {

            String uri = mMetadata.contextUri;
            String newuri = "";
            String uri1 = "spotify:user:austen2442:playlist:1VGYF8Kgk5ZC27WtSNx7JD";
            String uri2 = "spotify:user:1221015148:playlist:00L4b471lahfFLpcT5MZfX";
            String uri3 = "spotify:user:1221015148:playlist:5AEuJGeKUU12V6h5EoskYD";
            String uri4 = "spotify:user:djbkaye:playlist:2AcH9skrLqwuTZqO4kmJDw";
            String uri5 = "spotify:user:spotify:playlist:37i9dQZEVXcPKy0U2e7eN5";
            String uri6 = "spotify:user:1221015148:playlist:5uRhVWWbmRz9LDgHlb95JT";
            if (!uri.equals(uri1)){
                if(!uri.equals(uri2)){
                    if(!uri.equals(uri3)){
                        if(!uri.equals(uri4)) {
                            if (!uri.equals(uri5)) {
                                newuri = uri1;
                            } else {newuri = uri6;}
                        }else{ newuri = uri5;}
                    }else{ newuri = uri4;}
                }else{ newuri = uri3;}
            }else{ newuri = uri2;}

            mPlayer.playUri(mOperationCallback, newuri, 0, 0);


        }

        //public void onPlaylistButtonClicked(View view) {

        //   mPlayer.playUri(mOperationCallback, "spotify:user:spotify:playlist:37i9dQZF1E9G8oeYG9uL66", 0, 0);

        //}


        public void onPauseButtonClicked(View view) {
            playpause = (ToggleButton) findViewById(R.id.pause_button);
            if (playpause.isChecked()){
                mPlayer.resume(mOperationCallback);
            }else {
                mPlayer.pause(mOperationCallback);

            }
           /* if (mCurrentPlaybackState != null && mCurrentPlaybackState.isPlaying) {
                mPlayer.pause(mOperationCallback);
            }
            }*/
        }

        public void onSkipToPreviousButtonClicked(View view) {
            mPlayer.skipToPrevious(mOperationCallback);

        }

        public void onSkipToNextButtonClicked(View view) {
            mPlayer.skipToNext(mOperationCallback);

        }

        public void onQueueSongButtonClicked(View view) {


            // mPlayer.queue(mOperationCallback, "spotify:track:2ms1w53aIRN2nrQsedUua4" );

        }

        public void onToggleShuffleButtonClicked(View view) {
            mPlayer.setShuffle(mOperationCallback, true);

        }

        public void onToggleRepeatButtonClicked(View view) {
            mPlayer.setRepeat(mOperationCallback, !mCurrentPlaybackState.isRepeating);
        }
        public void onGClicked(View view){

        }
        public void onSaveButtonClicked(View view) {
            int genreval = 1;
           /* Runnable save = new Runnable() {
                @Override
                public void run() {

                }
            }
            /*
            partyView.setText(String.valueOf(partyseek.getProgress()));
            chillView.setText(String.valueOf(chillseek.getProgress()));
            studyView.setText(String.valueOf(studyseek.getProgress()));
            freshView.setText(String.valueOf(freshseek.getProgress()));
            mMetadata = mPlayer.getMetadata();
            String songname = mMetadata.currentTrack.name;
            String songidd = mMetadata.currentTrack.uri;
            if (rap.isChecked()){
                Log.e("GENRE", "RAP");
                genreval=genreval*3;
            }
            if (alt.isChecked()){
                Log.e("GENRE", "ALT");
                genreval=genreval*5;
            }
            if (edm.isChecked()){
                Log.e("GENRE", "EDM");
                genreval=genreval*7;
            }
            if (funk.isChecked()){
                Log.e("GENRE", "FUNK");
                genreval=genreval*11;
            }
            if (rock.isChecked()){
                Log.e("GENRE", "ROCK");
                genreval=genreval*13;
            }
            Log.e("G val", String.valueOf(genreval));

            int freshval = freshseek.getProgress();
            if (chillseek.getProgress() > 60){
                int chillval = chillseek.getProgress();
                Vibedata newsong = new Vibedata(chillval, songidd, songname, 1, freshval, genreval);
                db.addnewrating(newsong, 1);
                //db.getrequestlist(1,70, 90, 0, 100, 33);
                db.databaseToString(1);
            }
            if (studyseek.getProgress() > 60){
                int studyval = studyseek.getProgress();
                Vibedata song = new Vibedata(studyval, songidd, songname, 1, freshval, genreval);
                db.addnewrating(song,2);
                db.getrequestlist(2,70, 90, 0, 100, 33);

            }
            if (partyseek.getProgress() > 60){
                int partyval = partyseek.getProgress();
                Vibedata song = new Vibedata(partyval, songidd, songname, 1, freshval, genreval);
                db.addnewrating(song,3);
                db.getrequestlist(3,70, 90, 0, 100, 33);
                //db.databaseToString(3);

        }
        */

        }

        /*public void onSeekButtonClicked(View view) {
            final Integer seek = Integer.valueOf(mSeekEditText.getText().toString());
            mPlayer.seekToPosition(mOperationCallback, seek);
        }
*/
        @Override
        protected void onDestroy() {
            // VERY IMPORTANT! This must always be called or else you will leak resources
            Spotify.destroyPlayer(this);
            super.onDestroy();
        }

        @Override
        public void onPlaybackEvent(PlayerEvent playerEvent) {
            Log.d("MainActivity", "Playback event received: " + playerEvent.name());
            switch (playerEvent) {

                case kSpPlaybackNotifyPlay:
                    break;
                case kSpPlaybackNotifyPause:
                    break;
                case kSpPlaybackNotifyTrackChanged:
                    break;
                case kSpPlaybackNotifyNext:
                    break;
                case kSpPlaybackNotifyPrev:
                    break;
                case kSpPlaybackNotifyShuffleOn:
                    break;
                case kSpPlaybackNotifyShuffleOff:
                    break;
                case kSpPlaybackNotifyRepeatOn:
                    break;
                case kSpPlaybackNotifyRepeatOff:
                    break;
                case kSpPlaybackNotifyBecameActive:
                    break;
                case kSpPlaybackNotifyBecameInactive:
                    break;
                case kSpPlaybackNotifyLostPermission:
                    break;
                case kSpPlaybackEventAudioFlush:
                    break;
                case kSpPlaybackNotifyAudioDeliveryDone:
                    break;
                case kSpPlaybackNotifyContextChanged:
                    break;
                case kSpPlaybackNotifyTrackDelivered:
                    break;
                case kSpPlaybackNotifyMetadataChanged:

                            mMetadata = mPlayer.getMetadata();

                            Log.e(TAG, "Metadata: " + mMetadata);
                            updateView();

                    /*
                    int i = 1;
                    while (queuelist.size()>(i-1)){
                        if (mMetadata.currentTrack.uri == queuelist.get(i-1)) {
                            mPlayer.queue(mOperationCallback, (String) queuelist.get(i));
                            queuelist.remove(i-1);
                            i = queuelist.size();
                        }
                        else { i++;}


                    }

                    break;

                default:

                    break;
            }
*/
        }}

        @Override
        public void onPlaybackError(Error error) {
            Log.d("MainActivity", "Playback error received: " + error.name());
            switch (error) {
                // Handle error type as necessary
                default:
                    break;
            }
        }

        @Override
        public void onLoggedIn() {
            Log.d("MainActivity", "User logged in");
            mPlayer.playUri(null, "spotify:user:spotify:playlist:37i9dQZF1E9G8oeYG9uL66", 0, 0);
            mPlayer.pause(mOperationCallback);
            // mPlayer.playUri(null, "spotify:track:2TpxZ7JUBn3uw46aR7qd6V", 0, 0);
        }

        @Override
        public void onLoginFailed(Error error) {
            Log.d("MainActivity", "Login failed");
        }

        @Override
        public void onLoggedOut() {
            Log.d("MainActivity", "User logged out");
        }
        @Override
        public void onTemporaryError() {
            Log.d("MainActivity", "Temporary error occurred");
        }

        @Override
        public void onConnectionMessage(String message) {
            Log.d("MainActivity", "Received connection message: " + message);
        }



}
