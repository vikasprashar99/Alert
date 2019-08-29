package com.example.app.pinch;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Locale;


class MyLocationListener implements LocationListener {

    @Override
    public void onLocationChanged(Location loc) {

        String longitude = "Longitude: " + loc.getLongitude();
        String latitude = "Latitude: " + loc.getLatitude();

        String s = longitude + "\n" + latitude + "\n";
        Log.i(s, "");
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
}


public class MainActivity extends Activity implements LocationListener {


    //GPSTracker gps1 = new GPSTracker(this);
    Button btnSendSMS;
    String n1, n2, n3, n4, n5, name, sendLoc, pro1, lat, longi;
    TextView t1;
    public double latitude;
    public double longitude;
    public String latitud, longitud;
    public GPSTracker gps;
    private static final int PERMISSION_SEND_SMS = 123;


    public void readForm() {

        String path = getFilesDir().getAbsolutePath() + "/forms.json";
        File f = new File(path);
        f.setReadable(true, false);

        try {
            FileInputStream fis = openFileInput("forms.json");
            BufferedInputStream bis = new BufferedInputStream(fis);
            StringBuffer b = new StringBuffer();
            while (bis.available() != 0) {
                char c = (char) bis.read();
                b.append(c);
            }
            bis.close();
            fis.close();

            JSONArray data = new JSONArray(b.toString());

            n1 = data.getJSONObject(0).getString("Emergency Number 1 :");
            n2 = data.getJSONObject(0).getString("Emergency Number 2 :");
            n3 = data.getJSONObject(0).getString("Emergency Number 3 :");
            n4 = data.getJSONObject(0).getString("Emergency Number 4 :");
            n5 = data.getJSONObject(0).getString("Emergency Number 5 :");
            name = data.getJSONObject(0).getString("Name :");
            pro1 = data.getJSONObject(0).getString("Pro");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//
//
//    private void requestSmsPermission() {
//
//        // check permission is given
//        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//            // request permission (see result in onRequestPermissionsResult() method)
//            ActivityCompat.requestPermissions(MainActivity.this,
//                    new String[]{Manifest.permission.SEND_SMS},
//                    PERMISSION_SEND_SMS);
//        } else {
//            // permission already granted run sms send
//            sendSMS(phone, message);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
//
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // permission was granted
//                    sendSms(phone, message);
//                } else {
//                    // permission denied
//                }
//                return;
//            }
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Criteria c=new Criteria();
        LocationManager lm=(LocationManager)getSystemService(LOCATION_SERVICE);
        String provide=lm.getBestProvider(c,false);
        Location location=lm.getLastKnownLocation(provide);
        if(location!=null)
        {
            onLocationChanged(location);
        }
        lm.requestLocationUpdates(provide,20000,0,this);
        */

        /*
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.GPS_PROVIDER;
        // Or use LocationManager.GPS_PROVIDER
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };


        locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
        Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);

        final Double lati=lastKnownLocation.getLatitude();
        final Double logi=lastKnownLocation.getLongitude();
        */

        /*gps1 = new GPSTracker(MainActivity.this);
        if (gps1.canGetLocation())
        {
            latitude = gps1.getLatitude();
            longitude = gps1.getLongitude();
        }
        */

        t1 = (TextView) findViewById(R.id.location);
        btnSendSMS = (Button) findViewById(R.id.smsButton);
        btnSendSMS.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {


                gps = new GPSTracker(MainActivity.this);

                // check if GPS enabled
                if (gps.canGetLocation()) {

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line

                } else {
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

                readForm();
                lat = latitud;
                longi = longitud;
                //lat = String.valueOf(latitude);
                //longi = String.valueOf(longitude);
                lat = "28.5442";
                longi = "77.3334";
                t1.setText("Emergency Condition!!\nSOS Message sent\nCurrent Location is\nLatitude:" + lat + "\nLongitude:" + longi);
                sendLoc = "http://maps.google.com/?q=" + lat + "," + longi;
                sendSMS(n1, "Attention!!!\n" + name + " is in an emergency and needs immediate response, " + pro1 + " location is\n" + sendLoc);
                sendSMS(n2, "Attention!!!\n" + name + " is in an emergency and needs immediate response, " + pro1 + " location is\n" + sendLoc);
                sendSMS(n3, "Attention!!!\n" + name + " is in an emergency and needs immediate response, " + pro1 + " location is\n" + sendLoc);
                sendSMS(n4, "Attention!!!\n" + name + " is in an emergency and needs immediate response, " + pro1 + " location is\n" + sendLoc);
                sendSMS(n5, "Attention!!!\n" + name + " is in an emergency and needs immediate response, " + pro1 + " location is\n" + sendLoc);
            }
        });


    }

    private void sendSMS(String phoneNumber, String message) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
        /*

        Log.i("Send SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.putExtra("address"  , "12345");
        smsIntent.putExtra("sms_body"  , "abcdefghjikkbn");

        try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
        */
/*        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings)
        {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double log = location.getLongitude();
        Toast.makeText(MainActivity.this, "Lat: " + lat + " , Long: " + log, Toast.LENGTH_SHORT).show();
        latitud = Double.toString(lat);
        longitud = Double.toString(log);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Criteria c = new Criteria();
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        String provide = null;
        if (lm != null) {
            provide = lm.getBestProvider(c, true);
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = lm.getLastKnownLocation(provide);
        while (location == null) {
            lm.requestLocationUpdates(provide, 2, 0, this);
            location = lm.getLastKnownLocation(provide);
        }
        if (location != null) {
            onLocationChanged(location);
        }
        lm.requestLocationUpdates(provide, 2, 0, this);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Criteria c = new Criteria();
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        String provide = lm.getBestProvider(c, true);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = lm.getLastKnownLocation(provide);
        while (location == null) {
            lm.requestLocationUpdates(provide, 2, 0, this);
            location = lm.getLastKnownLocation(provide);
        }
        if (location != null) {
            onLocationChanged(location);
        }
        lm.requestLocationUpdates(provide, 2, 0, this);
    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public void call(View view) {
        readForm();
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + n1));
        if (intent.resolveActivity(getPackageManager()) != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(intent);
        }
    }

}
