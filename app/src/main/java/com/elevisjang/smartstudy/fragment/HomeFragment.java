package com.elevisjang.smartstudy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.elevisjang.smartstudy.R;
import com.elevisjang.smartstudy.activity.FaceDetectActivity;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ddfsffff";
    private View view;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle saveInstanceState){
        view = inflater.inflate(R.layout.fragment_home,null);
        initView();
        return view;
    }
    private void initView(){
        ImageButton faceImgeButton = view.findViewById(R.id.ib_sign);
        ImageButton classImgeButton = view.findViewById(R.id.ib_class);

        faceImgeButton.setOnClickListener(this);
        classImgeButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_sign:
                jump2FaceDetectActivity();
                break;
            case R.id.ib_class:

                break;
        }
    }

    private void jump2FaceDetectActivity(){
        Intent intent = new Intent(getActivity(),FaceDetectActivity.class);
        startActivity(intent);
    }
}
