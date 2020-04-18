package com.emanuel.mysqlselect;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.MenuInflater;

import java.util.ArrayList;
public class ViewTasksActivity extends AppCompatActivity {

    String urlAddress= "http://www.emanuelfrancis.com/read_info.php";
    String activityName = "viewTask";

    ArrayList<Spacecraft> spacecrafts=new ArrayList<>();


    String[] menuOptions={"Edit","Delete"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_view);
    //    Toolbar toolbar = findViewById(R.id.toolbar);
    //    setSupportActionBar(toolbar);

        Log.d("FileTag", "VIEWTASKSACTIVITY CALLED");

        final ListView lv = (ListView) findViewById(R.id.lv);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(ViewTasksActivity.this,android.R.layout.simple_list_item_1,menuOptions);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);
        //final View view = findViewById(R.id.add_item);


        final Spinner dropdown_cat = findViewById(R.id.drp_view_cat);

            ViewTaskDownloader d=new ViewTaskDownloader(ViewTasksActivity.this,lv,urlAddress, dropdown_cat);
            d.execute();
        Log.d("FileTag", "WE ARRE BAAACKKK");


    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        Log.d("FileTag", "MENU CREATED");
        menu.setHeaderTitle("Context Menu");
       // menu.add(0, v.getId(), 0, "Edit");
    //    menu.add(0, v.getId(), 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getTitle() == "Edit") {
            // do your coding
            Log.d("FileTag", "EDIT SELECTED");
            Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
        }else if (item.getTitle() == "Delete"){
            Log.d("FileTag", "DELETE SELECTED");
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
        }
        else {
            Log.d("FileTag", "Touched");

            Toast.makeText(this, "Touched", Toast.LENGTH_SHORT).show();
            return  false;
        }
        return true;
    }

}
