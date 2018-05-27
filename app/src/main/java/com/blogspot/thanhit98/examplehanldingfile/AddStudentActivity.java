package com.blogspot.thanhit98.examplehanldingfile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.thanhit98.examplehanldingfile.entity.Student;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AddStudentActivity extends AppCompatActivity {
    // khai báo các biến sử dụng
    Button btnSaveData, btnViewStudent;
    EditText name, phone;
    TextView view_Student_Name, view_Student_Phone;
    Student student = new Student();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // khi chuyển class sử lý sẽ set lại layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        /// khởi tạo 1 só giá trị mặc định
        init();
    }


    private void init() {
        // btn click save
        btnSaveData = findViewById(R.id.SaveStudent);
        btnSaveData.setOnClickListener(btnSaveDataOnClick);
        // btn click view
        btnViewStudent = findViewById(R.id.btn_student_view);
        btnViewStudent.setOnClickListener(btnViewStudentOnClick);
        // lấy về giá trị nhập
        name = findViewById(R.id.Add_Student_Name);
        phone = findViewById(R.id.Add_Student_Phone);

    }
    // khi click thì lấy về 2 giá trị và cho vào object rùi convert sang json r lưu
    private View.OnClickListener btnSaveDataOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // lấy value và set vào object
            student.setName(name.getText().toString());
            student.setPhone(phone.getText().toString());
            // convert sang json
            Gson gson = new Gson();
            String json = gson.toJson(student);
            //lưu chuỗi json
            writeData("DataStudent", json);
        }
    };
    // chuyển sang trang view
    private View.OnClickListener btnViewStudentOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String jsonStudent = readData("DataStudent");
            // vì chúng ta k có class sử lý cho trang view nên việc sử lý vẫn sẽ ở class này
            // chúng ta chuyển layout
            setContentView(R.layout.activity_view_student);

            // lấy về 2 viewId ở layout view
            view_Student_Name = findViewById(R.id.view_Student_Name);
            view_Student_Phone = findViewById(R.id.view_Student_Phone);

            // tạo gson để conver từ json thành object
            Gson gson = new Gson();
            //conver json thành object
            Student student1 = gson.fromJson(jsonStudent, Student.class);
            // set để hiển thị lại value
            view_Student_Name.setText(student1.getName());
            view_Student_Phone.setText(student1.getPhone());
        }
    };

    public void writeData(String urlFile, String str) {
        String file = urlFile;
        String content = str;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = this.openFileOutput(file, Context.MODE_PRIVATE);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            Toast toast = Toast.makeText(AddStudentActivity.this, "viết thành công", Toast.LENGTH_LONG);
            toast.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String readData(String urlFile) {
        InputStream fileOutputStream = null;
        InputStreamReader streamReader = null;
        BufferedReader bufferedReader = null;
        String stringContent = "";

        try {
            fileOutputStream = this.openFileInput(urlFile);
            streamReader = new InputStreamReader(fileOutputStream);
            bufferedReader = new BufferedReader(streamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringContent += line + "\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
                streamReader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(stringContent);
        return stringContent;
    }
}
