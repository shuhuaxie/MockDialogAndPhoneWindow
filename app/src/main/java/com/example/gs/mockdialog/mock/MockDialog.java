package com.example.gs.mockdialog.mock;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import com.example.gs.mockdialog.R;

public class MockDialog implements DialogInterface, KeyEvent.Callback, Window.Callback {
    private final WindowManager mWindowManager;
    protected boolean mCancelable = true;
    final Context mContext;
    final MockWindow mWindow;
    private boolean mShowing = false;
    private boolean mCanceled = false;
    private boolean mCreated = false;
    View mDecor;

    public MockDialog(@NonNull Context context) {
        this(context, 0, true);
    }

    public MockDialog(@NonNull Context context, @StyleRes int themeResId) {
        this(context, themeResId, true);
    }

    MockDialog(@NonNull Context context, @StyleRes int themeResId, boolean createContextThemeWrapper) {
        if (createContextThemeWrapper) {
            if (themeResId == 0) {
                themeResId = R.style.MockDialog;
            }
            mContext = new ContextThemeWrapper(context, themeResId);
        } else {
            mContext = context;
        }

        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        final MockWindow w = new MockWindow(mContext);
        mWindow = w;
        w.setCallback(this);

        w.setWindowManager(mWindowManager, null, null);
        w.setGravity(Gravity.CENTER);

    }

    public final @NonNull
    Context getContext() {
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
        mWindow.setCloseOnTouchOutside(cancel);
    }

    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if (mCancelable && mShowing && mWindow.shouldCloseOnTouch(mContext, event)) {
            cancel();
            return true;
        }

        return false;
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
        if (!mCreated) {
            dispatchOnCreate(null);
        }
        onStart();
        mDecor = mWindow.getDecorView();
        WindowManager.LayoutParams l = mWindow.getAttributes();
        l.height = -2;
        l.width = -2;
//        l.flags = 8519682;
//        l.dimAmount = 0.6f;
//        l.type = 2;

        mWindowManager.addView(mDecor, l);

        mShowing = true;
//        sendShowMessage();
    }

    void dispatchOnCreate(Bundle savedInstanceState) {
        if (!mCreated) {
            onCreate(savedInstanceState);
            mCreated = true;
        }
    }

    private void onStart() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_ESCAPE) {
            event.startTracking();
            return true;
        }

        return false;
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, @NonNull KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_ESCAPE)
                && event.isTracking()
                && !event.isCanceled()) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int count, KeyEvent event) {
        return false;
    }

    public void onBackPressed() {
        if (mCancelable) {
            cancel();
        }
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

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return event.dispatch(this, mDecor != null
                ? mDecor.getKeyDispatcherState() : null, this);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return false;
    }

    @Override
    public View onCreatePanelView(int featureId) {
        return null;
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        return false;
    }

    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        return false;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return false;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return false;
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams attrs) {

    }

    @Override
    public void onContentChanged() {

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

    }

    @Override
    public void onAttachedToWindow() {

    }

    @Override
    public void onDetachedFromWindow() {

    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {

    }

    @Override
    public boolean onSearchRequested() {
        return false;
    }

    @Override
    public boolean onSearchRequested(SearchEvent searchEvent) {
        return false;
    }

    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        return null;
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {

    }

    @Override
    public void onActionModeFinished(ActionMode mode) {

    }
}
