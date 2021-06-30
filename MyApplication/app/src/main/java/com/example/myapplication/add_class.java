//创建班级
package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class add_class extends AppCompatActivity {

    private ImageView course_cover;
    private EditText start_date;
    private EditText course_nameET, course_teacherET, course_introET, course_typeET;
    private Button btn_submit, btn_cancel, btn_video;
    private AlertDialog.Builder builder;
    private DatePicker datePicker;

    private Uri imageUri, videoUri;
    private View.OnClickListener dateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            builder = new AlertDialog.Builder(add_class.this);
            View view = (LinearLayout) getLayoutInflater().inflate(R.layout.date_dialog, null);
            datePicker = (DatePicker) view.findViewById(R.id.date_picker);             //设置日期简略显示 否则详细显示 包括:星期\周
            datePicker.setCalendarViewShown(false);                                                     //设置date布局
            builder.setView(view);
            builder.setTitle("选择日期");

            builder.setPositiveButton("确 定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //日期格式
                    int year = datePicker.getYear();
                    int month = datePicker.getMonth()+1;
                    int dayOfMonth = datePicker.getDayOfMonth();
                    start_date.setText(year+":"+month+":"+dayOfMonth);
                    dialog.cancel();
                }
            });
            builder.setNegativeButton("取 消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.create().show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        this.setTitle("发布课程");
        Intent intent = getIntent();
        final int userId = intent.getIntExtra("userId",1);

        course_cover = findViewById(R.id.cover);
        course_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("jpg/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent,1);
            }
        });
        course_nameET = findViewById(R.id.course_name);
        course_typeET = findViewById(R.id.course_type);
        course_teacherET = findViewById(R.id.course_teacher);
        course_introET = findViewById(R.id.course_introduce);
        start_date = findViewById(R.id.course_start);
        start_date.setOnClickListener(dateListener);

        btn_video = findViewById(R.id.course_video);
        btn_submit = findViewById(R.id.submit);
        btn_cancel = findViewById(R.id.cancel);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name",course_nameET.getText().toString());
                contentValues.put("kind",course_typeET.getText().toString());
                contentValues.put("video",videoUri.toString());
                contentValues.put("cover",imageUri.toString());
                contentValues.put("introdution",course_introET.getText().toString());
                contentValues.put("uploaderID",userId);
                contentValues.put("time",start_date.getText().toString());
                DBHelper dbHelper = new DBHelper(add_class.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.insert("class",null, contentValues);
                db.close();
                dbHelper.close();
                Toast.makeText(add_class.this,"课程添加完成",Toast.LENGTH_LONG).show();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("video/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 2);
            }
        });
    }
    public void comeback(View view){
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {//是否选择，没选择就不会继续
            if (requestCode == 1) {
                imageUri = data.getData();//得到uri，后面就是将uri转化成file的过程。
                course_cover.setImageURI(imageUri);
            } else if (requestCode == 2) {
                videoUri = data.getData();
                btn_video.setText("已选择视频");
            }
        }
    }
}
