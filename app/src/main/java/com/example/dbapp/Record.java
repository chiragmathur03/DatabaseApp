package com.example.dbapp;

public class Record {
    int _id;
    String _name;
    String _phone_number;
    String _password;
    String _state;
    String _dob;
    String _email;

    public Record() {}

    public Record(int id,String name,String _phone_number,String _password,String _email,String _dob,String _state) {
        this._id = id;
        this._name = name;
        this._state = _state;
        this._dob = _dob;
        this._email = _email;
        this._password = _password;
        this._phone_number = _phone_number;
    }

    public Record(String name,String _phone_number,String _password,String _email,String _dob,String _state) {
        this._name = name;
        this._state = _state;
        this._dob = _dob;
        this._email = _email;
        this._password = _password;
        this._phone_number = _phone_number;
    }

    public int getId() {return _id;}
    public void setId(int id) {this._id = id;}

    public String getName() {return this._name; }
    public void setName(String name) {this._name = name;}

    public String getState() {return this._state; }
    public void setState(String state) {this._state = state;}

    public String getPassword() {return this._password; }
    public void setPassword(String password) {this._password = password;}

    public String getEmail() {return this._email; }
    public void setEmail(String email) {this._email = email;}

    public String getDob() {return this._dob; }
    public void setDob(String dob) {this._dob = dob;}

    public String getPhoneNumber() {return this._phone_number;}
    public void setPhoneNumber(String _phone_number) {this._phone_number = _phone_number;}
}
