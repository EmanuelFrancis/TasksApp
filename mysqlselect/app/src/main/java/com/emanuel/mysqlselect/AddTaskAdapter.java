package com.emanuel.mysqlselect;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class AddTaskAdapter extends BaseAdapter {


    Context c;
    ArrayList<Spacecraft> spacecrafts;
    LayoutInflater inflater;
    public static String cat;
    String selectedValue;
    //  public ArrayList<String> catagories;
    // public List<String> catagories = new ArrayList<String>();
    // public List<String> catStore = new CatagoriesList()

    EditText Task, Task_Desc, Deadline, Est_Time, Actual_Time;
    String   priority, task, task_desc, status, deadline, est_time, actual_time, catagory;
    Catagory catagories = new Catagory();
    List catagoriesList;
    String activityName = "addTask";
    Spinner Priority;

    public AddTaskAdapter(Context c, ArrayList<Spacecraft> spacecrafts, String selectedValue) {
        this.c = c;
        this.spacecrafts = spacecrafts;
        this.selectedValue = selectedValue;

        //INITIALIE
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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
            if(convertView==null)
            {
                convertView=inflater.inflate(R.layout.model,parent,false);

            }



            TextView nameTxt= (TextView) convertView.findViewById(R.id.nameTxt);
            TextView priorityTxt= (TextView) convertView.findViewById(R.id.priorityTxt);
            TextView descTxt= (TextView) convertView.findViewById(R.id.descTxt);

            Task = convertView.findViewById(R.id.et_task_name);
            Task_Desc = convertView.findViewById(R.id.et_task_notes);
            Actual_Time = convertView.findViewById(R.id.et_act_time);
            Deadline = convertView.findViewById(R.id.et_deadline);
            Est_Time = convertView.findViewById(R.id.et_est_time);
            Priority = convertView.findViewById(R.id.drp_select_prior);

       //     submit_task = convertView.findViewById(R.id.btn_sub_task);



            nameTxt.setText(spacecrafts.get(position).getName());
            priorityTxt.setText(spacecrafts.get(position).getPriority());
            descTxt.setText(spacecrafts.get(position).getDescription());



            //  Catagory.add

            //CatagoriesList catString = new CatagoriesList(cat);




            //ITEM CLICKS
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(c,spacecrafts.get(position).getName(),Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;
        }
        //  public  void  GetCatagories(String cat){

        //      String addCat = cat;
        //      catagories.set(0, addCat);

        //  }



    }






