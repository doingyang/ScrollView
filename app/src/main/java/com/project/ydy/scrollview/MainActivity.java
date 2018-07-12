package com.project.ydy.scrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";
    private CustomScrollView mSv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSv = (CustomScrollView) findViewById(R.id.sv);
        mSv.setOnScrollStateListener(new CustomScrollView.OnScrollStateListener() {
            @Override
            public void onScrollStart() {
                Log.i(TAG, "onScrollStart: ");
            }

            @Override
            public void onScrollEnd() {
                Log.i(TAG, "onScrollEnd: ");
            }

            @Override
            public void onScrollUp() {
                Log.i(TAG, "onScrollUp: ");
            }

            @Override
            public void onScrollDown() {
                Log.i(TAG, "onScrollDown: ");
            }

            @Override
            public void onScrollTop() {
                Log.i(TAG, "onScrollTop: ");
            }

            @Override
            public void onScrollBottom() {
                Log.i(TAG, "onScrollBottom: ");
            }
        });
    }
}
