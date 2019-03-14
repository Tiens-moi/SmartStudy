package com.elevisjang.smartstudy.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.elevisjang.smartstudy.Adapter.ListView_item_adapter;
import com.elevisjang.smartstudy.R;

import java.util.ArrayList;

public class MyClass extends AppCompatActivity implements AdapterView.OnItemClickListener {

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
        lv.setOnItemClickListener(this);
    }

    private void getData() {

        ClassInformation classInformation = new ClassInformation();
        classInformation.setClassName("网络互联技术");
        classInformation.setTeacherName("赵荷");
        classInformations.add(classInformation);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        /*String teatext = (String) ((TextView)view.findViewById(R.id.teachertv)).getText();
        String classtext = (String) ((TextView)view.findViewById(R.id.classtv)).getText();
        String showtext = "课程：" +  classtext + "  " +"教师名字：" + teatext;
        Toast.makeText(this,showtext,Toast.LENGTH_LONG).show();*/
        Intent intent = new Intent(this,CourseVoice.class);
        startActivity(intent);
    }
}
