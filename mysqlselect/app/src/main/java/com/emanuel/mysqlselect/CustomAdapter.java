package com.emanuel.mysqlselect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Spacecraft> spacecrafts;
    LayoutInflater inflater;
    public static String cat;
  //  public ArrayList<String> catagories;
   // public List<String> catagories = new ArrayList<String>();
   // public List<String> catStore = new CatagoriesList()

    public String selectedCatagory;
    public String selectedPriority;
    public String selectedStatus;

    public CustomAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;

        //INITIALIE
        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);





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
        TextView statusTxt= (TextView) convertView.findViewById(R.id.statusTxt);
        TextView catTxt= (TextView) convertView.findViewById(R.id.catTxt);
        TextView deadlineTxt= (TextView) convertView.findViewById(R.id.deadlineTxt);
        TextView estTimeTxt= (TextView) convertView.findViewById(R.id.estTimeTxt);
        TextView actualTimeTxt= (TextView) convertView.findViewById(R.id.actualTimeTxt);



        nameTxt.setText(spacecrafts.get(position).getName());
        priorityTxt.setText(spacecrafts.get(position).getPriority());
        descTxt.setText(spacecrafts.get(position).getDescription());
        statusTxt.setText(spacecrafts.get(position).getStatus());
        catTxt.setText(spacecrafts.get(position).getCatagory());
        deadlineTxt.setText(spacecrafts.get(position).getDeadline());
        estTimeTxt.setText(spacecrafts.get(position).getEst_time());
        actualTimeTxt.setText(spacecrafts.get(position).getAct_time());



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

    static String getCat()
    {
        //String cat;
        return cat;
    }

    public Void setCatagory(String catag) {
        //catagories = new ArrayList<String>();
        selectedCatagory = catag;
        return null;
    }

    public Void setPriority(String prior) {
        //catagories = new ArrayList<String>();
        selectedPriority = prior;
        return null;
    }

    public Void setStatus(String status) {
        //catagories = new ArrayList<String>();
        selectedStatus = status;
        return null;
    }


}