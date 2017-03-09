package com.xiongmeng.android.frangment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 熊猛 on 2017/3/9.
 */

public class TypeFragment extends BaseFrgment {
    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }
    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "分类数据初始化了");
        textView.setText("分类");
    }
}
