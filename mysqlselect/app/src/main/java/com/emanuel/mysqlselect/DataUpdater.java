package com.emanuel.mysqlselect;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
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

public class DataUpdater  extends AsyncTask<Void,Void,Integer>{

    Context c;
    ListView lv;
    String jsonData;
    Spinner dropdownList;
    Spinner dropdown_cat;
    Spinner dropdown_priority;

    ProgressDialog pd;

    public ArrayList<Spacecraft> spacecrafts=new ArrayList<>();
    ArrayList<Spacecraft> spacecraftsCat=new ArrayList<>();

    ArrayAdapter<String> adapter6;
    ArrayAdapter<String> adapter2;
    ArrayAdapter<String> adapter3;
    ArrayAdapter<String> adapter4;
    ArrayAdapter<String> adapter5;

    public List cat = new ArrayList<>();
    public List catItems = new ArrayList<>();
    public List currTask;
    String selected;
    public Catagory catagories = new Catagory();
    public Spacecraft currentTask = new Spacecraft();
    //  public CurrentTaskItem currentTask = new CurrentTaskItem();
    public String selectedCat;
    // public String selectedItemText = "null";
    public String selectedItemText;
    String catName = "test";
    String activityName;
    String downloadTaskName;
    Spacecraft m = null;
    int spacecraftsLength;

    public DataUpdater(Context c, ListView lv, String jsonData,  String activityName, String downloadTaskName) {
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
        Log.d("FileTag", "DataUpdater run set by" + activityName);
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);



        pd.dismiss();
        // if (activityName == "updateCatagoryList") {
        //      cat.add(jsonData);
        // Integer result == 1;

        //      }
      //  if(result==0)
     //   {

    //        Toast.makeText(c,"Unable to parse",Toast.LENGTH_SHORT).show();

     //   }else {



            cat.add("All");
            cat.addAll(catagories.getCatagoryList());
            //   currTask = ;

                    String catsSize = String.valueOf(catagories.getSize());


                  Log.d("SizeOfList3", catsSize);

            spacecraftsLength = spacecrafts.size();

/*

            if (activityName == "viewTask") {




                adapter2 = new ArrayAdapter<>(c, android.R.layout.simple_spinner_dropdown_item, cat);
                dropdownList.setAdapter(adapter2);

                dropdownList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        selectedItemText = (String) adapterView.getItemAtPosition(i);

                        //   Toast.makeText(c, selectedItemText, Toast.LENGTH_SHORT).show();
                        spacecraftsCat.clear();;
                        if(selectedItemText != "All") {

                            for (i = 0; i < spacecraftsLength; i++) {
                                m = spacecrafts.get(i);
                                if (m.getCatagory() == selectedItemText) {
                                    spacecraftsCat.add(spacecrafts.get(i));

                                }
                            }

                            int catGrpSize = spacecraftsCat.size();
                            String abc = String.valueOf(catGrpSize);

                            //     Toast.makeText(c, abc, Toast.LENGTH_SHORT).show();
                            CustomAdapter adapter = new CustomAdapter(c, spacecraftsCat);
                            lv.setAdapter(adapter);


                        }else{
                            CustomAdapter adapter = new CustomAdapter(c, spacecrafts);
                            lv.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        CustomAdapter adapter3 = new CustomAdapter(c, spacecrafts);
                        lv.setAdapter(adapter3);
                        //          Toast.makeText(c, selectedItemText, Toast.LENGTH_SHORT).show();
                        Toast.makeText(c, "Nothing Selected", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            */


    //        if (activityName == "addTask") {


                //Display Tasks List
                //CALL ADAPTER TO BIND DATA
    //            CustomAdapter adapter = new CustomAdapter(c, spacecrafts);
    //            lv.setAdapter(adapter);

                //SELECT AND ADD CATAGORY
            //    if(downloadTaskName == "DL_catagories") {
                    adapter4 = new ArrayAdapter<>(c, android.R.layout.simple_spinner_dropdown_item, cat);
                    dropdown_cat.setAdapter(adapter4);
                    dropdown_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedItemText = (String) adapterView.getItemAtPosition(i);
                            selectedCat = selectedItemText;
                            Toast.makeText(c, selectedItemText, Toast.LENGTH_SHORT).show();
                            // AddTaskActivity lol = new AddTaskActivity();
                      //      Spacecraft lol = new Spacecraft();
                      //      lol.setCatagory(selectedItemText);
                     //       spacecrafts.set(5,lol);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> arg0) {
                            Toast.makeText(c, "Nothing Selected", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


      //          }

          //  }
     //   }




 //   }




    private int parseData()
    {
 /*       try {
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



             //   catagories.setCatagory(catagory);
                spacecrafts.add(s);
                //   currentTask.setCurrentTask(cu);
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        return 0;
    }

 //   private Spacecraft checkCatagory(Spacecraft spacecrafts){

   //     return spacecrafts;
   // }

   // void catList(){

 //   }
}