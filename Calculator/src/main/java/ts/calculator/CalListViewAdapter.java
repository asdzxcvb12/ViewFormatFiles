package ts.calculator;

/**
 * Created by user on 2017-02-21.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CalListViewAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    private List<CalculatorSaveList> calculatorsavelist = null;
    private ArrayList<CalculatorSaveList> CalArraylist;

    public CalListViewAdapter(Context context, List<CalculatorSaveList> calculatorsavelist) {
        mContext = context;
        this.calculatorsavelist = calculatorsavelist;
        inflater = LayoutInflater.from(mContext);
        this.CalArraylist = new ArrayList<CalculatorSaveList>();
        this.CalArraylist.addAll(calculatorsavelist);
    }

    public class ViewHolder {
        TextView calsavename;
        TextView calculationresult;
        TextView calculation;
    }

    @Override
    public int getCount() {
        return calculatorsavelist.size();
    }

    @Override
    public CalculatorSaveList getItem(int position) {
        return calculatorsavelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.callistview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.calsavename = (TextView) view.findViewById(R.id.cname);
            holder.calculationresult = (TextView) view.findViewById(R.id.calculationresult);
            holder.calculation = (TextView) view.findViewById(R.id.calculation);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.calsavename.setText(calculatorsavelist.get( position).getCalSaveName());
        holder.calculationresult.setText(calculatorsavelist.get(position).getCalculationResult());
        holder.calculation.setText(calculatorsavelist.get(position).getCalculation());

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
                                final CalDBManager CalDBManager = new CalDBManager(inflater.getContext(), "Calculator.db", null, 1);
                                CalDBManager.delete("delete from CALCULATOR where name = '" + calculatorsavelist.get( position).getCalSaveName() + "';");

                                int getPosition = 0;
                                for(int i = 0; i < CalArraylist.size(); i++) {
                                    if(CalArraylist.get(i).equals(calculatorsavelist.get( position).getCalSaveName())) {
                                        getPosition = i;
                                    }
                                }
                                calculatorsavelist.remove(calculatorsavelist.get(position));
                                CalArraylist.remove(CalArraylist.get(getPosition));
                                notifyDataSetChanged();
                            }
                        });

                alert.setNegativeButton("자세히",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Canceled.
                                Intent singleView = new Intent(mContext, CalSingleItemView.class);
                                singleView.putExtra("val", calculatorsavelist.get(position).getCalculation());
                                mContext.startActivity(singleView);

                            }
                        });

                alert.show();
                /*
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, CalSingleItemView.class);
                // Pass all data rank
                intent.putExtra("name",(calculatorsavelist.get(position).getCalSaveName()));
                // Pass all data country
                intent.putExtra("result",(calculatorsavelist.get(position).getCalculationResult()));
                // Pass all data population
                intent.putExtra("calculation",(calculatorsavelist.get(position).getCalculation()));
                // Pass all data flag
                // Start SingleItemView Class
                mContext.startActivity(intent);
                */
            }
        });

        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        calculatorsavelist.clear();
        if (charText.length() == 0) {
            calculatorsavelist.addAll(CalArraylist);
        }
        else
        {
            for (CalculatorSaveList cs : CalArraylist)
            {
                if (cs.getCalSaveName().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    calculatorsavelist.add(cs);
                }
            }
        }
        notifyDataSetChanged();
    }

}
