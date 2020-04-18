package com.emanuel.mysqlselect;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.Spinner;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class DropdownAdapter extends BaseAdapter {

    Context c;
   // private List<String> catagories = new ArrayList<String>();
    List<String> catagories;
    LayoutInflater inflater;
    public static String cat;
    ArrayList<Spacecraft> taskList;
    ArrayList<String> catList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    String selectedItemText;
    int spacecraftsLength;
    ListView lv;
    Catagory catags = new Catagory();
    Spinner dropdown_cat;

    Spacecraft m = null;
    ArrayList<Spacecraft> spacecrafts;
    ArrayList<Spacecraft> chosenCatItemsList=new ArrayList<>();

    public List<String> listItems = new ArrayList<String>();
    public ArrayList addcat = new ArrayList<>();

    ArrayList<String> catItems = new ArrayList<String>();

    Globals selectedCat;
    int spinnerPos = 0;
    int spinnerSize;


    public DropdownAdapter(Context c, ListView lv, ArrayList<Spacecraft> spacecrafts, Spinner dropdown_cat) {



        this.c = c;
     //   this.taskList = taskList;
    //    this.catList = catList;
        this.lv = lv;
     //   this.catItems = catItems;
        this.dropdown_cat = dropdown_cat;
        this.spacecrafts = spacecrafts;

        //INITIALIE
        inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    //    setContentView(R.layout.activity_tasks_view);

        Log.d("FileTag", "DROPDOWNADAPTER CALLED");

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
            convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);

        }
//
   //     String strIT = String.valueOf(catItems.size());
        catList.clear();
        Log.d("FileTag", "DROPDOWNADAPTER GETVIEW CALLED");

        selectedCat = new Globals(c);
        selectedItemText = (String) selectedCat.getSelectedDropdownItem();

        Log.d("FileTag", "selectedItemText is " + selectedItemText);
        Log.d("FileTag", "selectedItemText is " + selectedCat.getSelectedDropdownItem());


        for (int i = 0; i < getCount(); i++) {
            Boolean duplicates;
            String catName = spacecrafts.get(i).getCatagory();
         //   addcat.addAll(catItems);
            if (catItems.size() == 0) {
                Log.d("FileTag", "Test1");
                catItems.add("Show All");
            }else {

                duplicates = countDups(catName, catItems);
                if (!duplicates) {
                    Log.d("FileTag", "Test2");
                    catItems.add(catName);

                }
            }
        }


        //catItems = catags.getCatagoryList();
        catList.addAll(catItems);


//List test = new ArrayList();
//test.add("test");
//test.add("test");
//catList.addAll(test);

        adapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_dropdown_item, catList);
        dropdown_cat=(Spinner) ((ViewTasksActivity)c).findViewById(R.id.drp_view_cat);
        dropdown_cat.setAdapter(adapter);
       spinnerPos = setSpinner(dropdown_cat, selectedItemText);
          Log.d("FileTag", "Spinner pos is " + spinnerPos);
      //  spinnerSize = selectedCat.countSpinnerItems(dropdown_cat);
      //  Log.d("FileTag", "Spinner size is " + spinnerSize);
        dropdown_cat.setSelection(spinnerPos,true);
        dropdown_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemText = (String) adapterView.getItemAtPosition(i);
                chosenCatItemsList.clear();
                if (!selectedItemText.equals("Show All") ) {
                for(int x=0;x<getCount();x++){
                    m = new Spacecraft();
                    m = spacecrafts.get(x);
                    String b = m.getCatagory();
                    if(selectedItemText.equals(b)){
                        chosenCatItemsList.add(m);
                    }
                }
                    Toast.makeText(c, "chosenCat", Toast.LENGTH_SHORT).show();
                    ViewTaskAdapter adapter = new ViewTaskAdapter(c, chosenCatItemsList);
                    lv.setAdapter(adapter);

                    int chosenSize = chosenCatItemsList.size();
                    String chosen = String.valueOf(chosenSize);
                    Toast.makeText(c, chosen, Toast.LENGTH_SHORT).show();
                }else {
                        ViewTaskAdapter adapter = new ViewTaskAdapter(c, spacecrafts);
                        lv.setAdapter(adapter);
                    Toast.makeText(c, "show all", Toast.LENGTH_SHORT).show();
                }




                Toast.makeText(c, "abc", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                //      CustomAdapter adapter3 = new CustomAdapter(c, spacecrafts);
             //   lv.setAdapter(adapter);
                //          Toast.makeText(c, selectedItemText, Toast.LENGTH_SHORT).show();
           //     selectedItemText = "Show All";
         //       ViewTaskAdapter adapter = new ViewTaskAdapter(c, spacecrafts);
               // lv.setAdapter(adapter);
                Toast.makeText(c, "Nothing Selected", Toast.LENGTH_SHORT).show();
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
    //  public  void  GetCatagories(String cat){

    //      String addCat = cat;
    //      catagories.set(0, addCat);

    //  }

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

    private int setSpinner(Spinner spinner, String cat){

        int listPos=0;
        Spinner spin = spinner;
        String inputCat = cat;
        String compareCat;

        int spinnerSize = spin.getAdapter().getCount();

        for (int x=0; x<spinnerSize;x++){
            spin.setSelection(x,false);
           compareCat = spin.getSelectedItem().toString();

           if(inputCat.equals(compareCat)){
               listPos = x;
           }
        }


        return listPos;
    }



}