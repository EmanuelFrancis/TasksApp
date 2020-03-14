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



  // private Spinner dropdownList; //declare task category dropdown box ie, daily, head to toe, beauty plus etc
 //   private Spinner dropdown_priority; //declare priority dropdown ie, a b c
   // private Spinner Catagory, dropdown_status; //declare status dropdown
 //    private ArrayAdapter<String> adapter;
    Button submit_task;
    Button add_new_catagory;
    String urlAddress= "http://www.emanuelfrancis.com/read_info.php";

    EditText  Task, Task_Desc, Deadline, Est_Time, Actual_Time;
    String   priority, task, task_desc, status, deadline, est_time, actual_time, catagory,  Priority, Status;
    Catagory catagories = new Catagory();
    List catagoriesList;
    String activityName = "addTask";
    String downloadTaskName = "null";
    public List addcat = new ArrayList<>();
    public List addcatItems = new ArrayList<String>();
  //  Spinner Priority;
  //  public String selectedCatagory = "null";
 //   public String selectedPriority= "null";
 //   public String selectedStatus= "null";
//    public ArrayList<Spacecraft> spacecrafts=new ArrayList<>();

  public   Spinner dropdown_cat = null;
   public Spinner dropdown_priority = null;
  public Spinner dropdown_status = null;
    public String selectedCat;
    ArrayAdapter<String> adapter3;
    ArrayAdapter<String> adapter4;
    ArrayAdapter<String> adapter5;
    public String selectedItemText;
    public ArrayList<Spacecraft> spacecrafts=new ArrayList<>();
    int spacecraftsLength;
    public SpinnersList spinners = new SpinnersList();
    Spacecraft lol = new Spacecraft();

//
    public Downloader d=null;

 //   Spacecraft m = null;

   String m_Text = "";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);






        final ListView lv= (ListView) findViewById(R.id.lv);


        d=new Downloader(AddTaskActivity.this,urlAddress,lv, activityName);
        d.execute();

        dropdown_cat = findViewById(R.id.drp_select_cat);
        spinners.holdSpinner(dropdown_cat);
        downloadTaskName = "DL_catagories";
      //  selectedCatagory = cat.getCatagory();

        addcat.add("All");
        addcatItems = catagories.getCatagoryList();
        //    Log.d("FileTag", catagories.getdownloadTaskName(downloadTaskName));
        addcat.addAll(addcatItems);

        //          Log.d("downloadTaskName", downloadTaskName);

        //SELECT AND ADD CATAGORY
        //    if(downloadTaskName == "DL_catagories") {


        adapter4 = new ArrayAdapter<>(AddTaskActivity.this, android.R.layout.simple_spinner_dropdown_item, addcat);
        dropdown_cat.setAdapter(adapter4);
        dropdown_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemText = (String) adapterView.getItemAtPosition(i);
                selectedCat = selectedItemText;
                Toast.makeText(AddTaskActivity.this, selectedItemText, Toast.LENGTH_SHORT).show();
                // AddTaskActivity lol = new AddTaskActivity();
                Spacecraft lol = new Spacecraft();
                lol.setCatagory(selectedItemText);
                //  spacecrafts.set(5,lol);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                Toast.makeText(AddTaskActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
            }
        });


      //  catagoriesList = catagories.getCatagoryList();

        String catsSize = String.valueOf(catagories.getSize());

        spacecraftsLength = spacecrafts.size();

        dropdown_priority = findViewById(R.id.drp_select_prior);
        spinners.holdSpinner(dropdown_priority);
        downloadTaskName = "DL_priorities";

        String[] items2 = new String[]{"A", "B", "C"};
        adapter5 = new ArrayAdapter<>(AddTaskActivity.this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown_priority.setAdapter(adapter5);
        dropdown_priority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemText = (String) adapterView.getItemAtPosition(i);
                //   AddTaskActivity lol = new AddTaskActivity();
              //  Spacecraft lol = new Spacecraft();
                lol.setPriority(selectedItemText);
            //    spacecrafts.set(2,lol);
              //  spacecrafts.get(position).
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                Toast.makeText(AddTaskActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
            }
        });


   //     selectedPriority = cat.getPriority();

  //      Downloader e=new Downloader(AddTaskActivity.this,urlAddress,lv, dropdown_priority , activityName, downloadTaskName);
   //     e.execute();

        dropdown_status = findViewById(R.id.drp_select_status);
        spinners.holdSpinner(dropdown_status);
        downloadTaskName = "DL_status";


        String[] items3 = new String[]{"Not Started", "In Progress", "Complete"};
        adapter3 = new ArrayAdapter<>(AddTaskActivity.this, android.R.layout.simple_spinner_dropdown_item, items3);
        dropdown_status.setAdapter(adapter3);
        dropdown_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemText = (String) adapterView.getItemAtPosition(i);
                // selectedCat = selectedItemText;
                //   AddTaskActivity lol = new AddTaskActivity();

                lol.setStatus(selectedItemText);
            //  lol.add(3,lol);


                //   spacecrafts
                //                Toast.makeText(c, selectedItemText, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                Toast.makeText(AddTaskActivity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
            }
        });
  //      selectedStatus = cat.getStatus();

      //  Downloader f=new Downloader(AddTaskActivity.this,urlAddress,lv, dropdown_status , activityName, downloadTaskName);
     //   f.execute();


        //taskDetails = DataParser.

       // Toast.makeText(getApplicationContext(),selectedCatagory,Toast.LENGTH_LONG).show();


        Task = findViewById(R.id.et_task_name);
        Task_Desc = findViewById(R.id.et_task_notes);
        Actual_Time = findViewById(R.id.et_act_time);
        Deadline = findViewById(R.id.et_deadline);
        Est_Time = findViewById(R.id.et_est_time);
   //    if (selectedCatagory != null) {
           // Catagory = findViewById(R.id.drp_select_cat);
           // Catagory = selectedCatagory;
  //      }else {
           // Catagory = "Catagory null";
   //     }
  //      if (selectedPriority != null) {
         //   Priority = selectedPriority;

 //       }else {
         //   Priority = "Priority null";
  //      }
  //      if (selectedStatus != null) {
         //   Status = selectedStatus;
  //      }else {

        //    Status = "Status null";
  //      }


        add_new_catagory = findViewById(R.id.btn_add_cat);
        add_new_catagory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                AlertDialog.Builder builder = new AlertDialog.Builder(AddTaskActivity.this);
                builder.setTitle("Title");

