package com.emanuel.mysqlselect;

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.os.AsyncTask;
        import android.util.Log;
        import android.widget.ListView;
        import android.widget.Spinner;
        import android.widget.Toast;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.List;



public class ViewTasksParser  extends AsyncTask<Void,Void,Integer> {

    Context c;
    ListView lv;
    String jsonData;
    Spinner dropdown_cat;




    ProgressDialog pd;

    public ArrayList<Spacecraft> spacecrafts=new ArrayList<>();


    public List cat = new ArrayList<>();
    public Catagory catagories = new Catagory();
    String activityName;
    String downloadTaskName;
   //  ArrayList<String> catItems = new ArrayList<String>();

    private Boolean EndOfList = false;

    int x=0;


    public ViewTasksParser(Context c, ListView lv,  String jsonData, Boolean EndOfList, Spinner dropdown_cat ) {

        this.c = c;
        this.lv = lv;
        this.jsonData = jsonData;
        this.EndOfList = EndOfList;
        this.dropdown_cat = dropdown_cat;

       // registerForContextMenu(lv);

        Log.d("FileTag", "VIEWTASKSPARSER CALLED");
        if(EndOfList){

            DropdownAdapter DDadapter = new DropdownAdapter(c, lv, spacecrafts, dropdown_cat);
            lv.setAdapter(DDadapter);
        }
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
        return this.parseData();
    }

    @Override
    protected void onPostExecute (Integer result) {
        super.onPostExecute(result);



        pd.dismiss();
        if (result == 0) {
            Log.d("FileTag", "Unable to parse");
            Toast.makeText(c, "Unable to parse", Toast.LENGTH_SHORT).show();

        } else {

            x++;
            Log.d("FileTag", "viewable tasks" + x);

            ViewTaskAdapter adapter = new ViewTaskAdapter(c, spacecrafts);
            lv.setAdapter(adapter);


       //     if(EndOfList){

                DropdownAdapter DDadapter = new DropdownAdapter(c, lv, spacecrafts, dropdown_cat);
                lv.setAdapter(DDadapter);
       //     }


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

                spacecrafts.add(s);
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}