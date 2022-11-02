package com.example.dbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DataBaseHandler db;
    ListView listView;
    EditText searchText;
    Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DataBaseHandler(this);
        searchText = (EditText) findViewById(R.id.searchText);
        listView = (ListView) findViewById(R.id.listView);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable search = searchText.getText();
                showRecords(search.toString());
            }
        });
        showRecords("");

    }

    public void showRecords(String str) {
        ArrayList<Record> records = db.getAllRecords(str);
        listView.setAdapter(new CustomListAdapter(this,records));
    }

    public void redirectToAddRecord(View view) {
        Intent intent = new Intent(this, AddRecord.class);
        startActivity(intent);
    }
}