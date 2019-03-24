package com.example.sensors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SensorEventListener, OnClickListener {

    private TextView acceloX, acceloY, acceloZ, proximity, light, gyroX, gyroY, gyroZ, gravityX, gravityY, gravityZ;
    private Button bStart, bStop;
    private Sensor accelometer, proximitySensor, lightSensor, gyroscopeSensor, gravitySensor;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

//        accelometer
        accelometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        acceloX = (TextView)findViewById(R.id.txtAcceloX);
        acceloY = (TextView)findViewById(R.id.txtAcceloY);
        acceloZ = (TextView)findViewById(R.id.txtAcceloZ);

//        proximity
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        proximity = (TextView)findViewById(R.id.txtProximity);

//        temp sensor (ambient temperature sensor)
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        light = (TextView)findViewById(R.id.txtLight);

//        gyroscope sensor readings
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        gyroX = (TextView)findViewById(R.id.txtGyroX);
        gyroY = (TextView)findViewById(R.id.txtGyroY);
        gyroZ = (TextView)findViewById(R.id.txtGyroZ);

//        gravity sensor readings
        gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        gravityX = (TextView)findViewById(R.id.txtGravityX);
        gravityY = (TextView)findViewById(R.id.txtGravityY);
        gravityZ = (TextView)findViewById(R.id.txtGravityZ);

//        button click event
        bStart = (Button)findViewById(R.id.btnStart);
        bStop = (Button)findViewById(R.id.btnStop);

        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            acceloX.setText(""+event.values[0]);
            acceloY.setText(""+event.values[1]);
            acceloZ.setText(""+event.values[2]);
        }
        else if( event.sensor.getType() == Sensor.TYPE_PROXIMITY){
            proximity.setText(""+event.values[0]);
        }
        else if( event.sensor.getType() == Sensor.TYPE_LIGHT){
            light.setText(""+event.values[0]);
        }
        else if( event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            gyroX.setText(""+event.values[0]);
            gyroY.setText(""+event.values[1]);
            gyroZ.setText(""+event.values[2]);
        }else if( event.sensor.getType() == Sensor.TYPE_GRAVITY){
            gravityX.setText(""+event.values[0]);
            gravityY.setText(""+event.values[1]);
            gravityZ.setText(""+event.values[2]);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btnStart:
                sensorManager.registerListener(this,accelometer, SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(this,proximitySensor,SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(this,lightSensor,SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(this,gyroscopeSensor,SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(this,gravitySensor,SensorManager.SENSOR_DELAY_NORMAL);
                break;
            case R.id.btnStop:
                sensorManager.unregisterListener(this);
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
