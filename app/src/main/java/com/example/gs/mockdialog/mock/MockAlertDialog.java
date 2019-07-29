package com.example.gs.mockdialog.mock;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import com.example.gs.mockdialog.MainActivity;

public class MockAlertDialog extends Dialog {
    private MockAlertController mAlert;
    public MockAlertDialog(Context context) {
        super(context);
    }

    public MockAlertDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MockAlertDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
    MockAlertDialog(Context context, @StyleRes int themeResId, boolean createContextThemeWrapper) {
        super(context, 0);
        mAlert = MockAlertController.create(getContext(), this, getWindow());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAlert.installContent();
    }

    public static class Builder {
        private final MockAlertController.AlertParams P;

        public Builder(Context context) {
            P = new MockAlertController.AlertParams(context);
        }

        public void setMessage(String message) {
            P.mMessage = message;
        }

        public MockAlertDialog create() {
            final MockAlertDialog dialog = new MockAlertDialog(P.mContext, 0, false);
            return dialog;
        }
    }
}
