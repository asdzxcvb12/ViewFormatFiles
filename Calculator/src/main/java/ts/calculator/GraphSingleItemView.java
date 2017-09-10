package ts.calculator;

/**
 * Created by user on 2017-02-21.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.handstudio.android.hzgrapherlib.animation.GraphAnimation;
import com.handstudio.android.hzgrapherlib.graphview.CurveGraphView;
import com.handstudio.android.hzgrapherlib.vo.GraphNameBox;
import com.handstudio.android.hzgrapherlib.vo.curvegraph.CurveGraph;
import com.handstudio.android.hzgrapherlib.vo.curvegraph.CurveGraphVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class GraphSingleItemView extends Activity {

    int maxX, maxY;
    String g1, g2, g3;
    String graph1Val, graph2Val, graph3Val;
    RelativeLayout singlegraph;

    boolean isGraph1 = false, isGraph2 = false, isGraph3 = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphsingleitemview);

        singlegraph = (RelativeLayout) findViewById(R.id.singlegraph);
        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();
        // Get the results of rank
        //graphimg = i.getStringExtra("graphimg");
        // Get the results of country
        maxX = Integer.parseInt(i.getStringExtra("maxX"));
        maxY = Integer.parseInt(i.getStringExtra("maxY"));
        // Get the results of population
        g1 = i.getStringExtra("graph1");
        g2 = i.getStringExtra("graph2");
        g3 = i.getStringExtra("graph3");

        graph1Val = i.getStringExtra("graph1Val");
        graph2Val = i.getStringExtra("graph2Val");
        graph3Val = i.getStringExtra("graph3Val");

        if(!g1.equals("null")) {
            isGraph1 = true;
        }
        if(!g2.equals("null")) {
            isGraph2 = true;
        }
        if(!g3.equals("null")) {
            isGraph3 = true;
        }

        Log.d("ggg", "g1 : " + g1);
        Log.d("ggg", "g2 : " + g2);
        Log.d("ggg", "g3 : " + g3);

        setCurveGraph();
    }

    private void setCurveGraph() {
        //all setting
        CurveGraphVO vo = makeCurveGraphAllSetting();
        //default setting
//		CurveGraphVO vo = makeCurveGraphDefaultSetting();
        singlegraph.addView(new CurveGraphView(this, vo), 0);

    }

    private CurveGraphVO makeCurveGraphAllSetting() {
        int maxVal = maxX; // <--- x축 값 받아와야됨

        String[] legendArr = new String[(maxVal*2)+1];
        int minVal = maxVal * -1;

        //BASIC LAYOUT SETTING
        for(int i = 0; i < legendArr.length; i++) {
            legendArr[i] = String.valueOf(minVal);
            minVal++;
        }


        float[] graph1 = new float[(maxVal * 2) + 1];

        if(isGraph1) {
            for (int i = 0; i < graph1.length; i++) {
                String xG1 = graph1Val;
                xG1 = sctCount(xG1);
                graph1[i] = Float.parseFloat(cal(xG1.trim().replaceAll("ｘ", legendArr[i])));
            }
        }

        float[] graph2 = new float[(maxVal * 2) + 1];

        if(isGraph2) {
            for (int i = 0; i < graph2.length; i++) {
                String xG2 = graph2Val;
                xG2 = sctCount(xG2);
                graph2[i] = Float.parseFloat(cal(xG2.trim().replaceAll("ｘ", legendArr[i])));
            }
        }


        float[] graph3 = new float[(maxVal * 2) + 1];

        if(isGraph3) {
            for (int i = 0; i < graph3.length; i++) {
                String xG3 = graph3Val;
                xG3 = sctCount(xG3);
                graph3[i] = Float.parseFloat(cal(xG3.trim().replaceAll("ｘ", legendArr[i])));
            }
        }
        //padding
        int paddingBottom 	= CurveGraphVO.DEFAULT_PADDING;
        int paddingTop 		= CurveGraphVO.DEFAULT_PADDING;
        int paddingLeft 	= CurveGraphVO.DEFAULT_PADDING;
        int paddingRight 	= CurveGraphVO.DEFAULT_PADDING;

        //graph margin
        int marginTop 		= CurveGraphVO.DEFAULT_MARGIN_TOP;
        int marginRight 	= CurveGraphVO.DEFAULT_MARGIN_RIGHT;
        //max value
        int maxValue 		= maxY;

        //increment
        int increment 		= 1;
        //CurveGraphVO.DEFAULT_INCREMENT;

        //GRAPH SETTING


        List<CurveGraph> arrGraph = new ArrayList<CurveGraph>();
        if(isGraph1) {
            arrGraph.add(new CurveGraph(g1, 0xaa66ff33, graph1));
        }
        if(isGraph2) {
            arrGraph.add(new CurveGraph(g2, 0xaa00ffff, graph2));
        }
        if(isGraph3) {
            arrGraph.add(new CurveGraph(g3, 0xaaff0066, graph3));
        }

        CurveGraphVO vo = new CurveGraphVO(
                paddingBottom, paddingTop, paddingLeft, paddingRight,
                marginTop, marginRight, maxValue, increment, legendArr, arrGraph);

        //set animation
        vo.setAnimation(new GraphAnimation(GraphAnimation.LINEAR_ANIMATION, GraphAnimation.DEFAULT_DURATION));

        vo.setAni(true);
        vo.setGraphNameBox(new GraphNameBox());
        //set graph name box

        //vo.setGraphNameBox(new GraphNameBox());
        //set draw graph region
//		vo.setDrawRegion(true);

        //use icon
//		arrGraph.add(new Graph(0xaa66ff33, graph1, R.drawable.icon1));
//		arrGraph.add(new Graph(0xaa00ffff, graph2, R.drawable.icon2));
//		arrGraph.add(new Graph(0xaaff0066, graph3, R.drawable.icon3));

//		CurveGraphVO vo = new CurveGraphVO(
//				paddingBottom, paddingTop, paddingLeft, paddingRight,
//				marginTop, marginRight, maxValue, increment, legendArr, arrGraph, R.drawable.bg);
        return vo;
    }

    public String cal(String content) {

        String last = "";
        try{
            String test = null, front = null, getnum = content;

            int leftnum, t = 0, start, end;

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

                String result = getCalculate(test);

                if (start != 0) {
                    front = getnum.substring(start - 1, start);
                    if (front.equals("＋")) {

                    } else if (front.equals("－")) {

                    } else if (front.equals("×")) {

                    } else if (front.equals("÷")) {

                    } else if (front.equals("-")) {
                        result = "1×" + result;
                    } else if (front.equals("(")) {

                    } else if (front.equals("＾")) {

                    } else {
                        result = "×" + result;
                    }
                }

                getnum = replaceFst(getnum,
                        String.valueOf(
                                getnum.substring(start, end)), result);
                t--;
            }

            last = getCalculate(getnum);

        } catch(Exception e) {
            Toast.makeText(getApplicationContext(), "잘못된 수식입니다.", Toast.LENGTH_SHORT).show();
        }

        return last;
    }

    private String getCalculate(String content) {


        double i = 0;
        String result = content;
        //sin23+cos2!+2!
        int chkQ;

        chkQ = result.indexOf("＾");

        if(chkQ != -1) {
            result = getSquare(result);
        }

        Stack<String> stknum = new Stack<String>();

        StringTokenizer snum = new StringTokenizer(result, "＋－×÷");
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

    private static String sctCount(String content) {
        String result = content;

        ///////////////////////ｘ
        for(int i = 0; i < result.length(); i ++) {
            int seq = result.indexOf("ｘ",i);

            if((seq != result.indexOf("ｘ",i+1)) && (seq != -1)) {
                if(seq == 0 ) {

                } else {
                    if(result.substring(seq-1,seq).equals("＋")) {

                    } else if(result.substring(seq-1,seq).equals("－")) {

                    } else if(result.substring(seq-1,seq).equals("×")) {

                    } else if(result.substring(seq-1,seq).equals("÷")) {

                    } else if(result.substring(seq-1,seq).equals("-")) {
                        result = result.substring(0, seq) + "1×" + result.substring(seq, result.length());
                    } else if(result.substring(seq-1,seq).equals("(")) {

                    } else if(result.substring(seq-1,seq).equals("＾")) {

                    } else {
                        result = result.substring(0, seq) + "×" + result.substring(seq, result.length());
                    }
                }
            }
        }

        return result;
    }

    private String getSquare(String content) {

        String result = content;

        int run = 0;

        String val = null;
        Stack <String> stknum = new Stack<String>();

        StringTokenizer snum = new StringTokenizer(content, "＋－×÷＾");
        StringTokenizer soper = new StringTokenizer(content, "1234567890-.");
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

    private static String replaceFst(String string, String toReplace, String replacement) {

        int pos = string.indexOf(toReplace);

        if (pos > -1) {

            return string.substring(0, pos)+ replacement + string.substring(pos + toReplace.length(), string.length());

        } else {

            return string;

        }

    }
}