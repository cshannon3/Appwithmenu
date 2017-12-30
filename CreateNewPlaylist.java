package com.ratingrocker.appwithmenu;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.ToggleButton;

public class CreateNewPlaylist extends Activity implements View.OnClickListener{
    private EditText vibemin, vibemax, freshmin, freshmax;
    private Spinner spinner;
    private Button createbutton, backbutton, playlistsbutton;
    private RelativeLayout extraratings;
    private ToggleButton expratings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_playlist);
        vibemin = (EditText) findViewById(R.id.vibemin);
        vibemax = findViewById(R.id.vibemax);
        freshmin = findViewById(R.id.freshmin);
        freshmax = findViewById(R.id.freshmax);
        Spinner dropdown = findViewById(R.id.spinner);
//create a list of items for the spinner.
        String[] items = new String[]{"Chill Vibe", "Party Vibe", "Solo Vibe"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        extraratings = findViewById(R.id.extraratings);
        expratings = (ToggleButton) findViewById(R.id.plusratingd);
// hide until its title is clicked
        expratings.setOnClickListener(this);

        extraratings.setVisibility(View.GONE);
    }


        public void onClick(View view) {
            if(extraratings.isShown()){
                Fx.slide_up(this, extraratings);
                extraratings.setVisibility(View.GONE);
            }
            else if (!(extraratings.isShown())){
                extraratings.setVisibility(View.VISIBLE);
                Fx.slide_down(this, extraratings);
            }
        }


    public void onBackButtonClicked(View view) {
       finish();
    }
    public void onPlaylistsButtonClicked(View view) {
        Intent i = new Intent(this, Main2Activity.class);
        startActivity(i);

    }
    public void onCreateButtonClicked(View view) {


    }
}
