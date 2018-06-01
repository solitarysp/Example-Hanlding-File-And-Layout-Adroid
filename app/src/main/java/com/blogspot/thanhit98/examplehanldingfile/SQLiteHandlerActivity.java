package com.blogspot.thanhit98.examplehanldingfile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.blogspot.thanhit98.examplehanldingfile.config.HandlerDatabase;
import com.blogspot.thanhit98.examplehanldingfile.entity.Student;

import java.util.List;

public class SQLiteHandlerActivity extends AppCompatActivity {
    HandlerDatabase handlerDatabase;
    Button submit_SQLite, ViewAllStudentSQLite;
    EditText Name_SQLite;
    ListView ListViewStudentSQLite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // khi chuyển class sử lý sẽ set lại layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        handlerDatabase=new HandlerDatabase(this);
        /// khởi tạo 1 só giá trị mặc định
        init();
    }

    private void init() {
        Name_SQLite = findViewById(R.id.Name_SQLite);
        submit_SQLite = findViewById(R.id.submit_SQLite);
        submit_SQLite.setOnClickListener(onclickSumbmut);

        ViewAllStudentSQLite = findViewById(R.id.ViewAllStudentSQLite);
        ViewAllStudentSQLite.setOnClickListener(onclickViewAllStudentSQLite);

        ListViewStudentSQLite = findViewById(R.id.ListViewStudentSQLite);
    }

    View.OnClickListener onclickSumbmut = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String NameSQLit = Name_SQLite.getText().toString();
            handlerDatabase.addStudent();
        }
    };
    View.OnClickListener onclickViewAllStudentSQLite = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            List<Student> students = handlerDatabase.getAllStudent();
            String[] name = new String[students.size()];
            for (int i = 0; i < name.length; i++) {
                name[i] = students.get(i).getName();
            }
            ArrayAdapter<String>adapter=new ArrayAdapter<String>(SQLiteHandlerActivity.this, android.R.layout.simple_list_item_1,name);
            ListViewStudentSQLite.setAdapter(adapter);

        }
    };
}
