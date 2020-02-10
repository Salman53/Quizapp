package com.example.tryquiz2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WebpagesActivity extends AppCompatActivity {

    String[] urls = new String[10];
    Button btn1, btn2,btn3,btn4,btn5,btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webpages);

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);

        urls[0]="file:///android_asset/basics.html";
        urls[1]="file:///android_asset/Editing Documents.html";
        urls[2]="file:///android_asset/page formating.html";
        urls[3]="file:///android_asset/formating text.html";
        urls[4]="file:///android_asset/working with tables.html";
        //urls[5]="file:///android_asset/basics.html";

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),WebView.class);
                intent.putExtra("links",urls[0]);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebView.class);
                intent.putExtra("links", urls[1]);
                startActivity(intent);

            }});
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebView.class);
                intent.putExtra("links", urls[2]);
                startActivity(intent);

            }});
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebView.class);
                intent.putExtra("links", urls[3]);
                startActivity(intent);

            }});
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebView.class);
                intent.putExtra("links", urls[4]);
                startActivity(intent);

            }});
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("links", urls[5]);
                startActivity(intent);

            }});
    }
}
