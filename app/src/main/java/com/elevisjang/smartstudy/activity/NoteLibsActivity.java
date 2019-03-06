package com.elevisjang.smartstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.elevisjang.smartstudy.Adapter.ListView_item_adapter;
import com.elevisjang.smartstudy.Adapter.Notelibs_listview_item_adapter;
import com.elevisjang.smartstudy.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NoteLibsActivity extends AppCompatActivity{

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

    }

    private void getData() {

        NotelibsInformation notelibsInformation = new NotelibsInformation();
        notelibsInformation.setName("MAC协议章节");
        notelibsInformation.setTime("2018年12月11日");
        notelibsInformations.add(notelibsInformation);
    }
}
