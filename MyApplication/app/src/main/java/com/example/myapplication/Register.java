package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private EditText username, password1, password2, question, answer;
    private DBHelper dbHelper;
    private Context context;
    private Button buttonrigster;
    private RadioButton radiolaohi, radioxuesheng;
    private SQLiteDatabase db1_user_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = getApplicationContext();
        dbHelper = new DBHelper(context);
        username = findViewById(R.id.editusernameRegister);
        password1 = findViewById(R.id.editpassword1Register);
        password2 = findViewById(R.id.editpassword2Register);
        question = findViewById(R.id.editmbquestionRegister);
        radiolaohi = findViewById(R.id.radioButton);
        radioxuesheng = findViewById(R.id.radioButton2);
        answer = findViewById(R.id.editmbanswerRegister);
        buttonrigster = findViewById(R.id.confirmRegister);
        buttonrigster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registeruser();
                finish();
            }
        });
    }

    private void registeruser() {
        /*
        06-29增加第0层检查空值
         * 第一层if用来判断用户名是否注册
         * 第二层if判断两次密码是否一致
         * */
        if (!username.getText().toString().equals("") &&
                !password1.getText().toString().equals("") &&
                !password1.getText().toString().equals("") &&
                !question.getText().toString().equals("") &&
                !answer.getText().toString().equals("")) {//检查五个文本框输入不能有空值
            if (user_is_register()) {
                if (pass_isValid()) {
                    int identi = 0;
                    if (radiolaohi.isChecked()) {
                        identi = 0;
                    }
                    if (radioxuesheng.isChecked()) {
                        identi = 1;
                    }
                    ContentValues contentValues = new ContentValues();

                    contentValues.put("name", username.getText()
                            .toString());
                    contentValues.put("pwd", password1.getText()
                            .toString());
                    contentValues.put("question", question.getText()
                            .toString());
                    contentValues.put("answer", answer.getText()
                            .toString());
                    contentValues.put("identity", String.valueOf(identi));
                    dbHelper.insert("user", contentValues);
                    dbHelper.close();
                    Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
                    //跳转到正式页面

                } else {
                    Toast.makeText(context, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    password1.setText("");
                    password2.setText("");
                }
            } else {

                Toast.makeText(context, "该用户名已被占用", Toast.LENGTH_SHORT).show();
                username.setText("");
            }
        } else {
            Toast.makeText(context, "您的注册信息不完整！", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean pass_isValid() {
        if (password1.getText().toString().equals(password2.getText().toString())) {
            return true;
        }
        return false;

    }

    private Boolean user_is_register() {
        db1_user_get = dbHelper.getReadableDatabase();
        Cursor cursor = db1_user_get.rawQuery("select * FROM user where name=? ", new String[]{username.getText().toString()});
        if (cursor.getCount() > 0) {
            //判断是否查到了这个用户
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}