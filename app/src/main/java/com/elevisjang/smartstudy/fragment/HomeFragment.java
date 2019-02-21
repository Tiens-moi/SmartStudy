package com.elevisjang.smartstudy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elevisjang.smartstudy.R;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private View view;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle saveInstanceState){
        view = inflater.inflate(R.layout.fragment_home,null);
        return view;
    }
    @Override
    public void onClick(View v) {

    }
}
