package com.example.dongja94.samplecountdown;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView messageView;
    Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageView = (TextView)findViewById(R.id.text_message);
        Button btn = (Button)findViewById(R.id.btn_start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCount = 10;
                startTime = NOT_STARTED;
                mHandler.post(countRunnable);
            }
        });
    }

    int mCount = 0;
    private static final long NOT_STARTED = -1;
    long startTime = NOT_STARTED;

    Runnable countRunnable = new Runnable() {
        @Override
        public void run() {
            long currentTime = System.currentTimeMillis();
            if (startTime == NOT_STARTED) {
                startTime = currentTime;
            }
            int delta = (int)(currentTime - startTime);
            int elapsed = delta / 1000;
            int rest = 1000 - delta % 1000;

            if (mCount - elapsed > 0) {
                messageView.setText("count : " + (mCount - elapsed));
                mHandler.postDelayed(this, rest);
            } else {
                messageView.setText("count down complete");
            }
        }
    };

}