// Set up the input
                final EditText input = new EditText(AddTaskActivity.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                      //  catagories.setCatagory(m_Text);
                    //    List cat = new ArrayList<>();
                    //    cat.add(m_Text);
                    //    cat.addAll(catagories.getCatagoryList());
                    //    dropdown_cat.addA
                        downloadTaskName = "DL_catagories";
                        activityName = "updateCatagoryList";
lol.setCatagory(m_Text);
                     //   Spinner dropdownCat = spinners.getSpinner(dropdown_cat);

              //          DataUpdater updater=new DataUpdater(AddTaskActivity.this,lv,m_Text, dropdownCat, activityName,downloadTaskName);
                //        updater.execute();
                       // dropdown_cat.add(m_Text);
                     //   downloadTaskName = "DL_catagories";
                  //      Downloader g=new Downloader(AddTaskActivity.this,urlAddress,lv, dropdown_cat, activityName, downloadTaskName);
                  //      g.execute();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

           //     BackgroundTask backgroundTask = new BackgroundTask();
          //      backgroundTask.execute(priority,task,task_desc,status,deadline,est_time,actual_time,catagory);
         //       finish();
            }
        });


        submit_task = findViewById(R.id.btn_sub_task);
        submit_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = Task.getText().toString();
                task_desc = Task_Desc.getText().toString();//Task_Desc.getText().toString();
                priority = dropdown_priority.getSelectedItem().toString();//dropdown_priority.getSelectedItem().toString();
                status = dropdown_status.getSelectedItem().toString();
                deadline = Deadline.getText().toString();
                est_time = Est_Time.getText().toString();
                actual_time = Actual_Time.getText().toString();
                catagory = dropdown_cat.getSelectedItem().toString();
               // catagory = Catagory.toString();
             //   catagory = Catagory;//dropdown_cat.getSelectedItem().toString();
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

            add_task_url = "http://www.emanuelfrancis.com/add_info.php";
        }



        @Override
        protected String doInBackground(String... args){
            String priority, task, task_desc, status, deadline, est_time, actual_time, catagory;



            //priority = dropdown_priority.getSelectedItem().toString();//args[1];

            task = Task.getText().toString();//args[2];
            task_desc =Task_Desc.getText().toString();//args[3];

            deadline =Deadline.getText().toString();
            est_time = Est_Time.getText().toString();
            actual_time = Actual_Time.getText().toString();

            catagory = dropdown_cat.getSelectedItem().toString();
            priority = dropdown_priority.getSelectedItem().toString();//dropdown_priority.getSelectedItem().toString();
            status = dropdown_status.getSelectedItem().toString();
            //catagory = Catagory;//dropdown_cat.getSelectedItem().toString();
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
                        URLEncoder.encode("actual_time", "UTF-8")+"="+URLEncoder.encode(actual_time,"UTF-8")+"&"+
                        URLEncoder.encode("catagory", "UTF-8")+"="+URLEncoder.encode(catagory,"UTF-8");
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


            return "Issue";
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
     //   selectedCatagory = catag;
        return null;
    }

    public Void setPriority(String prior) {
        //catagories = new ArrayList<String>();
  //      selectedPriority = prior;
        return null;
    }

    public Void setStatus(String status) {
        //catagories = new ArrayList<String>();
     //   selectedStatus = status;
        return null;
    }



}
