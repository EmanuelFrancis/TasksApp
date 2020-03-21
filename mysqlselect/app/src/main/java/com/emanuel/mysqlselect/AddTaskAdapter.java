package com.emanuel.mysqlselect;

import android.content.Intent;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.app.Activity;

import android.os.AsyncTask;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;


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

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class AddTaskAdapter extends BaseAdapter {


    Context c;
    ListView lv;
    ArrayList<Spacecraft> spacecrafts;
    LayoutInflater inflater;
    public static String cat;
    String selectedValue;
    //  public ArrayList<String> catagories;
    // public List<String> catagories = new ArrayList<String>();
    // public List<String> catStore = new CatagoriesList()
    ArrayAdapter<String> catDropdownAdapter;
    ArrayAdapter<String> priorityDropdownAdapter;
    ArrayAdapter<String> statusDropdownAdapter;
    EditText Task, Task_Desc, Deadline, Est_Time, Actual_Time;
    String   priority, task, task_desc, status, deadline, est_time, actual_time, catagory;
    Catagory catagories = new Catagory();
    List catagoriesList;
    String activityName = "addTask";
    Spinner Priority;
    Button submit_task;

    Spinner dropdown_cat;
    Spinner dropdown_priority;
    Spinner dropdown_status;
     String selectedPriorityText;
     String selectedCatText;
     String selectedStatusText;
    ArrayList<Spacecraft> chosenCatItemsList=new ArrayList<>();

    public ArrayList<String> catList = new ArrayList<>();
    public ArrayList<String> catItems = new ArrayList<String>();




    public AddTaskAdapter(Context c, ListView lv, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.lv = lv;
        this.spacecrafts = spacecrafts;
      //  this.selectedValue = selectedValue;

        //INITIALIE
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Log.d("FileTag", "AddTaskAdapterRun");

    }

        @Override
        public int getCount() {

            return spacecrafts.size();
        }

        @Override
        public Object getItem(int position) {

            return spacecrafts.get(position);
        }

        @Override
        public long getItemId(int position) {

            return spacecrafts.get(position).getId();
        }




        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView=inflater.inflate(R.layout.list_inputs,parent,false);
//
            }

            Log.d("FileTag", "AddTaskAdapterRun_getViewRun");

            catList.clear();


            for (int i = 0; i < getCount(); i++) {
                Boolean duplicates;
                String catName = spacecrafts.get(i).getCatagory();
                //   addcat.addAll(catItems);
         //       if (catItems.size() == 0) {

         //           this.catItems.add("Show All");
      //          }

                duplicates = countDups(catName, catItems);
                if (!duplicates) {
                    this.catItems.add(catName);

                }

            }

            catList.addAll(catItems);


            catDropdownAdapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_dropdown_item, catList);
            dropdown_cat= (Spinner) convertView.findViewById(R.id.drp_select_cat);
            dropdown_cat.setAdapter(catDropdownAdapter);
            dropdown_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedCatText = (String) adapterView.getItemAtPosition(i);

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    //      CustomAdapter adapter3 = new CustomAdapter(c, spacecrafts);
                    //   lv.setAdapter(adapter);
                    //          Toast.makeText(c, selectedItemText, Toast.LENGTH_SHORT).show();
              //      selectedItemText = "Show All";
              //      ViewTaskAdapter adapter = new ViewTaskAdapter(c, spacecrafts);
              //      lv.setAdapter(adapter);
                    Toast.makeText(c, "Nothing Selected", Toast.LENGTH_SHORT).show();
                }
            });


            String[] items2 = new String[]{"A", "B", "C"};
            priorityDropdownAdapter = new ArrayAdapter<>(c, android.R.layout.simple_spinner_dropdown_item, items2);
            dropdown_priority= (Spinner) convertView.findViewById(R.id.drp_select_prior);
            dropdown_priority.setAdapter(priorityDropdownAdapter);
            dropdown_priority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedPriorityText = (String) adapterView.getItemAtPosition(i);

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    Toast.makeText(c, "Nothing Selected", Toast.LENGTH_SHORT).show();
                }
            });

            String[] statusChoices = new String[]{"Not Started", "In Progress", "On Hold", "Complete"};
            statusDropdownAdapter = new ArrayAdapter<>(c, android.R.layout.simple_spinner_dropdown_item, statusChoices);
            dropdown_status= (Spinner) convertView.findViewById(R.id.drp_select_status);
            dropdown_status.setAdapter(statusDropdownAdapter);
            dropdown_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedStatusText = (String) adapterView.getItemAtPosition(i);

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    Toast.makeText(c, "Nothing Selected", Toast.LENGTH_SHORT).show();
                }
            });







            final TextView nameTxt= (TextView) convertView.findViewById(R.id.et_task_name);
            final TextView descTxt= (TextView) convertView.findViewById(R.id.et_task_notes);
            final TextView deadlineTxt= (TextView) convertView.findViewById(R.id.et_deadline);
            final TextView estTimeTxt= (TextView) convertView.findViewById(R.id.et_est_time);
            final TextView actTimeTxt= (TextView) convertView.findViewById(R.id.et_act_time);







            submit_task = (Button) convertView.findViewById(R.id.btn_sub_task);
            submit_task.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    task = nameTxt.toString();
                    task_desc = descTxt.getText().toString();//Task_Desc.getText().toString();
                    priority = selectedPriorityText;//dropdown_priority.getSelectedItem().toString();
                    status = selectedStatusText;
                    deadline = deadlineTxt.getText().toString();
                    est_time = estTimeTxt.getText().toString();
                    actual_time = actTimeTxt.getText().toString();
                    catagory = dropdown_cat.getSelectedItem().toString();
                    // catagory = Catagory.toString();
                    //   catagory = Catagory;//dropdown_cat.getSelectedItem().toString();
                    BackgroundTask backgroundTask = new BackgroundTask();
                    backgroundTask.execute(priority,task,task_desc,status,deadline,est_time,actual_time,catagory);
                  //  backgroundTask.finish();
                }
            });



            //ITEM CLICKS
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(c,spacecrafts.get(position).getName(),Toast.LENGTH_SHORT).show();
                }
            });


            return convertView;
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


priority = args[0];
task =args[1];
        task_desc=args[2];
        status=args[3];
        deadline=args[4];
        est_time=args[5];
        actual_time=args[6];
        catagory=args[7];





            //priority = dropdown_priority.getSelectedItem().toString();//args[1];

/*

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

*/


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

            Toast.makeText(c,result,Toast.LENGTH_LONG).show();

        }

    }

    private Boolean countDups(String catag,ArrayList<String> catList) {

//String cat;
        ArrayList<String> list;
        // String catag="";
        Boolean duplicate = false;
        this.cat = catag;
        list = catList;
        //   this.catList=catList;
        String listCat = "null";

        //count the amount of catagories in the catagory list
        for (int i = 0; i < list.size(); i++) {
            listCat = list.get(i);


            if(cat.equals(listCat)){
                duplicate = true;
            }
        }
        // counter=0;
        return duplicate ;
    }

    }






