package com.example.studentsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class ViewStudentActivity extends AppCompatActivity {
    private TextView nameValueTv;
    private TextView idValueTv;
    private TextView phoneValueTv;
    private TextView addressValueTv;
    private CheckBox checkedCB;
    private int studentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        this.nameValueTv = findViewById(R.id.studentview_name_value_tv);
        this.idValueTv = findViewById(R.id.studentview_id_value_tv);
        this.phoneValueTv = findViewById(R.id.studentview_phone_value_tv);
        this.addressValueTv = findViewById(R.id.studentview_address_value_tv);
        this.checkedCB = findViewById(R.id.studentview_checkbox);
        Button editBtn = findViewById(R.id.studentview_edit_btn);

        this.studentPosition = (int)getIntent().getSerializableExtra(StudentListActivity.STUDENT_POSITION_PARAM_KEY);

        this.initStudentDetails();

        editBtn.setOnClickListener(this::navigateEditStudentPage);
    }

    public void initStudentDetails(){
        List<Student> data = Model.instance().getAllStudents();
        Student student = data.get(this.studentPosition);

        this.nameValueTv.setText(student.name);
        this.idValueTv.setText(student.id);
        this.phoneValueTv.setText(student.phone);
        this.addressValueTv.setText(student.address);
        this.checkedCB.setChecked(student.cb);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        this.studentListNavigationPage();
    }

    public void navigateEditStudentPage(View view){
        Intent editStudentIntent = new Intent(view.getContext(), EditStudentActivity.class);
        editStudentIntent.putExtra(StudentListActivity.STUDENT_POSITION_PARAM_KEY,  this.studentPosition);
        startActivity(editStudentIntent);
    }

    public void studentListNavigationPage(){
        Intent homePageIntent = new Intent(this, StudentListActivity.class);
        startActivity(homePageIntent);
        finish();
    }
}