package com.emanuel.mysqlselect;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ContextMenu;
import android.view.MenuItem;

import java.util.ArrayList;

public class ViewTaskAdapter  extends BaseAdapter  {

    MenuInflater inflater2;
    Context c;
    ArrayList<Spacecraft> spacecrafts;
    LayoutInflater inflater;
    public static String cat;


    public ViewTaskAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;

        //INITIALIE
        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      //  inflater2 = MainActivity.getMenuInflater();


        Log.d("FileTag", "VIEWTASKADAPTER CALLED");


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


        Log.d("FileTag", "VIEWTASKADAPTER VIEW CALLED");

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






        //ITEM CLICKS
      //  convertView.setOnClickListener(new View.OnClickListener() {
       //     @Override
      //      public void onClick(View v) {
          //      Toast.makeText(c,spacecrafts.get(position).getName(),Toast.LENGTH_SHORT).show();
    //        }
   //     });

        return convertView;
    }

    static String getCat()
    {

        return cat;
    }



}
