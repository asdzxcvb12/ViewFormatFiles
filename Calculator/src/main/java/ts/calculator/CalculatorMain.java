package ts.calculator;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by user on 2017-02-12.
 */
public class CalculatorMain  extends FragmentActivity implements
        ActionBar.TabListener {

    private BackPressCloseHandler backPressCloseHandler;
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_main);

        backPressCloseHandler = new BackPressCloseHandler(this);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mSectionsPagerAdapter = new SectionsPagerAdapter(
                getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            actionBar.addTab(actionBar.newTab()
                    .setText(mSectionsPagerAdapter.getPageTitle(i))
                    .setTabListener(this));

        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mqkenew, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu01:
                try {
                    if (mViewPager.getCurrentItem() == 0) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(this);

                        alert.setTitle("저장 될 이름을 지정해 주십시오.");

                        // Set an EditText view to get user input
                        final EditText input = new EditText(this);
                        alert.setView(input);

                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                CalListViewAdapter CalAdapter;
                                ArrayList<String> CalSaveName = new ArrayList<>();
                                ArrayList<String> CalculationResult = new ArrayList<>();
                                ArrayList<String> Calculation = new ArrayList<>();

                                ArrayList<CalculatorSaveList> CalArraylist = new ArrayList<CalculatorSaveList>();
                                final CalDBManager CalDBManager = new CalDBManager(getApplicationContext(), "Calculator.db", null, 1);
                                Log.d("이거이거", "size : " + String.valueOf(CalDBManager.nameArrayList().size()));

                                TextView vi2 = (TextView) findViewById(R.id.vi2);
                                TextView vi3 = (TextView) findViewById(R.id.vi3);
                                String name = input.getText().toString();
                                String result = vi2.getText().toString();
                                String calculation = vi3.getText().toString();
                                CalDBManager.insert("insert into CALCULATOR values(null, '" + name + "', '" + result + "', '" + calculation + "');");

                                for (int i = 0; i < CalDBManager.nameArrayList().size(); i++) {
                                    CalSaveName.add(CalDBManager.nameArrayList().get(i));
                                }

                                for (int i = 0; i < CalDBManager.resultArrayList().size(); i++) {
                                    CalculationResult.add(CalDBManager.resultArrayList().get(i));
                                }

                                for (int i = 0; i < CalDBManager.calculationArrayList().size(); i++) {
                                    Calculation.add(CalDBManager.calculationArrayList().get(i));
                                }


                                if (!CalSaveName.isEmpty()) {
                                    if (CalArraylist.isEmpty()) {
                                        for (int i = 0; i < CalSaveName.size(); i++) {
                                            CalculatorSaveList cs = new CalculatorSaveList(CalSaveName.get(i), CalculationResult.get(i),
                                                    Calculation.get(i));
                                            // Binds all strings into an array
                                            CalArraylist.add(cs);
                                        }
                                    }
                                }
                                CalAdapter = new CalListViewAdapter(getApplicationContext(), CalArraylist);

                                CalAdapter.notifyDataSetChanged();

                                Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
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
                    } else {
                        Toast.makeText(getApplicationContext(), "CALCULATOR페이지에서 저장해 주십시오.", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR : " + e, Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.action_menu02:

                try {
                    if(mViewPager.getCurrentItem() == 0) {
                            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                            alertBuilder.setTitle("로드 항목을 선택하세요.");

                            // List Adapter 생성
                        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                                    android.R.layout.select_dialog_singlechoice);
                        final CalDBManager CalDBManager = new CalDBManager(getApplicationContext(), "Calculator.db", null, 1);

                        ArrayList<String> CalSaveName = new ArrayList<>();
                        ArrayList<String> Calculation = new ArrayList<>();

                        for (int i = 0; i < CalDBManager.nameArrayList().size(); i++) {
                            CalSaveName.add(CalDBManager.nameArrayList().get(i));
                        }
                        for(int i=0; i < CalDBManager.calculationArrayList().size(); i++) {
                            Calculation.add(CalDBManager.calculationArrayList().get(i));
                        }

                        for(int i=0; i < CalSaveName.size(); i++) {
                            adapter.add("Name : " + CalSaveName.get(i) + "\n" + "Calculation : " + Calculation.get(i));
                        }

                            // 버튼 생성
                            alertBuilder.setNegativeButton("취소",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                            dialog.dismiss();
                                        }
                                    });

                            // Adapter 셋팅
                            alertBuilder.setAdapter(adapter,
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int id) {
                                            String strName = adapter.getItem(id);
                                            EditText vi1 = (EditText) findViewById(R.id.vi1);

                                            vi1.setText(strName);
                                            Toast.makeText(getApplicationContext(), "로드되었습니다.", Toast.LENGTH_SHORT).show();
                                            // AlertDialog 안에 있는 AlertDialog
                                            dialog.dismiss();

                                        }
                                    });
                            alertBuilder.show();
                    } else {
                        Toast.makeText(getApplicationContext(), "CALCULATOR페이지에서 로드해 주십시오.", Toast.LENGTH_SHORT).show();
                    }
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR : " + e, Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.action_menu03:
                Toast.makeText(this, "UpDownGame", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab,
                              FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab,
                                FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab,
                                FragmentTransaction fragmentTransaction) {

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        Context mContext;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {



            switch(position) {
                case 0:
                    return new FgCalculator(mContext);
                case 1:
                    return new FgGraph(mContext);
                case 2:
                    return new FgSavelist(mContext);
            }
            return null;
        }

        @Override
        public int getItemPosition(Object item) {
                return POSITION_NONE;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();



            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }


    public static class DummySectionFragment extends Fragment {

        public static final String ARG_SECTION_NUMBER = "section_number";

        public DummySectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.dummy,
                    container, false);
            TextView dummyTextView = (TextView) rootView
                    .findViewById(R.id.section_label);
            dummyTextView.setText(Integer.toString(getArguments().getInt(
                    ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    @Override public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }


}
