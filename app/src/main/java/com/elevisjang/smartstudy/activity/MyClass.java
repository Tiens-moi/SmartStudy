package com.elevisjang.smartstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.elevisjang.smartstudy.Adapter.ListView_item_adapter;
import com.elevisjang.smartstudy.R;

import java.util.ArrayList;

public class MyClass extends AppCompatActivity {

    private ListView lv;
    private ListView_item_adapter listViewItemAdapter;
    private ArrayList<ClassInformation> classInformations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_class);

        classInformations = new ArrayList<ClassInformation>();
        getData();
        lv = (ListView) findViewById(R.id.list_view);
        listViewItemAdapter = new ListView_item_adapter(this,classInformations);
        lv.setAdapter(listViewItemAdapter);
    }

    private void getData() {

        ClassInformation classInformation = new ClassInformation();
        classInformation.setClassName("");
        classInformation.setTeacherName("");
        classInformations.add(classInformation);
    }
}
