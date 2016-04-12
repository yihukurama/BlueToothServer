package com.yihukurama.bluetoothserver;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yihukurama.bluetoothserver.controler.bluetooth.BluetoothCS;
import com.yihukurama.bluetoothserver.controler.bluetooth.BluetoothCallBack;
import com.yihukurama.bluetoothserver.controler.bluetooth.BluetoothManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    BluetoothManager bluetoothManager;
    BluetoothCallBack blueCallback;
    BluetoothDevice device;
    TextView textView;
    Button button;
    BluetoothCS bcs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepare();
        initView();
        initData();

    }

    private void prepare() {
        textView = (TextView)findViewById(R.id.text);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
    }
    private void initView() {

    }

    private void initData() {
        blueCallback = new BluetoothCallBack();
        bluetoothManager = BluetoothManager.getInstance(blueCallback);
        bcs = new BluetoothCS("C0:EE:FB:46:90:33",LinkDetectedHandler);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                bcs.startServer();
                break;
        }
    }

    private Handler LinkDetectedHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if(msg.what==1)//客户端
            {
                String mes = (String)msg.obj;
                Log.i("bluetooth","client"+mes);
            }
            else//服务器端
            {
                String remes = (String)msg.obj;
                Log.i("bluetooth","server"+remes);
            }
        }

    };
}
