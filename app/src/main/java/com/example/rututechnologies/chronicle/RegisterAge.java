package com.example.rututechnologies.chronicle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterAge extends AppCompatActivity {




    private DatePicker ageSelectionPicker;
    private Button ageContinueButton;

    // age limit attribute
    private int ageLimit = 13;
    SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_age);

        Intent intent = getIntent();



        ageSelectionPicker = (DatePicker) findViewById(R.id.ageSelectionPicker);


        ageContinueButton = (Button) findViewById(R.id.ageContinueButton);

        ageContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                openMainActivity();
            }
        });


    }

    public void openMainActivity()
    {
        int age = getAge(ageSelectionPicker.getYear(),ageSelectionPicker.getMonth(),ageSelectionPicker.getDayOfMonth());

        // if user is above 13 years old then only he/she will be allowed to register to the system.
        if (age > ageLimit)
        {
            // code for converting date to string
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, ageSelectionPicker.getYear());
            cal.set(Calendar.MONTH, ageSelectionPicker.getMonth());
            cal.set(Calendar.DAY_OF_MONTH, ageSelectionPicker.getDayOfMonth());
            Date dateOfBirth = cal.getTime();
            String strDateOfBirth = dateFormatter.format(dateOfBirth);

            // code to set the dateOfBirthAttribute.


            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Age of the user should be greater than "+ageLimit+ " !!!",Toast.LENGTH_SHORT).show();
        }

    }

    // method to get the current age of the user.
    private int getAge(int year, int month, int day)
    {
        Calendar dateOfBirth = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dateOfBirth.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dateOfBirth.get(Calendar.DAY_OF_YEAR))
        {
            age--;
        }

        return age;
    }
}
