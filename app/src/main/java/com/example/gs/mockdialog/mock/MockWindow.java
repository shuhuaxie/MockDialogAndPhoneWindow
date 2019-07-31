package com.example.gs.mockdialog.mock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MockWindow extends Window {
    private MockDecorView mDecor;
    private LayoutInflater mLayoutInflater;
    private int mTheme = -1;
    private boolean mCloseOnTouchOutside;

    public MockWindow(Context context) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void takeSurface(SurfaceHolder.Callback2 callback) {

    }

    @Override
    public void takeInputQueue(InputQueue.Callback callback) {

    }

    @Override
    public boolean isFloating() {
        return false;
    }

    @Override
    public void setContentView(int layoutResID) {
        if (mDecor == null) {
            installDecor();
        }
        mLayoutInflater.inflate(layoutResID, mDecor);
    }

    @Override
    public void setContentView(View view) {
        if (mDecor == null ) {
            installDecor();
        }
        mDecor.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mDecor.addView(view, params);
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {

    }

    @Override
    public View getCurrentFocus() {
        return null;
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        return null;
    }

    @Override
    public void setTitle(CharSequence title) {

    }

    @Override
    public void setTitleColor(int textColor) {

    }

    @Override
    public void openPanel(int featureId, KeyEvent event) {

    }

    @Override
    public void closePanel(int featureId) {

    }

    @Override
    public void togglePanel(int featureId, KeyEvent event) {

    }

    @Override
    public void invalidatePanelMenu(int featureId) {

    }

    @Override
    public boolean performPanelShortcut(int featureId, int keyCode, KeyEvent event, int flags) {
        return false;
    }

    @Override
    public boolean performPanelIdentifierAction(int featureId, int id, int flags) {
        return false;
    }

    @Override
    public void closeAllPanels() {

    }

    @Override
    public boolean performContextMenuIdentifierAction(int id, int flags) {
        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }

    @Override
    public void setBackgroundDrawable(Drawable drawable) {

    }

    @Override
    public void setFeatureDrawableResource(int featureId, int resId) {

    }

    @Override
    public void setFeatureDrawableUri(int featureId, Uri uri) {

    }

    @Override
    public void setFeatureDrawable(int featureId, Drawable drawable) {

    }

    @Override
    public void setFeatureDrawableAlpha(int featureId, int alpha) {

    }

    @Override
    public void setFeatureInt(int featureId, int value) {

    }

    @Override
    public void takeKeyEvents(boolean get) {

    }

    @Override
    public boolean superDispatchKeyEvent(KeyEvent event) {
        return false;
    }

    @Override
    public boolean superDispatchKeyShortcutEvent(KeyEvent event) {
        return false;
    }

    @Override
    public boolean superDispatchTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean superDispatchTrackballEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean superDispatchGenericMotionEvent(MotionEvent event) {
        return false;
    }

    @Override
    public View getDecorView() {
        if (mDecor == null ) {
            installDecor();
        }
        return mDecor;
    }

    private void installDecor() {
        if (mDecor == null) {
            mDecor = generateDecor(-1);
        } else {
            mDecor.setWindow(this);
        }
        generateLayout(mDecor);
    }
    public void setCloseOnTouchOutside(boolean close) {
        mCloseOnTouchOutside = close;
    }

    public boolean shouldCloseOnTouch(Context context, MotionEvent event) {
        final boolean isOutside =
                event.getAction() == MotionEvent.ACTION_DOWN && isOutOfBounds(context, event)
                        || event.getAction() == MotionEvent.ACTION_OUTSIDE;
        if (mCloseOnTouchOutside && peekDecorView() != null && isOutside) {
            return true;
        }
        return false;
    }
    private boolean isOutOfBounds(Context context, MotionEvent event) {
        final int x = (int) event.getX();
        final int y = (int) event.getY();
        final int slop = ViewConfiguration.get(context).getScaledWindowTouchSlop();
        final View decorView = getDecorView();
        return (x < -slop) || (y < -slop)
                || (x > (decorView.getWidth()+slop))
                || (y > (decorView.getHeight()+slop));
    }
    private void generateLayout(MockDecorView decor) {
//        TypedArray a = getWindowStyle(); //Content from style
        WindowManager.LayoutParams params = getAttributes();
        setLayout(WRAP_CONTENT, WRAP_CONTENT);
        params.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        params.dimAmount =  0.5f;
    }

    @SuppressLint("NewApi")
    protected MockDecorView generateDecor(int featureId) {
        // System process doesn't have application context and in that case we need to directly use
        // the context we have. Otherwise we want the application context, so we don't cling to the
        // activity.
        Context context;
//        if (mUseDecorContext) {
//            Context applicationContext = getContext().getApplicationContext();
//            if (applicationContext == null) {
//                context = getContext();
//            } else {
//                context = new DecorContext(applicationContext, getContext().getResources());
//                if (mTheme != -1) {
//                    context.setTheme(mTheme);
//                }
//            }
//        } else {
            context = getContext();
//        }
        return new MockDecorView(context, featureId, this, getAttributes());
    }
    @Override
    public View peekDecorView() {
        return mDecor;
    }

    @Override
    public Bundle saveHierarchyState() {
        return null;
    }

    @Override
    public void restoreHierarchyState(Bundle savedInstanceState) {

    }

    @Override
    protected void onActive() {

    }

    @Override
    public void setChildDrawable(int featureId, Drawable drawable) {

    }

    @Override
    public void setChildInt(int featureId, int value) {

    }

    @Override
    public boolean isShortcutKey(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void setVolumeControlStream(int streamType) {

    }

    @Override
    public int getVolumeControlStream() {
        return 0;
    }

    @Override
    public int getStatusBarColor() {
        return 0;
    }

    @Override
    public void setStatusBarColor(int color) {

    }

    @Override
    public int getNavigationBarColor() {
        return 0;
    }

    @Override
    public void setNavigationBarColor(int color) {

    }

    @Override
    public void setDecorCaptionShade(int decorCaptionShade) {

    }

    @Override
    public void setResizingCaptionDrawable(Drawable drawable) {

    }
}
