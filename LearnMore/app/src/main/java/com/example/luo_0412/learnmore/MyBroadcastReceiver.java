package com.example.luo_0412.learnmore;

/**
 * Created by Luo_0412 on 2016/10/14.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received in MyBroadcastReceive",
                Toast.LENGTH_SHORT).show();

        // 在广播接收器中是不允许开启线程的，
        // 当onReceive()方法运行了较长时间而没有结束时，程序就会报错
        // ANR(Application No Response)

        // 广播接收器更多的是扮演一种打开程序其他组件的角色，
        // 比如创建一条状态栏通知，或者启动一个服务等
    }

}
