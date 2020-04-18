package com.emanuel.mysqlselect;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String urlAddress= "http://www.emanuelfrancis.com/read_info.php";
    private Button btn_viewTask;
    private Button btn_addTask;
    TextView textView;
    ListView lv;

    Globals methods;
    String catagory = "Show All";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("FileTag", "MAINACTIVITY CALLED");
       // textView = (TextView) findViewById(R.id.textView);
        //textView.setVisibility(View.VISIBLE);

        methods = new Globals(this);


        lv= (ListView) findViewById(R.id.lv);

        btn_addTask = (Button) findViewById(R.id.btn_addTasks);
        btn_addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                methods.openAddTaskActivity(catagory);
            }
        });

        btn_viewTask = (Button) findViewById(R.id.btn_viewTasks);
        btn_viewTask.setOnClickListener(new View.OnClickListener() {
            @Override
           //public void onClick(View view) {
         //       openViewTasksActivity();
         //   }
        public void onClick(View view) {
                methods.setSelectedDropdownItem(catagory);
                methods.openViewTasksActivity(catagory);
        }
        });

        textView = (TextView) findViewById(R.id.textView);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected())
        {
            textView.setVisibility(View.INVISIBLE);
        }
        else{
            btn_viewTask.setEnabled(false);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openViewTasksActivity(){
        Intent intent = new Intent(this, ViewTasksActivity.class);

        startActivity(intent);
    }

    public void openAddTaskActivity(){
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);

    }


}
