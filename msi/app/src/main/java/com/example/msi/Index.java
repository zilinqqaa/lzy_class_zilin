package com.example.msi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class Index extends AppCompatActivity implements View.OnClickListener{

    //引入工具类
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        //设置数据的按钮
        Button save_data = (Button)findViewById(R.id.save_data);
        save_data.setOnClickListener(this);
        //获取数据的按钮
        Button get_data = (Button)findViewById(R.id.get_data);
        get_data.setOnClickListener(this);
        //跳转登录界面
        Button goto_login = (Button)findViewById(R.id.goto_login);
        goto_login.setOnClickListener(this);
        //监听点击按钮事件
        Button create_database = (Button)findViewById(R.id.create_database);
        create_database.setOnClickListener(this);
        //添加书目
        Button add_shu = (Button)findViewById(R.id.add_shu);
        add_shu.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_shu:
                Intent k = new Intent(Index.this,OptionsDatabase.class);
                startActivity(k);
                break;
            case R.id.create_database:
                //指定需要创建的数据库
                dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,3);
                //进行建库操作
                dbHelper.getWritableDatabase();
                break;
            case R.id.goto_login:
                Intent i = new Intent(Index.this,Login.class);
                startActivity(i);
                break;
            case R.id.get_data:
                SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
                String name = pref.getString("name","");
                int age = pref.getInt("age",1);
                String job = pref.getString("job","");
                boolean married = pref.getBoolean("married",false);
                Toast.makeText(Index.this,name + "的年龄是：" +
                        age + ",职业是：" + job + "," +
                        "是否结婚：" + married,Toast.LENGTH_LONG).show();
                break;
            case R.id.save_data:
                //使用键值对存储数据
                SharedPreferences.Editor editor =
                        getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name","翠花");
                editor.putInt("age",18);
                editor.putString("job","上泡菜");
                editor.putBoolean("married",false);
                //实现存储
                editor.apply();
                Toast.makeText(Index.this,"数据设置成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
