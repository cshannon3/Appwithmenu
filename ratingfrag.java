package com.ratingrocker.appwithmenu;

import android.app.Fragment;
//import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.EditText;
import android.widget.ExpandableListView;
//import android.widget.SeekBar;
//import android.widget.TextView;
import android.widget.Toast;
//import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Todo make seekbars grey when toggle off
public class ratingfrag extends Fragment {
    CustomAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    List<String> current;
    //private SeekBar mainseek;
    //private TextView mainVibeval;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.rating_fragment, container, false);

        expListView = (ExpandableListView) view.findViewById(R.id.lvExp2);

        //listDataHeader.get(0)
        // mainseek.getProgress()
        current = new ArrayList<String>();
        // preparing list data
        prepareListData();

        listAdapter = new CustomAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        //Log.e("Ready", listDataChild.get(listDataHeader.get(1)).get(3));
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {


                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

                /*
                .findView
                mainVibeval = (TextView) view.findViewById(R.id.vibevalText);
                mainseek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    int progressChangedValue = 0;

                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressChangedValue = progress;
                    }

                    public void onStartTrackingTouch(SeekBar seekBar) {
                        // TODO Auto-generated method stub
                    }

                    public void onStopTrackingTouch(SeekBar seekBar) {
                        Toast.makeText(getActivity(), "Seek bar progress is :" + progressChangedValue,
                                Toast.LENGTH_SHORT).show();
                    }
                });
                */
                Toast.makeText(getActivity(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();
            }
        });
        return view;
        }

/*
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getActivity(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
            }

                return
        });
    }

*/
        /*
         * Preparing the list data
         */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Chill");
        listDataHeader.add("Party");
        listDataHeader.add("Solo");
        listDataHeader.add("Fresh");
        //List<String> gtestlist = new ArrayList<>();
        //gtestlist.add("spotify:user:djbkaye:playlist:2AcH9skrLqwuTZqO4kmJDw");
        //gtestlist.add("spotify:user:spotify:playlist:37i9dQZEVXcPKy0U2e7eN5");
        //gtestlist.add("spotify:user:1221015148:playlist:5uRhVWWbmRz9LDgHlb95JT");
        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Whole Heart(BKAYE Remix)-Gryffin");



        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("10 years(Remix)- Travis Mendes");


        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Shortcuts and Dead Ends-MAYDAY");

        List<String> comingSooner = new ArrayList<String>();
        comingSooner.add("Shortcuts and Dead Ends-MAYDAY");



        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
        listDataChild.put(listDataHeader.get(3), comingSooner);
    }
}


/*
    private GridLayout txt_help_gest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] playlists = {"Chill Vibe", "Party Vibe", "Fresh Vibe", "Solo Vibe"};
        ListAdapter playListAdaptor = new CustomAdapter(this, playlists);
        ListView playlistView = findViewById(R.id.playlist_list);

        playlistView.setAdapter(playListAdaptor);
        txt_help_gest = (GridLayout) playlistView.findViewById(R.id.help_gest);
// hide until its title is clicked
        txt_help_gest.setVisibility(View.GONE);
    }   @Nullable
        public void toggle_contents(View view){

        if(txt_help_gest.isShown()){
            //Fx.slide_up(this, txt_help_gest);
            txt_help_gest.setVisibility(View.GONE);
        }
        else if (!(txt_help_gest.isShown())){
            txt_help_gest.setVisibility(View.VISIBLE);
            Fx.slide_down(this, txt_help_gest);
        }}
        /*
        playlistView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        String playtitle = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(MainActivity.this, playtitle, Toast.LENGTH_LONG).show();

                }

                }

        );




    public void playlistactivity(View view) {
        Intent intent = new Intent(this, com.ratingrocker.appwithmenu.Main2Activity.class);
        startActivity(intent);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

*/