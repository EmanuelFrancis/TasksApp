package com.emanuel.mysqlselect;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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
import java.util.List;

public class AddTaskActivity extends AppCompatActivity {



  // private Spinner dropdownList; //declare task category dropdown box ie, daily, head to toe, beauty plus etc
 //   private Spinner dropdown_priority; //declare priority dropdown ie, a b c
    private Spinner dropdown_status; //declare status dropdown
     private ArrayAdapter<String> adapter;
    Button submit_task;
    String urlAddress= "http://www.emanuelfrancis.com/read_info.php";

    EditText  Task, Task_Desc, Deadline, Est_Time, Actual_Time, Priority;
    String   priority, task, task_desc, status, deadline, est_time, actual_time, catagory;
    Catagory catagories = new Catagory();
    List catagoriesList;
    String activityName = "addTask";
    String downloadTaskName = "null";
  //  Spinner Priority;
    public String selectedVal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);




        catagoriesList = catagories.getCatagoryList();

     final Spinner dropdown_cat = findViewById(R.id.drp_select_cat);
     downloadTaskName = "DL_catagories";

      //  String[] items = new String[]{"Daily", "Web Artists", "Head To Toe"};
      //  adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, catagoriesList);
     //   dropdown_cat.setAdapter(adapter);

        final ListView lv= (ListView) findViewById(R.id.lv);
        Downloader d=new Downloader(AddTaskActivity.this,urlAddress,lv, dropdown_cat, activityName, downloadTaskName);
        d.execute();

        final Spinner  dropdown_priority = findViewById(R.id.drp_select_prior);
        downloadTaskName = "DL_priorities";
     //   String[] items2 = new String[]{"A", "B", "C"};
     //   adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
     //   dropdown_priority.setAdapter(adapter);

        Downloader e=new Downloader(AddTaskActivity.this,urlAddress,lv, dropdown_priority , activityName, downloadTaskName);
        e.execute();

        dropdown_status = findViewById(R.id.drp_select_status);
        String[] items3 = new String[]{"Not Started", "In Progress", "Complete"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items3);
        dropdown_status.setAdapter(adapter);



        Task = findViewById(R.id.et_task_name);
        Task_Desc = findViewById(R.id.et_task_notes);
        Actual_Time = findViewById(R.id.et_act_time);
        Deadline = findViewById(R.id.et_deadline);
        Est_Time = findViewById(R.id.et_est_time);
       // Priority = selectedVal;

        submit_task = findViewById(R.id.btn_sub_task);
        submit_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = Task.getText().toString();
                task_desc = Task_Desc.getText().toString();//Task_Desc.getText().toString();
                priority = selectedVal;//dropdown_priority.getSelectedItem().toString();
                status = "null";
                deadline = "null";
                est_time = "null";
                actual_time = "null";
                catagory = "null";//dropdown_cat.getSelectedItem().toString();
                BackgroundTask backgroundTask = new BackgroundTask();
                backgroundTask.execute(priority,task,task_desc,status,deadline,est_time,actual_time,catagory);
                finish();
            }
        });


    }



    class BackgroundTask extends AsyncTask<String,Void,String>
    {
        String add_task_url;


        @Override
        protected void onPreExecute(){


        }



        @Override
        protected String doInBackground(String... args){
            String priority, task, task_desc, status, deadline, est_time, actual_time, catagory;

            //priority = dropdown_priority.getSelectedItem().toString();//args[1];
            priority = selectedVal.toString();//args[1];
            task = Task.getText().toString();//args[2];
            task_desc =Task_Desc.getText().toString();//args[3];
            status ="null";// args[4];
            deadline ="null";//args[5];
            est_time = "null";//args[6];
            actual_time = "null";//args[7];
          //  catagory = dropdown_cat.getSelectedItem().toString();//args[8];

            try{
                URL url = new URL(add_task_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data_string = URLEncoder.encode("priority", "UTF-8")+"="+URLEncoder.encode(priority,"UTF-8")+"&"+
                        URLEncoder.encode("task", "UTF-8")+"="+URLEncoder.encode(task,"UTF-8")+"&"+
                        URLEncoder.encode("task_desc", "UTF-8")+"="+URLEncoder.encode(task_desc,"UTF-8")+"&"+
                        URLEncoder.encode("status", "UTF-8")+"="+URLEncoder.encode(status,"UTF-8")+"&"+
                        URLEncoder.encode("deadline", "UTF-8")+"="+URLEncoder.encode(deadline,"UTF-8")+"&"+
                        URLEncoder.encode("est_time", "UTF-8")+"="+URLEncoder.encode(est_time,"UTF-8")+"&"+
                        URLEncoder.encode("actual_time", "UTF-8")+"="+URLEncoder.encode(actual_time,"UTF-8");
        //                URLEncoder.encode("catagory", "UTF-8")+"="+URLEncoder.encode(catagory,"UTF-8");
                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();
                return  "One Row of data inserted";




            }catch (MalformedURLException e)
            {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result){
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
        }
    }

    public Void setCatagory(String catag) {
        //catagories = new ArrayList<String>();
        selectedVal = catag;
        return null;
    }



}
