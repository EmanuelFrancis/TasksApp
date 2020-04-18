package com.emanuel.mysqlselect;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class AddTaskActivity extends AppCompatActivity {


    Button submit_task;
    Button add_new_catagory;
    String urlAddress= "http://www.emanuelfrancis.com/read_info.php";

    EditText  Task, Task_Desc, Deadline, Est_Time, Actual_Time;
    String   priority, task, task_desc, status, deadline, est_time, actual_time, catagory,  Priority, Status;
    Catagory catagories = new Catagory();
    String activityName = "addTask";
    String downloadTaskName = "null";
    public List addcat = new ArrayList<>();


    public String selectedCat;
    ArrayAdapter<String> adapter3;
    ArrayAdapter<String> adapter4;
    ArrayAdapter<String> adapter5;
    public String selectedItemText;
    public ArrayList<Spacecraft> spacecrafts=new ArrayList<>();
    int spacecraftsLength;
    public SpinnersList spinners = new SpinnersList();
    Spacecraft lol = new Spacecraft();

    public Downloader d=null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("FileTag", "ADDTASKACTIVITY CALLED");

        setContentView(R.layout.activity_add_task);

      //  final ListView lv= (ListView) findViewById(R.id.lv);

        final Spinner dropdown_cat = findViewById(R.id.drp_select_cat);
        final Spinner dropdown_priority = findViewById(R.id.drp_select_prior);
        final Spinner dropdown_status = findViewById(R.id.drp_select_status);

        Task = findViewById(R.id.et_task_name);
        Task_Desc = findViewById(R.id.et_task_notes);
        Actual_Time = findViewById(R.id.et_act_time);
        Deadline = findViewById(R.id.et_deadline);
        Est_Time = findViewById(R.id.et_est_time);

   //     add_new_catagory = findViewById(R.id.btn_add_cat);

 //       submit_task = findViewById(R.id.btn_sub_task);

        Log.d("FileTag", "AddTaskActivityRun");


        AddTasksDownloader d = new AddTasksDownloader(AddTaskActivity.this,urlAddress);
        d.execute();




}}
