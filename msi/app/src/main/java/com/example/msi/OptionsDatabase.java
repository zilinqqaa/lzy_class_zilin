package com.example.msi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OptionsDatabase extends AppCompatActivity implements View.OnClickListener{
    private MyDatabaseHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_database);
        //添加书目
        Button add = (Button)findViewById(R.id.add);
        add.setOnClickListener(this);
        //查询按钮
        Button select = (Button)findViewById(R.id.select);
        select.setOnClickListener(this);
        //实例化数据库对象
        dbhelper = new MyDatabaseHelper(this,"BookStore.db",null,4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                //获取到用户填写信息
                EditText shuming = (EditText)findViewById(R.id.shuming);
                String shumingText = shuming.getText().toString();

                EditText chubanshe = (EditText)findViewById(R.id.chubanshe);
                String chubansheText = chubanshe.getText().toString();

                EditText jiage = (EditText)findViewById(R.id.jiage);
                String jiageText = jiage.getText().toString();

                EditText yema = (EditText)findViewById(R.id.yema);
                String yemaText = yema.getText().toString();

                EditText zuozhe = (EditText)findViewById(R.id.zuozhe);
                String zuozheText = zuozhe.getText().toString();

                //存入数据库表--shu
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name",shumingText);
                values.put("c",chubansheText);
                values.put("price",Double.valueOf(jiageText));
                values.put("pages",Integer.valueOf(yemaText));
                values.put("writer",zuozheText);
                db.insert("shu",null,values);
                values.clear();
                Toast.makeText(OptionsDatabase.this,"添加成功！",Toast.LENGTH_SHORT).show();
                break;
            //查询数据
            case R.id.select:
                SQLiteDatabase db2 = dbhelper.getWritableDatabase();
                //查询表中所有的数据并且打印
                Cursor cursor = db2.query(
                        "shu",
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
                if (cursor.moveToFirst()){
                    do {
                        String shuming1 = cursor.getString(cursor.getColumnIndex("name"));
                        String chubanshe2 = cursor.getString(cursor.getColumnIndex("c"));
                        double jiage2 = cursor.getDouble(cursor.getColumnIndex("price"));
                        int yema2 = cursor.getInt(cursor.getColumnIndex("pages"));
                        String zuozhe2 = cursor.getString(cursor.getColumnIndex("writer"));
                        Log.d("***:",shuming1);
                        Log.d("***:",chubanshe2);
                        Log.d("***:",jiage2 + "");
                        Log.d("***:",yema2 + " ");
                        Log.d("***:",zuozhe2);
                    }while (cursor.moveToNext());
                    Toast.makeText(OptionsDatabase.this,"查询成功！",Toast.LENGTH_SHORT).show();
                }
                break;

            /**
             *
             * update shu set price = "新的值" where id = ?
             *
             * delete from shu where id = ?
             *
             */
        }
    }
}
