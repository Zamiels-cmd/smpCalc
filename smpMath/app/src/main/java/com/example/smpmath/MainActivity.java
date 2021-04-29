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
                try {
                    if (inputText.contains("x")) {
                        output.setText(inputText);
                    }else{
                        output.setText(String.valueOf(MathCore.eval(inputText)));
                    }
                }catch (RuntimeException e){
                    output.setText(e.getMessage());
                }
                 
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
                }else if(!lastChar.equals("")&&(MathCore.isInt(lastChar)||lastChar.equals("x")||lastChar.equals("i")||lastChar.equals(")"))){
                    inputText+=" )";
                }else{
                    inputText+=" (";
                }

                TextView inputv = findViewById(R.id.input);
                inputv.setText(inputText);
            }
        });

        Button delB = findViewById(R.id.buttonDel);
        delB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText = (inputText.length() > 0 ? inputText.substring(0,inputText.length() - 1) : inputText).trim();
                TextView inputv = findViewById(R.id.input);
                inputv.setText(inputText);
            }
        });

    }

    public void onClickSolve(View v){
        TextView output = findViewById(R.id.display);
        try {
            if (inputText.contains("x")) {
                String small="0.0001";
                String text="( "+inputText+" )";
                String incr=text.replaceAll("x","( x + "+small+" )");
                String derv="( "+incr+" - ( "+text+ ") ) / ( "+small+" )";
                String newtonsAdd="( "+text+" ) / ( "+derv+" )";
                String newtons = "( x - ( "+newtonsAdd+" ) )";
                ComplexNumber out = new ComplexNumber(.75,.75);
                int OVER_TIME=100;
                int overtime=0;

                while (true){
                    out=MathCore.eval(newtons.replaceAll("x",out.toString()));
                    Double ERROR=0.000000000001;
                    Log.i("solve", "onClickSolve: " + out.toString() +" and " + newtons);
                    overtime+=1;
                    if (overtime>OVER_TIME||MathCore.eval(newtonsAdd.replaceAll("x",out.toString())).abs()<ERROR){
                        break;
                    }

                }
                output.setText(out.toString());

            }else{
                output.setText((MathCore.eval(inputText).equals(0.0))?"True":"False");
                Log.i("solve", "onClickSolve: " + MathCore.eval(inputText).toString());
            }

        }catch (RuntimeException e){
            output.setText(e.getMessage());
        }
    }

    public void onClickSimple(View v){

        Button selfB = findViewById(v.getId());
        String textOfB=selfB.getText().toString();
        String lastChar = inputText.length() > 1 ? inputText.substring(inputText.length() - 1) : inputText;
        //if (textOfB.equals("x")) return;

        if (MathCore.isInt(textOfB)||textOfB.equals(".")){
            if (lastChar.equals(".") || MathCore.isInt(lastChar)){
                if (lastChar.equals(".")&&textOfB.equals(".")){return;}
                    inputText+=textOfB;
            }else if(lastChar.equals(")")||lastChar.equals("x")||lastChar.equals("i")){
                inputText+=" * "+textOfB;
            }
            else{
                inputText+=" "+textOfB;
            }
        }else if(textOfB.equals("i")){
            if( !(lastChar.equals(".")) && (lastChar.equals("i") || lastChar.equals("x") || lastChar.equals(")") || MathCore.isInt(lastChar)))
                inputText+=" * i";
            else if(!lastChar.equals(".")){
                inputText+=" i";
            }
        }
        else if(lastChar.equals(")")|| MathCore.isInt(lastChar)||
                ((lastChar.equals("x")||lastChar.equals("i"))&&!(textOfB.equals("x")||textOfB.equals("i"))||(textOfB.equals("x")||textOfB.equals("i"))&&!(lastChar.equals("x")||lastChar.equals("i"))) && !lastChar.equals("^")){
            if (textOfB.equals("x") && (lastChar.equals("i") || lastChar.equals("x") || lastChar.equals(")") || MathCore.isInt(lastChar))){
                inputText+=" * "+textOfB;
            }
            else{
                inputText+= " "+textOfB;
            }
        }

        TextView inputv = findViewById(R.id.input);
        Log.i("Debug", "onClickSimple: "+inputText);
        inputv.setText(inputText);

    }

}