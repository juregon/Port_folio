package org.androidtown.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    public static int temp;
    public static int temp2;
    public static int CurrentDay;
    int todayflag = 1;
    int CurrentMonth;
    int addtemp = 0;
    int addtemp2 =0;
    /**
     * 연/월 텍스트뷰
     */
    private TextView tvDate;
    /**
     * 그리드뷰 어댑터
     */
    private GridAdapter gridAdapter;
    /**
     * 일 저장 할 리스트
     */
    public static ArrayList<String> dayList;
    /**
     * 그리드뷰
     */
    private GridView gridView;
    /**
     * 캘린더 변수
     */
    private Calendar mCal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDate = (Button)findViewById(R.id.tv_date);
        gridView = (GridView)findViewById(R.id.gridview);
        findViewById(R.id.button_next).setOnClickListener(nClickListener);
        findViewById(R.id.button_prev).setOnClickListener(pClickListener);
        findViewById(R.id.button_total).setOnClickListener(button_total);
        findViewById(R.id.button_refresh).setOnClickListener(button_refresh);

        // 오늘에 날짜를 세팅 해준다.
        long now = System.currentTimeMillis();
        final Date date = new Date(now);
        //연,월,일을 따로 저장
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);
        //현재 날짜 텍스트뷰에 뿌려줌
        tvDate.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));
        //gridview 요일 표시
        dayList = new ArrayList<String>();
        mCal = Calendar.getInstance();
        //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        //1일 - 요일 매칭 시키기 위해 공백 add
        for (int i = 1; i < dayNum; i++) {
            dayList.add("");
            addtemp++;
            addtemp2++;
        }
        setCalendarDate(mCal.get(Calendar.MONTH) + 1);
        temp = mCal.get(Calendar.MONTH) + 1;
        CurrentMonth = temp;
        temp2 = mCal.get(Calendar.YEAR);
        gridAdapter = new GridAdapter(getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d(TELECOM_SERVICE, "juregonasdasdasd"+ dayList.get(position));
                Intent intent = new Intent(
                        getApplicationContext(), // 현재 화면의 제어권자
                        inner.class); // 다음 넘어갈 클래스 지정
                CurrentDay = position;
                if(dayList.get(position) != "")
                    startActivity(intent);
            }
        });
    }


    /**
     * 해당 월에 표시할 일 수 구함
     *
     * @param month
     */
    private void setCalendarDate(int month) {
        mCal.set(Calendar.MONTH, month - 1);
        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            dayList.add("" + (i + 1));
            addtemp++;
        }
        for (int i = addtemp; i < 42; i++) {
            dayList.add("");
        }
    }
    Button.OnClickListener button_total = new View.OnClickListener(){
        public void onClick(View v){
            Intent intent = new Intent(
                    getApplicationContext(),
                    total.class);
            startActivity(intent);
        }
    };
    Button.OnClickListener button_refresh = new View.OnClickListener(){
        public void onClick(View v){
            gridAdapter.notifyDataSetChanged();
        }
    };
    Button.OnClickListener pClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            todayflag = 0;
            addtemp = 0;
            addtemp2=0;
            dayList = new ArrayList<String>();
            dayList.clear();
            gridAdapter.setlist(dayList);

            long now = System.currentTimeMillis();
            final Date date2 = new Date(now);
            if(temp == 1)
                temp = 12;
            else
                temp--;
            if(temp == 12)
                temp2--;

            mCal = Calendar.getInstance();
            mCal.set(temp2, temp-1, 1);
            int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
            for (int i = 1; i < dayNum; i++) {
                dayList.add("");
                addtemp++;
                addtemp2++;
            }
            setCalendarDate(mCal.get(Calendar.MONTH) + 1);
            tvDate.setText(temp2 + "/" + temp);
            gridAdapter.notifyDataSetChanged();
        }
    };

    Button.OnClickListener nClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            todayflag = 0;
            addtemp = 0;
            addtemp2=0;
            dayList = new ArrayList<String>();
            dayList.clear();
            gridAdapter.setlist(dayList);

            long now = System.currentTimeMillis();
            final Date date2 = new Date(now);
            if(temp == 12)
                temp = 1;
            else
                temp++;
            if(temp == 1)
                temp2++;

            mCal = Calendar.getInstance();
            mCal.set(temp2, temp-1 , 1);
            int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
            for (int i = 1; i < dayNum; i++) {
                dayList.add("");
                addtemp++;
                addtemp2++;
            }
            setCalendarDate(mCal.get(Calendar.MONTH) + 1);
          //  int tempmonth = mCal.get(Calendar.MONTH) + 1;
            tvDate.setText(temp2 + "/" + temp);
            gridAdapter.notifyDataSetChanged();

        }
    };

    /**
     * 그리드뷰 어댑터
     *
     */
    private class GridAdapter extends BaseAdapter {
        private  List<String> list;
        private final LayoutInflater inflater;
        /**
         * 생성자
         *
         * @param context
         * @param list
         */
        public GridAdapter(Context context, List<String> list) {
            this.list = list;
            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return list.size();
        }
        @Override
        public String getItem(int position) {
            return list.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        public void setlist(List<String> list){
            this.list = list;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int tempposition = position - addtemp2 +1;
            Log.d(TELECOM_SERVICE, "leejung temp  "+ tempposition + "second addtemp2 " + addtemp2);
            final DB dbHelper = new DB(getApplicationContext(), "MoneyBook4.db", null, 1);
            final String currentDay2 = temp2 + "년" + temp + "월" + tempposition + "일";

            String gridtext = "";
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
                holder = new ViewHolder();
                holder.tvItemGridView = (TextView)convertView.findViewById(R.id.tv_item_gridview);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }

            String resultString = "";
            int tempposition2;
            int tempresult = dbHelper.getResult2(1, currentDay2) - dbHelper.getResult2(0, currentDay2);
            if(tempresult >= 0)
                resultString += "+"
                        + tempresult
                        + "원";
            else
                resultString +=
                        + tempresult
                        + "원";
            Log.d(TELECOM_SERVICE, "leejung  "+ dayList.get(position) + "second  " + position);
            if(getItem(position) != "") {
                gridtext = "" + getItem(position)
                        + "\n\n\n\n"

                        + resultString;
                holder.tvItemGridView.setText(gridtext);
            }
            else
                holder.tvItemGridView.setText(""+ getItem(position));
            //해당 날짜 텍스트 컬러,배경 변경
            mCal = Calendar.getInstance();
            //오늘 day 가져옴
            Integer today = mCal.get(Calendar.DAY_OF_MONTH);
            String sToday = String.valueOf(today);
            if(temp == CurrentMonth) {
                if (sToday.equals(getItem(position))) { //오늘 day 텍스트 컬러 변경
                    holder.tvItemGridView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.color_green));
                }
            }
            else{
                    holder.tvItemGridView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.transparent));
            }
            return convertView;
        }
    }
    private class ViewHolder {
        TextView tvItemGridView;
    }
}