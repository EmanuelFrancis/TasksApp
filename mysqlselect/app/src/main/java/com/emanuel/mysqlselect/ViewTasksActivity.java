package com.emanuel.mysqlselect;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
public class ViewTasksActivity extends AppCompatActivity {

    String urlAddress= "http://www.emanuelfrancis.com/read_info.php";
    String activityName = "viewTask";

    ArrayList<Spacecraft> spacecrafts=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_view);
    //    Toolbar toolbar = findViewById(R.id.toolbar);
    //    setSupportActionBar(toolbar);

        Log.d("FileTag", "VIEWTASKSACTIVITY CALLED");
        final ListView lv = (ListView) findViewById(R.id.lv);


        final Spinner dropdown_cat = findViewById(R.id.drp_view_cat);

            ViewTaskDownloader d=new ViewTaskDownloader(ViewTasksActivity.this,lv,urlAddress, dropdown_cat);
            d.execute();

    }

}
