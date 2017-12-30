package com.ratingrocker.appwithmenu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ToggleButton;
import android.app.Activity;


public class genrefrag extends Fragment implements View.OnClickListener{
    private CheckBox rap, alt, rock, funk, edm, soul, beach, oldie;
    private GridLayout txt_help_gest;
    private ToggleButton genreexp;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.genre_bar, container, false);
        rap = (CheckBox) view.findViewById(R.id.checkboxtextGenre1);
        alt = (CheckBox) view.findViewById(R.id.checkboxtextGenre2);
        rock = (CheckBox) view.findViewById(R.id.checkboxtextGenre3);
        funk = (CheckBox) view.findViewById(R.id.checkboxtextGenre4);
        edm = (CheckBox) view.findViewById(R.id.checkboxtextGenre5);
        soul = (CheckBox) view.findViewById(R.id.checkboxtextGenre6);
        beach = (CheckBox) view.findViewById(R.id.checkboxtextGenre7);
        oldie = (CheckBox) view.findViewById(R.id.checkboxtextGenre8);
        txt_help_gest = (GridLayout) view.findViewById(R.id.help_gest);
        genreexp = (ToggleButton) view.findViewById(R.id.plus);
// hide until its title is clicked
        genreexp.setOnClickListener(this) ;

        txt_help_gest.setVisibility(view.GONE);
        return view;
    }
    @Override
    public void onClick(View v) {
        if(txt_help_gest.isShown()){
            Fx.slide_up(getActivity(), txt_help_gest);
            txt_help_gest.setVisibility(View.GONE);
        }
        else if (!(txt_help_gest.isShown())){
            txt_help_gest.setVisibility(View.VISIBLE);
            Fx.slide_down(getActivity(), txt_help_gest);
        }
    }
    public int getGenreval(View view){
            int genreval = 1;
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
            if (soul.isChecked()){
                Log.e("GENRE", "soul");
                genreval=genreval*17;
            }
            if (beach.isChecked()){
                Log.e("GENRE", "beach");
                genreval=genreval*19;
            }
            if (oldie.isChecked()){
                Log.e("GENRE", "other");
                genreval=genreval*23;
            }

            Log.e("G val", String.valueOf(genreval));
            return genreval;


        }
    public void resetChecks(View view){
        rap.setChecked(false);
        alt.setChecked(false);
        funk.setChecked(false);
        edm.setChecked(false);
        rock.setChecked(false);
        beach.setChecked(false);
        soul.setChecked(false);
        rock.setChecked(false);
    }
    }


/*
    public void onMoreGenre(View view){

        if(txt_help_gest.isShown()){
            //Fx.slide_up(this, txt_help_gest);
            txt_help_gest.setVisibility(View.GONE);
        }
        else if (!(txt_help_gest.isShown())){
            txt_help_gest.setVisibility(View.VISIBLE);
            Fx.slide_down(getActivity(), txt_help_gest);
        }}
    }

*/