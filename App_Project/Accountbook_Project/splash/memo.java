package org.androidtown.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class memo extends AppCompatActivity {
    RadioButton radio1;
    RadioButton radio2;
    RadioButton radio2_1;
    RadioButton radio2_2;
    RadioButton radio3_1;
    RadioButton radio3_2;
    RadioButton radio3_3;
    RadioButton radio3_4;
    RadioButton radio3_5;
    RadioButton radio3_6;
    int radio1Check;
    int radio2Check;
    int radio3Check;
    static MainActivity mainActivity = new MainActivity();
    private int temp3 = mainActivity.temp;
    private int temp4 = mainActivity.temp2;
    private int temp5 = mainActivity.CurrentDay;
    private ArrayList<String> tempString = mainActivity.dayList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo);

        final DB dbHelper = new DB(getApplicationContext(), "MoneyBook4.db", null, 1);
        final EditText etPrice = (EditText) findViewById(R.id.editText1);
        final EditText etItem = (EditText) findViewById(R.id.editText2);

        Button current = (Button) findViewById(R.id.current);
        radio1 = (RadioButton) findViewById(R.id.radio1_1);
        radio2 = (RadioButton) findViewById(R.id.radio1_2);
        radio2_1 = (RadioButton) findViewById(R.id.radio2_1);
        radio2_2 = (RadioButton) findViewById(R.id.radio2_2);
        radio3_1 = (RadioButton) findViewById(R.id.radio3_1);
        radio3_2 = (RadioButton) findViewById(R.id.radio3_2);
        radio3_3 = (RadioButton) findViewById(R.id.radio3_3);
        radio3_4 = (RadioButton) findViewById(R.id.radio3_4);
        radio3_5 = (RadioButton) findViewById(R.id.radio3_5);
        radio3_6 = (RadioButton) findViewById(R.id.radio3_6);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);
        radio2.setChecked(true);
        radio1.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                radio3_1.setText("월급");
                radio3_2.setText("용돈");
                radio3_3.setText("상여");
                radio3_4.setText("주식");
                radio3_5.setText("이자");
                radio3_6.setText("기타");
            }
         });
        radio2.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                radio3_1.setText("식비");
                radio3_2.setText("교통");
                radio3_3.setText("교육");
                radio3_4.setText("건강");
                radio3_5.setText("주거");
                radio3_6.setText("기타");
            }
        });

        current.setText(temp4 + "년" + temp3 + "월" + tempString.get(temp5) + "일");

        Button complete = (Button) findViewById(R.id.complete);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radio1.isChecked())
                    radio1Check = 1;
                else
                    radio1Check = 0;
                if(radio2_1.isChecked())
                    radio2Check = 1;
                else
                    radio2Check = 0;
                if(radio2.isChecked()) {
                    if (radio3_1.isChecked())
                        radio3Check = 1;
                    else if (radio3_2.isChecked())
                        radio3Check = 2;
                    else if (radio3_3.isChecked())
                        radio3Check = 3;
                    else if (radio3_4.isChecked())
                        radio3Check = 4;
                    else if (radio3_5.isChecked())
                        radio3Check = 5;
                    else
                        radio3Check = 6;
                }
                else{
                    if (radio3_1.isChecked())
                        radio3Check = 7;
                    else if (radio3_2.isChecked())
                        radio3Check = 8;
                    else if (radio3_3.isChecked())
                        radio3Check = 9;
                    else if (radio3_4.isChecked())
                        radio3Check = 10;
                    else if (radio3_5.isChecked())
                        radio3Check = 11;
                    else
                        radio3Check = 12;
                }
                String date = (temp4 + "년" + temp3 + "월" + tempString.get(temp5) + "일").toString();
                String date2 = (temp4 + "년" + temp3 + "월");
                String item = etItem.getText().toString();
                int price = Integer.parseInt(etPrice.getText().toString());

                dbHelper.insert(date, date2, radio1Check, radio2Check, radio3Check, item, price);
                finish();
            }
        });
    }
}
