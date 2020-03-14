package com.emanuel.mysqlselect;

import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class Spacecraft {

    private ArrayList currentTask= new ArrayList<>();
    String currTask="null";

    int id;
    String name, priority,status, deadline, est_time,act_time,description, catagory;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    //    currentTask.add(0,id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    //    currentTask.add(1,name);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    //    currentTask.add(2,status);
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
       // currentTask.add(3,priority);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
     //   currentTask.add(4,description);
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
   //     currentTask.add(5,catagory);
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    //    currentTask.add(6,deadline);
    }

    public String getEst_time() {
        return est_time;
    }

    public void setEst_time(String est_time) {
        this.est_time = est_time;
   //     currentTask.add(7,est_time);
    }

    public String getAct_time() {
        return act_time;
    }

    public void setAct_time(String act_time) {
        this.act_time = act_time;
  //      currentTask.add(8,act_time);
    }



    public void addToList(String listItem){

       // placeInArray(l)

        String listItemToAdd = listItem;
        currentTask.add(0,listItemToAdd);
    }

    public void addIntToList(Integer listItem){
        Integer listItemToAdd = listItem;
        currentTask.add(listItemToAdd);
    }

    public ArrayList<Spacecraft> getCurrentTaskList(){
        //ArrayList listToAdd = list;
       // currentTask.addAll(listToAdd);
        return currentTask;
    }

    public void clearCurrentList(){
        currentTask.clear();
    }

    public int getSizeofList(){
        int size = currentTask.size();
        return size;
    }

  //  public int placeInArray(String name){

    //    int place;

     //   switch(name){
   //         case name
    //    }


     //   return place;
  //  }

    void populateList(){

        currentTask.add(id);
        currentTask.add(name);
        currentTask.add(status);
        currentTask.add(priority);
        currentTask.add(description);
        currentTask.add(catagory);
        currentTask.add(deadline);
        currentTask.add(est_time);
        currentTask.add(act_time);

    }


    public void setCurrentTask(String currTask) {
        currentTask.add(currTask);
    }

    public List getCurrentTask() {
        return currentTask;
    }

  //  public ArrayList<Spacecraft> getWholeTask(){
 //       Spacecraft a;
//
 //   }
}