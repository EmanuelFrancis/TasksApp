package com.emanuel.mysqlselect;

import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SpinnersList {

    public List<Spinner> spinners = new ArrayList<>();


    public void holdSpinner(Spinner name) {
        Spinner spinner = name;



        spinners.add(spinner);
    }
/*
    public Spinner getSpinner(Spinner name){
        Spinner spinner;
        Spinner returnSpinner = null;

        Spinner spinnerName = name;

        for (int i = 0;i<=spinners.size();i++) {
             spinner = spinners.get(i);
             
             if(spinnerName==spinner) {
                 returnSpinner = spinner;

             }

        }
        return returnSpinner;

    }
*/
    int getSize(){

        return spinners.size();
    }

}
