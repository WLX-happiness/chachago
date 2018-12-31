package com.frag.q.frag;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TarotActivity extends AppCompatActivity {
    Intent intent;
    SpeechRecognizer mRecognizer;
    TextView textView;
    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tarot);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_RECORD_AUDIO
                );
            }
        }

        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");

        mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mRecognizer.setRecognitionListener(recognitionListener);


        textView = (TextView) findViewById(R.id.textView);

        Button button = (Button) findViewById(R.id.button01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecognizer.startListening(intent);
            }
        });

    }

    private RecognitionListener recognitionListener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle bundle) {
        }

        @Override
        public void onBeginningOfSpeech() {
        }

        @Override
        public void onRmsChanged(float v) {
        }

        @Override
        public void onBufferReceived(byte[] bytes) {
        }

        @Override
        public void onEndOfSpeech() {
        }

        @Override
        public void onError(int i) {
            textView.setText("다시 말씀해주세요.");
        }

        @Override
        public void onResults(Bundle bundle) {
            String key = "";
            key = SpeechRecognizer.RESULTS_RECOGNITION;
            ArrayList<String> mResult = bundle.getStringArrayList(key);

            String[] rs = new String[mResult.size()];
            mResult.toArray(rs);

            textView.setText(rs[0]);
        }

        @Override
        public void onPartialResults(Bundle bundle) {
        }

        @Override
        public void onEvent(int i, Bundle bundle) {
        }
    };
}


//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v4.app.FragmentManager;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParser;
//
//import org.w3c.dom.Text;
//
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//
//public class TarotActivity extends AppCompatActivity {
//
//    Button btTranslate;
//    EditText etSource;
//    TextView tvResult;
//
//    String clientId = "fOSyVIXhjCXik5qGLubm";//애플리케이션 클라이언트 아이디값";
//    String clientSecret = "QldWxo4Zn_";//애플리케이션 클라이언트 시크릿값";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tarot);
//
//        etSource = (EditText) findViewById(R.id.et_source);
//        tvResult = (TextView) findViewById(R.id.tv_result);
//        btTranslate = (Button) findViewById(R.id.bt_translate);
//
//        //번역 실행버튼 클릭이벤트
//        btTranslate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //소스에 입력된 내용이 있는지 체크하고 넘어가자.
//                if (etSource.getText().toString().length() == 0) {
//                    Toast.makeText(TarotActivity.this, "번역할 내용을 입력하세요.", Toast.LENGTH_SHORT).show();
//                    etSource.requestFocus();
//                    return;
//                }
//
//                //실행버튼을 클릭하면 AsyncTask를 이용 요청하고 결과를 반환받아서 화면에 표시하도록 해보자.
//                NaverTranslateTask asyncTask = new NaverTranslateTask();
//                String sText = etSource.getText().toString();
//                asyncTask.execute(sText);
//            }
//        });
//
//
//    }
//
//    public class NaverTranslateTask extends AsyncTask<String, Void, String> {
//
//        public String resultText;
//        //Naver
//        //언어선택도 나중에 사용자가 선택할 수 있게 옵션 처리해 주면 된다.
//        String sourceLang = "en";
//        String targetLang = "ko";
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        //AsyncTask 메인처리
//        @Override
//        protected String doInBackground(String... strings) {
//            String sourceText = strings[0];
//
//            try {
//                String text = URLEncoder.encode(sourceText, "UTF-8");
//                String apiURL = "https://openapi.naver.com/v1/language/translate";
//                URL url = new URL(apiURL);
//                HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                con.setRequestMethod("POST");
//                con.setRequestProperty("X-Naver-Client-Id", clientId);
//                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
//                // post request
//                String postParams = "source=" + sourceLang + "&target=" + targetLang + "&text=" + text;
//                con.setDoOutput(true);
//                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//                wr.writeBytes(postParams);
//                wr.flush();
//                wr.close();
//                int responseCode = con.getResponseCode();
//                BufferedReader br;
//                if (responseCode == 200) { // 정상 호출
//                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                } else {  // 에러 발생
//                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
//                }
//                String inputLine;
//                StringBuffer response = new StringBuffer();
//                while ((inputLine = br.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                br.close();
//                System.out.println(response.toString());
//                return response.toString();
//
//            } catch (Exception e) {
//                System.out.println(e);
//                Log.d("error", e.getMessage());
//                return null;
//            }
//        }
//
//        //번역된 결과를 받아서 처리
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            //최종 결과 처리부
//            //Log.d("background result", s.toString()); //네이버에 보내주는 응답결과가 JSON 데이터이다.
//
//            //JSON데이터를 자바객체로 변환해야 한다.
//            //Gson을 사용할 것이다.
//
//            Gson gson = new GsonBuilder().create();
//            JsonParser parser = new JsonParser();
//            JsonElement rootObj = parser.parse(s.toString())
//                    //원하는 데이터 까지 찾아 들어간다.
//                    .getAsJsonObject().get("message")
//                    .getAsJsonObject().get("result");
//            //안드로이드 객체에 담기
//            TranslatedItem items = gson.fromJson(rootObj.toString(), TranslatedItem.class);
//            //Log.d("result", items.getTranslatedText());
//            //번역결과를 텍스트뷰에 넣는다.
//            tvResult.setText(items.getTranslatedText());
//        }
//
//        private class TranslatedItem {
//            String translatedText;
//
//            public String getTranslatedText() {
//                return translatedText;
//            }
//        }
//    }
//}
//



//
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//    @Override
//    public void onMapReady(final GoogleMap map) {
//        LatLng DAEGEON = new LatLng(36.35, 127.38);
//
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(DAEGEON);
//        markerOptions.title("대전");
//        markerOptions.snippet("카이스트");
//        map.addMarker(markerOptions);
//
//        map.moveCamera(CameraUpdateFactory.newLatLng(DAEGEON));
//        map.animateCamera(CameraUpdateFactory.zoomTo(10));
//    }

