package com.emanuel.mysqlselect;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class ViewTaskDownloader extends AsyncTask<Void,Void,String> {



    Context c;
    String urlAddress;
    ListView lv;
    private Boolean EndOfList = false;
    private String ListEnd = "More Values";
    Spinner dropdown_cat;


    ProgressDialog pd;

    public ViewTaskDownloader(Context c, ListView lv, String urlAddress, Spinner dropdown_cat) {

        this.c = c;
        this.lv = lv;
        this.urlAddress = urlAddress;
        this.dropdown_cat = dropdown_cat;

        //  this.downloadTaskName = downloadTaskName;
        Log.d("FileTag", "VIEWTASKDOWNLOADER CALLED");
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Fetch");
        pd.setMessage("Fetching....Please wait");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... params) {

       // Log.d("FileTag", " ");
     //   Log.d("FileTag", "Downloader Run by" + downloadTaskName);
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        pd.dismiss();

        if(s==null)
        {
      //      Log.d("FileTag", "All Tasks Read ");
            Toast.makeText(c,"Unsuccessfull,Null returned",Toast.LENGTH_SHORT).show();
        }else
        {
            Log.d("FileTag", "ListStatus" + ListEnd);

            //CALL DATA PARSER TO PARSE
            ViewTasksParser parser2=new ViewTasksParser(c,lv,s, EndOfList, dropdown_cat);
            parser2.execute();

        }

    }

    private String downloadData()
    {
        HttpURLConnection con=Connector.connect(urlAddress);
        if(con==null)
        {
            return null;
        }

        InputStream is=null;
        try {

            is=new BufferedInputStream(con.getInputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            String line=null;
            StringBuffer response=new StringBuffer();

            if(br != null)
            {
                while ((line=br.readLine()) != null)
                {
                    response.append(line+"n");
                }

                br.close();

            }else
            {

                return null;
            }

            return response.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null)
            {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}