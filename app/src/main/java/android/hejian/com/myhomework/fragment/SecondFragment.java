package android.hejian.com.myhomework.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 何健 on 2017/3/9.
 */

public class SecondFragment extends BaseFragment {

    private TextView textView;
    @Override
    public View initView() {
        textView=new TextView(mContext);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(20);
        return textView;
    }

    @Override
    protected void initData() {
        super.initData();
        textView.setText("我的second页面");
    }
}
