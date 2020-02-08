package com.emanuel.mysqlselect;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class CurrentTaskItem extends AppCompatActivity
{

    private List<String> currentTask= new ArrayList<String>();
    String currTask="null";

    // catag =



    public void setCurrentTask(String currTask) {
        currentTask.add(currTask);
    }

    //  public String getCatagory(String catag) {
    //catagories = new ArrayList<String>();
    //    return catagories.get(catag);
    //  }

    public List getCurrentTask() {
        //catagories = new ArrayList<String>();
        return currentTask;
    }
    // public List<String> getCatagories(){
    //    return catagories;
    //  }
    //public AddCatagory(){

    // }
}


