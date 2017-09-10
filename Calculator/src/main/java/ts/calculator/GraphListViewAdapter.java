package ts.calculator;

/**
 * Created by user on 2017-02-21.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.handstudio.android.hzgrapherlib.animation.GraphAnimation;
import com.handstudio.android.hzgrapherlib.graphview.CurveGraphView;
import com.handstudio.android.hzgrapherlib.vo.Graph;
import com.handstudio.android.hzgrapherlib.vo.GraphNameBox;
import com.handstudio.android.hzgrapherlib.vo.curvegraph.CurveGraph;
import com.handstudio.android.hzgrapherlib.vo.curvegraph.CurveGraphVO;

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Stack;
import java.util.StringTokenizer;

public class GraphListViewAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    private List<GraphSaveList> graphsavelist = null;
    private ArrayList<GraphSaveList> GraphArraylist;

    int X, Y;

    String g1, g2, g3;
    String g1val ,g2val, g3val;
    boolean isGraph1 = false, isGraph2 = false, isGraph3 = false;

    public GraphListViewAdapter(Context context, List<GraphSaveList> graphsavelist) {
        mContext = context;
        this.graphsavelist = graphsavelist;
        inflater = LayoutInflater.from(mContext);
        this.GraphArraylist = new ArrayList<GraphSaveList>();
        this.GraphArraylist.addAll(graphsavelist);
    }

    public class ViewHolder {
        RelativeLayout graphimg;
        TextView maxX;
        TextView maxY;
        TextView graphsavename;
        TextView graph1;
        TextView graph2;
        TextView graph3;
        TextView graph1Val;
        TextView graph2Val;
        TextView graph3Val;
    }

    @Override
    public int getCount() {
        return graphsavelist.size();
    }

    @Override
    public GraphSaveList getItem(int position) {
        return graphsavelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.graphlistview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.graphimg = (RelativeLayout) view.findViewById(R.id.graphimg);
            holder.maxX = (TextView) view.findViewById(R.id.maxX);
            holder.maxY = (TextView) view.findViewById(R.id.maxY);
            holder.graphsavename = (TextView) view.findViewById(R.id.gname);
            holder.graph1 = (TextView) view.findViewById(R.id.graph1);
            holder.graph2 = (TextView) view.findViewById(R.id.graph2);
            holder.graph3 = (TextView) view.findViewById(R.id.graph3);
            holder.graph1Val = (TextView) view.findViewById(R.id.graph1Val);
            holder.graph2Val = (TextView) view.findViewById(R.id.graph2Val);
            holder.graph3Val = (TextView) view.findViewById(R.id.graph3Val);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        //test


        X = Integer.parseInt(graphsavelist.get(position).getMaxX());
        Y = Integer.parseInt(graphsavelist.get(position).getMaxY());
        g1 = graphsavelist.get(position).getGraph1();
        g2 = graphsavelist.get(position).getGraph2();
        g3 = graphsavelist.get(position).getGraph3();
        g1val = graphsavelist.get(position).getGraph1Val();
        g2val = graphsavelist.get(position).getGraph2Val();
        g3val = graphsavelist.get(position).getGraph3Val();

        if(!g1.equals("null")) {
            isGraph1 = true;
        }

        if(!g2.equals("null")) {
            isGraph2 = true;
        }

        if(!g3.equals("null")) {
            isGraph3 = true;
        }


        Log.d("하", "graph1 : "+g1);
        Log.d("하", "graph2 : "+g2);
        Log.d("하", "graph3 : "+g3);

        Log.d("하", "graph1Val : "+g1val);
        Log.d("하", "graph2Val : "+g2val);
        Log.d("하", "graph3Val : "+g3val);

        Log.d("하", "isGraph1 : "+isGraph1);
        Log.d("하", "isGraph2 : "+isGraph2);
        Log.d("하", "isGraph3 : "+isGraph3);


        CurveGraphVO vo = makeCurveGraphAllSetting();
        holder.graphimg.addView(new CurveGraphView(inflater.getContext(), vo), 0);
        holder.maxX.setText(graphsavelist.get(position).getMaxX());
        holder.maxY.setText(graphsavelist.get(position).getMaxY());
        holder.graphsavename.setText(graphsavelist.get(position).getGraphName());
        holder.graph1.setText(graphsavelist.get(position).getGraph1());
        holder.graph2.setText(graphsavelist.get(position).getGraph2());
        holder.graph3.setText(graphsavelist.get(position).getGraph3());
        holder.graph1Val.setText(graphsavelist.get(position).getGraph1Val());
        holder.graph2Val.setText(graphsavelist.get(position).getGraph2Val());
        holder.graph3Val.setText(graphsavelist.get(position).getGraph3Val());

        isGraph1 = false;
        isGraph2 = false;
        isGraph3 = false;

        // Listen for ListView Item Click
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                AlertDialog.Builder alert = new AlertDialog.Builder(inflater.getContext());

                alert.setTitle("항목을 선택해 주십시오.");

                // Set an EditText view to get user input



                alert.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {



                        // Do something with value!
                    }
                });

                alert.setNeutralButton("삭제",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                final GraphDBManager GraphDBManager = new GraphDBManager(inflater.getContext(), "Graph.db", null, 1);
                                GraphDBManager.delete("delete from GRAPH where name = '" + graphsavelist.get( position).getGraphName() + "';");

                                int getPosition = 0;

                                for(int i = 0; i < GraphArraylist.size(); i++) {
                                    if(GraphArraylist.get(i).equals(graphsavelist.get( position).getGraphName())) {
                                        getPosition = i;
                                    }
                                }
                                graphsavelist.remove(graphsavelist.get(position));
                                GraphArraylist.remove(GraphArraylist.get(getPosition));
                                notifyDataSetChanged();
                            }
                        });


                alert.setNegativeButton("자세히",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Canceled.
                                Intent singleView = new Intent(mContext, GraphSingleItemView.class);
                                singleView.putExtra("maxX", graphsavelist.get(position).getMaxX());
                                singleView.putExtra("maxY", graphsavelist.get(position).getMaxY());
                                singleView.putExtra("graph1", graphsavelist.get(position).getGraph1());
                                singleView.putExtra("graph2", graphsavelist.get(position).getGraph2());
                                singleView.putExtra("graph3", graphsavelist.get(position).getGraph3());
                                singleView.putExtra("graph1Val", graphsavelist.get(position).getGraph1Val());
                                singleView.putExtra("graph2Val", graphsavelist.get(position).getGraph2Val());
                                singleView.putExtra("graph3Val", graphsavelist.get(position).getGraph3Val());
                                mContext.startActivity(singleView);
                            }
                        });

                alert.show();
                /*
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, GraphSingleItemView.class);
                // Pass all data rank
                //intent.putExtra("graphimg",(graphsavelist.get(position).getGraphImg()));
                // Pass all data country
                intent.putExtra("name",(graphsavelist.get(position).getGraphName()));
                // Pass all data population
                intent.putExtra("graph1",(graphsavelist.get(position).getGraph1()));

                intent.putExtra("graph2",(graphsavelist.get(position).getGraph2()));

                intent.putExtra("graph3",(graphsavelist.get(position).getGraph3()));
                // Pass all data flag
                // Start SingleItemView Class
                mContext.startActivity(intent);
                */
            }
        });

        return view;
    }

    private CurveGraphVO makeCurveGraphAllSetting() {
        int maxVal = X; // <--- x축 값 받아와야됨

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
                String xG1 = g1val;
                xG1 = sctCount(xG1);
                graph1[i] = Float.parseFloat(cal(xG1.trim().replaceAll("ｘ", legendArr[i])));
            }
        }

        float[] graph2 = new float[(maxVal * 2) + 1];

        if(isGraph2) {
            for (int i = 0; i < graph2.length; i++) {
                String xG2 = g2val;
                xG2 = sctCount(xG2);
                graph2[i] = Float.parseFloat(cal(xG2.trim().replaceAll("ｘ", legendArr[i])));
            }
        }


        float[] graph3 = new float[(maxVal * 2) + 1];

        if(isGraph3) {
            for (int i = 0; i < graph3.length; i++) {
                String xG3 = g3val;
                xG3 = sctCount(xG3);
                graph3[i] = Float.parseFloat(cal(xG3.trim().replaceAll("ｘ", legendArr[i])));
            }
        }
        //padding
        int paddingBottom 	= 0;
        int paddingTop 		= 0;
        int paddingLeft 	= 0;
        int paddingRight 	= 0;

        //graph margin
        int marginTop 		= 0;
        int marginRight 	= 0;

        //max value
        int maxValue 		= Y;

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

        vo.setAni(false);

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

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        graphsavelist.clear();
        if (charText.length() == 0) {
            graphsavelist.addAll(GraphArraylist);
        }
        else
        {
            for (GraphSaveList gs : GraphArraylist)
            {
                if (gs.getGraphName().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    graphsavelist.add(gs);
                }
            }
        }
        notifyDataSetChanged();
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
            Toast.makeText(inflater.getContext(), "잘못된 수식입니다.", Toast.LENGTH_SHORT).show();
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
