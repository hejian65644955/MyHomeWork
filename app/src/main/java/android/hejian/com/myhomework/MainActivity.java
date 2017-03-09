package android.hejian.com.myhomework;

import android.hejian.com.myhomework.fragment.BaseFragment;
import android.hejian.com.myhomework.fragment.FirstFragment;
import android.hejian.com.myhomework.fragment.SecondFragment;
import android.hejian.com.myhomework.fragment.ThirdFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {


    @InjectView(R.id.fl_main)
    FrameLayout flMain;
    RadioGroup rgMain;
    private ArrayList<BaseFragment> fragments;
    //记录点击位置
    private int position;
    //记录缓存页面
    private BaseFragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        rgMain = (RadioGroup) findViewById(R.id.rg_main);
        initFragment();
        initListener();


    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_first:
                        position = 0;
                        break;
                    case R.id.rb_second:
                        position = 1;
                        break;
                    case R.id.rb_third:
                        position = 2;
                        break;
                }
                //切换到对应的fragment
                BaseFragment currentFragment = fragments.get(position);
                switchFragment(currentFragment);
            }
        });
        rgMain.check(R.id.rb_first);


    }

    private void switchFragment(BaseFragment currentFragment) {

        if (currentFragment != tempFragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (currentFragment.isAdded()) {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.show(currentFragment);
            } else {
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.add(R.id.fl_main, currentFragment);
            }
            ft.commit();
            tempFragment = currentFragment;

        }
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new FirstFragment());
        fragments.add(new SecondFragment());
        fragments.add(new ThirdFragment());
    }

}
