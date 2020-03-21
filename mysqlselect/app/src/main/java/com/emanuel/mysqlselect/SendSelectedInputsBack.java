package com.emanuel.mysqlselect;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class SendSelectedInputsBack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent i = new Intent(this, SendSelectedInputsBack.class);
        startActivityForResult(i,1);

        Intent intent = new Intent();
        intent.putExtra("edittextvalue","value_here");
        setResult(RESULT_OK, intent);
        finish();
    }
}
