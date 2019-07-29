package com.example.gs.mockdialog.mock;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Window;
import com.example.gs.mockdialog.R;

public class MockAlertController {
    private CharSequence mMessage;
    private int mAlertDialogLayout;
    protected MockAlertController(Context context, DialogInterface di, Window window) {
//        TypedArray a = context.obtainStyledAttributes((AttributeSet)null,  R.styleable.AlertDialog, attr.alertDialogStyle, 0);
//        this.mAlertDialogLayout = a.getResourceId(R.styleable.AlertDialog_layout, R.layout.alert_dialog);

    }
    public void setMessage(CharSequence message) {
        mMessage = message;
//        if (mMessageView != null) {
//            mMessageView.setText(message);
//        }
    }
    public void installContent(AlertParams params) {
        params.apply(this);
        installContent();
    }

    public void installContent() {
//        int contentView = selectContentView();
//        mWindow.setContentView(contentView);
//        setupView();
    }
    public static MockAlertController create(Context context, MockAlertDialog di, Window window) {
        return new MockAlertController(context, di, window);
    }

    public static class AlertParams {
        public final Context mContext;
        public boolean mCancelable;
        public final LayoutInflater mInflater;
        public CharSequence mMessage;

        public AlertParams(Context context) {
            this.mContext = context;
            this.mCancelable = true;
            this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        public void apply(MockAlertController dialog) {
            if (mMessage != null) {
                dialog.setMessage(mMessage);
            }
        }
    }
}
