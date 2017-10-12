package org.androidtown.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class Loading extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        // SPLASH_DISPLAY_LENGTH 뒤에 메인 액티비티를 실행시키고 종료한다.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 메인 액티비티를 실행하고 로딩화면을 죽인다.
                Intent intent = new Intent(Loading.this, MainActivity.class);
                Loading.this.startActivity(intent);
                Loading.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}

