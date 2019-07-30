package com.example.gs.mockdialog.mock;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.util.TypedValue;
import android.view.*;

public class MockDialog implements DialogInterface {
    private final WindowManager mWindowManager;
    protected boolean mCancelable = true;
    final Context mContext;
    final Window mWindow;
    private boolean mShowing = false;
    private boolean mCanceled = false;
    View mDecor;
    public MockDialog(@NonNull Context context) {
        this(context, 0, true);
    }
    public MockDialog(@NonNull Context context, @StyleRes int themeResId) {
        this(context, themeResId, true);
    }

    MockDialog(@NonNull Context context, @StyleRes int themeResId, boolean createContextThemeWrapper) {
        if (createContextThemeWrapper) {

            mContext = new ContextThemeWrapper(context, themeResId);
        } else {
            mContext = context;
        }

        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        final Window w = new MockWindow(mContext);
        mWindow = w;
//        w.setCallback(this);

        w.setWindowManager(mWindowManager, null, null);
        w.setGravity(Gravity.CENTER);

    }
    public final @NonNull Context getContext() {
        return mContext;
    }
    public void setCancelable(boolean flag) {
        mCancelable = flag;
        updateWindowForCancelable();
    }
    protected void onCreate(Bundle savedInstanceState) {
        
    }
    private void updateWindowForCancelable() {
//        mWindow.setCloseOnSwipeEnabled(mCancelable);
    }

    public void setCanceledOnTouchOutside(boolean cancel) {
        if (cancel && !mCancelable) {
            mCancelable = true;
            updateWindowForCancelable();
        }

//        mWindow.setCloseOnTouchOutside(cancel);
    }

    public void show() {
        if (mShowing) {
            if (mDecor != null) {
                if (mWindow.hasFeature(Window.FEATURE_ACTION_BAR)) {
                    mWindow.invalidatePanelMenu(Window.FEATURE_ACTION_BAR);
                }
                mDecor.setVisibility(View.VISIBLE);
            }
            return;
        }
        mCanceled = false;
//        if (!mCreated) {
//            dispatchOnCreate(null);
//        } else {
//            // Fill the DecorView in on any configuration changes that
//            // may have occured while it was removed from the WindowManager.
//            final Configuration config = mContext.getResources().getConfiguration();
//            mWindow.getDecorView().dispatchConfigurationChanged(config);
//        }
        onStart();
        mDecor = mWindow.getDecorView();
//        if (mActionBar == null && mWindow.hasFeature(Window.FEATURE_ACTION_BAR)) {
//            final ApplicationInfo info = mContext.getApplicationInfo();
//            mWindow.setDefaultIcon(info.icon);
//            mWindow.setDefaultLogo(info.logo);
//            mActionBar = new WindowDecorActionBar(this);
//        }
        WindowManager.LayoutParams l = mWindow.getAttributes();
        boolean restoreSoftInputMode = false;
        if ((l.softInputMode
                & WindowManager.LayoutParams.SOFT_INPUT_IS_FORWARD_NAVIGATION) == 0) {
            l.softInputMode |=
                    WindowManager.LayoutParams.SOFT_INPUT_IS_FORWARD_NAVIGATION;
            restoreSoftInputMode = true;
        }
        mWindowManager.addView(mDecor, l);
        if (restoreSoftInputMode) {
            l.softInputMode &=
                    ~WindowManager.LayoutParams.SOFT_INPUT_IS_FORWARD_NAVIGATION;
        }
        mShowing = true;
//        sendShowMessage();
    }

    private void onStart() {

    }

    @Override
    public void cancel() {

        dismiss();
    }

    @Override
    public void dismiss() {
        dismissDialog();
    }
    void dismissDialog() {
        if (mDecor == null || !mShowing) {
            return;
        }
//        if (mWindow.isDestroyed()) {
////            Log.e(TAG, "Tried to dismissDialog() but the Dialog's window was already destroyed!");
//            return;
//        }
        try {
            mWindowManager.removeViewImmediate(mDecor);
        } finally {
//            if (mActionMode != null) {
//                mActionMode.finish();
//            }
            mDecor = null;
            mWindow.closeAllPanels();
            onStop();
            mShowing = false;

        }
    }

    private void onStop() {

    }
}
