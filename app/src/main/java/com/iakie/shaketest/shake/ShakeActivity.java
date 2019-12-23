package com.iakie.shaketest.shake;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
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
    private AnimationSet mSetUp;
    private AnimationSet mSetDown;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        initView();
        initAnimation();
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
            upImg.startAnimation(mSetUp);
            downImg.startAnimation(mSetDown);
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

    //使用代码来实现补间动画
    private void initAnimation() {
        // 上面图片对应的动画效果
        TranslateAnimation mAnimationUp = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,-1);
        mAnimationUp.setDuration(500); //设置持续时间
        TranslateAnimation mAnimationUpDown = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,-1,Animation.RELATIVE_TO_SELF,0);
        mAnimationUpDown.setDuration(500);

        // 将上下移动的动画添加到集合中
        mSetUp = new AnimationSet(true);
        mSetUp.addAnimation(mAnimationUp);
        mSetUp.addAnimation(mAnimationUpDown);

        // 设置动画之间执行的时差
        mSetUp.setStartOffset(500);

        // 下面图片对应的动画效果
        TranslateAnimation mAnimationDown = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,1);
        mAnimationUp.setDuration(500); //设置持续时间 毫秒
        TranslateAnimation mAnimationDownUp = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF,1,Animation.RELATIVE_TO_SELF,0);
        mAnimationUpDown.setDuration(500);

        mSetDown = new AnimationSet(true);
        mSetDown.addAnimation(mAnimationDown);
        mSetDown.addAnimation(mAnimationDownUp);
        mSetDown.setStartOffset(500);
    }
}
