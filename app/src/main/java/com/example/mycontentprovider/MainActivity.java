package com.example.mycontentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ContentResolver resolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resolver = this.getContentResolver();
        Button buttonQuery = (Button)findViewById(R.id.find);
        Button buttonInsert = (Button) findViewById(R.id.add);
        Button buttonDelete = (Button) findViewById(R.id.delete);
        Button buttonUpdate = (Button) findViewById(R.id.updata);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.word.provider/WordBook");
                String strWord = "Banana";
                String strMeaning = "香蕉";
                String strSample = "this is a Banana";
                ContentValues values = new ContentValues();
                values.put("word",strWord);
                values.put("mean",strMeaning);
                values.put("example",strSample);
                getContentResolver().insert(uri,values);
                Toast.makeText(MainActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.word.provider/WordBook");
                ContentValues values = new ContentValues();
                values.put("mean","橙子");
                getContentResolver().update(uri,values,"word=?",new String[]{"Banana"});
                Toast.makeText(MainActivity.this,"编辑成功！",Toast.LENGTH_SHORT).show();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Uri uri = Uri.parse("content://com.example.word.provider/WordBook");
                    getContentResolver().delete(uri,"word = ?",new String []{"Banana"});
                    Toast.makeText(MainActivity.this,"删除成功！请在单词本中查看是否成功删除",Toast.LENGTH_SHORT).show();
            }
        });
        buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.example.word.provider/WordBook");
                Cursor cursor = getContentResolver().query(uri,null,"word=?",new String[]{"Banana"},null);
                while(cursor.moveToNext()){
                    Log.e("word",cursor.getString(0));
                    Log.e("mean",cursor.getString(1));
                    Log.e("example",cursor.getString(2));
                    Toast.makeText(MainActivity.this,"查询成功，请在logcat查询！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
