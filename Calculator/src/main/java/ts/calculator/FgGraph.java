package ts.calculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.handstudio.android.hzgrapherlib.animation.GraphAnimation;
import com.handstudio.android.hzgrapherlib.graphview.CurveGraphView;
import com.handstudio.android.hzgrapherlib.vo.GraphNameBox;
import com.handstudio.android.hzgrapherlib.vo.curvegraph.CurveGraph;
import com.handstudio.android.hzgrapherlib.vo.curvegraph.CurveGraphVO;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by user on 2017-02-11.
 */

@SuppressLint("ValidFragment")
public class FgGraph extends Fragment {
    Context mContext;
    View view;
    TextView MaxX, MaxY, GraphOne, GraphTwo, GraphThree, maxXval, maxYval, tv_g1N, tv_g2N, tv_g3N, g1Val, g2Val, g3Val;
    RadioButton radioValue, radioGraph;
    CheckBox xyDfault, cbG1, cbG2, cbG3;
    Button saveGraph, horizon, btnX, btnY, g1Name, g2Name, g3Name, g1CV, g2CV, g3CV;
    boolean isGraph = false;

    int btnSeq = 0;


    private static final int ID_CALCULAR = 0;
    private Dialog dlg;
    private EditText DigText;
    private Button[] numButtons = new Button[20];
    private Integer[] numBtnIDs = { R.id.DigOne, R.id.DigTwo, R.id.DigThree, R.id.DigFour, R.id.DigFive, R.id.DigSix, R.id.DigSeven, R.id.DigEight, R.id.DigNine, R.id.DigZero,
            R.id.DigAdd, R.id.DigSub, R.id.DigMul, R.id.DigDiv, R.id.DigMinus, R.id.DigComma, R.id.DigX, R.id.DigSqur, R.id.DigLeft, R.id.DigRight};

    private int i;


    private ViewGroup layoutGraphView, layoutValueView;

    public FgGraph(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fggraph, null);

        MaxX = (TextView) view.findViewById(R.id.MaxX);
        MaxY = (TextView) view.findViewById(R.id.MaxY);
        GraphOne = (TextView) view.findViewById(R.id.GraphOne);
        GraphTwo = (TextView) view.findViewById(R.id.GraphTwo);
        GraphThree = (TextView) view.findViewById(R.id.GraphThree);
        maxXval = (TextView) view.findViewById(R.id.maxXval);
        maxYval = (TextView) view.findViewById(R.id.maxYval);
        tv_g1N = (TextView) view.findViewById(R.id.tv_g1N);
        tv_g2N = (TextView) view.findViewById(R.id.tv_g2N);
        tv_g3N = (TextView) view.findViewById(R.id.tv_g3N);
        g1Val = (TextView) view.findViewById(R.id.g1Val);
        g2Val = (TextView) view.findViewById(R.id.g2Val);
        g3Val = (TextView) view.findViewById(R.id.g3Val);

        GraphOne.setVisibility(View.INVISIBLE);
        GraphTwo.setVisibility(View.INVISIBLE);
        GraphThree.setVisibility(View.INVISIBLE);

        saveGraph = (Button) view.findViewById(R.id.saveGraph);
        horizon = (Button) view.findViewById(R.id.horizon);
        btnX = (Button) view.findViewById(R.id.btnX);
        btnY = (Button) view.findViewById(R.id.btnY);

        g1Name = (Button) view.findViewById(R.id.g1Name);
        g2Name = (Button) view.findViewById(R.id.g2Name);
        g3Name = (Button) view.findViewById(R.id.g3Name);
        g1CV = (Button) view.findViewById(R.id.g1CV);
        g2CV = (Button) view.findViewById(R.id.g2CV);
        g3CV = (Button) view.findViewById(R.id.g3CV);

        saveGraph.setVisibility(View.INVISIBLE);
        horizon.setVisibility(View.INVISIBLE);

        layoutGraphView = (ViewGroup) view.findViewById(R.id.layoutGraphView);
        layoutValueView = (ViewGroup) view.findViewById(R.id.layoutValueView);

        layoutGraphView.setVisibility(View.INVISIBLE);

        radioValue = (RadioButton) view.findViewById(R.id.radioValue);
        radioGraph = (RadioButton) view.findViewById(R.id.radioGraph);

