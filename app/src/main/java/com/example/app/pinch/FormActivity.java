package com.example.app.pinch;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;




public class FormActivity extends Activity
{

    RadioButton rb1, rb2;
    String Selection;


    TextView t1;
    EditText name,dob,mail,n1,n2,n3,n4,n5;
    String Name, DOB,Mail,N1,N2,N3,N4,N5,pro;






    public void writeForm () throws IOException, JSONException
    {

        rb1 = (RadioButton) findViewById(R.id.maleRadioButton);
        rb2 = (RadioButton) findViewById(R.id.femaleRadioButton);
        name = (EditText) findViewById(R.id.nameEditText);
        dob = (EditText) findViewById(R.id.dateEditText);
        mail = (EditText) findViewById(R.id.emailEditText);
        n1 = (EditText) findViewById(R.id.enumber1);
        n2 = (EditText) findViewById(R.id.enumber2);
        n3 = (EditText) findViewById(R.id.enumber3);
        n4 = (EditText) findViewById(R.id.enumber4);
        n5 = (EditText) findViewById(R.id.enumber5);

        Name = name.getText().toString();
        DOB = dob.getText().toString();
        Mail = mail.getText().toString();
        N1 = n1.getText().toString();
        N2 = n2.getText().toString();
        N3 = n3.getText().toString();
        N4 = n4.getText().toString();
        N5 = n5.getText().toString();
        if(rb1.isChecked())
        {
            Selection = "Male";
            pro = "his";
        }
        else
        {
            Selection = "Female";
            pro = "her";
        }
        JSONArray data = new JSONArray();
        JSONObject object = new JSONObject();
        try
        {
            object.put("Name :", Name);
            object.put("Date of Birth :", DOB);
            object.put("Sex :", Selection);
            object.put("Email :", Mail);
            object.put("Emergency Number 1 :", N1);
            object.put("Emergency Number 2 :", N2);
            object.put("Emergency Number 3 :", N3);
            object.put("Emergency Number 4 :", N4);
            object.put("Emergency Number 5 :", N5);
            object.put("Pro",pro);

            data.put(object);

            String text = data.toString();
            FileOutputStream fos = openFileOutput("forms.json", MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            writer.write(text);
            writer.flush();
            writer.close();

            Intent intent;
            intent = new Intent(FormActivity.this, MainActivity.class);
            FormActivity.this.startActivity(intent);
            finish();





        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        t1 = (TextView) findViewById(R.id.textView);


        Button b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                try
                {
                    writeForm();
                }
                catch (IOException | JSONException e)
                {
                    e.printStackTrace();
                }
            }

        });
    }












    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form, menu);
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
