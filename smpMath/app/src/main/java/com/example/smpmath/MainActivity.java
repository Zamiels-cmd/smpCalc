package com.example.smpmath;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputText="";
        setContentView(R.layout.activity_main);
        Button answerB = findViewById(R.id.bans);
        answerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void onClickSimple(View v){
        Button selfB = findViewById(v.getId());
        inputText+=selfB.getText().toString();
        EditText inputv = findViewById(R.id.input);
        inputv.setText(inputText);

    }

}