        radioValue.setOnClickListener(optionOnClickListener);
        radioGraph.setOnClickListener(optionOnClickListener);

        xyDfault = (CheckBox) view.findViewById(R.id.xyDefault);
        cbG1 = (CheckBox) view.findViewById(R.id.cbG1);
        cbG2 = (CheckBox) view.findViewById(R.id.cbG2);
        cbG3 = (CheckBox) view.findViewById(R.id.cbG3);

        radioValue.setChecked(true);

        btnX.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());

                alert.setTitle("최대 x값을 입력해 주십시오.");
                alert.setMessage("(음수값은 자동으로 지정됩니다.)");

                // Set an EditText view to get user input
                final EditText input = new EditText(view.getContext());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        maxXval.setText(value.toString());
                        MaxX.setText(MaxX.getText().toString().substring(0,8)+maxXval.getText().toString());
                        // Do something with value!
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
        });

        btnY.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());

                alert.setTitle("최대 y값을 입력해 주십시오.");
                alert.setMessage("(음수값은 자동으로 지정됩니다.)");

                // Set an EditText view to get user input
                final EditText input = new EditText(view.getContext());
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        maxYval.setText(value.toString());
                        MaxY.setText(MaxY.getText().toString().substring(0,8)+maxYval.getText().toString());
                        // Do something with value!
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
        });

        g1Name.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());

                alert.setTitle("Graph1의 이름을 지정해 주십시오.");

                // Set an EditText view to get user input
                final EditText input = new EditText(view.getContext());
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        tv_g1N.setText(value.toString());
                        GraphOne.setText(GraphTwo.getText().toString().substring(0,9) + value.toString());
                        // Do something with value!
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
        });

        g2Name.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());

                alert.setTitle("Graph2의 이름을 지정해 주십시오.");

                // Set an EditText view to get user input
                final EditText input = new EditText(view.getContext());
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        tv_g2N.setText(value.toString());
                        GraphTwo.setText(GraphTwo.getText().toString().substring(0,9) + value.toString());
                        // Do something with value!
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
        });

        g3Name.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());

                alert.setTitle("Graph3의 이름을 지정해 주십시오.");

                // Set an EditText view to get user input
                final EditText input = new EditText(view.getContext());
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        tv_g3N.setText(value.toString());
                        GraphThree.setText(GraphThree.getText().toString().substring(0,9) + value.toString());
                        // Do something with value!
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
        });

        ContextWrapper wrapper = new ContextWrapper(view.getContext());
        SharedPreferences xy = wrapper.getSharedPreferences("xy", Activity.MODE_PRIVATE);
        Boolean chk = xy.getBoolean("check", false);
        Boolean cbG1k = xy.getBoolean("cbG1", false);
        Boolean cbG2k = xy.getBoolean("cbG2", false);
        Boolean cbG3k = xy.getBoolean("cbG3", false);

        xyDfault.setChecked(chk);
        cbG1.setChecked(cbG1k);
        cbG2.setChecked(cbG2k);
        cbG3.setChecked(cbG3k);

        if(xyDfault.isChecked()) {
            String maxXvaltext = xy.getString("maxXval", "");
            String maxYvaltext = xy.getString("maxYval", "");

            maxXval.setText(maxXvaltext);
            maxYval.setText(maxYvaltext);
        }



        ///첫번째 쳌박

        if(cbG1.isChecked()) {
            g1Name.setEnabled(true);
            g1CV.setEnabled(true);
            GraphOne.setVisibility(View.VISIBLE);

            String gettv_g1N = xy.getString("tv_g1N", "");
            String getg1Val = xy.getString("g1Val", "");

            tv_g1N.setText(gettv_g1N);
            g1Val.setText(getg1Val);

            GraphOne.setText(GraphOne.getText().toString().substring(0,9)+tv_g1N.getText().toString());
        } else {
            GraphOne.setVisibility(View.INVISIBLE);
            g1Name.setEnabled(false);
            g1CV.setEnabled(false);
        }

        cbG1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if(cbG1.isChecked()) {
                    g1Name.setEnabled(true);
                    g1CV.setEnabled(true);
                    GraphOne.setVisibility(View.VISIBLE);
                    GraphOne.setText(GraphOne.getText().toString().substring(0,9)+tv_g1N.getText().toString());
                } else {
                    GraphOne.setVisibility(View.INVISIBLE);
                    g1Name.setEnabled(false);
                    g1CV.setEnabled(false);
                }
                Log.d("비지비지", String.valueOf(GraphOne.getVisibility()));
                Log.d("비지비지", String.valueOf(g1CV.isEnabled()));
            }
        });


        ////

