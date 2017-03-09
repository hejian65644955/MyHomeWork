package android.hejian.com.myhomework.adapter;

import android.hejian.com.myhomework.fragment.BaseFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 何健 on 2017/3/4.
 */

public class CommunityViewPagerAdapter extends FragmentPagerAdapter {
    private final List<BaseFragment> fragments;
    private String[] titles = new String[]{"新帖", "热帖"};


    public CommunityViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments =fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
