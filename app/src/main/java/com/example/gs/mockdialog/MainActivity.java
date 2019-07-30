package com.example.gs.mockdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import com.example.gs.mockdialog.mock.MockAlertDialog;
import com.example.gs.mockdialog.mock.MockWindow;

public class MainActivity extends AppCompatActivity {

    private Window mWindow;
    private View mDecor;
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.show_system_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder normalDialog = new AlertDialog.Builder(
                        MainActivity.this);
                normalDialog.setMessage("hi");
                AlertDialog alertDialog = normalDialog.create();
                alertDialog.show();
            }
        });
        findViewById(R.id.show_mock_dialog).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                MockAlertDialog.Builder mockDialog = new MockAlertDialog.Builder(MainActivity.this);
                mockDialog.setMessage("mock hi");
                mockDialog.create().show();

//                WindowManager mWindowManager = (WindowManager) MainActivity.this.getSystemService(Context.WINDOW_SERVICE);
//
//                // init
//                final Window w = new MockWindow(MainActivity.this);
//                mWindow = w;
//                w.setWindowManager(mWindowManager, null, null);
//                w.setGravity(Gravity.CENTER);
//                w.setCallback(MainActivity.this);
//
//                // content
//                mInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View contentView = mInflater.inflate(R.layout.mock_dialog,null);
//                w.setContentView(contentView);
//
//                // show
//                mDecor = mWindow.getDecorView();
////                WindowManager.LayoutParams l = mWindow.getAttributes();
////                mWindowManager.addView(mDecor, l);
//                mWindowManager.addView(mDecor, new WindowManager.LayoutParams());
            }
        });
    }
}
