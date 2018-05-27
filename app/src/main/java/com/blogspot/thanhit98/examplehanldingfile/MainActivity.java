package com.blogspot.thanhit98.examplehanldingfile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    // khai báo các biến sử dụng
    Button btnSaveData, btnReadData,btnAddSutdent;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //khai báo sự kiện btnSave khi click
        btnSaveData = findViewById(R.id.saveData);
        btnSaveData.setOnClickListener(btnSaveDataOnclick);

        //khai báo sự kiện btnRead khi click
        btnReadData = findViewById(R.id.btnReadData);
        btnReadData.setOnClickListener(btnReadDataOnclick);
        editText = findViewById(R.id.editText2);


        //khai báo sự kiện btnAddSutdent khi click để chuyển sang layout khác
        btnAddSutdent=findViewById(R.id.btnAddStudent);
        btnAddSutdent.setOnClickListener(btnAddSutdentOnclick);

    }
    //tạo sự kiện save
    View.OnClickListener btnSaveDataOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            writeData();
        }
    };

    public void writeData() {
        //chọn tên file lưu
        String file = "Data";
        //get về value nhập
        String content = editText.getText().toString();
        //khai báo truy cập file
        FileOutputStream fileOutputStream = null;
        try {
            // khởi tạo truy cập file
            fileOutputStream = this.openFileOutput(file, Context.MODE_PRIVATE);
            //viết dữ liệu vào file
            fileOutputStream.write(content.getBytes());
            //đẩy dữ liệu từ memory vào file cứng
            fileOutputStream.flush();

            //tạo 1 sự kiện thị thông báo nhỏ ở góc 6h
            //MainActivity class , value hiển thị , Toast.LENGTH_LONG tốc độ tắt,
            Toast toast = Toast.makeText(MainActivity.this, "viết thành công", Toast.LENGTH_LONG);
            //hiển thị
            toast.show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //đóng truy cập file
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //tạo sự kiện read

    View.OnClickListener btnReadDataOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            readData();
        }
    };

    public void readData() {
        //chọn tên file read
        String file = "Data";
        //Khai báo một stream
        InputStream fileOutputStream = null;
        //Khai báo một StreamReader
        InputStreamReader streamReader=null;
        //Khai báo một BufferedReader
        BufferedReader bufferedReader=null;

        try {
            //khởi tạo mở file
            fileOutputStream = this.openFileInput(file);
            //chuyển file đó vào streamReader
             streamReader = new InputStreamReader(fileOutputStream);
             //chuyển dữ liệu đó vào memory
             bufferedReader = new BufferedReader(streamReader);
            String line = "";
            String stringContent = "";
            //dùng vòng lặp để lấy dữ liệu
            while ((line = bufferedReader.readLine()) != null) {
                stringContent+=line+"\n";
            }
            //set content
            editText.setText(stringContent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //đóng kết nối
                fileOutputStream.close();
                streamReader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    // sự kiển truyển sang layout mới
    View.OnClickListener btnAddSutdentOnclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //new mới class sử lý
            Intent i = new Intent(getBaseContext(), AddStudentActivity.class);
            // set để hiển thị class sử lý
            startActivity(i);
        }
    };
}
