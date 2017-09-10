package ts.calculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by user on 2017-02-21.
 */
public class CalSingleItemView extends Activity {
    // Declare Variables
    String calculation;
    TextView resultVal, Calculation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calsingleitemview);
        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();

        resultVal = (TextView) findViewById(R.id.resultVal);
        Calculation = (TextView) findViewById(R.id.Calculation);

        calculation = i.getStringExtra("val");

        Log.d("요거요거", calculation);

        resultVal.setText(cal(calculation));
    }

    public String cal(String content) {

        String last = "";
        try{
            String test = null, front = null, getnum = content;
            String bolgetnum;

            Calculation.setText("Calculation ↙ " + "\n" + getnum + "\n");

            int leftnum, t = 0, start, end;

            if(getnum.length() != sctCount(getnum).length()) {
                Calculation.setText(Calculation.getText().toString() + "\n" + "생략된 연산자 표시 ↙ " + "\n" + sctCount(getnum) + "\n");
            }

            getnum = sctCount(getnum);

            bolgetnum = getnum;

            if (getnum.indexOf("%") != -1 || getnum.indexOf("ppm") != -1 || getnum.indexOf("π") != -1 || getnum.indexOf("Ε") != -1
                    || getnum.indexOf("MOD") != -1 || getnum.indexOf("EXP") != -1) {
                if (getnum.indexOf("%") != -1) {
                    getnum = getnum.trim().replace("%", "×0.01");
                }
                if (getnum.indexOf("ppm") != -1) {
                    getnum = getnum.trim().replace("ppm", "×0.000001");
                }
                if (getnum.indexOf("π") != -1) {
                    getnum = getnum.trim().replace("π", String.valueOf(Math.PI)); //버튼 리스너 조건 등록 필요
                }
                if (getnum.indexOf("Ε") != -1) {
                    getnum = getnum.trim().replace("Ε", String.valueOf(Math.E)); //버튼 리스너 조건 등록 필요
                }
                if (getnum.indexOf("MOD") != -1) {
                    getnum = getnum.trim().replace("MOD", String.valueOf("%"));
                }
                if (getnum.indexOf("EXP") != -1) {
                    getnum = getnum.trim().replace("EXP", String.valueOf("＾10×"));
                }
            }

            if(getnum.length() != bolgetnum.length()) {
                Calculation.setText(Calculation.getText().toString() + "\n" + "%/ppm/π/Ε/MOD/EXP 치환 ↙ " + "\n" + getnum + "\n");
            }



            ArrayList<Integer> left = new ArrayList<Integer>();

            for (int i = 0; i < getnum.length(); i++) {
                if (getnum.charAt(i) == ')') {
                    t++;
                }
            }

            while (t != 0) {

                leftnum = getnum.indexOf(')');

                for (int i = 0; i < leftnum; i++) {
                    if (getnum.charAt(i) == '(') {
                        left.add(i);
                    }
                }
                start = left.get(left.size() - 1);
                end = leftnum + 1;


                test = getnum.substring(start + 1, end - 1);

                Calculation.setText(Calculation.getText().toString() + "\n" + "() 수식 추출 ↙ " + "\n" + test + "\n");

                String result = getCalculate(test);

                Calculation.setText(Calculation.getText().toString() + "\n" + "() 수식 값 계산 ↙ " + "\n" + result + "\n");

                if (start != 0) {
                    front = getnum.substring(start - 1, start);
                    if (front.equals("＋")) {

                    } else if (front.equals("－")) {

                    } else if (front.equals("×")) {

                    } else if (front.equals("÷")) {

                    } else if (front.equals("-")) {
                        result = "1×" + result;
                    } else if (front.equals("(")) {

                    } else if (front.equals("n")) {

                    } else if (front.equals("s")) {

                    } else if (front.equals("＾")) {

                    } else if (front.equals("!")) {

                    } else if (front.equals("g")) {

                    } else if (front.equals("√")) {

                    } else if (front.equals("π")) {

                    } else if (front.equals("Ε")) {

                    } else if (front.equals("%")) {

                    } else if (front.equals("h")) {

                    } else if (front.equals("S")) {

                    } else {
                        result = "×" + result;
                    }
                }

                getnum = replaceFst(getnum,
                        String.valueOf(
                                getnum.substring(start, end)), result);

                Calculation.setText(Calculation.getText().toString() + "\n" + "() 값 치환 ↙ " + "\n" + getnum + "\n");

                t--;
            }

            last = getCalculate(getnum);

            Calculation.setText(Calculation.getText().toString() + "\n" + "결과 값 ↙ " + "\n" + last);

        } catch(Exception e) {
            Toast.makeText(getApplicationContext(), "잘못된 수식입니다.", Toast.LENGTH_SHORT).show();
        }

        return last;
    }

    private String getCalculate(String content) {

        if(content.indexOf("ABS") != -1) {
            content = getABS(content);
            Calculation.setText(Calculation.getText().toString() + "\n" + "ABS 치환 ↙ " + "\n" + content + "\n");
        }

        double i = 0;
        String result = content;
        //sin23+cos2!+2!
        int chkF, chkS, chkQ, chkC, chkT, chkLog, chkLn, chkR,
                chkAS, chkSH, chkAC, chkCH, chkAT, chkTH;
        chkF = result.indexOf("!");
        chkQ = result.indexOf("＾");

        chkS = result.indexOf("sin");
        chkAS = result.indexOf("asin");
        chkSH = result.indexOf("sinh");

        chkC = result.indexOf("cos");
        chkAC = result.indexOf("acos");
        chkCH = result.indexOf("cosh");

        chkT = result.indexOf("tan");
        chkAT = result.indexOf("atan");
        chkTH = result.indexOf("tanh");

        chkLog = result.indexOf("log");
        chkLn = result.indexOf("ln");
        chkR = result.indexOf("√");

        if(chkF != -1) {
            result = getFactorial(result);
            Calculation.setText(Calculation.getText().toString() + "\n" + "Factorial계산 후  치환 ↙ " + "\n" + result + "\n");
        }

        if(chkQ != -1 && chkS != -1 || chkC != -1 || chkT != -1
                || chkLog != -1 || chkLn != -1 || chkR != -1
                || chkAS != -1 || chkAC != -1 || chkAT != -1
                || chkSH != -1 || chkCH != -1 || chkTH != -1) {
            result = getSquare(result);
            Calculation.setText(Calculation.getText().toString() + "\n" + "Square계산 후 치환 ↙ " + "\n" + result + "\n");
        }

        if(chkS != -1 || chkC != -1 || chkT != -1
                || chkLog != -1 || chkLn != -1 || chkR != -1
                || chkAS != -1 || chkAC != -1 || chkAT != -1
                || chkSH != -1 || chkCH != -1 || chkTH != -1) {
            result = getCalculate2(result);
            Calculation.setText(Calculation.getText().toString() + "\n" + "삼각함수 및 log/ln계산 후 치환 : " + result + "\n");
        }

        if(chkQ != -1) {
            result = getSquare(result);
            Calculation.setText(Calculation.getText().toString() + "\n" + "Square계산 후 치환 ↙ " + "\n" + result + "\n");
        }

        Stack<String> stknum = new Stack<String>();


        StringTokenizer snum = new StringTokenizer(result, "＋－×÷%");
        StringTokenizer soper = new StringTokenizer(result, "1234567890-.");


        stknum.push(snum.nextToken());

        while(snum.hasMoreTokens()){

            String num = snum.nextToken();

            char oper = soper.nextToken().charAt(0);

            double res = 0;
            if(oper == '＋'){
                stknum.push(num);
            }else if(oper == '－') {
                stknum.push(String.valueOf(-1 * (Double.parseDouble(num))));
            }else if(oper == '×') {
                res = Double.parseDouble(stknum.pop());
                res *= Double.parseDouble(num);
                stknum.push(String.valueOf(res));
            }else if(oper == '÷') {
                res = Double.parseDouble(stknum.pop());
                res /= Double.parseDouble(num);
                stknum.push(String.valueOf(res));
            }else if(oper == '%') {
                res = Double.parseDouble(stknum.pop());
                res %= Double.parseDouble(num);
                stknum.push(String.valueOf(res));
            }


        }


        while(!stknum.isEmpty()){
            i += Double.parseDouble(stknum.pop());
        }
        String t = String.valueOf(i);

        if(t.substring(t.indexOf("."), t.length()).length() > 11) {
            t = String.format("%.10f", i);
        }else if(t.substring(t.indexOf("."), t.length()).equals(".0")){
            t = String.format("%.0f", i);
        }
        result = String.valueOf(t);

        return result;
    }

    private String getCalculate2(String content) {

        if(content.indexOf("ABS") != -1) {
            content = getABS(content);
            Calculation.setText(Calculation.getText().toString() + "\n" + "ABS 치환 ↙ " + "\n" + content + "\n");
        }

        String getContent = content;
        String result = getContent;

        if(getContent.substring(0,1).equals("s")){
            getContent = getContent + "★";
        }else if(getContent.substring(0,1).equals("c")){
            getContent = getContent + "★";
        }else if(getContent.substring(0,1).equals("t")){
            getContent = getContent + "★";
        }else if(getContent.substring(0,1).equals("√")){
            getContent = getContent + "★";
        }else if(getContent.substring(0,1).equals("l")){
            getContent = getContent + "★";
        }else if(getContent.substring(0,1).equals("a")){
            getContent = getContent + "★";
        }else {
            getContent = "★" + getContent + "★";
        }

        StringTokenizer snum = new StringTokenizer(getContent, "√sincotalgh＋－×÷%★");
        StringTokenizer soper = new StringTokenizer(getContent, "1234567890-.ABCDEF");

        while(snum.hasMoreTokens()) {
            String num = snum.nextToken();
            String foper = soper.nextToken();
            String op = "";

            if(foper.indexOf("√") != -1) {
                op = foper.substring(foper.indexOf("√"));
            }else if(foper.indexOf("sinh") != -1) {
                op = foper.substring(foper.indexOf("sinh"));
            }else if(foper.indexOf("asin") != -1) {
                op = foper.substring(foper.indexOf("asin"));
            }else if(foper.indexOf("sin") != -1) {
                op = foper.substring(foper.indexOf("sin"));
            }else if(foper.indexOf("cosh") != -1) {
                op = foper.substring(foper.indexOf("cosh"));
            }else if(foper.indexOf("acos") != -1) {
                op = foper.substring(foper.indexOf("acos"));
            }else if(foper.indexOf("cos") != -1) {
                op = foper.substring(foper.indexOf("cos"));
            }else if(foper.indexOf("tanh") != -1) {
                op = foper.substring(foper.indexOf("tanh"));
            }else if(foper.indexOf("atan") != -1) {
                op = foper.substring(foper.indexOf("atan"));
            }else if(foper.indexOf("tan") != -1) {
                op = foper.substring(foper.indexOf("tan"));
            }else if(foper.indexOf("log") != -1) {
                op = foper.substring(foper.indexOf("log"));
            }else if(foper.indexOf("ln") != -1) {
                op = foper.substring(foper.indexOf("ln"));
            }

            if(foper.indexOf("√") != -1) {
                result = result.trim().replace(op+num, String.valueOf(Math.sqrt(Double.parseDouble(num))));
            }else if(foper.indexOf("sinh") != -1) {
                result = result.trim().replace(op+num, String.valueOf(Math.sinh(Math.toRadians(Double.parseDouble(num)))));
            }else if(foper.indexOf("asin") != -1) {
                result = result.trim().replace(op+num, String.valueOf(Math.asin(Math.toRadians(Double.parseDouble(num)))));
            }else if(foper.indexOf("sin") != -1) {
                result = result.trim().replace(op+num, String.valueOf(Math.sin(Math.toRadians(Double.parseDouble(num)))));
            }else if(foper.indexOf("cosh") != -1) {
                result = result.trim().replace(op+num, String.valueOf(Math.cosh(Math.toRadians(Double.parseDouble(num)))));
            }else if(foper.indexOf("acos") != -1) {
                result = result.trim().replace(op+num, String.valueOf(Math.acos(Math.toRadians(Double.parseDouble(num)))));
            }else if(foper.indexOf("cos") != -1) {
                result = result.trim().replace(op+num, String.valueOf(Math.cos(Math.toRadians(Double.parseDouble(num)))));
            }else if(foper.indexOf("tanh") != -1) {
                result = result.trim().replace(op+num, String.valueOf(Math.tanh(Math.toRadians(Double.parseDouble(num)))));
            }else if(foper.indexOf("atan") != -1) {
                result = result.trim().replace(op+num, String.valueOf(Math.atan(Math.toRadians(Double.parseDouble(num)))));
            }else if(foper.indexOf("tan") != -1) {
                result = result.trim().replace(op+num, String.valueOf(Math.tan(Math.toRadians(Double.parseDouble(num)))));
            }else if(foper.indexOf("log") != -1) {
                result = result.trim().replace(op+num, String.valueOf(Math.log10(Double.parseDouble(num))));
            }else if(foper.indexOf("ln") != -1) {
                result = result.trim().replace(op+num, String.valueOf(Math.log(Double.parseDouble(num))));
            }
        }
        return result;
    }

    private static String sctCount(String content) {
        String result = content;

        ///////////////////////log
        //log,ln,π,Ε,√,sin,cos,tan,asin,acos,atan,sinh,cosh,tanh,
        for(int i = 0; i < result.length(); i ++) {
            int seq = result.indexOf("log",i);

            if((seq != result.indexOf("log",i+1)) && (seq != -1)) {
                if(seq == 0 ) {

                } else {
                    if(result.substring(seq-1,seq).equals("＋")) {

                    } else if(result.substring(seq-1,seq).equals("－")) {

                    } else if(result.substring(seq-1,seq).equals("×")) {

                    } else if(result.substring(seq-1,seq).equals("÷")) {

                    } else if(result.substring(seq-1,seq).equals("%")) {

                    } else if(result.substring(seq-1,seq).equals("-")) {
                        result = result.substring(0, seq) + "1×" + result.substring(seq, result.length());
                    } else if(result.substring(seq-1,seq).equals("(")) {

                    } else if(result.substring(seq-1,seq).equals("n")) {

                    } else if(result.substring(seq-1,seq).equals("s")) {

                    } else if(result.substring(seq-1,seq).equals("g")) {

                    } else if(result.substring(seq-1,seq).equals("h")) {

                    } else if(result.substring(seq-1,seq).equals("D")) {

                    } else if(result.substring(seq-1,seq).equals("S")) {

                    } else if(result.substring(seq-1,seq).equals("√")) {

                    } else if(result.substring(seq-1,seq).equals("π")) {

                    } else if(result.substring(seq-1,seq).equals("Ε")) {

                    } else if(result.substring(seq-1,seq).equals("＾")) {

                    } else if(result.substring(seq-1,seq).equals("a")) {

                    } else {
                        result = result.substring(0, seq) + "×" + result.substring(seq, result.length());
                    }
                }
            }
        }

        /////////////////////////ln
        //log,ln,π,Ε,√,sin,cos,tan,asin,acos,atan,sinh,cosh,tanh,
        for(int i = 0; i < result.length(); i ++) {
            int seq = result.indexOf("ln",i);

            if((seq != result.indexOf("ln",i+1)) && (seq != -1)) {
                if(seq == 0 ) {

                } else {
                    if(result.substring(seq-1,seq).equals("＋")) {

                    } else if(result.substring(seq-1,seq).equals("－")) {

                    } else if(result.substring(seq-1,seq).equals("×")) {

                    } else if(result.substring(seq-1,seq).equals("÷")) {

                    } else if(result.substring(seq-1,seq).equals("%")) {

                    } else if(result.substring(seq-1,seq).equals("-")) {
                        result = result.substring(0, seq) + "1×" + result.substring(seq, result.length());
                    } else if(result.substring(seq-1,seq).equals("(")) {

                    } else if(result.substring(seq-1,seq).equals("n")) {

                    } else if(result.substring(seq-1,seq).equals("s")) {

                    } else if(result.substring(seq-1,seq).equals("g")) {

                    } else if(result.substring(seq-1,seq).equals("h")) {

                    } else if(result.substring(seq-1,seq).equals("D")) {

                    } else if(result.substring(seq-1,seq).equals("S")) {

                    } else if(result.substring(seq-1,seq).equals("√")) {

                    } else if(result.substring(seq-1,seq).equals("π")) {

                    } else if(result.substring(seq-1,seq).equals("Ε")) {

                    } else if(result.substring(seq-1,seq).equals("＾")) {

                    } else if(result.substring(seq-1,seq).equals("a")) {

                    } else {
                        result = result.substring(0, seq) + "×" + result.substring(seq, result.length());
                    }
                }
            }
        }

        /////////////////////////π
        //log,ln,π,Ε,√,sin,cos,tan,asin,acos,atan,sinh,cosh,tanh,
        for(int i = 0; i < result.length(); i ++) {
            int seq = result.indexOf("π",i);

            if((seq != result.indexOf("π",i+1)) && (seq != -1)) {
                if(seq == 0 ) {

                } else {
                    if(result.substring(seq-1,seq).equals("＋")) {

                    } else if(result.substring(seq-1,seq).equals("－")) {

                    } else if(result.substring(seq-1,seq).equals("×")) {

                    } else if(result.substring(seq-1,seq).equals("÷")) {

                    } else if(result.substring(seq-1,seq).equals("%")) {

                    } else if(result.substring(seq-1,seq).equals("-")) {
                        result = result.substring(0, seq) + "1×" + result.substring(seq, result.length());
                    } else if(result.substring(seq-1,seq).equals("(")) {

                    } else if(result.substring(seq-1,seq).equals("n")) {

                    } else if(result.substring(seq-1,seq).equals("s")) {

                    } else if(result.substring(seq-1,seq).equals("g")) {

                    } else if(result.substring(seq-1,seq).equals("h")) {

                    } else if(result.substring(seq-1,seq).equals("D")) {

                    } else if(result.substring(seq-1,seq).equals("S")) {

                    } else if(result.substring(seq-1,seq).equals("√")) {

                    } else if(result.substring(seq-1,seq).equals("π")) {

                    } else if(result.substring(seq-1,seq).equals("Ε")) {

                    } else if(result.substring(seq-1,seq).equals("＾")) {

                    } else if(result.substring(seq-1,seq).equals("a")) {

                    } else {
                        result = result.substring(0, seq) + "×" + result.substring(seq, result.length());
                    }
                }
            }
        }
        /////////////////////////Ε
        //log,ln,π,Ε,√,sin,cos,tan,asin,acos,atan,sinh,cosh,tanh,
        for(int i = 0; i < result.length(); i ++) {
            int seq = result.indexOf("Ε",i);

            if((seq != result.indexOf("Ε",i+1)) && (seq != -1)) {
                if(seq == 0 ) {

                } else {
                    if(result.substring(seq-1,seq).equals("＋")) {

                    } else if(result.substring(seq-1,seq).equals("－")) {

                    } else if(result.substring(seq-1,seq).equals("×")) {

                    } else if(result.substring(seq-1,seq).equals("÷")) {

                    } else if(result.substring(seq-1,seq).equals("%")) {

                    } else if(result.substring(seq-1,seq).equals("-")) {
                        result = result.substring(0, seq) + "1×" + result.substring(seq, result.length());
                    } else if(result.substring(seq-1,seq).equals("(")) {

                    } else if(result.substring(seq-1,seq).equals("n")) {

                    } else if(result.substring(seq-1,seq).equals("s")) {

                    } else if(result.substring(seq-1,seq).equals("g")) {

                    } else if(result.substring(seq-1,seq).equals("h")) {

                    } else if(result.substring(seq-1,seq).equals("D")) {

                    } else if(result.substring(seq-1,seq).equals("S")) {

                    } else if(result.substring(seq-1,seq).equals("√")) {

                    } else if(result.substring(seq-1,seq).equals("π")) {

                    } else if(result.substring(seq-1,seq).equals("Ε")) {

                    } else if(result.substring(seq-1,seq).equals("＾")) {

                    } else if(result.substring(seq-1,seq).equals("a")) {

                    } else {
                        result = result.substring(0, seq) + "×" + result.substring(seq, result.length());
                    }
                }
            }
        }
        /////////////////////////√
        //log,ln,π,Ε,√,sin,cos,tan,asin,acos,atan,sinh,cosh,tanh,
        for(int i = 0; i < result.length(); i ++) {
            int seq = result.indexOf("√",i);

            if((seq != result.indexOf("√",i+1)) && (seq != -1)) {
                if(seq == 0 ) {

                } else {
                    if(result.substring(seq-1,seq).equals("＋")) {

                    } else if(result.substring(seq-1,seq).equals("－")) {

                    } else if(result.substring(seq-1,seq).equals("×")) {

                    } else if(result.substring(seq-1,seq).equals("÷")) {

                    } else if(result.substring(seq-1,seq).equals("%")) {

                    } else if(result.substring(seq-1,seq).equals("-")) {
                        result = result.substring(0, seq) + "1×" + result.substring(seq, result.length());
                    } else if(result.substring(seq-1,seq).equals("(")) {

                    } else if(result.substring(seq-1,seq).equals("n")) {

                    } else if(result.substring(seq-1,seq).equals("s")) {

                    } else if(result.substring(seq-1,seq).equals("g")) {

                    } else if(result.substring(seq-1,seq).equals("h")) {

                    } else if(result.substring(seq-1,seq).equals("D")) {

                    } else if(result.substring(seq-1,seq).equals("S")) {

                    } else if(result.substring(seq-1,seq).equals("√")) {

                    } else if(result.substring(seq-1,seq).equals("π")) {

                    } else if(result.substring(seq-1,seq).equals("Ε")) {

                    } else if(result.substring(seq-1,seq).equals("＾")) {

                    } else if(result.substring(seq-1,seq).equals("a")) {

                    } else {
                        result = result.substring(0, seq) + "×" + result.substring(seq, result.length());
                    }
                }
            }
        }
        /////////////////////////asin
        //log,ln,π,Ε,√,sin,cos,tan,asin,acos,atan,sinh,cosh,tanh,
        for(int i = 0; i < result.length(); i ++) {
            int seq = result.indexOf("asin",i);

            if((seq != result.indexOf("asin",i+1)) && (seq != -1)) {
                if(seq == 0 ) {

                } else {
                    if(result.substring(seq-1,seq).equals("＋")) {

                    } else if(result.substring(seq-1,seq).equals("－")) {

                    } else if(result.substring(seq-1,seq).equals("×")) {

                    } else if(result.substring(seq-1,seq).equals("÷")) {

                    } else if(result.substring(seq-1,seq).equals("%")) {

                    } else if(result.substring(seq-1,seq).equals("-")) {
                        result = result.substring(0, seq) + "1×" + result.substring(seq, result.length());
                    } else if(result.substring(seq-1,seq).equals("(")) {

                    } else if(result.substring(seq-1,seq).equals("n")) {

                    } else if(result.substring(seq-1,seq).equals("s")) {

                    } else if(result.substring(seq-1,seq).equals("g")) {

                    } else if(result.substring(seq-1,seq).equals("h")) {

                    } else if(result.substring(seq-1,seq).equals("D")) {

                    } else if(result.substring(seq-1,seq).equals("S")) {

                    } else if(result.substring(seq-1,seq).equals("√")) {

                    } else if(result.substring(seq-1,seq).equals("π")) {

                    } else if(result.substring(seq-1,seq).equals("Ε")) {

                    } else if(result.substring(seq-1,seq).equals("＾")) {

                    } else if(result.substring(seq-1,seq).equals("a")) {

                    } else {
                        result = result.substring(0, seq) + "×" + result.substring(seq, result.length());
                    }
                }
            }
        }
        /////////////////////////acos
        //log,ln,π,Ε,√,sin,cos,tan,asin,acos,atan,sinh,cosh,tanh,
        for(int i = 0; i < result.length(); i ++) {
            int seq = result.indexOf("acos",i);

            if((seq != result.indexOf("acos",i+1)) && (seq != -1)) {
                if(seq == 0 ) {

                } else {
                    if(result.substring(seq-1,seq).equals("＋")) {

                    } else if(result.substring(seq-1,seq).equals("－")) {

                    } else if(result.substring(seq-1,seq).equals("×")) {

                    } else if(result.substring(seq-1,seq).equals("÷")) {

                    } else if(result.substring(seq-1,seq).equals("%")) {

                    } else if(result.substring(seq-1,seq).equals("-")) {
                        result = result.substring(0, seq) + "1×" + result.substring(seq, result.length());
                    } else if(result.substring(seq-1,seq).equals("(")) {

                    } else if(result.substring(seq-1,seq).equals("n")) {

                    } else if(result.substring(seq-1,seq).equals("s")) {

                    } else if(result.substring(seq-1,seq).equals("g")) {

                    } else if(result.substring(seq-1,seq).equals("h")) {

                    } else if(result.substring(seq-1,seq).equals("D")) {

                    } else if(result.substring(seq-1,seq).equals("S")) {

                    } else if(result.substring(seq-1,seq).equals("√")) {

                    } else if(result.substring(seq-1,seq).equals("π")) {

                    } else if(result.substring(seq-1,seq).equals("Ε")) {

                    } else if(result.substring(seq-1,seq).equals("＾")) {

                    } else if(result.substring(seq-1,seq).equals("a")) {

                    } else {
                        result = result.substring(0, seq) + "×" + result.substring(seq, result.length());
                    }
                }
            }
        }
        /////////////////////////atan
        //log,ln,π,Ε,√,sin,cos,tan,asin,acos,atan,sinh,cosh,tanh,
        for(int i = 0; i < result.length(); i ++) {
            int seq = result.indexOf("atan",i);

            if((seq != result.indexOf("atan",i+1)) && (seq != -1)) {
                if(seq == 0 ) {

                } else {
                    if(result.substring(seq-1,seq).equals("＋")) {

                    } else if(result.substring(seq-1,seq).equals("－")) {

                    } else if(result.substring(seq-1,seq).equals("×")) {

                    } else if(result.substring(seq-1,seq).equals("÷")) {

                    } else if(result.substring(seq-1,seq).equals("%")) {

                    } else if(result.substring(seq-1,seq).equals("-")) {
                        result = result.substring(0, seq) + "1×" + result.substring(seq, result.length());
                    } else if(result.substring(seq-1,seq).equals("(")) {

                    } else if(result.substring(seq-1,seq).equals("n")) {

                    } else if(result.substring(seq-1,seq).equals("s")) {

                    } else if(result.substring(seq-1,seq).equals("g")) {

                    } else if(result.substring(seq-1,seq).equals("h")) {

                    } else if(result.substring(seq-1,seq).equals("D")) {

                    } else if(result.substring(seq-1,seq).equals("S")) {

                    } else if(result.substring(seq-1,seq).equals("√")) {

                    } else if(result.substring(seq-1,seq).equals("π")) {

                    } else if(result.substring(seq-1,seq).equals("Ε")) {

                    } else if(result.substring(seq-1,seq).equals("＾")) {

                    } else if(result.substring(seq-1,seq).equals("a")) {

                    } else {
                        result = result.substring(0, seq) + "×" + result.substring(seq, result.length());
                    }
                }
            }
        }
        /////////////////////////sin
        //log,ln,π,Ε,√,sin,cos,tan,asin,acos,atan,sinh,cosh,tanh,
        for(int i = 0; i < result.length(); i ++) {
            int seq = result.indexOf("sin",i);

            if((seq != result.indexOf("sin",i+1)) && (seq != -1)) {
                if(seq == 0 ) {

                } else {
                    if(result.substring(seq-1,seq).equals("＋")) {

                    } else if(result.substring(seq-1,seq).equals("－")) {

                    } else if(result.substring(seq-1,seq).equals("×")) {

                    } else if(result.substring(seq-1,seq).equals("÷")) {

                    } else if(result.substring(seq-1,seq).equals("%")) {

                    } else if(result.substring(seq-1,seq).equals("-")) {
                        result = result.substring(0, seq) + "1×" + result.substring(seq, result.length());
                    } else if(result.substring(seq-1,seq).equals("(")) {

                    } else if(result.substring(seq-1,seq).equals("n")) {

                    } else if(result.substring(seq-1,seq).equals("s")) {

                    } else if(result.substring(seq-1,seq).equals("g")) {

                    } else if(result.substring(seq-1,seq).equals("h")) {

                    } else if(result.substring(seq-1,seq).equals("D")) {

                    } else if(result.substring(seq-1,seq).equals("S")) {

                    } else if(result.substring(seq-1,seq).equals("√")) {

                    } else if(result.substring(seq-1,seq).equals("π")) {

                    } else if(result.substring(seq-1,seq).equals("Ε")) {

                    } else if(result.substring(seq-1,seq).equals("a")) {

                    } else if(result.substring(seq-1,seq).equals("＾")) {

                    } else {
                        result = result.substring(0, seq) + "×" + result.substring(seq, result.length());
                    }
                }
            }
        }
        /////////////////////////cos
        //log,ln,π,Ε,√,sin,cos,tan,asin,acos,atan,sinh,cosh,tanh,
        for(int i = 0; i < result.length(); i ++) {
            int seq = result.indexOf("cos",i);

            if((seq != result.indexOf("cos",i+1)) && (seq != -1)) {
                if(seq == 0 ) {

                } else {
                    if(result.substring(seq-1,seq).equals("＋")) {

                    } else if(result.substring(seq-1,seq).equals("－")) {

                    } else if(result.substring(seq-1,seq).equals("×")) {

                    } else if(result.substring(seq-1,seq).equals("÷")) {

                    } else if(result.substring(seq-1,seq).equals("%")) {

                    } else if(result.substring(seq-1,seq).equals("-")) {
                        result = result.substring(0, seq) + "1×" + result.substring(seq, result.length());
                    } else if(result.substring(seq-1,seq).equals("(")) {

                    } else if(result.substring(seq-1,seq).equals("n")) {

                    } else if(result.substring(seq-1,seq).equals("s")) {

                    } else if(result.substring(seq-1,seq).equals("g")) {

                    } else if(result.substring(seq-1,seq).equals("h")) {

                    } else if(result.substring(seq-1,seq).equals("D")) {

                    } else if(result.substring(seq-1,seq).equals("S")) {

                    } else if(result.substring(seq-1,seq).equals("√")) {

                    } else if(result.substring(seq-1,seq).equals("π")) {

                    } else if(result.substring(seq-1,seq).equals("Ε")) {

                    } else if(result.substring(seq-1,seq).equals("＾")) {

                    } else if(result.substring(seq-1,seq).equals("a")) {

                    } else {
                        result = result.substring(0, seq) + "×" + result.substring(seq, result.length());
                    }
                }
            }
        }
        /////////////////////////tan
        //log,ln,π,Ε,√,sin,cos,tan,asin,acos,atan,sinh,cosh,tanh,
        for(int i = 0; i < result.length(); i ++) {
            int seq = result.indexOf("tan",i);

            if((seq != result.indexOf("tan",i+1)) && (seq != -1)) {
                if(seq == 0 ) {

                } else {
                    if(result.substring(seq-1,seq).equals("＋")) {

                    } else if(result.substring(seq-1,seq).equals("－")) {

                    } else if(result.substring(seq-1,seq).equals("×")) {

                    } else if(result.substring(seq-1,seq).equals("÷")) {

                    } else if(result.substring(seq-1,seq).equals("%")) {

                    } else if(result.substring(seq-1,seq).equals("-")) {
                        result = result.substring(0, seq) + "1×" + result.substring(seq, result.length());
                    } else if(result.substring(seq-1,seq).equals("(")) {

                    } else if(result.substring(seq-1,seq).equals("n")) {

                    } else if(result.substring(seq-1,seq).equals("s")) {

                    } else if(result.substring(seq-1,seq).equals("g")) {

                    } else if(result.substring(seq-1,seq).equals("h")) {

                    } else if(result.substring(seq-1,seq).equals("D")) {

                    } else if(result.substring(seq-1,seq).equals("S")) {

                    } else if(result.substring(seq-1,seq).equals("√")) {

                    } else if(result.substring(seq-1,seq).equals("π")) {

                    } else if(result.substring(seq-1,seq).equals("Ε")) {

                    } else if(result.substring(seq-1,seq).equals("＾")) {

                    } else if(result.substring(seq-1,seq).equals("a")) {

                    } else {
                        result = result.substring(0, seq) + "×" + result.substring(seq, result.length());
                    }
                }
            }
        }
        return result;
    }

    private  String getFactorial(String content) {

        if(content.indexOf("ABS") != -1) {
            content = getABS(content);
            Calculation.setText(Calculation.getText().toString() + "\n" + "ABS 치환 ↙ " + "\n" + content + "\n");
        }

        String getContent = content;
        String values = content;
        String getNum = null;

        Double one = 1.0;

        getContent = getContent + "★";

        StringTokenizer snum = new StringTokenizer(getContent, "＋－×÷＾%!★");
        StringTokenizer soper = new StringTokenizer(getContent, "1234567890-.sincotah√lgABCDEF");

        while(snum.hasMoreTokens()) {

            String num = snum.nextToken();
            String oper = soper.nextToken();

            if(oper.substring(0, 1).equals("!")) {
                if(num.indexOf("sinh") != -1) {
                    num = num.substring(4);
                } else if(num.indexOf("asin") != -1) {
                    num = num.substring(4);
                } else if(num.indexOf("sin") != -1) {
                    num = num.substring(3);
                } else if(num.indexOf("cosh") != -1) {
                    num = num.substring(4);
                } else if(num.indexOf("acos") != -1) {
                    num = num.substring(4);
                } else if(num.indexOf("cos") != -1) {
                    num = num.substring(3);
                } else if(num.indexOf("tanh") != -1) {
                    num = num.substring(4);
                } else if(num.indexOf("atan") != -1) {
                    num = num.substring(4);
                } else if(num.indexOf("tan") != -1) {
                    num = num.substring(3);
                } else if(num.indexOf("log") != -1) {
                    num = num.substring(3);
                } else if(num.indexOf("ln") != -1) {
                    num = num.substring(2);
                } else if(num.indexOf("√") != -1) {
                    num = num.substring(1);
                }
            }

            getNum = num + "!";

            if(oper.substring(0, 1).equals("!") && Double.parseDouble(num) != 0) {
                if(Double.parseDouble(num) > 0){
                    for(Double dnum = Double.parseDouble(num); dnum >= 1; dnum--) {
                        one *= dnum;

                    }

                    values = values.trim().replace(getNum, String.valueOf(one));

                    one = 1.0;
                } else if (Double.parseDouble(num) < 0) {
                    for(Double dnum = Double.parseDouble(num); dnum < 0; dnum++) {
                        one *= dnum;

                    }

                    values = values.trim().replace(getNum, String.valueOf(one));

                    one = 1.0;
                }
            } else if(oper.substring(0, 1).equals("!") && Double.parseDouble(num) == 0) {
                values = values.trim().replace(getNum, "1");
            }
        }

        return values;
    }

    private String getSquare(String content) {

        if(content.indexOf("ABS") != -1) {
            content = getABS(content);
            Calculation.setText(Calculation.getText().toString() + "\n" + "ABS 치환 ↙ " + "\n" + content + "\n");
        }

        String result = content;

        int run = 0;

        while(run != -12){
            run = content.indexOf("sin") + content.indexOf("asin") + content.indexOf("sinh") +
                    content.indexOf("cos") + content.indexOf("acos") + content.indexOf("cosh") +
                    content.indexOf("tan") + content.indexOf("atan") + content.indexOf("tanh") +
                    content.indexOf("log") + content.indexOf("ln") + content.indexOf("√");
            if(content.indexOf("sinh") != -1) {
                content = content.trim().replace("sinh", "");
            } else if(content.indexOf("asin") != -1) {
                content = content.trim().replace("asin", "");
            } else if(content.indexOf("sin") != -1) {
                content = content.trim().replace("sin", "");
            } else if(content.indexOf("cosh") != -1) {
                content = content.trim().replace("cosh", "");
            } else if(content.indexOf("acos") != -1) {
                content = content.trim().replace("acos", "");
            } else if(content.indexOf("cos") != -1) {
                content = content.trim().replace("cos", "");
            } else if(content.indexOf("tanh") != -1) {
                content = content.trim().replace("tanh", "");
            } else if(content.indexOf("atan") != -1) {
                content = content.trim().replace("atan", "");
            } else if(content.indexOf("tan") != -1) {
                content = content.trim().replace("tan", "");
            } else if(content.indexOf("log") != -1) {
                content = content.trim().replace("log", "");
            } else if(content.indexOf("ln") != -1) {
                content = content.trim().replace("ln", "");
            } else if(content.indexOf("√") != -1) {
                content = content.trim().replace("√", "");
            }
        }

        String val = null;
        Stack <String> stknum = new Stack<String>();

        StringTokenizer snum = new StringTokenizer(content, "＋－×÷%＾");
        StringTokenizer soper = new StringTokenizer(content, "1234567890-.ABCDEF");
        stknum.push(snum.nextToken());

        while(snum.hasMoreTokens()){

            String num = snum.nextToken();
            char oper = soper.nextToken().charAt(0);
            double res = 0;

            if(oper == '＾') {
                res = Double.parseDouble(stknum.pop());

                String fnum = String.valueOf(res);

                if(fnum.substring(fnum.indexOf("."), fnum.length()).equals(".0")){
                    fnum = String.format("%.0f", res);
                }

                val = fnum + "＾" + num;
                res = Math.pow(res,Double.parseDouble(num));

                result = result.trim().replace(val, String.valueOf(res));

            } else {
                stknum.push(num);
            }

        }
        stknum.clear();

        return result;
    }

    private static String getABS(String content) {
        String result = content;

        if(content.substring(0,1).equals("s")){
            content = content + "★";
        }else if(content.substring(0,1).equals("c")){
            content = content + "★";
        }else if(content.substring(0,1).equals("t")){
            content = content + "★";
        }else if(content.substring(0,1).equals("√")){
            content = content + "★";
        }else if(content.substring(0,1).equals("l")){
            content = content + "★";
        }else if(content.substring(0,1).equals("a")){
            content = content + "★";
        }else {
            content = "★" + content + "★";
        }

        StringTokenizer snum = new StringTokenizer(content, "＋－×÷%＾()abcdefghijklnmopqrstu√!＾★");
        StringTokenizer soper = new StringTokenizer(content, "1234567890-.ABS");

        while(snum.hasMoreTokens()) {
            String absNum = snum.nextToken();
            String absSoper = soper.nextToken();
            String num = null;

            if(absNum.indexOf("-ABS") != -1) {
                num = absNum.substring(4);
                result = result.trim().replace(absNum, "-"+String.valueOf(Math.abs(Double.parseDouble(num))));
            }else if(absNum.indexOf("ABS") != -1) {
                num = absNum.substring(3);
                result = result.trim().replace(absNum, String.valueOf(Math.abs(Double.parseDouble(num))));
            }

        }

        return result;
    }

    private static String replaceFst(String string, String toReplace, String replacement) {

        int pos = string.indexOf(toReplace);

        if (pos > -1) {

            return string.substring(0, pos)+ replacement + string.substring(pos + toReplace.length(), string.length());

        } else {

            return string;

        }

    }

}