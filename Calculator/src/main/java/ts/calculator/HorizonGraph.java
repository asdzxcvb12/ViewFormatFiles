package ts.calculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.RelativeLayout;

import com.handstudio.android.hzgrapherlib.animation.GraphAnimation;
import com.handstudio.android.hzgrapherlib.graphview.CurveGraphView;
import com.handstudio.android.hzgrapherlib.vo.GraphNameBox;
import com.handstudio.android.hzgrapherlib.vo.curvegraph.CurveGraph;
import com.handstudio.android.hzgrapherlib.vo.curvegraph.CurveGraphVO;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

import android.widget.Button;
import android.widget.ImageView;

public class HorizonGraph extends AppCompatActivity {
    Button saveGraph;

    int maxX, maxY;
    String graph1Name, graph2Name, graph3Name;
    String graph1Value, graph2Value, graph3Value;
    boolean isGraph1 = false, isGraph2 = false, isGraph3 = false;
    private ViewGroup cap;
    RelativeLayout horizonGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizon_graph);

        Intent getValue = getIntent();

        saveGraph = (Button) findViewById(R.id.saveGraph);
        cap = (ViewGroup) findViewById(R.id.cap);
        horizonGraph = (RelativeLayout) findViewById(R.id.horizonGraph);

        maxX = Integer.parseInt(getValue.getStringExtra("maxX"));
        maxY = Integer.parseInt(getValue.getStringExtra("maxY"));

        if(getValue.hasExtra("graph1name")){
            graph1Name = getValue.getStringExtra("graph1name");
            graph1Value = getValue.getStringExtra("graph1value");
            isGraph1 = true;
        }
        if(getValue.hasExtra("graph2name")){
            graph2Name = getValue.getStringExtra("graph2name");
            graph2Value = getValue.getStringExtra("graph2value");
            isGraph2 = true;
        }
        if(getValue.hasExtra("graph3name")){
            graph3Name = getValue.getStringExtra("graph3name");
            graph3Value = getValue.getStringExtra("graph3value");
            isGraph3 = true;
        }
        setCurveGraph();
    }

    public void saveData(View v) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("저장 될 이름을 지정해 주십시오.");

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                try {

                    /*
                    CurveGraphVO vo = makeCurveGraphAllSetting();
                    //Bitmap trans = viewToBitmap(new CurveGraphView(getApplicationContext(), vo));

                    View trans = new CurveGraphView(getApplicationContext(), vo);

                    //View va = getWindow().getDecorView();

                    trans.setDrawingCacheEnabled(true);
                    Bitmap t = viewToBitmap(trans);
                    //Bitmap captureView = Bitmap.createBitmap(va.getRootView().getMeasuredWidth(),
                     //       trans.getRootView().getMeasuredHeight(), Bitmap.Config.ARGB_8888);

                    //Canvas screenShotCanvas = new Canvas(captureView);

                  //  SurfaceView surfaceView = (SurfaceView) a;
                  //  surfaceView.setZOrderOnTop(true);
                    //trans.draw(screenShotCanvas);
                  //  surfaceView.setZOrderOnTop(false);

                    ByteArrayOutputStream bao = new ByteArrayOutputStream();
                    t.compress(Bitmap.CompressFormat.JPEG, 100, bao);

                    byte[] ba = bao.toByteArray();



                    String img = Base64.encodeToString(ba, Base64.DEFAULT);

*/
                    final GraphDBManager GraphDBManager = new GraphDBManager(getApplicationContext(), "Graph.db", null, 1);
                    String name = input.getText().toString();
                    String putMaxX = String.valueOf(maxX), putMaxY = String.valueOf(maxY);
                    String graph1 = "null", graph2 = "null", graph3 = "null";
                    String graph1Val = "null", graph2Val = "null", graph3Val = "null";

                    if (isGraph1) {
                        graph1 = graph1Name;
                        graph1Val = graph1Value;
                    }

                    if (isGraph2) {
                        graph2 = graph2Name;
                        graph2Val = graph2Value;
                    }

                    if (isGraph3) {
                        graph3 = graph3Name;
                        graph3Val = graph3Value;
                    }

                    Log.d("홬", "graph1 : "+graph1);
                    Log.d("홬", "graph2 : "+graph2);
                    Log.d("홬", "graph3 : "+graph3);

                    Log.d("홬", "graph1Val : "+graph1Val);
                    Log.d("홬", "graph2Val : "+graph2Val);
                    Log.d("홬", "graph3Val : "+graph3Val);

                    GraphDBManager.insert("insert into Graph values(null, '" + putMaxX + "', '" + putMaxY + "', '" + name + "', '" + graph1 + "', '" + graph2 + "', '" + graph3 + "', '" + graph1Val + "', '" + graph2Val + "', '" + graph3Val + "');");

                    Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
                    // Do something with value!
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "에러.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        alert.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

        alert.show();
    }

    private void setCurveGraph() {
        //all setting
        CurveGraphVO vo = makeCurveGraphAllSetting();
        //default setting
//		CurveGraphVO vo = makeCurveGraphDefaultSetting();
        horizonGraph.addView(new CurveGraphView(this, vo), 0);

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
                String xG1 = graph1Value;
                xG1 = sctCount(xG1);
                graph1[i] = Float.parseFloat(cal(xG1.trim().replaceAll("ｘ", legendArr[i])));
            }
        }

        float[] graph2 = new float[(maxVal * 2) + 1];

        if(isGraph2) {
            for (int i = 0; i < graph2.length; i++) {
                String xG2 = graph2Value;
                xG2 = sctCount(xG2);
                graph2[i] = Float.parseFloat(cal(xG2.trim().replaceAll("ｘ", legendArr[i])));
            }
        }


        float[] graph3 = new float[(maxVal * 2) + 1];

        if(isGraph3) {
            for (int i = 0; i < graph3.length; i++) {
                String xG3 = graph3Value;
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
            arrGraph.add(new CurveGraph(graph1Name, 0xaa66ff33, graph1));
        }
        if(isGraph2) {
            arrGraph.add(new CurveGraph(graph2Name, 0xaa00ffff, graph2));
        }
        if(isGraph3) {
            arrGraph.add(new CurveGraph(graph3Name, 0xaaff0066, graph3));
        }

        CurveGraphVO vo = new CurveGraphVO(
                paddingBottom, paddingTop, paddingLeft, paddingRight,
                marginTop, marginRight, maxValue, increment, legendArr, arrGraph);

        //set animation
        vo.setAni(true);
        vo.setAnimation(new GraphAnimation(GraphAnimation.LINEAR_ANIMATION, GraphAnimation.DEFAULT_DURATION));
        //set graph name box
        vo.setGraphNameBox(new GraphNameBox());
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
/*
    public void touch(View v) {
        try{
            CurveGraphVO vo = makeCurveGraphAllSetting();
            Bitmap a = viewToBitmap(new CurveGraphView(this, vo));
            Toast.makeText(getApplicationContext(), "ssss",Toast.LENGTH_SHORT).show();

        }catch(Exception e) {
            Toast.makeText(getApplicationContext(), "ddd",Toast.LENGTH_SHORT).show();
        }
    }

    public static Bitmap viewToBitmap(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        if (view instanceof SurfaceView) {
            SurfaceView surfaceView = (SurfaceView) view;
            surfaceView.setZOrderOnTop(true);
            surfaceView.draw(canvas);
            surfaceView.setZOrderOnTop(false);
            return bitmap;
        } else {

//For ViewGroup & View
            view.draw(canvas);
            return bitmap;
        }
    }
    */
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

