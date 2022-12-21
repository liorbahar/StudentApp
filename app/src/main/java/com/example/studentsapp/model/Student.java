package com.example.studentsapp.model;

import java.io.Serializable;

public class Student implements Serializable {
    public String name;
    public String id;
    public String phone;
    public String address;
    public Boolean cb;

    public Student(String name, String id, String phone, String address, Boolean cb) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.cb = cb;
    }
}