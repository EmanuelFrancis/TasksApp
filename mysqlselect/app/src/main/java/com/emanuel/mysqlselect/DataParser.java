package com.emanuel.mysqlselect;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataParser  extends AsyncTask<Void,Void,Integer>{

    Context c;
    ListView lv;
    String jsonData;
    Spinner dropdownList;
    Spinner dropdown_cat;
    Spinner dropdown_priority;

    ProgressDialog pd;

    public ArrayList<Spacecraft> spacecrafts=new ArrayList<>();
    ArrayList<Spacecraft> spacecraftsCat=new ArrayList<>();

    ArrayAdapter<String> catagoryAdapter;
    ArrayAdapter<String> adapter2;
    ArrayAdapter<String> adapter3;
    ArrayAdapter<String> adapter4;
    ArrayAdapter<String> adapter5;

    public List cat;
    public List currTask;
    String selected;
    public Catagory catagories = new Catagory();
    public Spacecraft currentTask = new Spacecraft();
  //  public CurrentTaskItem currentTask = new CurrentTaskItem();
    public String selectedCat;
    public String selectedItemText = "null";
    String catName = "test";
    String activityName;
    String downloadTaskName;

    public DataParser(Context c, ListView lv, String jsonData, Spinner dropdownList, String activityName, String downloadTaskName) {
        this.c = c;
        this.lv = lv;
        this.jsonData = jsonData;
        this.dropdownList = dropdownList;
        this.dropdown_cat = dropdownList;
        this.dropdown_priority = dropdownList;
        this.activityName = activityName;
        this.downloadTaskName = downloadTaskName;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing...Please wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);



        pd.dismiss();
        if(result==0)
        {
            Toast.makeText(c,"Unable to parse",Toast.LENGTH_SHORT).show();
        }else {

            cat = catagories.getCatagoryList();
         //   currTask = ;

            int spacecraftsLength = spacecrafts.size();
            Spacecraft m = null;


            if (activityName == "viewTask") {

                //Create catogory List
                //CALL ADAPTER TO BIND DATA


          //      Toast.makeText(c, "YOOOOOO", Toast.LENGTH_SHORT).show();
                //Display Tasks List
                //CALL ADAPTER TO BIND DATA
                CustomAdapter adapter = new CustomAdapter(c, spacecrafts);
                lv.setAdapter(adapter);

                for (int i = 0; i < spacecraftsLength; i++) {
                    m = spacecrafts.get(i);
                   //Toast.makeText(c, m, Toast.LENGTH_SHORT).show();
                    if (m.getCatagory() != null) {
                        catName = m.getCatagory();
                      //  AddTaskActivity.selectedCatagory
                        if (catName.contains(selectedItemText)) {
                            spacecraftsCat.add(spacecrafts.get(i));
                            spacecrafts = spacecraftsCat;
                        }
                    }
                }


                //Dropdown Selection
                //CALL ADAPTER TO BIND DATA


                adapter2 = new ArrayAdapter<>(c, android.R.layout.simple_spinner_dropdown_item, cat);
                dropdownList.setAdapter(adapter2);
                dropdownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        selectedItemText = (String) adapterView.getItemAtPosition(i);
                        selectedCat = selectedItemText;
                        //   spacecrafts
        //                Toast.makeText(c, selectedCat, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        Toast.makeText(c, "Nothing Selected", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            if (activityName == "addTask") {




                Toast.makeText(c, "YOOOOOO", Toast.LENGTH_SHORT).show();
                //Display Tasks List
                //CALL ADAPTER TO BIND DATA
        //         AddTaskAdapter adapter = new AddTaskAdapter(c, spacecrafts, selectedItemText);
       //         lv.setAdapter(adapter);

                CustomAdapter adapter = new CustomAdapter(c, spacecrafts);
                lv.setAdapter(adapter);

       //         for (int i = 0; i < spacecraftsLength; i++) {
       //             m = spacecrafts.get(i);
      //              if (m.getCatagory() != null) {
       //                 catName = m.getCatagory();
     //                   if (catName.contains(selectedItemText)) {
     //                       spacecraftsCat.add(spacecrafts.get(i));
     //                       spacecrafts = spacecraftsCat;
     //                   }
             //       }
     //           }
                if(downloadTaskName == "DL_catagories") {
                    adapter4 = new ArrayAdapter<>(c, android.R.layout.simple_spinner_dropdown_item, cat);
                    dropdown_cat.setAdapter(adapter4);
                    dropdown_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedItemText = (String) adapterView.getItemAtPosition(i);
                            selectedCat = selectedItemText;
                            AddTaskActivity lol = new AddTaskActivity();
                            lol.setCatagory(selectedItemText);
                            //   spacecrafts
                      //      Toast.makeText(c, selectedCat, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            Toast.makeText(c, "Nothing Selected", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                //SELECT AND ADD PRIORITY
                if(downloadTaskName == "DL_priorities") {
                    String[] items2 = new String[]{"A", "B", "C"};
                    adapter5 = new ArrayAdapter<>(c, android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown_priority.setAdapter(adapter5);
                    dropdown_priority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedItemText = (String) adapterView.getItemAtPosition(i);
                           // selectedCat = selectedItemText;
                            AddTaskActivity lol = new AddTaskActivity();
                            lol.setPriority(selectedItemText);

                            //   spacecrafts
                    //        Toast.makeText(c, selectedItemText, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            Toast.makeText(c, "Nothing Selected", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                //SELECT AND ADD STATUS
                if(downloadTaskName == "DL_status") {
                    String[] items3 = new String[]{"Not Started", "In Progress", "Complete"};
                    adapter3 = new ArrayAdapter<>(c, android.R.layout.simple_spinner_dropdown_item, items3);
                    dropdownList.setAdapter(adapter3);
                    dropdownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedItemText = (String) adapterView.getItemAtPosition(i);
                           // selectedCat = selectedItemText;
                            AddTaskActivity lol = new AddTaskActivity();
                            lol.setStatus(selectedItemText);

                            //   spacecrafts
            //                Toast.makeText(c, selectedItemText, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            Toast.makeText(c, "Nothing Selected", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        }




    }




    private int parseData()
    {
        try {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;

            spacecrafts.clear();
            Spacecraft s=null;

            for(int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);


                int id=jo.getInt("id");
                String name=jo.getString("task");
                String priority=jo.getString("priority");
                String status=jo.getString("status");
                String description=jo.getString("task_desc");
                String catagory=jo.getString("catagory");
                String deadline=jo.getString("deadline");
                String est_time=jo.getString("est_time");
                String actual_time=jo.getString("actual_time");



                    s = new Spacecraft();
                    s.setId(id);
                    s.setName(name);
                    s.setPriority(priority);
                    s.setStatus(status);
                    s.setDescription(description);
                    s.setCatagory(catagory);
                    s.setDeadline(deadline);
                    s.setEst_time(est_time);
                    s.setAct_time(actual_time);



                catagories.setCatagory(catagory);
                spacecrafts.add(s);
             //   currentTask.setCurrentTask(cu);
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private Spacecraft checkCatagory(Spacecraft spacecrafts){

        return spacecrafts;
    }

    void catList(){

    }
}