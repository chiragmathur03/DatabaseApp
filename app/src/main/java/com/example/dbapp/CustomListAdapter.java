package com.example.dbapp;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {
    private ArrayList<Record> listData;
    private LayoutInflater layoutInflater;
    DataBaseHandler db;

    public CustomListAdapter(Context aContext, ArrayList<Record> listData) {
        this.listData= listData;
        layoutInflater = LayoutInflater.from(aContext);
        db = new DataBaseHandler(layoutInflater.getContext());
    }
    public int getCount() {
        return listData.size();
    }
    public Object getItem(int position) {
        return listData.get(position);
    }
    public long getItemId(int position) {
        return position;
    }
    static class ViewHolder {
        TextView uName;
        TextView uPhoneNumber;
        Button editBtn;
        Button deleteBtn;
    }
    public View getView(final int position, View v, ViewGroup vg) {
        ViewHolder holder;
        if(v == null) {
            v = layoutInflater.inflate(R.layout.listview, null);
            holder = new ViewHolder();
            holder.uName = (TextView) v.findViewById(R.id.name);
            holder.uPhoneNumber = (TextView) v.findViewById(R.id.phoneNumber);
            holder.editBtn = (Button) v.findViewById(R.id.editBtn);
            holder.deleteBtn = (Button) v.findViewById(R.id.deleteBtn);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.uName.setText(listData.get(position).getName());
        holder.uPhoneNumber.setText(listData.get(position).getPhoneNumber());
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(layoutInflater.getContext(), EditRecord.class);
                intent.putExtra("position", listData.get(position).getId() + "");
                layoutInflater.getContext().startActivity(intent);
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Record record = listData.get(position);
                db.deleteRecord(record);
                ((MainActivity) layoutInflater.getContext()).showRecords("");
            }
        });

        return v;
    }
}
