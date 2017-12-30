package com.ratingrocker.appwithmenu;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.GridLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CustomAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    List<String> current;
    ToggleButton myplaybut;

    int grouppos = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity3);


        //get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp2);

        current = new ArrayList<String>();
        // preparing list data
        prepareListData();

        listAdapter = new CustomAdapter(this, listDataHeader, listDataChild);

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
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }


    public void activity1(View view) {
        Intent intent = new Intent(this, com.ratingrocker.appwithmenu.MainActivity.class);
        startActivity(intent);
    }

/*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


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
        List<String> gtestlist = new ArrayList<>();
        gtestlist.add("spotify:user:djbkaye:playlist:2AcH9skrLqwuTZqO4kmJDw");
        gtestlist.add("spotify:user:spotify:playlist:37i9dQZEVXcPKy0U2e7eN5");
        gtestlist.add("spotify:user:1221015148:playlist:5uRhVWWbmRz9LDgHlb95JT");
        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Whole Heart(BKAYE Remix)-Gryffin");


        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("10 years(Remix)- Travis Mendes");


        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Shortcuts and Dead Ends-MAYDAY");

        List<String> comingSooner = new ArrayList<String>();
        comingSoon.add("Shortcuts and Dead Ends-MAYDAY");


        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
        listDataChild.put(listDataHeader.get(2), comingSooner);
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