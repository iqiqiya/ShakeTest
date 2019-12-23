package com.iakie.shaketest.shake;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.iakie.shaketest.R;

/**
 * Author: iqiqiya
 * Date: 2019-12-22
 * Time: 22:02
 * Blog: blog.77sec.cn
 * Github: github.com/iqiqiya
 */
public class ShakeActivity extends AppCompatActivity implements SensorEventListener {
    ImageView upImg,downImg,centerImg;
    SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        initView();
    }

    private void initView() {
        /* 初始化控件等操作 */

        upImg = findViewById(R.id.id_shake_upimg);
        downImg = findViewById(R.id.id_shake_downimg);
        centerImg = findViewById(R.id.id_shake_centerimg);

        // 1.获取传感器管理者对象
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // 2.获取指定的传感器对象----加速度传感器
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // 3.注册传感器对象
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // 当传感器数值发生改变时调用的函数
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];
        int minValue = 12;
        if (Math.abs(x)>minValue||Math.abs(y)>minValue||Math.abs(z)>minValue) {
            // 开始动画效果
            
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // 当传感器精度发生改变时调用的函数

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 4.注销传感器对象
        sensorManager.unregisterListener(this);
    }
}
