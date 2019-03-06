package com.elevisjang.smartstudy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.elevisjang.smartstudy.R;
import com.elevisjang.smartstudy.activity.NotelibsInformation;

import java.util.ArrayList;

public class Notelibs_listview_item_adapter extends BaseAdapter {

    ArrayList<NotelibsInformation> notelibsInformations;
    LayoutInflater inflater;

    public Notelibs_listview_item_adapter(Context context,ArrayList<NotelibsInformation> notelibsInformations){
        this.notelibsInformations = notelibsInformations;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){

        return notelibsInformations.size();
    }

    @Override
    public Object getItem(int arg0){
        return notelibsInformations.get(arg0);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        NotelibsInformation notelibsInformation = notelibsInformations.get(position);
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.note_listview_item,null);
            holder.Nametv = convertView.findViewById(R.id.name_tv);
            holder.Timetv = convertView.findViewById(R.id.time_tv);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.Nametv.setText(notelibsInformation.getName());
        holder.Timetv.setText(notelibsInformation.getTime());

        return convertView;
    }


    private class ViewHolder {
        TextView Nametv;
        TextView Timetv;
    }
}
