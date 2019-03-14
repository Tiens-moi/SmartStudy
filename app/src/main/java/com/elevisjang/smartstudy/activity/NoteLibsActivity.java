package com.elevisjang.smartstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.elevisjang.smartstudy.Adapter.ListView_item_adapter;
import com.elevisjang.smartstudy.Adapter.Notelibs_listview_item_adapter;
import com.elevisjang.smartstudy.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NoteLibsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private Notelibs_listview_item_adapter notelibs_listview_item_adapter;
    private ArrayList<NotelibsInformation> notelibsInformations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_libs);

        notelibsInformations = new ArrayList<NotelibsInformation>();
        getData();
        listView = (ListView) findViewById(R.id.note_list_view);
        notelibs_listview_item_adapter = new Notelibs_listview_item_adapter(this,notelibsInformations);
        listView.setAdapter(notelibs_listview_item_adapter);
        listView.setOnItemClickListener(this);

    }

    private void getData() {

        NotelibsInformation notelibsInformation = new NotelibsInformation();
        notelibsInformation.setName("MAC协议章节");
        notelibsInformation.setTime("2018年12月11日");
        notelibsInformations.add(notelibsInformation);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String nametext = (String) ((TextView) view.findViewById(R.id.name_tv)).getText();
        String timetext = (String) ((TextView) view.findViewById(R.id.time_tv)).getText();
        String showtext = "笔记名字：" + nametext + "时间：" + timetext;
        Toast.makeText(this, showtext, Toast.LENGTH_SHORT).show();
    }
}
