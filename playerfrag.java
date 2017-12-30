package com.ratingrocker.appwithmenu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.app.Activity;
import android.os.Looper;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
//import android.widget.ExpandableListView;
// import android.widget.ScrollView;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.SeekBar;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

//import android.widget.Toast;

//import android.widget.Toast;
import android.support.v4.app.FragmentActivity;
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

//TODO Add button listeners for playback buttons
public class playerfrag extends Fragment implements
        SpotifyPlayer.NotificationCallback, ConnectionStateCallback{
    private static final String CLIENT_ID = "d570917696114c588cc8e1b302d801ed";
    // TODO: Replace with your redirect URI
    private static final String REDIRECT_URI = "rating-rocker-login://callback";

    // Request code that will be used to verify if the result comes from correct activity
    // Can be any integer
    private static final int REQUEST_CODE = 1337;
    private Player mPlayer;
    private TextView mMetadataText;
    private PlaybackState mCurrentPlaybackState;
    private BroadcastReceiver mNetworkStateReceiver;
    private static final String TAG = "Mymessages";
    private Metadata mMetadata;
    private TextView playlist_name;

    //final ToggleButton play_button;
   //private ToggleButton shuffle_button;
   //private Button skip_back;
   // private Button skip_forward, album_button;


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
/*
    PlaybarListener activitycommander;
    public interface PlaybarListener{
        public
    }
    */
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.player_layout, container, false);
        mMetadataText= (TextView) view.findViewById(R.id.metadatas);
        playlist_name= (TextView) view.findViewById(R.id.metadata);
        final ToggleButton play_button = (ToggleButton) view.findViewById(R.id.pause_button);
        final ToggleButton shuffle_button = view.findViewById(R.id.toggle_shuffle_button);
        final Button skip_back = view.findViewById(R.id.skip_prev_button);
        final Button skip_forward = view.findViewById(R.id.skip_next_button);
        final Button album_button = view.findViewById(R.id.play_album_button);
        //Todo replace the track and album button with ways to switch to other screens
        play_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            playbuttonClicked(v);
                        }
                    });
        shuffle_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shufflebuttonClicked(v);
                    }
                });
        skip_back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        skipbackClicked(v);
                    }
                });
        skip_forward.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        skipforwardClicked(v);
                    }
                });
        album_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        albumbuttonClicked(v);
                    }
                });

        AuthenticationRequest.Builder builder = new AuthenticationRequest.Builder(CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                REDIRECT_URI);
        builder.setScopes(new String[]{"user-read-private", "playlist-read", "playlist-read-private", "streaming"});
        AuthenticationRequest request = builder.build();

        AuthenticationClient.openLoginActivity(getActivity(), REQUEST_CODE, request);

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        // Set up the broadcast receiver for network events. Note that we also unregister
        // this receiver again in onPause().
        mNetworkStateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (mPlayer != null) {
                    //Connectivity connectivity = getNetworkConnectivity(getBaseContext());
                   // mPlayer.setConnectivityStatus(mOperationCallback, connectivity);
                }
            }
        };
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        //registerReceiver(mNetworkStateReceiver, filter);

        if (mPlayer != null) {
            mPlayer.addNotificationCallback(playerfrag.this);
            mPlayer.addConnectionStateCallback(playerfrag.this);
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
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
            if (response.getType() == AuthenticationResponse.Type.TOKEN) {
                Config playerConfig = new Config(getActivity(), response.getAccessToken(), CLIENT_ID);
                Spotify.getPlayer(playerConfig, this, new SpotifyPlayer.InitializationObserver() {
                    @Override
                    public void onInitialized(SpotifyPlayer spotifyPlayer) {
                        mPlayer = spotifyPlayer;
                        mPlayer.addConnectionStateCallback(playerfrag.this);
                        mPlayer.addNotificationCallback(playerfrag.this);

                    }
                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
                    }
                });
            }
        }
    }
    public void playbuttonClicked(View view) {

    }
    public void shufflebuttonClicked(View view) {

    }
    public void skipbackClicked(View view) {
        mPlayer.skipToPrevious(mOperationCallback);
    }
    public void skipforwardClicked(View view) {
        mPlayer.skipToPrevious(mOperationCallback);

    }
    public void albumbuttonClicked(View view) {
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

    private void updateView() {
        mMetadata = mPlayer.getMetadata();


        if (mMetadata != null && mMetadata.currentTrack != null) {
            playlist_name.setText(mMetadata.contextName);
            mMetadataText.setText( mMetadata.currentTrack.name + " - "
                    + mMetadata.currentTrack.artistName);
        } else {
            mMetadataText.setText("<nothing is playing>");
            //coverArtView.setBackground(null);
        }

    }
        /* Communication with fragments*/


//TODO thiw won't work until everything is loaded onto this activity page
    //Todo create a menu
    //Will set up everything first then redo layout to mach new setup




/*
    @Override
    public void changeMetadata(String playlistname, String songinfo){
        playerfrag pfrag = (playerfrag) getSupportFragmentManager().findFragmentById(R.id.fragment);
        pfrag.setMetadata(playlistname, songinfo);
    }
     /*Commands for playback

/*
    public void onTrackButtonClicked(View view) {
        Intent i = new Intent(getActivity(), MainActivity.class);
        startActivity(i);

        mPlayer.playUri(mOperationCallback, "spotify:track:5glXTXNIkUMLIDJVMEBLFQ", 0, 0);


    }

    public void onAlbumButtonClicked(View view) {



    }

    //public void onPlaylistButtonClicked(View view) {

    //   mPlayer.playUri(mOperationCallback, "spotify:user:spotify:playlist:37i9dQZF1E9G8oeYG9uL66", 0, 0);

    //}

/*
    public void onPlayButtonClicked(View view) {
        if (mCurrentPlaybackState != null && mCurrentPlaybackState.isPlaying) {
            mPlayer.pause(mOperationCallback);
        } else {
            mPlayer.resume(mOperationCallback);
        }
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


    /*public void onSeekButtonClicked(View view) {
        final Integer seek = Integer.valueOf(mSeekEditText.getText().toString());
        mPlayer.seekToPosition(mOperationCallback, seek);
    }*/
    @Override
    public void onDestroy() {
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
*/
                break;

            default:

                break;
        }

    }


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
