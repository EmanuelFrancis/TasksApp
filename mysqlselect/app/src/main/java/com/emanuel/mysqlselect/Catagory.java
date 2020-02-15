package com.emanuel.mysqlselect;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Catagory extends AppCompatActivity
 {

    private List<String> catagories= new ArrayList<String>();
    boolean exists = false;
    String catag="null";

   // catag =



    public void setCatagory(String catag) {
        //catagories = new ArrayList<String>();
int duplicateNo = 0;





        String cat = "empty";
   //     for(int i=0; i == catagories.size() ; i++) {


            if(catagories.size() == 0) {
                catagories.add(catag);
            }else {
            //    cat = catagories.get(i);
                duplicateNo = countDuplicates( catag);
            }

        //}

                if(duplicateNo==0){
                    exists = false;
                    catagories.add(catag);

                }else{
                    exists = true;

                   // return null;

                }

        //    }else {

    //}

   //     if(exists=true){

   //     }else {
    //        catagories.add(catag);
  //      }



    }

   //  public String getCatagory(String catag) {
         //catagories = new ArrayList<String>();
     //    return catagories.get(catag);
   //  }

     public List getCatagoryList() {
         //catagories = new ArrayList<String>();
         return catagories;
     }

     int countDuplicates(String catag) {

         int counter = 0;

         for (int i = 0; i == catagories.size(); i++) {
             String cat = catagories.get(i);
            if(cat == catag){
                counter++;
            }
         }
         return counter;
     }
  // public List<String> getCatagories(){
    //    return catagories;
  //  }
  //public AddCatagory(){

 // }
}


