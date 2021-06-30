package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class pageFragment_self extends Fragment {
    private int userId;
    private int identify;
    public pageFragment_self(int userId, int identify) {
        this.userId = userId;
        this.identify =identify ;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_self, container, false);

        DBOpenHelper dbsqLiteOpenHelper = new DBOpenHelper(getActivity(),"background.db",null,1);
        final SQLiteDatabase db = dbsqLiteOpenHelper.getWritableDatabase();
        Cursor cursor1 = db.query("background", new String[]{"id","ph"}, "id=?", new String[]{"1"}, null, null, null);
        //利用游标遍历所有数据对象
        while(cursor1.moveToNext()){
            String id = cursor1.getString(cursor1.getColumnIndex("id"));
            //String pn = cursor1.getString(cursor1.getColumnIndex("pn"));
            String ph = cursor1.getString(cursor1.getColumnIndex("ph"));


            Log.i("setactivity","result: id="  + id +" ph: " + ph  );
            if (ph.equals("1"))
                getActivity().getWindow().setBackgroundDrawableResource(R.drawable.caihong);
            else if (ph.equals("2"))

                getActivity().getWindow().setBackgroundDrawableResource(R.drawable.sky);
        }
        // 关闭游标，释放资源
        cursor1.close();


        TextView txt=(TextView)view.findViewById(R.id.id1);
        TextView txt2=view.findViewById(R.id.id2);
        txt2.setText("用户id："+Integer.toString(userId));
        Button information=(Button)view.findViewById(R.id.button);//打开用户信息

        Button button9=(Button)view.findViewById(R.id.button9) ;//打开分享
        Button button10=(Button)view.findViewById(R.id.button10) ;//设置
        Button button12 = (Button)view.findViewById(R.id.button12);
        if(identify == 0){
            button12.setVisibility(View.VISIBLE);
        }
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),add_class.class);
                intent.putExtra("userId",userId);
                startActivity(intent);}
        });
        Toast.makeText(getActivity(), String.valueOf(userId), Toast.LENGTH_LONG).show();
        ImageView imageView = view.findViewById(R.id.imageView);
        DBHelper dbHelper = new DBHelper(getActivity());
        String[] info = dbHelper.getinfo(userId).split(" ");
        Log.i("user_info",dbHelper.getinfo(userId));


        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("userID",userId);
                intent.setClass(getActivity(),infoactivity.class);

                startActivity(intent);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent();
                intent3.setClass(getActivity(),prepactivity.class);
                startActivity(intent3);
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent();
                intent4.setClass(getActivity(),setactivity.class);
                startActivity(intent4);
            }
        });

        return view;

    }
}
