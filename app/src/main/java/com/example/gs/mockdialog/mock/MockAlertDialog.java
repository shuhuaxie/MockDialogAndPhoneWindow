package com.example.gs.mockdialog.mock;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Window;

public class MockAlertDialog extends MockDialog {
    private MockAlertController mAlert;

    MockAlertDialog(Context context, @StyleRes int themeResId, boolean createContextThemeWrapper) {
        super(context, 0);
        mAlert = MockAlertController.create(getContext(), this, getWindow());
    }

    public @Nullable Window getWindow() {
        return mWindow;
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
            P.apply(dialog.mAlert);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            return dialog;
        }
    }
}
