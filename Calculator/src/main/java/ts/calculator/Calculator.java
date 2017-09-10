package ts.calculator;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent cal = new Intent(getApplicationContext(), CalculatorMain.class);
                startActivity(cal);
            }
        },2000);

    }
}
