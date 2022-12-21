package com.example.studentsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class EditStudentActivity extends AddStudentActivity {
    private Student student;
    private int studentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        this.nameEt = findViewById(R.id.addstudent_name_et);
        this.idEt = findViewById(R.id.addstudent_id_et);
        this.phoneEt = findViewById(R.id.addstudent_phone_et);
        this.addressEt = findViewById(R.id.addstudent_adress_et);
        this.checkedCB = findViewById(R.id.addstudent_checkbox);

        Button saveBtn = findViewById(R.id.editstudent_save_btn);
        Button deleteBtn = findViewById(R.id.editstudent_delete_btn);
        Button cancelBtn = findViewById(R.id.editstudent_cancel_btn);

        this.studentPosition = (int)getIntent().getSerializableExtra(StudentListActivity.STUDENT_POSITION_PARAM_KEY);
        List<Student> data = Model.instance().getAllStudents();
        this.student = data.get(this.studentPosition);

        this.injectStudentDetails();

        saveBtn.setOnClickListener(view -> this.saveStudent());
        deleteBtn.setOnClickListener(view -> this.removeStudent());
        cancelBtn.setOnClickListener(view -> this.navigateStudentViewPage());
    }

    public void injectStudentDetails(){
        this.nameEt.setText(this.student.name);
        this.idEt.setText(this.student.id);
        this.phoneEt.setText(this.student.phone);
        this.addressEt.setText(this.student.address);
        this.checkedCB.setChecked(this.student.cb);
    }

    public void removeStudent(){
        Model.instance().removeStudent(this.student);
        Intent homePage = new Intent(this, StudentListActivity.class);
        startActivity(homePage);
        finish();
    }

    public void saveStudent(){
        this.student.name = this.nameEt.getText().toString();
        this.student.id = this.idEt.getText().toString();
        this.student.phone = this.phoneEt.getText().toString();
        this.student.address = this.addressEt.getText().toString();
        this.student.cb = this.checkedCB.isChecked();

        Model.instance().editStudent(this.student);

        this.navigateStudentViewPage();
        finish();
    }

    public void navigateStudentViewPage(){
        Intent viewStudent = new Intent(this, ViewStudentActivity.class);
        viewStudent.putExtra(StudentListActivity.STUDENT_POSITION_PARAM_KEY,  this.studentPosition);
        startActivity(viewStudent);
    }
}