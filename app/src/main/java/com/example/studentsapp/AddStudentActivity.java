package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.example.studentsapp.model.Student;
import com.example.studentsapp.model.Model;

public class AddStudentActivity extends AppCompatActivity {
    public EditText nameEt;
    public EditText idEt;
    public EditText phoneEt;
    public EditText addressEt;
    public CheckBox checkedCB;
    public Button saveStudentBtn;
    public Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        this.nameEt = findViewById(R.id.addstudent_name_et);
        this.idEt = findViewById(R.id.addstudent_id_et);
        this.phoneEt = findViewById(R.id.addstudent_phone_et);
        this.addressEt = findViewById(R.id.addstudent_adress_et);
        this.checkedCB = findViewById(R.id.addstudent_checkbox);

        this.saveStudentBtn = findViewById(R.id.addstudent_save_btn);
        this.cancelBtn = findViewById(R.id.addstudent_cancell_btn);

        this.saveStudentBtn.setOnClickListener(view -> saveStudent());
        this.cancelBtn.setOnClickListener(view -> finish());
    }

    public void saveStudent(){
        String name = this.nameEt.getText().toString();
        String id = this.idEt.getText().toString();
        String phone = this.phoneEt.getText().toString();
        String address = this.addressEt.getText().toString();
        Boolean checked = this.checkedCB.isChecked();
        Student newStudent = new Student(name,id , phone, address,checked);

        Model.instance().addStudent(newStudent);
        this.studentListNavigationPage();
    }

    public void studentListNavigationPage(){
        Intent homePageIntent = new Intent(this, StudentListActivity.class);
        startActivity(homePageIntent);
        finish();
    }
}