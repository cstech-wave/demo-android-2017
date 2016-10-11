package com.example.luo_0412.helloworld;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


/**
 * Created by Luo_0412 on 2016/10/10.
 */

public class WidgetDemoActivity extends Activity implements OnClickListener {
    private Button edit_ctl_btn;
    private Button img_ctl_btn;
    private Button pgbar_ctl_btn;
    private Button pgbar_ctl_btn2;
    private Button alert_dialog_ctl_btn;
    private Button progress_dialog_ctl_btn;


    private TextView demo_text_view;
    private EditText demo_edit_text;
    private ImageView demo_image_view;
    private ProgressBar demo_progress_bar;
    private ProgressBar demo_progress_bar2;
    private RatingBar rb_normal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 基本设置
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.widget_demo);

        // 声名组件
        img_ctl_btn = (Button) findViewById(R.id.img_ctl_btn);
        pgbar_ctl_btn = (Button) findViewById(R.id.pgbar_ctl_btn);
        pgbar_ctl_btn2 = (Button) findViewById(R.id.pgbar_ctl_btn2);
        edit_ctl_btn = (Button) findViewById(R.id.edit_ctl_btn);
        edit_ctl_btn = (Button) findViewById(R.id.edit_ctl_btn);
        alert_dialog_ctl_btn = (Button) findViewById(R.id.alert_dialog_ctl_btn);
        progress_dialog_ctl_btn = (Button) findViewById(R.id.progress_dialog_ctl_btn);

        // 注册按钮的监听器
        img_ctl_btn.setOnClickListener(this);
        pgbar_ctl_btn.setOnClickListener(this);
        pgbar_ctl_btn2.setOnClickListener(this);
        alert_dialog_ctl_btn.setOnClickListener(this);
        progress_dialog_ctl_btn.setOnClickListener(this);
        edit_ctl_btn.setOnClickListener(this);

        demo_text_view = (TextView) findViewById(R.id.demo_text_view);
        demo_edit_text = (EditText) findViewById(R.id.demo_edit_text);
        demo_image_view = (ImageView) findViewById(R.id.demo_image_view);
        demo_progress_bar = (ProgressBar) findViewById(R.id.demo_progress_bar);
        demo_progress_bar2 = (ProgressBar) findViewById(R.id.demo_progress_bar2);
        rb_normal = (RatingBar) findViewById(R.id.rb_normal);

        // 星级评分监听事件
        rb_normal.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(WidgetDemoActivity.this, "rating:" + String.valueOf(rating),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    //判断事件源
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_ctl_btn:
                demo_image_view.setImageResource(R.mipmap.ic_launcher);
                break;
            case R.id.pgbar_ctl_btn:
                if (demo_progress_bar.getVisibility() == View.GONE) {
                    demo_progress_bar.setVisibility(View.VISIBLE);
                } else {
                    demo_progress_bar.setVisibility(View.GONE);
                }
                break;
            case R.id.pgbar_ctl_btn2:
                int progress = demo_progress_bar2.getProgress();
                progress = progress + 10;
                demo_progress_bar2.setProgress(progress);
                break;
            case R.id.edit_ctl_btn:
                String inputText = demo_edit_text.getText().toString();
                Toast.makeText(WidgetDemoActivity.this, inputText,
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.alert_dialog_ctl_btn:
                AlertDialog.Builder dialog = new AlertDialog.Builder
                        (WidgetDemoActivity.this);
                dialog.setTitle("This is Dialog");
                dialog.setMessage("Something important.");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface. OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                break;
            case R.id.progress_dialog_ctl_btn:
                ProgressDialog progressDialog = new ProgressDialog
                        (WidgetDemoActivity.this);
                progressDialog.setTitle("This is ProgressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            default:
                break;
        }
    }


}

