package com.frag.q.frag;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.frag.q.frag.mRecyclerView.ListItem;
import com.frag.q.frag.mRecyclerView.MyAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class NickNameActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nick_name);

        recyclerView = (RecyclerView) findViewById(R.id.nickname_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        listItems = new ArrayList<>();

        String json = null;

        // JSON parsing
        try {

            InputStream is = getAssets().open("nick_numbers.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray("numbers");

            for(int i = 0; i < array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                ListItem item = new ListItem(
                        o.getString("nick"),
                        o.getString("number")
                );

                listItems.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        listItems.add(new ListItem("김김", "010-2306-4143"));
//        listItems.add(new ListItem("조조", "010-0000-0000"));

        adapter = new MyAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
    }
}