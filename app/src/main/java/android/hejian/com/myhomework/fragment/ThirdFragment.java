package android.hejian.com.myhomework.fragment;

import android.hejian.com.myhomework.R;
import android.hejian.com.myhomework.adapter.CommunityViewPagerAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 何健 on 2017/3/9.
 */

public class ThirdFragment extends BaseFragment {
    private TabLayout tablayout;
    private List<BaseFragment> fragments;
    private CommunityViewPagerAdapter adapter;
    private ViewPager view_pager;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.item_third, null);
        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        view_pager = (ViewPager) view.findViewById(R.id.view_pager);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        initFragment();

        //设置适配器
        adapter = new CommunityViewPagerAdapter(getFragmentManager(), fragments);
        view_pager.setAdapter(adapter);

        //关联ViewPager
        tablayout.setupWithViewPager(view_pager);
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());

    }
}
