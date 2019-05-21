package com.example.msi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
public class Login extends AppCompatActivity implements View.OnClickListener{
    //实例化控件
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText usernameEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox remember_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        usernameEdit = (EditText)findViewById(R.id.user_name);
        passwordEdit = (EditText)findViewById(R.id.pass_word);
        remember_user = (CheckBox)findViewById(R.id.remember_user);
        boolean isRemember = pref.getBoolean("remember_user",false);
        if (isRemember){
            //选择了记住密码，那么将值保存
            String username = pref.getString("username","");
            String password = pref.getString("password","");
            usernameEdit.setText(username);
            passwordEdit.setText(password);
            remember_user.setChecked(true);
        }
        //监听登录按钮
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                String username = usernameEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if(username.equals("root") && password.equals("123456")){
                    editor = pref.edit();
                    if (remember_user.isChecked()){
                        editor.putString("username",username);
                        editor.putString("password",password);
                        editor.putBoolean("remember_user",true);
                    }else{
                        editor.clear();
                    }
                    editor.apply();
                    Intent j = new Intent(Login.this,Index.class);
                    startActivity(j);
                    finish();
                }else{
                    Toast.makeText(Login.this,
                            "账号密码不正确！",
                            Toast.LENGTH_LONG).show();
                }
                break;

        }
    }
}
