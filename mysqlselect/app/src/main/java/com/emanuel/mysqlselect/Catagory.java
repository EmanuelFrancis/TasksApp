package com.emanuel.mysqlselect;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class Catagory extends Application
 {

    private List<String> catagories = new ArrayList<String>();
    boolean exists = false;
    String catag="null";
    public int counter = 0;

   // catag =

     public String addTotCatagoryList(String catag,List catList) {

         List list = catList;
         String cat = catag;
         String Addcat = null;
        // Boolean duplicates = false;

         if(list.size() == 0) {
             Addcat=cat;
         }else {
           Boolean duplicates=countDups(cat,list);
           //  int duplicateNo = counter;
             //**// reset counter
         //    counter = 0;

             //       String dups = String.valueOf(duplicateNo);

             //        Log.d("Duplicates", dups);

             //**// if there are no duplcates of the catagory in the catagory list
             if(duplicates==true){
                 //**// it does not currently exist
                 Addcat = "duplicate";


             }else{
                 Addcat =cat;


             }
         }

         return Addcat;
     }

     private Boolean countDups(String catag,List<String> catList) {


         // String catag="";
         Boolean duplicate = false;
         String cat = catag;
         List<String> list = catList;

         //count the amount of catagories in the catagory list
         for (int i = 0; i < list.size(); i++) {
              String listCat = list.get(i);


             if(cat.equals(listCat)){
                duplicate = true;
             }
         }
         // counter=0;
         return duplicate ;
     }

    public void setCatagory(String catag) {
        //catagories = new ArrayList<String>();
//int duplicateNo = null;

        String cat = catag;
        Log.d("FileTag", "SetCatagory called using " + cat);


     //   String cat = this.catag;

        String catsSize = String.valueOf(catagories.size());


     //   Log.d("SizeOfList", catsSize);
     //   Log.d("AddedCatName", catag);

            //**// if list of catagories is empty
            if(catagories.size() == 0) {
                String strI = String.valueOf(catagories.size());

                //**// add the catagory to it
                catagories.add(cat);
            }else {
         //       cat = catagories.get(i);

                //**// check if catagory already exists in catagory list, if so counter++
                countDuplicates(cat);
                //**// store counter value
                int duplicateNo = counter;
                //**// reset counter
                counter = 0;

         //       String dups = String.valueOf(duplicateNo);

        //        Log.d("Duplicates", dups);

                //**// if there are no duplcates of the catagory in the catagory list
                if(duplicateNo==0){
                    //**// it does not currently exist
                    exists = false;
                    //**// so we can add it to the catagory list
                    catagories.add(cat);
                    Log.d("FileTag", "Last catagory stored is " + catagories.get(catagories.size() - 1));
                }else{
                    exists = true;
                  //  Toast.makeText(this,"Catagory already exists - please select from list" ,Toast.LENGTH_LONG).show();


                    // return null;

                }
            }

          //  String dupno = String.valueOf(duplicateNo);
      //  Log.d("CRE", dupno);
       //  Toast.makeText(this, String.valueOf(duplicateNo),Toast.LENGTH_LONG).show();



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

     public String getdownloadTaskName(String downloadTaskName) {
         String downloadTask = downloadTaskName;
         Log.d("FileTag", "getdownloadTaskName() called by " +  downloadTask);
         return downloadTask;
     }



     public List<String> getCatagoryList() {
         //catagories = new ArrayList<String>();
       //  String downloadTask = downloadTaskName;

         Log.d("FileTag", " ");
         Log.d("FileTag", "GetCatagoryList() called");

         if(catagories.size()==0){
            // catagories.add("empty list");
             Log.d("FileTag", " No catagories in list");
         }else {
             for (int i = 0; i < catagories.size(); i++) {


                 String cats = catagories.get(i);
                 Log.d("FileTag", "  GetCatagory List Contents " + cats);
             }
         }



         return catagories;
     }

     void countDuplicates(String catag) {


        // String catag="";

           catag = catag;

         //count the amount of catagories in the catagory list
         for (int i = 0; i < catagories.size(); i++) {
             String cat = catagories.get(i);

           //  String a = String.valueOf(cat);

      //       Log.d("match", catag);

      //       Log.d("List", cat);
            if(catag.equals(cat)){
    //            Log.d("Dupe","WE HAVE A DUPLICATE!!");
                counter++;
            }
         }
        // counter=0;
         return ;
     }

     int getSize(){

        return catagories.size();
     }
  // public List<String> getCatagories(){
    //    return catagories;
  //  }
  //public AddCatagory(){

 // }
}


