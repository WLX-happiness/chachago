package com.frag.q.frag.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.frag.q.frag.MainActivity;
import com.frag.q.frag.NameActivity;
import com.frag.q.frag.NickNameActivity;
import com.frag.q.frag.R;

public class FragmentB extends Fragment {
    MainActivity activity;


    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_b, container, false);

        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(0);
            }
        });

        return rootView;
    }
}