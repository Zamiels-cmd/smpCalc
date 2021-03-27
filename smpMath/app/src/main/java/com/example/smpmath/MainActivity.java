package com.example.smpmath;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    String inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputText="";
        setContentView(R.layout.activity_main);
        TextView output = findViewById(R.id.display);
        Button answerB = findViewById(R.id.bans);
        answerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 output.setText(String.valueOf(MathCore.eval(inputText)));
            }
        });

        Button parenthB = findViewById(R.id.bParenthesis);
        parenthB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lastChar = inputText.length() > 1 ? inputText.substring(inputText.length() - 1) : inputText;
                Log.i("Debug", "onClickSimple: "+lastChar);
                if (lastChar.equals(".")){
                    return;
                }else if(MathCore.isInt(lastChar)||lastChar.equals(")")){
                    inputText+=" )";
                }else{
                    inputText+=" (";
                }

                EditText inputv = findViewById(R.id.input);
                inputv.setText(inputText);
            }
        });

        Button delB = findViewById(R.id.buttonDel);
        delB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText = (inputText.length() > 0 ? inputText.substring(0,inputText.length() - 1) : inputText).trim();
                EditText inputv = findViewById(R.id.input);
                inputv.setText(inputText);
            }
        });

    }

    public void onClickSimple(View v){

        Button selfB = findViewById(v.getId());
        String textOfB=selfB.getText().toString();
        String lastChar = inputText.length() > 1 ? inputText.substring(inputText.length() - 1) : inputText;
        if (textOfB.equals("=")||textOfB.equals("x")){
            return;
        }

        if (MathCore.isInt(textOfB)){
            if (lastChar.equals(".") || MathCore.isInt(lastChar)){
                inputText+=lastChar;
            }else if(lastChar.equals(")")||lastChar.equals("x")){
                inputText+=" * "+textOfB;
            }
            else{
                inputText+=" "+textOfB;
            }
        }else if(textOfB.equals("i")||lastChar.equals(")")){
                inputText+=" * i";
        }
        else if(lastChar.equals(")")|| MathCore.isInt(lastChar)||
                (lastChar.equals("x")&&!textOfB.equals("x")||textOfB.equals("x")&&!lastChar.equals("x")) && !lastChar.equals("^")){
            inputText+=" "+textOfB;
        }

        EditText inputv = findViewById(R.id.input);
        Log.i("Debug", "onClickSimple: "+inputText);
        inputv.setText(inputText);

    }

}