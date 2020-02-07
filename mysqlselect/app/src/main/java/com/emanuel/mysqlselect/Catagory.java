package com.emanuel.mysqlselect;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Catagory extends AppCompatActivity
 {

    private List<String> catagories= new ArrayList<String>();
    String catag="null";

   // catag =



    public void setCatagory(String catag) {
        //catagories = new ArrayList<String>();
        catagories.add(catag);
    }

   //  public String getCatagory(String catag) {
         //catagories = new ArrayList<String>();
     //    return catagories.get(catag);
   //  }

     public List getCatagoryList() {
         //catagories = new ArrayList<String>();
         return catagories;
     }
  // public List<String> getCatagories(){
    //    return catagories;
  //  }
  //public AddCatagory(){

 // }
}


