package com.frag.q.frag;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.frag.q.frag.Fragment.FragmentA;
import com.frag.q.frag.Fragment.FragmentB;
import com.frag.q.frag.Fragment.FragmentC;

public class MainActivity extends AppCompatActivity {

    Button btn_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        btn_1 = (Button) findViewById(R.id.btn1);
//
//        btn_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(getApplicationContext(), NameActivity.class);
//                startActivity(intent);
//
//            }
//        });

        if(findViewById(R.id.fragment_container) != null){

            if(savedInstanceState != null){
                return;
            }
            FragmentA firstFragment = new FragmentA();
            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
        }
    }

    public void selectFragment(View view){
        Fragment fr = null;
        switch (view.getId()){
            case R.id.btn1:
                fr = new FragmentA();
                break;
            case R.id.btn2:
                fr = new FragmentB();
                break;
            case R.id.btn3:
                fr = new FragmentC();
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fr);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}