package com.frag.q.frag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import com.frag.q.frag.Fragment.FragmentB;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        GridView gv = (GridView) findViewById(R.id.gridView1);
        gv.setAdapter(new MyGridAdapter(this));

        final String[] posterName = {
                "chrysanthemum", "desert", "hydranges", "jellyfish",
                "koala", "penguins", "tulips", "lighthouse",
                "chrysanthemum", "desert", "hydranges", "jellyfish",
                "koala", "penguins", "tulips", "lighthouse",
                "chrysanthemum", "desert", "hydranges", "jellyfish",
                "koala", "penguins", "tulips", "lighthouse"
        };

        gv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GalleryActivity.this, ""+posterName[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class MyGridAdapter extends BaseAdapter {
        private Context context;
        private Integer[] posterID = {
                R.drawable.chrysanthemum, R.drawable.desert, R.drawable.hydranges,
                R.drawable.jellyfish,R.drawable.koala, R.drawable.penguins,
                R.drawable.tulips,R.drawable.lighthouse,R.drawable.chrysanthemum,
                R.drawable.desert, R.drawable.hydranges, R.drawable.jellyfish,
                R.drawable.koala, R.drawable.penguins, R.drawable.tulips,
                R.drawable.lighthouse, R.drawable.chrysanthemum, R.drawable.desert,
                R.drawable.hydranges, R.drawable.jellyfish,R.drawable.koala,
                R.drawable.penguins, R.drawable.tulips,R.drawable.lighthouse
        };

        private String[] posterName = {
                "chrysanthemum", "desert", "hydranges", "jellyfish",
                "koala", "penguins", "tulips", "lighthouse",
                "chrysanthemum", "desert", "hydranges", "jellyfish",
                "koala", "penguins", "tulips", "lighthouse",
                "chrysanthemum", "desert", "hydranges", "jellyfish",
                "koala", "penguins", "tulips", "lighthouse"
        };


        public MyGridAdapter (Context c){
            context = c;
        }

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public View getView(final int position, View convertView, ViewGroup parent){
            ImageView imageView;
            if (convertView == null){
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(300,450));
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setPadding(5,5,5,5);
            }
            else{
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(posterID[position]);

            final int pos = position;
            imageView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    View dialogView = (View) View.inflate(GalleryActivity.this, R.layout.dialog,null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(GalleryActivity.this);
                    ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[pos]);
                    dlg.setTitle(""+posterName[position]);
                    dlg.setIcon(R.drawable.ic_launcher_background);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기",null);
                    dlg.show();
                }
            });

            return imageView;
        }
    }

}
