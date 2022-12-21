package com.example.studentsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;

import com.example.studentsapp.model.Model;
import com.example.studentsapp.model.Student;

import java.util.List;

public class StudentListActivity extends AppCompatActivity {
    public static String STUDENT_POSITION_PARAM_KEY = "POS";
    List<Student> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        Button addStudentBtn = findViewById(R.id.studentlist_add_btn);
        ListView list = findViewById(R.id.studentlist_list);

        data = Model.instance().getAllStudents();
        list.setAdapter(new StudentListAdapter());

        addStudentBtn.setOnClickListener(view -> this.onAddStudentButtonClick());

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent intent = new Intent(view.getContext(), ViewStudentActivity.class);
                intent.putExtra(STUDENT_POSITION_PARAM_KEY,  pos);
                startActivity(intent);
            }
        });
    }

    public void onAddStudentButtonClick(){
        Intent intent = new Intent(this, AddStudentActivity.class);
        startActivity(intent);
    }

    class StudentListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int pos, View view, ViewGroup viewGroup) {

            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.student_list_row, null);
                CheckBox cb = view.findViewById(R.id.studentlistrow_cb);
                cb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = (int) cb.getTag();
                        Student st = data.get(pos);
                        st.cb = cb.isChecked();
                    }
                });
            }
            TextView nameTv = view.findViewById(R.id.studentlistrow_name_tv);
            TextView idTv = view.findViewById(R.id.studentlistrow_id_tv);
            CheckBox cb = view.findViewById(R.id.studentlistrow_cb);

            Student st = data.get(pos);

            nameTv.setText(st.name);
            idTv.setText(st.id);
            cb.setChecked(st.cb);
            cb.setTag(pos);
            return view;
        }
    }
}