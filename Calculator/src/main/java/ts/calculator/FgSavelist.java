package ts.calculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by user on 2017-02-11.
 */

@SuppressLint("ValidFragment")
public class FgSavelist extends Fragment {
    Context mContext;
    View view;

    CalListViewAdapter CalAdapter;
    EditText caleditsearch;
    ArrayList<String> CalSaveName = new ArrayList<>();
    ArrayList<String> CalculationResult = new ArrayList<>();
    ArrayList<String> Calculation = new ArrayList<>();
    ListView Callist;

    GraphListViewAdapter GraphAdapter;
    EditText grapheditsearch;
    ArrayList<String> maxX  = new ArrayList<>();
    ArrayList<String> maxY  = new ArrayList<>();
    ArrayList<String> GraphSaveName  = new ArrayList<>();
    ArrayList<String> Graph1 = new ArrayList<>();
    ArrayList<String> Graph2 = new ArrayList<>();
    ArrayList<String> Graph3 = new ArrayList<>();
    ArrayList<String> Graph1Val = new ArrayList<>();
    ArrayList<String> Graph2Val = new ArrayList<>();
    ArrayList<String> Graph3Val = new ArrayList<>();
    ListView Graphlist;

    ArrayList<CalculatorSaveList> CalArraylist = new ArrayList<CalculatorSaveList>();
    ArrayList<GraphSaveList> GraphArraylist = new ArrayList<GraphSaveList>();

    public FgSavelist(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fgsavelist, null);

        final CalDBManager CalDBManager = new CalDBManager(view.getContext(), "Calculator.db", null, 1);
        final GraphDBManager GraphDBManager = new GraphDBManager(view.getContext(), "Graph.db", null, 1);

        caleditsearch = (EditText) view.findViewById(R.id.calsearch);

        grapheditsearch = (EditText) view.findViewById(R.id.graphsearch);

        for(int i=0; i < CalDBManager.nameArrayList().size(); i++) {
            CalSaveName.add(CalDBManager.nameArrayList().get(i));
        }

        for(int i=0; i < CalDBManager.resultArrayList().size(); i++) {
            CalculationResult.add(CalDBManager.resultArrayList().get(i));
        }

        for(int i=0; i < CalDBManager.calculationArrayList().size(); i++) {
            Calculation.add(CalDBManager.calculationArrayList().get(i));
        }

        Callist = (ListView) view.findViewById(R.id.CalListView);

        if(!CalSaveName.isEmpty()) {
            if(CalArraylist.isEmpty()) {
                for (int i = 0; i < CalSaveName.size(); i++) {
                    CalculatorSaveList cs = new CalculatorSaveList(CalSaveName.get(i), CalculationResult.get(i),
                            Calculation.get(i));
                    // Binds all strings into an array
                    CalArraylist.add(cs);
                }
            }
        }

        CalAdapter = new CalListViewAdapter(view.getContext(), CalArraylist);

        Callist.setAdapter(CalAdapter);

        caleditsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = caleditsearch.getText().toString().toLowerCase(Locale.getDefault());
                CalAdapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });


        for(int i=0; i < GraphDBManager.maxXList().size(); i++) {
            maxX.add(GraphDBManager.maxXList().get(i));
        }

        for(int i=0; i < GraphDBManager.maxYList().size(); i++) {
            maxY.add(GraphDBManager.maxYList().get(i));
        }

        for(int i=0; i < GraphDBManager.nameArrayList().size(); i++) {
            GraphSaveName.add(GraphDBManager.nameArrayList().get(i));
        }

        for(int i=0; i < GraphDBManager.graph1ArrayList().size(); i++) {
            Graph1.add(GraphDBManager.graph1ArrayList().get(i));
        }

        for(int i=0; i < GraphDBManager.graph2ArrayList().size(); i++) {
            Graph2.add(GraphDBManager.graph2ArrayList().get(i));
        }

        for(int i=0; i < GraphDBManager.graph3ArrayList().size(); i++) {
            Graph3.add(GraphDBManager.graph3ArrayList().get(i));
        }

        for(int i=0; i < GraphDBManager.graph1ValArrayList().size(); i++) {
            Graph1Val.add(GraphDBManager.graph1ValArrayList().get(i));
        }

        for(int i=0; i < GraphDBManager.graph2ValArrayList().size(); i++) {
            Graph2Val.add(GraphDBManager.graph2ValArrayList().get(i));
        }

        for(int i=0; i < GraphDBManager.graph3ValArrayList().size(); i++) {
            Graph3Val.add(GraphDBManager.graph3ValArrayList().get(i));
        }

        Graphlist = (ListView) view.findViewById(R.id.GraphListView);
        if(!GraphSaveName.isEmpty()) {
            if(GraphArraylist.isEmpty()) {
                for (int i = 0; i < GraphSaveName.size(); i++) {
                    GraphSaveList gs = new GraphSaveList(maxX.get(i), maxY.get(i), GraphSaveName.get(i),
                            Graph1.get(i), Graph2.get(i), Graph3.get(i), Graph1Val.get(i), Graph2Val.get(i), Graph3Val.get(i));
                    // Binds all strings into an array

                    GraphArraylist.add(gs);
                }
            }
        }

        GraphAdapter = new GraphListViewAdapter(view.getContext(), GraphArraylist);

        Graphlist.setAdapter(GraphAdapter);

        grapheditsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = grapheditsearch.getText().toString().toLowerCase(Locale.getDefault());
                GraphAdapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });

        return view;
    }

    public void onStart() {
        super.onStart();
        Log.d("확인확인", "3호출?");

        CalSaveName.clear();
        CalculationResult.clear();
        Calculation.clear();
        CalArraylist.clear();

        final CalDBManager CalDBManager = new CalDBManager(view.getContext(), "Calculator.db", null, 1);

        for(int i=0; i < CalDBManager.nameArrayList().size(); i++) {
            CalSaveName.add(CalDBManager.nameArrayList().get(i));
        }

        for(int i=0; i < CalDBManager.resultArrayList().size(); i++) {
            CalculationResult.add(CalDBManager.resultArrayList().get(i));
        }

        for(int i=0; i < CalDBManager.calculationArrayList().size(); i++) {
            Calculation.add(CalDBManager.calculationArrayList().get(i));
        }

        Callist = (ListView) view.findViewById(R.id.CalListView);

        if(!CalSaveName.isEmpty()) {
            if(CalArraylist.isEmpty()) {
                for (int i = 0; i < CalSaveName.size(); i++) {
                    CalculatorSaveList cs = new CalculatorSaveList(CalSaveName.get(i), CalculationResult.get(i),
                            Calculation.get(i));
                    // Binds all strings into an array
                    CalArraylist.add(cs);
                }
            }
        }
        CalAdapter = new CalListViewAdapter(view.getContext(), CalArraylist);

        CalAdapter.notifyDataSetChanged();


        maxX.clear();
        maxY.clear();
        GraphSaveName.clear();
        Graph1.clear();
        Graph2.clear();
        Graph3.clear();
        Graph1Val.clear();
        Graph2Val.clear();
        Graph3Val.clear();
        GraphArraylist.clear();

        final GraphDBManager GraphDBManager = new GraphDBManager(view.getContext(), "Graph.db", null, 1);


        for(int i=0; i < GraphDBManager.maxXList().size(); i++) {
            maxX.add(GraphDBManager.maxXList().get(i));
        }

        for(int i=0; i < GraphDBManager.maxYList().size(); i++) {
            maxY.add(GraphDBManager.maxYList().get(i));
        }

        for(int i=0; i < GraphDBManager.nameArrayList().size(); i++) {
            GraphSaveName.add(GraphDBManager.nameArrayList().get(i));
        }

        for(int i=0; i < GraphDBManager.graph1ArrayList().size(); i++) {
            Graph1.add(GraphDBManager.graph1ArrayList().get(i));
        }

        for(int i=0; i < GraphDBManager.graph2ArrayList().size(); i++) {
            Graph2.add(GraphDBManager.graph2ArrayList().get(i));
        }

        for(int i=0; i < GraphDBManager.graph3ArrayList().size(); i++) {
            Graph3.add(GraphDBManager.graph3ArrayList().get(i));
        }

        for(int i=0; i < GraphDBManager.graph1ValArrayList().size(); i++) {
            Graph1Val.add(GraphDBManager.graph1ValArrayList().get(i));
        }

        for(int i=0; i < GraphDBManager.graph2ValArrayList().size(); i++) {
            Graph2Val.add(GraphDBManager.graph2ValArrayList().get(i));
        }

        for(int i=0; i < GraphDBManager.graph3ValArrayList().size(); i++) {
            Graph3Val.add(GraphDBManager.graph3ValArrayList().get(i));
        }

        Graphlist = (ListView) view.findViewById(R.id.GraphListView);
        if(!GraphSaveName.isEmpty()) {
            if(GraphArraylist.isEmpty()) {
                for (int i = 0; i < GraphSaveName.size(); i++) {
                    GraphSaveList gs = new GraphSaveList(maxX.get(i), maxY.get(i), GraphSaveName.get(i),
                            Graph1.get(i), Graph2.get(i), Graph3.get(i), Graph1Val.get(i), Graph2Val.get(i), Graph3Val.get(i));
                    // Binds all strings into an array

                    GraphArraylist.add(gs);
                }
            }
        }

        GraphAdapter = new GraphListViewAdapter(view.getContext(), GraphArraylist);

        GraphAdapter.notifyDataSetChanged();
    }

}