package android.hejian.com.myhomework.fragment;

import android.hejian.com.myhomework.R;
import android.view.View;

/**
 * Created by 何健 on 2017/3/9.
 */

public class FirstFragment extends BaseFragment {

    @Override
    public View initView() {
        View view =View.inflate(mContext, R.layout.fragment_frist,null);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
    }
}
