package com.frag.q.frag.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;


import com.frag.q.frag.GalleryActivity;
import com.frag.q.frag.MainActivity;
import com.frag.q.frag.NameActivity;
import com.frag.q.frag.NickNameActivity;
import com.frag.q.frag.R;

public class FragmentB extends Fragment {

    Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_b, container, false);

        button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GalleryActivity.class);
                startActivityForResult(intent, 100);
            }
        });

        return v;
    }

    public static class MyGridAdapter {
        public MyGridAdapter(GalleryActivity galleryActivity) {

        }
    }
}