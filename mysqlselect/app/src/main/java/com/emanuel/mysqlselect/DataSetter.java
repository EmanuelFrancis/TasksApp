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

public class DataSetter extends AsyncTask<Void,Void,Integer> {

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
    public List catItems = new ArrayList<String>();
    public List addcat = new ArrayList<>();
    public List addcatItems = new ArrayList<String>();
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

    public DataSetter(Context c, ListView lv, String jsonData, Spinner dropdownList, String activityName, String downloadTaskName) {
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
        Log.d("FileTag", " ");
        Log.d("FileTag", "DataSetter run set by " + downloadTaskName);
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
        if(result==0)
        {

            Toast.makeText(c,"Unable to parse",Toast.LENGTH_SHORT).show();

        }else {



            //      cat.add("All");
            //     catItems = catagories.getCatagoryList();
            //      Log.d("FileTag", catagories.getdownloadTaskName(downloadTaskName));
            //      cat.addAll(catItems);
            //   currTask = ;

            //        String catItem = (String) cat.get(3);

            //      String catsSize = String.valueOf(catagories.getSize());
            //   Log.d("SizeOfList2", catsSize);
            //   Log.d("Listitem", catItem);

            spacecraftsLength = spacecrafts.size();




            if (activityName == "addTask") {


                //Display Tasks List
                //CALL ADAPTER TO BIND DATA
                //     CustomAdapter adapter = new CustomAdapter(c, spacecrafts);
                //     lv.setAdapter(adapter);

            //    addcat.add("All");
        //        addcatItems = catagories.getCatagoryList();
        //        Log.d("FileTag", catagories.getdownloadTaskName(downloadTaskName));
        //        addcat.addAll(addcatItems);

                //          Log.d("downloadTaskName", downloadTaskName);

                //SELECT AND ADD CATAGORY


                //SELECT AND ADD PRIORITY
                if(downloadTaskName == "DL_priorities") {
                    String[] items2 = new String[]{"A", "B", "C"};
                    adapter5 = new ArrayAdapter<>(c, android.R.layout.simple_spinner_dropdown_item, items2);
                    dropdown_priority.setAdapter(adapter5);
                    dropdown_priority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            selectedItemText = (String) adapterView.getItemAtPosition(i);
                            //   AddTaskActivity lol = new AddTaskActivity();
                            Spacecraft lol = new Spacecraft();
                            lol.setPriority(selectedItemText);
                            spacecrafts.set(2,lol);
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
                            //   AddTaskActivity lol = new AddTaskActivity();
                            Spacecraft lol = new Spacecraft();
                            lol.setStatus(selectedItemText);
                            spacecrafts.set(3,lol);

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


            return 1;


    }


}
