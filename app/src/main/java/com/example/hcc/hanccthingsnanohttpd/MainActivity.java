package com.example.hcc.hanccthingsnanohttpd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NanoHttpServer server = new NanoHttpServer(8888);
        try {
            server.start();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        String IPStr = NanoHttpServer.getLocalIpStr(MainActivity.this);
        Log.v("Hancc","当前IP:"+IPStr);

        TextView currentiptext = (TextView)findViewById(R.id.currentiptext);
        currentiptext.setText("请访问   "+IPStr+":"+"8888");
    }
}
