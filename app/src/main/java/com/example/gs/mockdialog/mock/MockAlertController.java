package com.example.gs.mockdialog.mock;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.*;
import android.widget.TextView;
import com.example.gs.mockdialog.R;

public class MockAlertController {
    private CharSequence mMessage;
    protected TextView mMessageView;
    private int mAlertDialogLayout;
    private final Context mContext;
    private final DialogInterface mDialogInterface;
    protected final Window mWindow;

    protected MockAlertController(Context context, DialogInterface di, Window window) {
        mContext = context;
        mDialogInterface = di;
        mWindow = window;
//        TypedArray a = context.obtainStyledAttributes((AttributeSet)null,  R.styleable.AlertDialog, attr.alertDialogStyle, 0);
        mAlertDialogLayout = R.layout.mock_dialog;
    }

    public void setMessage(CharSequence message) {
        mMessage = message;
        if (mMessageView != null) {
            mMessageView.setText(message);
        }
    }

    public void installContent(AlertParams params) {
        params.apply(this);
        installContent();
    }

    public void installContent() {
        int contentView = selectContentView();
        mWindow.setContentView(contentView);
        setupView();
    }
    private int selectContentView() {

        return mAlertDialogLayout;
    }
    private void setupView() {
        final ViewGroup parentPanel = mWindow.findViewById(R.id.parentPanel);
//        final View defaultContentPanel = parentPanel.findViewById(R.id.contentPanel);
//        final ViewGroup customPanel = (ViewGroup) parentPanel.findViewById(R.id.customPanel);
//        final View customContentPanel = customPanel.findViewById(R.id.contentPanel);
//        final ViewGroup contentPanel = resolvePanel(customContentPanel, defaultContentPanel);
        setupContent(parentPanel);
    }

    protected void setupContent(ViewGroup contentPanel) {
        mMessageView = (TextView) contentPanel.findViewById(R.id.message);
        if (mMessage != null) {
            mMessageView.setText(mMessage);
        }
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
