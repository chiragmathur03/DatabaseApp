package com.example.dbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;

public class EditRecord extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String[] states = {"Karnataka","Kerala","Tamil Nadu"};
    String selectedState = "";
    DataBaseHandler db;
    EditText name, password,email, phoneNumber;
    DatePicker dob;
    Spinner state;
    Record record;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        db = new DataBaseHandler(this);

        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        dob = (DatePicker) findViewById(R.id.dob);
        email = (EditText) findViewById(R.id.email);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        state = (Spinner) findViewById(R.id.state);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, states);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        state.setAdapter(aa);
        state.setOnItemSelectedListener(this);

        Intent intent = getIntent();
        restoreValues(Integer.parseInt(intent.getStringExtra("position")));
    }
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedState = states[i];
    }
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void restoreValues(int position) {
        record = (Record) db.getRecord(position);
        Log.d("TAG", "restoreValues: " + position);
        name.setText(record._name);
        password.setText(record._password);
        phoneNumber.setText(record._phone_number);
        email.setText(record._email);
        state.setSelection(Arrays.asList(states).indexOf(record._state));
        selectedState = record._state;
        String strArr[] = record._dob.split(("-"));
        dob.init(Integer.parseInt(strArr[0]), Integer.parseInt(strArr[1]), Integer.parseInt(strArr[2]),null);
    }

    public void updateRecordToDb(View view) {
        db.updateRecord(new Record(
                    record.getId(),
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