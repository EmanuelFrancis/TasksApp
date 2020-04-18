
package com.emanuel.mysqlselect;

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.os.AsyncTask;
        import android.util.Log;
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
        import android.widget.AdapterView;


        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

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
        import androidx.appcompat.app.AlertDialog;
        import android.content.DialogInterface;
        import android.text.InputType;

public class AddTasksParser extends AsyncTask<Void,Void,Integer> {

    Context c;
  //  ListView lv;
    String jsonData;
   // Spinner dropdown_cat;


    ProgressDialog pd;

    public ArrayList<Spacecraft> spacecrafts=new ArrayList<>();


    public List cat = new ArrayList<>();
    public Catagory catagories = new Catagory();
    String activityName;
    String downloadTaskName;
    ArrayList<String> catItems = new ArrayList<String>();
   // public String selectedCatText;
    String selectedItemText;

    Button add_new_catagory;
    Button submit_task;
    String   priority, task, task_desc, status, deadline, est_time, actual_time, catagory;
    Spinner dropdown_cat;
    Spinner dropdown_priority;
    Spinner dropdown_status;
    String selectedPriorityText;
    String selectedCatText;
    String selectedStatusText;
    ArrayList<Spacecraft> chosenCatItemsList=new ArrayList<>();

    public ArrayList<String> catList = new ArrayList<>();
    ArrayAdapter<String> catDropdownAdapter;
    ArrayAdapter<String> priorityDropdownAdapter;
    ArrayAdapter<String> statusDropdownAdapter;

    Globals methods;

    ArrayAdapter<String> adapter2;

    private Boolean EndOfList = false;

    public AddTasksParser(Context c, String jsonData, Boolean EndOfList ) {
        this.c = c;
       // this.lv = lv;
        this.jsonData = jsonData;
        this.EndOfList = EndOfList;
        //this.dropdown_cat = dropdown_cat;
        Log.d("FileTag", "ADDTASKSPARSER CALLED");

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
            addCatToList();

        }

            catList.clear();


            for (int i = 0; i < getCount(); i++) {
                Boolean duplicates;
                String catName = spacecrafts.get(i).getCatagory();
                //   addcat.addAll(catItems);


                duplicates = countDups(catName, catItems);
                if (!duplicates) {
                    this.catItems.add(catName);

                }

            }



            drawCatDropdown();

/*
            catList.addAll(catItems);


            catDropdownAdapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_dropdown_item, catList);
            dropdown_cat= (Spinner) ((AddTaskActivity)c).findViewById(R.id.drp_select_cat);
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

*/

            drawPriorityDropdown();
drawStatusDropdown();

            addCatagory();


            final TextView nameTxt= (TextView) ((AddTaskActivity)c).findViewById(R.id.et_task_name);
            final TextView descTxt= (TextView) ((AddTaskActivity)c).findViewById(R.id.et_task_notes);
            final TextView deadlineTxt= (TextView) ((AddTaskActivity)c).findViewById(R.id.et_deadline);
            final TextView estTimeTxt= (TextView) ((AddTaskActivity)c).findViewById(R.id.et_est_time);
            final TextView actTimeTxt= (TextView) ((AddTaskActivity)c).findViewById(R.id.et_act_time);

            submit_task = (Button) ((AddTaskActivity)c).findViewById(R.id.btn_sub_task);
            submit_task.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    task = nameTxt.getText().toString();
                    task_desc = descTxt.getText().toString();//Task_Desc.getText().toString();
                    priority = selectedPriorityText;//dropdown_priority.getSelectedItem().toString();
                    status = selectedStatusText;
                    deadline = deadlineTxt.getText().toString();
                    est_time = estTimeTxt.getText().toString();
                    actual_time = actTimeTxt.getText().toString();
                    catagory = dropdown_cat.getSelectedItem().toString();
                    // catagory = Catagory.toString();
                    //   catagory = Catagory;//dropdown_cat.getSelectedItem().toString();
                    AddTasksParser.BackgroundTask backgroundTask = new AddTasksParser.BackgroundTask();
                    backgroundTask.execute(priority,task,task_desc,status,deadline,est_time,actual_time,catagory);
                    //  backgroundTask.finish();
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

           // Toast.makeText(c,result,Toast.LENGTH_LONG).show();
            Toast.makeText(c, "Task Added", Toast.LENGTH_SHORT).show();
            methods = new Globals(c);
            methods.setSelectedDropdownItem(catagory);
          //  String test = methods.getSelectedDropdownItem();
            Log.d("FileTag", "SETselectedDropdownItem is " + methods.getSelectedDropdownItem() );
            methods.openViewTasksActivity(catagory);

        }

    }

    private Boolean countDups(String catag,ArrayList<String> catList) {

//String cat;
        ArrayList<String> list;
        // String catag="";
        Boolean duplicate = false;
        String cat = catag;
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

    public String selectedCatagory(String selCat){
        String cat = selCat;

        return cat;
    }

    public int getCount() {

        return spacecrafts.size();
    }

    void drawCatDropdown(){
        catList.addAll(catItems);


        catDropdownAdapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_dropdown_item, catList);
        dropdown_cat= (Spinner) ((AddTaskActivity)c).findViewById(R.id.drp_select_cat);
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
    }

    void drawStatusDropdown(){


        String[] statusChoices = new String[]{"Not Started", "In Progress", "On Hold", "Complete"};
        statusDropdownAdapter = new ArrayAdapter<>(c, android.R.layout.simple_spinner_dropdown_item, statusChoices);
        dropdown_status= (Spinner) ((AddTaskActivity)c).findViewById(R.id.drp_select_status);
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
    }

    void drawPriorityDropdown(){
        String[] items2 = new String[]{"A", "B", "C"};
        priorityDropdownAdapter = new ArrayAdapter<>(c, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown_priority= (Spinner) ((AddTaskActivity)c).findViewById(R.id.drp_select_prior);
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
    }

    void addCatagory(){

        add_new_catagory = ((AddTaskActivity)c).findViewById(R.id.btn_add_cat);
        add_new_catagory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

addCatToList();
            }
        });
    }

    void addCatToList(){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Title");


// Set up the input
        final EditText input = new EditText(c);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String addedCat = input.getText().toString();
                catList.clear();
                catList.add(addedCat);
                drawCatDropdown();


                //  activityName = "updateCatagoryList";
                //    DataUpdater updater=new DataUpdater(AddTaskActivity.this,lv,m_Text, dropdown_cat, activityName, downloadTaskName);
                //     updater.execute();
            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}