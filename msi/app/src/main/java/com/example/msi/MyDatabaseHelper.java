package com.example.msi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;
public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final  String CREATE_TABLE = "create table Book(" +
            "id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)";

    /**
     * 创建书目表
     */
    public static final String create_shu = "create table shu(" +
            "id integer primary key autoincrement," +
            "name text," +
            "c text," +
            "price real," +
            "pages integer," +
            "writer text)";


    private Context mContext;
    /**
     * 构造函数
     * @param context---上下文
     * @param name--数据库的名称
     * @param factory--类型
     * @param version--版本--用于更新数据
     */
    public MyDatabaseHelper(@Nullable Context context,
                            @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库
        //execute执行SQL语句
        db.execSQL(CREATE_TABLE);
        db.execSQL(create_shu);
        Toast.makeText(mContext,"create database successed!",Toast.LENGTH_LONG).show();
    }

    /**
     * 更新数据库
     * @param db--需要更新的数据库
     * @param oldVersion--旧版本
     * @param newVersion--新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists shu");
        onCreate(db);
    }
}
