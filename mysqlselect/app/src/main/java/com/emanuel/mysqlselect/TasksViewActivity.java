package com.emanuel.mysqlselect;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class TasksViewActivity extends AppCompatActivity {

    String urlAddress= "http://www.emanuelfrancis.com/read_info.php";
  //  private Spinner dropdown_cat;
    private ArrayAdapter<String> adapter;
   // public List<String> cats = new ArrayList<>();
   String activityName = "viewTask";
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Catagory catagories = new Catagory();


       final Spinner dropdownList = findViewById(R.id.drp_view_cat);
        final ListView lv= (ListView) findViewById(R.id.lv);
        String downloadTaskName = "DL_all_tasks";
        Downloader d=new Downloader(TasksViewActivity.this,urlAddress,lv, dropdownList, activityName,downloadTaskName);
        d.execute();



    }





}
