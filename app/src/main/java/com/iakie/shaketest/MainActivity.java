package com.iakie.shaketest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.iakie.shaketest.shake.ShakeActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "iAKie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {

        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_shake:
                intent.setClass(this, ShakeActivity.class);
                break;
        }

        Log.e(TAG, "onClick: "+intent.toString());
        startActivity(intent);
    }
}
