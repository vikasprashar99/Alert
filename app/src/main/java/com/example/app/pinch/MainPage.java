package com.example.app.pinch;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;



public class MainPage extends Activity {

    Button start;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        start = (Button) findViewById(R.id.pressButton);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent;
                String path = getFilesDir().getAbsolutePath() + "/forms.json";
                File file = new File(path);
                if (file.exists()) {
                    intent = new Intent(MainPage.this, MainActivity.class);
                    MainPage.this.startActivity(intent);
                } else {
                    intent = new Intent(MainPage.this, FormActivity.class);
                    MainPage.this.startActivity(intent);
                }

                finish();
            }

        });



    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
        LocationListener ll=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
            };
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
