package com.elevisjang.smartstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.elevisjang.smartstudy.R;

import java.lang.reflect.Array;
import java.util.List;

public class NoteLibsActivity extends AppCompatActivity{
    private String[] data = {"MAC协议章节","MAC协议章节","MAC协议章节","MAC协议章节","MAC协议章节","MAC协议章节","MAC协议章节",
            "MAC协议章节","MAC协议章节","MAC协议章节","MAC协议章节"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_libs);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(NoteLibsActivity.this,android.R.layout.simple_list_item_1,data);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

    }
}