//////////////

//두번째
        if(cbG2.isChecked()) {
            g2Name.setEnabled(true);
            g2CV.setEnabled(true);
            GraphTwo.setVisibility(View.VISIBLE);

            String gettv_g2N = xy.getString("tv_g2N", "");
            String getg2Val = xy.getString("g2Val", "");

            tv_g2N.setText(gettv_g2N);
            g2Val.setText(getg2Val);
            GraphTwo.setText(GraphTwo.getText().toString().substring(0,9)+tv_g2N.getText().toString());
        } else {
            GraphTwo.setVisibility(View.INVISIBLE);
            g2Name.setEnabled(false);
            g2CV.setEnabled(false);
        }

        cbG2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(cbG2.isChecked()) {
                    g2Name.setEnabled(true);
                    g2CV.setEnabled(true);
                    GraphTwo.setVisibility(View.VISIBLE);
                    GraphTwo.setText(GraphTwo.getText().toString().substring(0,9)+tv_g2N.getText().toString());
                } else {
                    GraphTwo.setVisibility(View.INVISIBLE);
                    g2Name.setEnabled(false);
                    g2CV.setEnabled(false);
                }
            }
        });
////
        ///3번째

        if(cbG3.isChecked()) {
            GraphThree.setVisibility(View.VISIBLE);
            g3Name.setEnabled(true);
            g3CV.setEnabled(true);

            String gettv_g3N = xy.getString("tv_g3N", "");
            String getg3Val = xy.getString("g3Val", "");

            tv_g3N.setText(gettv_g3N);
            g3Val.setText(getg3Val);
            GraphThree.setText(GraphThree.getText().toString().substring(0,9)+tv_g3N.getText().toString());
        } else {
            GraphThree.setVisibility(View.INVISIBLE);
            g3Name.setEnabled(false);
            g3CV.setEnabled(false);
        }

        cbG3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(cbG3.isChecked()) {
                    GraphThree.setVisibility(View.VISIBLE);
                    g3Name.setEnabled(true);
                    g3CV.setEnabled(true);
                    GraphThree.setText(GraphThree.getText().toString().substring(0,9)+tv_g3N.getText().toString());
                } else {
                    GraphThree.setVisibility(View.INVISIBLE);
                    g3Name.setEnabled(false);
                    g3CV.setEnabled(false);
                }
            }
        });

        GraphOne.setText(GraphOne.getText().toString().substring(0,9)+tv_g1N.getText().toString());
        GraphTwo.setText(GraphTwo.getText().toString().substring(0,9)+tv_g2N.getText().toString());
        GraphThree.setText(GraphThree.getText().toString().substring(0,9)+tv_g3N.getText().toString());

        MaxX.setText(MaxX.getText().toString().substring(0,8)+maxXval.getText().toString());
        MaxY.setText(MaxY.getText().toString().substring(0,8)+maxYval.getText().toString());

        horizon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent horizonGraph = new Intent(view.getContext(), HorizonGraph.class);
                horizonGraph.putExtra("maxX", String.valueOf(MaxX.getText().toString().substring(8)));
                horizonGraph.putExtra("maxY", String.valueOf(MaxY.getText().toString().substring(8)));

                if(cbG1.isChecked()) {
                    horizonGraph.putExtra("graph1name", tv_g1N.getText().toString());
                    horizonGraph.putExtra("graph1value", g1Val.getText().toString());
                } else {

                }
                if(cbG2.isChecked()) {
                    horizonGraph.putExtra("graph2name", tv_g2N.getText().toString());
                    horizonGraph.putExtra("graph2value", g2Val.getText().toString());
                } else {

                }
                if(cbG3.isChecked()) {
                    horizonGraph.putExtra("graph3name", tv_g3N.getText().toString());
                    horizonGraph.putExtra("graph3value", g3Val.getText().toString());
                } else {

                }
                startActivity(horizonGraph);
            }
        });

        g1CV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnSeq = 1;
                createdDialog(ID_CALCULAR).show();
            }
        });

        g2CV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnSeq = 2;
                createdDialog(ID_CALCULAR).show();
            }
        });

        g3CV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnSeq = 3;
                createdDialog(ID_CALCULAR).show();
            }
        });

        return view;
    }

    protected Dialog createdDialog(int id) {
        dlg = null;

        switch (id) {
            case ID_CALCULAR:

                Context mContext = view.getContext();
                dlg = new Dialog(mContext);
                dlg.setContentView(R.layout.dialog_calculator_view);



                DigText = (EditText) dlg.findViewById(R.id.DigText);

                for(i = 0; i < numBtnIDs.length; i++) {
                    numButtons[i] = (Button) dlg.findViewById(numBtnIDs[i]);
                }

                for(i=0; i < numBtnIDs.length; i++) {
                    final int index;
                    index = i;

                    numButtons[index].setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            String getTx = DigText.getText().toString();
                            String getBtn = numButtons[index].getText().toString();
                            String isNum = getTx + getBtn;

                            int leg = DigText.getText().toString().length();

                            if(getTx.equals("0")) {

                                if (getBtn.equals("0")) {

                                } else if(getBtn.equals(")")) {

                                } else if(getBtn.equals("×")){
                                    DigText.setText(isNum);
                                } else if(getBtn.equals("＋")){
                                    DigText.setText(isNum);
                                } else if(getBtn.equals("－")){
                                    DigText.setText(isNum);
                                } else if(getBtn.equals("÷")){
                                    DigText.setText(isNum);
                                } else if(getBtn.equals(".")){
                                    DigText.setText(isNum);
                                } else if(getBtn.equals("＾")){
                                    DigText.setText(isNum);
                                } else if(getBtn.equals("±")) {
                                    DigText.setText("-");
                                } else {
                                    DigText.setText(getBtn);
                                }
                            } else if (getTx.substring(leg-1).equals("＋")) {
                                if(getBtn.equals("＋")) {

                                } else if (getBtn.equals("－")) {
                                    DigText.setText(getTx.substring(0, leg-1) + getBtn);
                                } else if (getBtn.equals("×")) {
                                    DigText.setText(getTx.substring(0, leg-1) + getBtn);
                                } else if (getBtn.equals("÷")) {
                                    DigText.setText(getTx.substring(0, leg-1) + getBtn);
                                } else if (getBtn.equals("±")) {
                                    DigText.setText(getTx + "-");
                                } else if (getBtn.equals(".")) {

                                } else if (getBtn.equals("＾")) {

                                } else if (getBtn.equals(")")) {

                                } else {
                                    DigText.setText(isNum);
                                }
                            } else if (getTx.substring(leg-1).equals("－")) {
                                if(getBtn.equals("－")) {

                                } else if (getBtn.equals("＋")) {
                                    DigText.setText(getTx.substring(0, leg-1) + getBtn);
                                } else if (getBtn.equals("×")) {
                                    DigText.setText(getTx.substring(0, leg-1) + getBtn);
                                } else if (getBtn.equals("÷")) {
                                    DigText.setText(getTx.substring(0, leg-1) + getBtn);
                                } else if (getBtn.equals("±")) {
                                    DigText.setText(getTx + "-");
                                } else if (getBtn.equals(".")) {

                                } else if (getBtn.equals("＾")) {

                                } else if (getBtn.equals(")")) {

                                } else {
                                    DigText.setText(isNum);
                                }
                            } else if (getTx.substring(leg-1).equals("×")) {
                                if(getBtn.equals("×")) {

                                } else if (getBtn.equals("＋")) {
                                    DigText.setText(getTx.substring(0, leg-1) + getBtn);
                                } else if (getBtn.equals("－")) {
                                    DigText.setText(getTx.substring(0, leg-1) + getBtn);
                                } else if (getBtn.equals("÷")) {
                                    DigText.setText(getTx.substring(0, leg-1) + getBtn);
                                } else if (getBtn.equals("±")) {
                                    DigText.setText(getTx + "-");
                                } else if (getBtn.equals(".")) {

                                } else if (getBtn.equals("＾")) {

                                } else if (getBtn.equals(")")) {

                                } else {
                                    DigText.setText(isNum);
                                }
                            } else if (getTx.substring(leg-1).equals("÷")) {
                                if(getBtn.equals("÷")) {

                                } else if (getBtn.equals("＋")) {
                                    DigText.setText(getTx.substring(0, leg-1) + getBtn);
                                } else if (getBtn.equals("－")) {
                                    DigText.setText(getTx.substring(0, leg-1) + getBtn);
                                } else if (getBtn.equals("×")) {
                                    DigText.setText(getTx.substring(0, leg-1) + getBtn);
                                } else if (getBtn.equals("±")) {
                                    DigText.setText(getTx + "-");
                                } else if (getBtn.equals(".")) {

                                } else if (getBtn.equals("＾")) {

                                } else if (getBtn.equals(")")) {

                                } else {
                                    DigText.setText(isNum);
                                }
                            } else if (getTx.substring(leg-1).equals("-")) {
                                if(getBtn.equals("±")) {

                                } else if (getBtn.equals("＋")) {

                                } else if (getBtn.equals("－")) {

                                } else if (getBtn.equals("×")) {

                                } else if (getBtn.equals("÷")) {

                                } else if (getBtn.equals(".")) {

                                } else if (getBtn.equals("＾")) {

                                } else {
                                    DigText.setText(isNum);
                                }
                            } else if (getTx.substring(leg-1).equals(".")) {
                                if(getBtn.equals(".")) {

                                } else if (getBtn.equals("＋")) {

                                } else if (getBtn.equals("－")) {

                                } else if (getBtn.equals("×")) {

                                } else if (getBtn.equals("÷")) {

                                } else if (getBtn.equals("±")) {

                                } else if (getBtn.equals("＾")) {

                                } else {
                                    DigText.setText(isNum);
                                }
                            } else if (getTx.substring(leg-1).equals("＾")) {
                                if(getBtn.equals("＾")) {

                                } else if (getBtn.equals("＋")) {

                                } else if (getBtn.equals("－")) {

                                } else if (getBtn.equals("×")) {

                                } else if (getBtn.equals("÷")) {

                                } else if (getBtn.equals("±")) {
                                    DigText.setText(getTx + "-");
                                } else if (getBtn.equals(".")) {

                                } else {
                                    DigText.setText(isNum);
                                }
                            } else if (getTx.substring(leg-1).equals("(")) {
                                if(getBtn.equals("＾")) {

                                } else if (getBtn.equals("＋")) {

                                } else if (getBtn.equals("－")) {

                                } else if (getBtn.equals("×")) {

                                } else if (getBtn.equals("÷")) {

                                } else if (getBtn.equals("±")) {
                                    DigText.setText(getTx + "-");
                                } else if (getBtn.equals(".")) {

                                } else {
                                    DigText.setText(isNum);
                                }
                            } else if (getTx.substring(leg-1).equals("ｘ")) {
                                if(getBtn.equals("ｘ")) {

                                } else if (getBtn.equals("0")) {

                                } else if (getBtn.equals("1")) {

                                } else if (getBtn.equals("2")) {

                                } else if (getBtn.equals("3")) {

                                } else if (getBtn.equals("4")) {

                                } else if (getBtn.equals("5")) {

                                } else if (getBtn.equals("6")) {

                                } else if (getBtn.equals("7")) {

                                } else if (getBtn.equals("8")) {

                                } else if (getBtn.equals("9")) {

                                } else if (getBtn.equals(".")) {

                                } else if (getBtn.equals("±")) {

                                }else {
                                    DigText.setText(isNum);
                                }
                            } else {
                                if(getBtn.equals("±")){
                                    DigText.setText(getTx + "-");
                                } else {
                                    DigText.setText(isNum);
                                }
                            }
                        }
                    });
                }

                dlg.setTitle("y 대입식 변경");

                Button okDialogButton = (Button) dlg.findViewById(R.id.btnOk);
                okDialogButton.setOnClickListener(okDlgCalculator);

                Button closeDialogButton = (Button) dlg.findViewById(R.id.btnClose);
                closeDialogButton.setOnClickListener(closeDlgCalculator);

                Button delDialogButton = (Button) dlg.findViewById(R.id.btnDel);
                delDialogButton.setOnClickListener(delDlgCalculator);

                Button clearDialogButton = (Button) dlg.findViewById(R.id.btnClear);
                clearDialogButton.setOnClickListener(clearDlgCalculator);


                break;
            default:
                break;
        }
        return dlg;
    }

    private Button.OnClickListener okDlgCalculator =
            new Button.OnClickListener() {

                public void onClick(View v) {

                    if(btnSeq == 1) {
                        if(DigText.getText().toString().indexOf("ｘ") == -1){
                            Toast.makeText(view.getContext(), "대입식 안에 ｘ를 넣어주세요.", Toast.LENGTH_SHORT).show();
                        } else {
                            g1Val.setText(DigText.getText().toString());
                            btnSeq = 0;
                            dlg.dismiss();
                        }
                    } else if(btnSeq == 2) {
                        if(DigText.getText().toString().indexOf("ｘ") == -1){
                            Toast.makeText(view.getContext(), "대입식 안에 ｘ를 넣어주세요.", Toast.LENGTH_SHORT).show();
                        } else {
                            g2Val.setText(DigText.getText().toString());
                            btnSeq = 0;
                            dlg.dismiss();
                        }
                    } else if(btnSeq == 3) {
                        if(DigText.getText().toString().indexOf("ｘ") == -1){
                            Toast.makeText(view.getContext(), "대입식 안에 ｘ를 넣어주세요.", Toast.LENGTH_SHORT).show();
                        } else {
                            g3Val.setText(DigText.getText().toString());
                            btnSeq = 0;
                            dlg.dismiss();
                        }
                    }

                }
            };
    private Button.OnClickListener closeDlgCalculator =
            new Button.OnClickListener() {

                public void onClick(View v) {

                    dlg.dismiss();

                }
            };
    private Button.OnClickListener delDlgCalculator =
            new Button.OnClickListener() {

                public void onClick(View v) {

                    Editable edit = DigText.getText();
                    int st = edit.length();
                    if (st > 1) {
                        edit.delete(st-1, st);
                        DigText.setText(edit);
                    } else  if (st <= 1) {
                        DigText.setText("0");
                    }

                }
            };
    private Button.OnClickListener clearDlgCalculator =
            new Button.OnClickListener() {

                public void onClick(View v) {

                    DigText.setText("0");

                }
            };

    RadioButton.OnClickListener optionOnClickListener = new RadioButton.OnClickListener() {

        public void onClick(View v) {

            if(radioValue.isChecked()) {
                saveGraph.setVisibility(View.INVISIBLE);
                horizon.setVisibility(View.INVISIBLE);
                layoutValueView.setVisibility(View.VISIBLE);
                layoutGraphView.setVisibility(View.INVISIBLE);
                MaxX.setVisibility(View.VISIBLE);
                MaxY.setVisibility(View.VISIBLE);
                GraphOne.setVisibility(View.VISIBLE);
                GraphTwo.setVisibility(View.VISIBLE);
                GraphThree.setVisibility(View.VISIBLE);

                if(cbG1.isChecked()) {
                    GraphOne.setVisibility(View.VISIBLE);
                } else {
                    GraphOne.setVisibility(View.INVISIBLE);
                }
                if(cbG2.isChecked()) {
                    GraphTwo.setVisibility(View.VISIBLE);
                } else {
                    GraphTwo.setVisibility(View.INVISIBLE);
                }
                if(cbG3.isChecked()) {
                    GraphThree.setVisibility(View.VISIBLE);
                } else {
                    GraphThree.setVisibility(View.INVISIBLE);
                }

                setCurveGraph();
                isGraph =false;
            }else if(radioGraph.isChecked()) {
                layoutValueView.setVisibility(View.INVISIBLE);
                MaxX.setVisibility(View.INVISIBLE);
                MaxY.setVisibility(View.INVISIBLE);
                GraphOne.setVisibility(View.INVISIBLE);
                GraphTwo.setVisibility(View.INVISIBLE);
                GraphThree.setVisibility(View.INVISIBLE);
                layoutGraphView.setVisibility(View.VISIBLE);
                saveGraph.setVisibility(View.VISIBLE);
                horizon.setVisibility(View.VISIBLE);
                setCurveGraph();
                isGraph = true;
            }

        }
    };

    private void setCurveGraph() {
        //all setting
        CurveGraphVO vo = makeCurveGraphAllSetting();
        //default setting
//		CurveGraphVO vo = makeCurveGraphDefaultSetting();
        boolean makeGraph;

        if(cbG1.isChecked() || cbG2.isChecked() || cbG3.isChecked()) {
            makeGraph = true;
        } else {
            makeGraph = false;
        }

        if(isGraph == false && makeGraph == false) {
            Toast.makeText(view.getContext(), "그래프를 하나 이상 활성화 시켜주십시오.", Toast.LENGTH_SHORT).show();
        } else if(isGraph == false && makeGraph == true) {
            layoutGraphView.addView(new CurveGraphView(view.getContext(), vo));
        } else if(isGraph == true) {
            layoutGraphView.removeAllViewsInLayout();
        }
    }

    /**
     * make Curve graph using options
     * @return
     */
    private CurveGraphVO makeCurveGraphAllSetting() {
        int maxVal = Integer.parseInt(MaxX.getText().toString().substring(8)); // <--- x축 값 받아와야됨

        String[] legendArr = new String[(maxVal*2)+1];
        int minVal = maxVal * -1;

        //BASIC LAYOUT SETTING
        for(int i = 0; i < legendArr.length; i++) {
            legendArr[i] = String.valueOf(minVal);
            minVal++;
        }

        float[] graph1 = new float[(maxVal*2)+1];
        for(int i = 0; i < graph1.length; i++) {
            String xG1 = g1Val.getText().toString();
            xG1 = sctCount(xG1);
            graph1[i] = Float.parseFloat(cal(xG1.trim().replaceAll("ｘ", legendArr[i])));
        }

        float[] graph2 = new float[(maxVal*2)+1];
        for(int i = 0; i < graph2.length; i++) {
            String xG2 = g2Val.getText().toString();
            xG2 = sctCount(xG2);
            graph2[i] = Float.parseFloat(cal(xG2.trim().replaceAll("ｘ", legendArr[i])));
        }

        float[] graph3 = new float[(maxVal*2)+1];
        for(int i = 0; i < graph3.length; i++) {
            String xG3 = g3Val.getText().toString();
            xG3 = sctCount(xG3);
            graph3[i] = Float.parseFloat(cal(xG3.trim().replaceAll("ｘ", legendArr[i])));
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
        int maxValue 		= Integer.parseInt(MaxY.getText().toString().substring(8));

        //increment
        int increment 		= 1;
        //CurveGraphVO.DEFAULT_INCREMENT;

        //GRAPH SETTING


        List<CurveGraph> arrGraph = new ArrayList<CurveGraph>();
        if(cbG1.isChecked()) {
            arrGraph.add(new CurveGraph(GraphOne.getText().toString().substring(9), 0xaa66ff33, graph1));
        }
        if(cbG2.isChecked()) {
            arrGraph.add(new CurveGraph(GraphTwo.getText().toString().substring(9), 0xaa00ffff, graph2));
        }
        if(cbG3.isChecked()) {
            arrGraph.add(new CurveGraph(GraphThree.getText().toString().substring(9), 0xaaff0066, graph3));
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
    public void onStop(){
        super.onStop();

        ContextWrapper wrapper = new ContextWrapper(view.getContext());
        SharedPreferences xy = wrapper.getSharedPreferences("xy", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = xy.edit();

        editor.putString("maxXval", maxXval.getText().toString());
        editor.putString("maxYval", maxYval.getText().toString());

        editor.putString("tv_g1N", tv_g1N.getText().toString());
        editor.putString("tv_g2N", tv_g2N.getText().toString());
        editor.putString("tv_g3N", tv_g3N.getText().toString());

        editor.putString("g1Val", g1Val.getText().toString());
        editor.putString("g2Val", g2Val.getText().toString());
        editor.putString("g3Val", g3Val.getText().toString());

        editor.putBoolean("cbG1", cbG1.isChecked());
        editor.putBoolean("cbG2", cbG2.isChecked());
        editor.putBoolean("cbG3", cbG3.isChecked());
        editor.putBoolean("check", xyDfault.isChecked());
        editor.commit();

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
            Toast.makeText(view.getContext(), "잘못된 수식입니다.", Toast.LENGTH_SHORT).show();
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