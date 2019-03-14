package com.elevisjang.smartstudy.Adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.elevisjang.smartstudy.R;
import com.elevisjang.smartstudy.activity.ClassInformation;
import com.elevisjang.smartstudy.activity.CourseVoice;
import com.elevisjang.smartstudy.activity.FaceDetectActivity;
import com.elevisjang.smartstudy.activity.MyClass;

import java.util.ArrayList;

public class ListView_item_adapter extends BaseAdapter{

    ArrayList<ClassInformation> classInformations;
    LayoutInflater inflater;



    public ListView_item_adapter(Context context,ArrayList<ClassInformation> classInformations){
        this.classInformations = classInformations;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount(){
        return classInformations.size();
    }

    @Override
    public Object getItem(int arg0){
        return classInformations.get(arg0);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        ClassInformation classInformation = classInformations.get(position);
        ViewHolder holder;


        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listview_item,null);
            holder.Classtv = convertView.findViewById(R.id.classtv);
            holder.Teatv = convertView.findViewById(R.id.teachertv);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.Classtv.setText(classInformation.getClassName());
        holder.Teatv.setText(classInformation.getTeacherName());

        return convertView;
    }




    private class ViewHolder {
        TextView Classtv;
        TextView Teatv;
    }



}
