package com.emanuel.mysqlselect;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewTasksActivity extends AppCompatActivity {

    String urlAddress= "http://www.emanuelfrancis.com/read_info.php";
    //  private Spinner dropdown_cat;
    private ArrayAdapter<String> adapter;
    // public List<String> cats = new ArrayList<>();
    String activityName = "viewTask";
    SpinnersList spinners = new SpinnersList();
    //  ArrayList<Spacecraft> taskList;

    public String selectedItemText;

    Catagory catags = new Catagory();
    public ArrayList catList = new ArrayList<>();
    public List addcatItems = new ArrayList<String>();
    int spacecraftsLength;
    // Catagory catags = new Catagory();

    Spacecraft m = null;

    Spacecraft b = null;

    ArrayList<Spacecraft> chosenCatItemsList=new ArrayList<>();

    ArrayList<Spacecraft> spacecrafts=new ArrayList<>();
    public ArrayList<Spacecraft> Task = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //  Task.

        Log.d("FileTag", "Do we reach here? ");

        //       Catagory catagories = new Catagory();


        //     final Spinner dropdown_cat = findViewById(R.id.drp_view_cat);

        // spinners.holdSpinner(dropdown_cat);
//
        final ListView lv = (ListView) findViewById(R.id.lv);
        String downloadTaskName = "DL_all_tasks";
        //  Downloader d=new Downloader(ViewTasksActivity.this,urlAddress,lv, activityName);
        //   d.execute();

     //   addcatItems = catags.getCatagoryList();
     //   catList.addAll(addcatItems);


      //  spacecraftsLength = catList.size();

//


        final Spinner dropdown_cat = findViewById(R.id.drp_view_cat);
        /*
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };


        final ArrayList<String> list = new ArrayList<String>();

        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        adapter = new ArrayAdapter<String>(ViewTasksActivity.this, android.R.layout.simple_spinner_dropdown_item, list);
        dropdown_cat.setAdapter(adapter);
*/
            ViewTaskDownloader d=new ViewTaskDownloader(ViewTasksActivity.this,lv,urlAddress, dropdown_cat);
            d.execute();

        //    CustomAdapter adapter = new CustomAdapter(ViewTasksActivity.this, spacecrafts);
        //    lv.setAdapter(adapter);
/*
        final Spinner dropdown_cat = findViewById(R.id.drp_view_cat);
        DropdownAdapter DDadapter = new DropdownAdapter(ViewTasksActivity.this,lv,spacecrafts,catList);

        dropdown_cat.setAdapter(DDadapter);
        dropdown_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemText = (String) adapterView.getItemAtPosition(i);

                //   Toast.makeText(c, selectedItemText, Toast.LENGTH_SHORT).show();
                chosenCatItemsList.clear();
                ;
                if (selectedItemText != "All") {

                    for (i = 0; i < spacecraftsLength; i++) {
                        m = spacecrafts.get(i);
                        if (m.getCatagory() == selectedItemText) {
                            chosenCatItemsList.add(spacecrafts.get(i));

                        }
                    }

                    int catGrpSize = chosenCatItemsList.size();
                    String abc = String.valueOf(catGrpSize);

                    //     Toast.makeText(c, abc, Toast.LENGTH_SHORT).show();
                    CustomAdapter adapter = new CustomAdapter(ViewTasksActivity.this, chosenCatItemsList);
                    lv.setAdapter(adapter);


                } else {
                    CustomAdapter adapter = new CustomAdapter(ViewTasksActivity.this, spacecrafts);
                    lv.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                CustomAdapter adapter3 = new CustomAdapter(ViewTasksActivity.this, spacecrafts);
                lv.setAdapter(adapter3);
                //          Toast.makeText(ViewTasksActivity.this, selectedItemText, Toast.LENGTH_SHORT).show();
                Toast.makeText(ViewTasksActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
            }
        });



*/


    }



}
