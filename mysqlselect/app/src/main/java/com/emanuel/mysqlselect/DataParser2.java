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

public class DataParser2  extends AsyncTask<Void,Void,Integer>{

    Context c;
    ListView lv;
    String jsonData;
    Spinner dropdown_status;
    Spinner dropdown_cat;
    Spinner dropdown_priority;

    public SpinnersList spinners = new SpinnersList();

    ProgressDialog pd;

    public ArrayList<Spacecraft> spacecrafts=new ArrayList<>();
    ArrayList<Spacecraft> chosenCatItemsList=new ArrayList<>();

    ArrayAdapter<String> adapter6;
    ArrayAdapter<CharSequence> adapter2;
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

    public DataParser2(Context c,  String jsonData) {
        this.c = c;

        this.jsonData = jsonData;


        //  this.downloadTaskName = downloadTaskName;

        //   dropdown_status = spinners.getSpinner(dropdown_status);


        //     this.dropdownList = dropdownList;
        //    dropdown_cat = spinners.getSpinner(dropdown_cat);
        //    dropdown_priority = spinners.getSpinner(dropdown_priority);
    }

        @Override
        protected void onPreExecute () {
            super.onPreExecute();

            pd = new ProgressDialog(c);
            pd.setTitle("Parse");
            pd.setMessage("Parsing...Please wait");
            pd.show();
        }

        @Override
        protected Integer doInBackground (Void...params){
            Log.d("FileTag", " ");
            Log.d("FileTag", "DataParser run");
            return this.parseData();
        }

        @Override
        protected void onPostExecute (Integer result){
            super.onPostExecute(result);


            pd.dismiss();
            // if (activityName == "updateCatagoryList") {
            //      cat.add(jsonData);
            // Integer result == 1;

            //      }
            if (result == 0) {

                Toast.makeText(c, "Unable to parse", Toast.LENGTH_SHORT).show();

            } else {


         return;

            }
            return;
        }



    //   }

    public ArrayList<Spacecraft> parserMethod(){
        // ArrayList<Spacecraft> spacecrafts = new ArrayList<>();
        //     ArrayList<Spacecraft> dataList = ArrayList<Spacecraft> spacecrafts;

        //    return dataList;
        return spacecrafts;
    }


    private int parseData()
    {
        try {
            //   Log.d("FileTag", "ParseData() run using " + jsonData);
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;


            spacecrafts.clear();
            Spacecraft s=null;

            //   if (s.getSizeofList()>0){
            //  s.clearCurrentList();}

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
                //     s.addIntToList(id);
                s.setName(name);
                //        s.addToList(name);
                s.setPriority(priority);
                //        s.addToList(priority);
                s.setStatus(status);
                //     s.addToList(status);
                s.setDescription(description);
                //        s.addToList(description);
                s.setCatagory(catagory);
                //        s.addToList(catagory);
                s.setDeadline(deadline);
                //         s.addToList(deadline);
                s.setEst_time(est_time);
                //         s.addToList(est_time);
                s.setAct_time(actual_time);
                //         s.addToList(actual_time);

                Log.d("FileTag", "ParseData() set catagory is " + catagory);
                //     if(downloadTaskName == "DL_catagories") {


                catagories.setCatagory(catagory);
                //     }
                spacecrafts.add(s);
                //  spacecrafts.getC
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