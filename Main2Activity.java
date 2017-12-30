package com.ratingrocker.appwithmenu;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Main2Activity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    List<String> current;
    ToggleButton myplaybut;

    int grouppos = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    // get the listview
    expListView = (ExpandableListView) findViewById(R.id.lvExp);

    current = new ArrayList<String>();
    // preparing list data
    prepareListData();

    listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

    // setting list adapter
        expListView.setAdapter(listAdapter);
        //Log.e("Ready", listDataChild.get(listDataHeader.get(1)).get(3));
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

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
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expListView.setOnChildClickListener(new OnChildClickListener() {

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

    public void activity1(View view){
    Intent intent = new Intent(this,com.ratingrocker.appwithmenu.MainActivity.class);
    finish();
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
        listDataHeader.add("#BKAYEWEEKEND");
        listDataHeader.add("Discover Weekly");
        listDataHeader.add("Best Finds of October");
        List<String> gtestlist = new ArrayList<>();
        gtestlist.add("spotify:user:djbkaye:playlist:2AcH9skrLqwuTZqO4kmJDw"); gtestlist.add("spotify:user:spotify:playlist:37i9dQZEVXcPKy0U2e7eN5"); gtestlist.add("spotify:user:1221015148:playlist:5uRhVWWbmRz9LDgHlb95JT");
        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Whole Heart(BKAYE Remix)-Gryffin");
        top250.add("Last To Leave-Louis The Child");
        top250.add("Get It Right-Diplo");
        top250.add("Move That Body-Micky Valen");
        top250.add("Slow-Matoma");
        top250.add("First Love-Lost Kings");
        top250.add("Drowning(KUUR Remix)-KREAM");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("10 years(Remix)- Travis Mendes");
        nowShowing.add("Bad Song-Rosemary Joaquin");
        nowShowing.add("Roses-Predz");
        nowShowing.add("Bloom-Kaizen");
        nowShowing.add("Serve-The WDNG");
        nowShowing.add("Tension-gianni & kyle");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Shortcuts and Dead Ends-MAYDAY");
        comingSoon.add("Last One Standing-Mayday");
        comingSoon.add("Long Night-MAYDAY");
        comingSoon.add("Fragile-Tech N9ne");
        comingSoon.add("Worldwide Choppers-Tech N9ne");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}


