package com.frag.q.frag.Fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.frag.q.frag.NameActivity;
import com.frag.q.frag.NickNameActivity;
import com.frag.q.frag.R;

public class FragmentA extends Fragment {
    Button btn_name;
    Button btn_nickname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_a, container, false);

        btn_name = (Button) v.findViewById(R.id.btn_name);
        btn_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NameActivity.class);
                startActivityForResult(intent, 100);
            }
        });

        btn_nickname = (Button) v.findViewById(R.id.btn_nickname);
        btn_nickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NickNameActivity.class);
                startActivityForResult(intent, 100);
            }
        });


        return v;


    }

}
//
//public class OneFragment extends Fragment {
//
//    Button moveButton; // 이걸 누르면 이동!
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        //OneFragment.java와 fragment_1.xml 를 이어주는 코드
//        View view = inflater.inflate(R.layout.fragment_1, container, false);
//
//        //moveButton은 fragment_1.xml 코드에서 미리 만들어줘야함
//        //현재 moveButton과 xml 파일의 moveButton을 이어줌
//        moveButton = view.findViewById(R.id.moveButton);
//
//        //moveButton을 누르면 작동할 코드
//        moveButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //Intent를 이용해서 TwoActivity로 이동
//                Intent intent = new Intent(getActivity(),TwoActivity.class);
//
//                //숫자 100은 의미X
//                startActivityForResult(intent,100);
//            }
//        });
//        return view;
//    }
//
//}
//[출처] [Android] Fragment 에서 Activity로 이동|작성자 후후
