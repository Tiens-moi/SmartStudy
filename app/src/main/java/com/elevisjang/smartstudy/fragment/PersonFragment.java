package com.elevisjang.smartstudy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.elevisjang.smartstudy.R;
import com.elevisjang.smartstudy.activity.NoteLibsActivity;

public class PersonFragment extends Fragment implements View.OnClickListener {

    private View view;
    private static String TAG = "sdasd";
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle saveInstanceState){
        view = inflater.inflate(R.layout.fragment_person,null);
        initView();
        return view;
    }

    private void initView() {
        ImageButton noteButton = view.findViewById(R.id.ib_note_person_more);
        noteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_note_person_more:
                Log.i(TAG, "onClick: "+"hajfkdhaifa");
                JumpToNotelibs();
                break;

        }
    }

    private void JumpToNotelibs() {
        Intent intent = new Intent(getActivity(),NoteLibsActivity.class);
        startActivity(intent);
    }


}
