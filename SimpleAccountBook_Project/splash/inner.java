package org.androidtown.splash;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by home on 2016-11-27.
 */
public class inner extends AppCompatActivity {
    static MainActivity mainActivity = new MainActivity();
    private int temp3 = mainActivity.temp;
    private int temp4 = mainActivity.temp2;
    private int temp5 = mainActivity.CurrentDay;
    private ArrayList<String> tempString = mainActivity.dayList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inner);
        final DB dbHelper = new DB(getApplicationContext(), "MoneyBook4.db", null, 1);
        final TextView result = (TextView) findViewById(R.id.result);
        final TextView result_in = (TextView) findViewById(R.id.result_in);
        final TextView result_out = (TextView) findViewById(R.id.result_out);
        final TextView result_result = (TextView) findViewById(R.id.result_result);
        Button current = (Button) findViewById(R.id.current);
        current.setText(temp4 + "년" + temp3 + "월" + tempString.get(temp5) + "일");
        final String currentDay = temp4 + "년" + temp3 + "월" + tempString.get(temp5) + "일";
        Button income = (Button)findViewById(R.id.button_income);
        Button delete_button = (Button)findViewById(R.id.delete_button);
        final EditText delete_edit = (EditText)findViewById(R.id.delete_edit);
        ImageView iv = (ImageView)findViewById(R.id.button_memo);
        String inString = "";
        String outString = "";
        String resultString = "";
        inString += "+"
                + dbHelper.getResult2(1, currentDay)
                + "원";
        outString += "-"
                + dbHelper.getResult2(0, currentDay)
                + "원";
        result_in.setText(inString);
        result_out.setText(outString);
        int tempresult = dbHelper.getResult2(1, currentDay) - dbHelper.getResult2(0, currentDay);
        if(tempresult >= 0) {
            result_result.setTextColor(Color.parseColor("#0000ff"));
            resultString += "+"
                    + tempresult
                    + "원";
        }
        else {
            result_result.setTextColor(Color.parseColor("#ff0000"));
            resultString +=
                    + tempresult
                    + "원";
        }
        result_result.setText(resultString);

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = delete_edit.getText().toString();
                dbHelper.delete(item, currentDay);
            }
        });

        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TELECOM_SERVICE, "juregon"+ currentDay);
                String inString = "";
                String outString = "";
                String resultString = "";
                inString += "+"
                        + dbHelper.getResult2(1, currentDay)
                        + "원";
                outString += "-"
                        + dbHelper.getResult2(0, currentDay)
                        + "원";
                result_in.setText(inString);
                result_out.setText(outString);
                int tempresult = dbHelper.getResult2(1, currentDay) - dbHelper.getResult2(0, currentDay);
                if(tempresult >= 0)
                    resultString += "+"
                            + tempresult
                            + "원";
                else
                    resultString +=
                            + tempresult
                            + "원";
                result_result.setText(resultString);
                result.setText(dbHelper.getResult(1, currentDay));
            }
        });

        Button outcome = (Button)findViewById(R.id.button_outcome);
        outcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inString = "";
                String outString = "";
                String resultString = "";
                inString += "+"
                        + dbHelper.getResult2(1, currentDay)
                        + "원";
                outString += "-"
                        + dbHelper.getResult2(0, currentDay)
                        + "원";
                result_in.setText(inString);
                result_out.setText(outString);
                int tempresult = dbHelper.getResult2(1, currentDay) - dbHelper.getResult2(0, currentDay);
                if(tempresult >= 0)
                    resultString += "+"
                            + tempresult
                            + "원";
                else
                    resultString +=
                            + tempresult
                            + "원";
                result_result.setText(resultString);
                result.setText(dbHelper.getResult(0, currentDay));
            }
        });

        iv.setImageResource(R.drawable.memo);
        iv.setOnClickListener(new memoListener());
    }
    class memoListener implements View.OnClickListener {
        int i = 0;

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(
                    getApplicationContext(), // 현재 화면의 제어권자
                    memo.class); // 다음 넘어갈 클래스 지정
            startActivity(intent);
        }
    }
}


