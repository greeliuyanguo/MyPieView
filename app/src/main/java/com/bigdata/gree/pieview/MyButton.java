package com.bigdata.gree.pieview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Author:liuyanguo
 * Date:2017/10/8
 * Time:10:58
 * Description:Nothing to say.
 */

@SuppressLint("AppCompatCustomView")
class MyButton extends Button {

    public static final String MODE_ENABLE = "enable";
    public static final String MODE_STOP = "stop";

    private String mMode;

    public MyButton(Context context) {
        this(context, null);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setMode(String mode) {
        mMode = mode;
    }

    public String getMode() {
        return this.mMode;
    }
}