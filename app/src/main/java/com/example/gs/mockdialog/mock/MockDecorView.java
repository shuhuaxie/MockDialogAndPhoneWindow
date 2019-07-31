package com.example.gs.mockdialog.mock;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
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

    public void setWindow(MockWindow mockWindow) {
        mWindow = mockWindow;
    }
}
