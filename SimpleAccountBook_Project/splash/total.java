package org.androidtown.splash;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class total extends AppCompatActivity {
    static MainActivity mainActivity = new MainActivity();
    private int temp3 = mainActivity.temp;
    private int temp4 = mainActivity.temp2;
    private int temp5 = mainActivity.CurrentDay;
    private ArrayList<String> tempString = mainActivity.dayList;
    String date = (temp4 + "년" + temp3 + "월" + tempString.get(temp5) + "일").toString();
    String date2 = (temp4 + "년" + temp3 + "월");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total);

        final DB dbHelper = new DB(getApplicationContext(), "MoneyBook4.db", null, 1);
        TextView income = (TextView)findViewById(R.id.income);
        TextView outcome = (TextView)findViewById(R.id.outcome);
        TextView Text_1 = (TextView)findViewById(R.id.id_1);
        TextView Text_2 = (TextView)findViewById(R.id.id_2);
        TextView Text_3 = (TextView)findViewById(R.id.id_3);
        TextView Text_4 = (TextView)findViewById(R.id.id_4);
        TextView Text_5 = (TextView)findViewById(R.id.id_5);
        TextView Text_6 = (TextView)findViewById(R.id.id_6);
        TextView Text_7 = (TextView)findViewById(R.id.id_7);
        TextView Text_8 = (TextView)findViewById(R.id.id_8);
        TextView Text_9 = (TextView)findViewById(R.id.id_9);
        TextView Text_10 = (TextView)findViewById(R.id.id_10);
        TextView Text_11 = (TextView)findViewById(R.id.id_11);
        TextView Text_12 = (TextView)findViewById(R.id.id_12);
        Button total = (Button)findViewById(R.id.totalbutton);
        String s1 = "총 수입 : " + dbHelper.getResult4(1, date2) + "\n";
        String s2 = "총 지출 : " + dbHelper.getResult4(0, date2) + "\n";
        String s3 = "월급 : " + dbHelper.getResult3(1,7, date2) + "\t\t\t\t\t" + (int)(((float)dbHelper.getResult3(1,7, date2)/dbHelper.getResult4(1, date2))*100) +"%";
        String s4 = "용돈 : " + dbHelper.getResult3(1,8, date2) + "\t\t\t\t\t" + (int)(((float)dbHelper.getResult3(1,8, date2)/dbHelper.getResult4(1, date2))*100) +"%";
        String s5 = "상여 : " + dbHelper.getResult3(1,9, date2)+ "\t\t\t\t\t" + (int)(((float)dbHelper.getResult3(1,9, date2)/dbHelper.getResult4(1, date2))*100) +"%";
        String s6 = "주식 : " + dbHelper.getResult3(1,10, date2)+ "\t\t\t\t\t" + (int)(((float)dbHelper.getResult3(1,10, date2)/dbHelper.getResult4(1, date2))*100) +"%";
        String s7 = "이자 : " + dbHelper.getResult3(1,11, date2)+ "\t\t\t\t\t" + (int)(((float)dbHelper.getResult3(1,11, date2)/dbHelper.getResult4(1, date2))*100) +"%";
        String s8 = "기타 : " + dbHelper.getResult3(1,12, date2)+ "\t\t\t\t\t" + (int)(((float)dbHelper.getResult3(1,12, date2)/dbHelper.getResult4(1, date2))*100) +"%\n";
        String s9 = "식비 : " + dbHelper.getResult3(0,1, date2)+ "\t\t\t\t\t" + (int)(((float)dbHelper.getResult3(0,1, date2)/dbHelper.getResult4(0, date2))*100) +"%";
        String s10 = "교통 : " + dbHelper.getResult3(0,2, date2)+ "\t\t\t\t\t" + (int)(((float)dbHelper.getResult3(0,2, date2)/dbHelper.getResult4(0, date2))*100) +"%";
        String s11 = "교육 : " + dbHelper.getResult3(0,3, date2)+ "\t\t\t\t\t" + (int)(((float)dbHelper.getResult3(0,3, date2)/dbHelper.getResult4(0, date2))*100) +"%";
        String s12 = "건강 : " + dbHelper.getResult3(0,4, date2)+ "\t\t\t\t\t" + (int)(((float)dbHelper.getResult3(0,4, date2)/dbHelper.getResult4(0, date2))*100) +"%";
        String s13 = "주거 : " + dbHelper.getResult3(0,5, date2)+ "\t\t\t\t\t" + (int)(((float)dbHelper.getResult3(0,5, date2)/dbHelper.getResult4(0, date2))*100) +"%";
        String s14 = "기타 : " + dbHelper.getResult3(0,6, date2)+ "\t\t\t\t\t" + (int)(((float)dbHelper.getResult3(0,6, date2)/dbHelper.getResult4(0, date2))*100) +"%\n\n";
        String s15 = "결산 : ";
        int tempresult = dbHelper.getResult4(1, date2) - dbHelper.getResult4(0, date2);
        if(tempresult>=0) {
            total.setTextColor(Color.parseColor("#0000ff"));
            s15 += "+" + tempresult;
        }
        else {
            total.setTextColor(Color.parseColor("#ff0000"));
            s15 += tempresult;
        }
        s15 += "원";
        SpannableString content = new SpannableString(s1);
        content.setSpan(new UnderlineSpan(), 0, s1.length(), 0);
        SpannableString content2 = new SpannableString(s2);
        content2.setSpan(new UnderlineSpan(), 0, s2.length(), 0);

        income.setText(content);
        outcome.setText(content2);
        total.setText(s15);
        Text_1.setText(s3);
        Text_2.setText(s4);
        Text_3.setText(s5);
        Text_4.setText(s6);
        Text_5.setText(s7);
        Text_6.setText(s8);
        Text_7.setText(s9);
        Text_8.setText(s10);
        Text_9.setText(s11);
        Text_10.setText(s12);
        Text_11.setText(s13);
        Text_12.setText(s14);
    }
}
