package com.example.finalmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Route extends AppCompatActivity {
    EditText etSource, etDestination;
    Button btTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        btTrack = findViewById(R.id.bt_track);

        btTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sSource = etSource.getText().toString().trim();
                String sDestination = etDestination.getText().toString().trim();

                //check condition
                if (sSource.equals("") && sDestination.equals("")) {

                    //when both value blank
                    Toast.makeText(getApplicationContext(), "Enter both location", Toast.LENGTH_SHORT).show();

                }else{
                    //when both value fill
                    //Display track
                    DisplayTrack(sSource,sDestination);
                }
            }

        });
    }

    private void DisplayTrack(String sSource, String sDestination) {

        //if the device does not have a map installed then redirect to play store
        try {
            //when google map is installed
            //initialize uri

            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + sSource + "/" + sDestination);

            //initialize intent with action view
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);

            //set package
            intent.setPackage("com.google.android.apps.maps");

            //set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //start activity
            startActivity(intent);
        }catch (ActivityNotFoundException e){

            //when google map is not installed
            //initialize uri
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");

            //initialize intent with action view
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);

            //set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //start activity
            startActivity(intent);
        }
    }
}