package com.example.calci;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText tv_1;
    TextView tv_2;
    MaterialButton but_clear, but_open_par, but_close_par, but_bksp,but_dot;
    MaterialButton but_div,but_mul,but_add,but_sub,but_equ;
    MaterialButton but_0,but_1,but_2,but_3,but_4,but_5,but_6,but_7,but_8,but_9;

    String tv1 = "";
    String formula = "";
    String tempFormula = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        initTextViews();
    }

    private void initTextViews() {
        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
    }

    private void setTv_1(String givenValue){
        tv1 = tv1 + givenValue;
        tv_1.setText(tv1);

    }
    @Override
    public void onClick(View view) {

    }

    public void equalOnClick(View view) {
        Double res = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPow();

        try {
            res = (double)engine.eval(formula);
        } catch (ScriptException e) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
        }
        if(res != null)
            tv_2.setText(String.valueOf(res.doubleValue()));

    }

    private void checkForPow() {
        ArrayList<Integer> index = new ArrayList<>();
        for(int i=0;i<tv1.length();i++){
            if(tv1.charAt(i) == '^'){
                index.add(i);
            }
        }
        formula = tv1;
        tempFormula = tv1;
        for(Integer indexOf: index){
            changeFormula(indexOf);
        }
        formula = tempFormula;
    }

    private void changeFormula(Integer indexOf) {
        String numLeft = "";
        String numRight = "";

        for(int i=indexOf+1;i<tv1.length();i++){
            if(isNumeric(tv1.charAt(i)))
                numRight = numRight + tv1.charAt(i);
            else
                break;
        }
        for(int i=indexOf - 1; i >= 0;i--){
            if(isNumeric(tv1.charAt(i)))
                numLeft = numLeft + tv1.charAt(i);
            else
                break;
        }
        String original = numLeft + "^" + numRight;
        String changed = "Math.pow("+numLeft+","+numRight+")";
        tempFormula = tempFormula.replace(original, changed);
    }

    private boolean isNumeric(char c){
        if((c <= '9' && c >= '0') || c == '.')
            return true;
        return false;
    }

    public void clearOnClick(View view) {
        tv_1.setText("0");
        tv1 = "";
        tv_2.setText("0");
        leftPar = true;
    }

    boolean leftPar = true;

    public void parOnClick(View view) {
        if(leftPar){
            setTv_1("(");
            leftPar = false;
        }
        else {
            setTv_1(")");
            leftPar = true;
        }
    }

    public void backspaceOnClick(View view) {
        String s = tv_1.getText().toString();
        if (s.length()>0){
            s = s.substring(0,s.length()-1);
            tv_1.setText(s);
            tv1 = s;
        }
    }

    public void powOnClick(View view) {
        setTv_1("^");
    }

    public void divOnClick(View view) {
        setTv_1("/");
    }

    public void mulOnClick(View view) {
        setTv_1("*");
    }

    public void subOnClick(View view) {
        setTv_1("-");
    }

    public void dotOnClick(View view) {
        setTv_1(".");
    }

    public void addOnClick(View view) {
        setTv_1("+");
    }

    public void oneOnClick(View view) {
        setTv_1("1");
    }

    public void twoOnClick(View view) {
        setTv_1("2");
    }

    public void threeOnClick(View view) {
        setTv_1("3");
    }

    public void fourOnClick(View view) {
        setTv_1("4");
    }

    public void fiveOnClick(View view) {
        setTv_1("5");
    }

    public void sixOnClick(View view) {
        setTv_1("6");
    }

    public void sevenOnClick(View view) {
        setTv_1("7");
    }

    public void eightOnClick(View view) {
        setTv_1("8");
    }

    public void nineOnClick(View view) {
        setTv_1("9");
    }

    public void zeroOnClick(View view) {
        setTv_1("0");
    }



}