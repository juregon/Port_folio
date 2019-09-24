package org.androidtown.splash;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {
    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        db.execSQL("CREATE TABLE MONEYBOOK4 (_id INTEGER PRIMARY KEY AUTOINCREMENT, radio1Check INTEGER, radio2Check INTEGER, radio3Check INTEGER,item TEXT, price INTEGER, create_at TEXT, totalday TEXT);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insert(String create_at, String totalday, int radio1Check, int radio2Check, int radio3Check, String item, int price) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO MONEYBOOK4 VALUES(null, " + radio1Check + " , " + radio2Check + " ,  " + radio3Check + " ,'" + item + "', " + price + ", '" + create_at + "', '" + totalday + "');");
        db.close();
    }

    public void delete(String item, String currentDay2) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM MONEYBOOK4 WHERE item='" + item + "' AND create_at='" + currentDay2 +"';");
        db.close();

    }

    public String getResult(int radio1Check, String currentDay) {
    // 읽기가 가능하게 DB 열기
    SQLiteDatabase db = getReadableDatabase();
    String result = "";
    // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
    Cursor cursor = db.rawQuery("SELECT * FROM MONEYBOOK4 WHERE radio1Check =" + radio1Check + ";", null);
    String category = "";
    String charge = "";
    while (cursor.moveToNext()) {
        if(cursor.getString(6).equals(currentDay)){
            if (cursor.getInt(3) == 1)
                category = "식비";
            else if (cursor.getInt(3) == 2)
                category = "교통";
            else if (cursor.getInt(3) == 3)
                category = "교육";
            else if (cursor.getInt(3) == 4)
                category = "건강";
            else if (cursor.getInt(3) == 5)
                category = "주거";
            else if (cursor.getInt(3) == 6)
                category = "기타";
            else if (cursor.getInt(3) == 7)
                category = "월급";
            else if (cursor.getInt(3) == 8)
                category = "용돈";
            else if (cursor.getInt(3) == 9)
                category = "상여";
            else if (cursor.getInt(3) == 10)
                category = "주식";
            else if (cursor.getInt(3) == 11)
                category = "이자";
            else
                category = "기타";
            if (cursor.getInt(2) == 1)
                charge = "현금";
            else
                charge = "카드";
            if (cursor.getInt(5) >= 100000) {
                result += "       "
                        + cursor.getInt(5)
                        + "원\t\t\t\t\t\t "
                        + category
                        + "\t\t\t\t\t\t\t\t\t"
                        + charge
                        + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
                        + cursor.getString(4)
                        + "\n";
            } else if (cursor.getInt(5) >= 10000) {
                result += "       "
                        + cursor.getInt(5)
                        + "원\t\t\t\t\t\t\t "
                        + category
                        + "\t\t\t\t\t\t\t\t\t"
                        + charge
                        + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
                        + cursor.getString(4)
                        + "\n";
            } else if (cursor.getInt(5) >= 1000){
                result += "       "
                        + cursor.getInt(5)
                        + "원\t\t\t\t\t\t\t\t\t "
                        + category
                        + "\t\t\t\t\t\t\t\t\t"
                        + charge
                        + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
                        + cursor.getString(4)
                        + "\n";
            }else {
                result += "       "
                        + cursor.getInt(5)
                        + "원\t\t\t\t\t\t\t\t\t\t "
                        + category
                        + "\t\t\t\t\t\t\t\t\t"
                        + charge
                        + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
                        + cursor.getString(4)
                        + "\n";
            }
        }
    }
        db.close();
    return result;
}
    public int getResult2(int radio1Check, String currentDay) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM MONEYBOOK4 WHERE radio1Check =" + radio1Check + ";", null);
        int resultcharge = 0;
        while (cursor.moveToNext()) {
            if(cursor.getString(6).equals(currentDay))
                resultcharge += cursor.getInt(5);
        }
        db.close();
        return resultcharge;
    }

    public int getResult3(int radio1Check, int category, String currentDay) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM MONEYBOOK4 WHERE radio1Check =" + radio1Check + ";", null);
        int resultcharge = 0;
        while (cursor.moveToNext()) {
            if(cursor.getString(7).equals(currentDay))
                if (cursor.getInt(3) == category)
                resultcharge += cursor.getInt(5);
        }
        db.close();
        return resultcharge;
    }

    public int getResult4(int radio1Check,  String currentDay) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM MONEYBOOK4 WHERE radio1Check =" + radio1Check + ";", null);
        int resultcharge = 0;
        while (cursor.moveToNext()) {
            if(cursor.getString(7).equals(currentDay))
                    resultcharge += cursor.getInt(5);
        }
        db.close();
        return resultcharge;
    }
}

