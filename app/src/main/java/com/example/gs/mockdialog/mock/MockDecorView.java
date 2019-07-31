package com.example.gs.mockdialog.mock;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class MockDecorView extends FrameLayout {
    private MockWindow mWindow;
    public MockDecorView( Context context) {
        super(context);
    }

    public MockDecorView( Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MockDecorView( Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MockDecorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    MockDecorView(Context context, int featureId, MockWindow window,
              WindowManager.LayoutParams params) {
        super(context);
        setWindow(window);
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        final Window.Callback cb = mWindow.getCallback();
        final boolean handled = cb != null  ? cb.dispatchKeyEvent(event)
                : super.dispatchKeyEvent(event);
        if (handled) {
            return true;
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final Window.Callback cb = mWindow.getCallback();
        return cb != null
                ? cb.dispatchTouchEvent(ev) : super.dispatchTouchEvent(ev);
    }

    public void setWindow(MockWindow mockWindow) {
        mWindow = mockWindow;
    }
}
