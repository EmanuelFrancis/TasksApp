package com.emanuel.mysqlselect;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Spinner;

public class Globals {
    Context mContext;

    private static String selectedDropdownItem;

    // constructor
    public Globals(Context context){
        this.mContext = context;
    }



    public void openViewTasksActivity(String selectedCat){
        selectedDropdownItem= selectedCat;

      //  setSelectedDropdownItem(selectedCat);

        Log.d("FileTag", "selectedDropdownItem is " + selectedDropdownItem);
        Intent intent = new Intent(mContext, ViewTasksActivity.class);

        mContext.startActivity(intent);
    }

    public void setSelectedDropdownItem(String selectedCat){
        Log.d("FileTag", "setSelectedDropdownItem called with " + selectedCat);
        selectedDropdownItem = selectedCat;
        Log.d("FileTag", "selectedDropdownItem has been set to " + selectedDropdownItem);
    }

    public String getSelectedDropdownItem(){
        Log.d("FileTag", "getSelectedDropdownItem called " + selectedDropdownItem);
        return selectedDropdownItem;
    }

    public void openAddTaskActivity(String selectedCat){
        selectedDropdownItem= selectedCat;

        Intent intent = new Intent(mContext, AddTaskActivity.class);
        mContext.startActivity(intent);

    }




    public int countSpinnerItems(Spinner spinner){

        int spinnerSize = spinner.getAdapter().getCount();


        return spinnerSize;
    }

    public boolean compareStrings(String a, String b){
        if(a.equals(b)){
            return true;
        }else{
            return false;}
    }









    public String getUserName(){
        return "test";
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }
}