package com.csj.aidlparcelclient;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.csj.aidl.ISalary;
import com.csj.aidl.Person;
import com.csj.aidl.Salary;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private Button btnQuery;
    private TextView tvShow;

    private ISalary salary;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //返回的是代理对象,要调用这个方法哦!
            salary = ISalary.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            salary = null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //必须指定远程服务器的ation和包名
        Intent intent = new Intent();
        intent.setAction("android.intent.action.AIDLService");
        intent.setPackage("com.csj.aidlparcelserver");

        bindService(intent, conn, Service.BIND_AUTO_CREATE);

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                try {
                    Salary msg = salary.getMsg(new Person(1, name));
                    tvShow.setText(name+", "+msg.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initView() {
        etName = (EditText) findViewById(R.id.etName);
        btnQuery = (Button) findViewById(R.id.btnQuery);
        tvShow = (TextView) findViewById(R.id.tvShow);
    }

    @Override
    protected void onDestroy() {
        this.unbindService(conn);
        super.onDestroy();
    }
}
