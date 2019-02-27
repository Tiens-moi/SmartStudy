package com.elevisjang.smartstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.elevisjang.smartstudy.R;

public class MyClass extends AppCompatActivity {
    private String[] data = {"网络互联技术","PHP程序设计","脚本语言开发","物联网技术","网络互联技术","PHP程序设计","脚本语言开发","物联网技术"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MyClass.this,android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}
