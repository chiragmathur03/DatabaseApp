package com.example.dbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class AddRecord extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] states = {"Karnataka","Kerala","Tamil Nadu"};
    String selectedState = "";
    DataBaseHandler db;
    EditText name, password,email, phoneNumber;
    DatePicker dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        db = new DataBaseHandler(this);

        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        dob = (DatePicker) findViewById(R.id.dob);
        email = (EditText) findViewById(R.id.email);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        Spinner state = (Spinner) findViewById(R.id.state);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, states);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        state.setAdapter(aa);
        state.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedState = states[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void addRecordToDB(View view) {
        db.addRecord(new Record(
                name.getText() + "",
                phoneNumber.getText() + "",
                password.getText() + "",
                email.getText() + "",
                dob.getYear() + "-" + dob.getMonth() + "-" + dob.getDayOfMonth(),
                selectedState + ""
        ));
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}