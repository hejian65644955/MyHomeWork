package android.hejian.com.myhomework.fragment;

import android.hejian.com.myhomework.R;
import android.hejian.com.myhomework.adapter.HomeAdapter;
import android.hejian.com.myhomework.bean.HomeBean;
import android.hejian.com.myhomework.utils.CacheUtils;
import android.hejian.com.myhomework.utils.Constants;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 何健 on 2017/3/9.
 */

public class FirstFragment extends BaseFragment {

    private RecyclerView rvFirst;
    private HomeAdapter homeAdapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_frist, null);
        rvFirst = (RecyclerView) view.findViewById(R.id.rv_first);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        String string = CacheUtils.getString(mContext, "atguigu");
        if(!TextUtils.isEmpty(string)){
            processData(string);
        }
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get()
                .url(Constants.HOME_URL)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网请求失败"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "联网请求成功");
                        CacheUtils.putString(mContext,"atguigu",response);
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        HomeBean homeBean = JSON.parseObject(json, HomeBean.class);
        Log.e("TAG", "解析成功=="+homeBean.getResult().getRecommend_info().get(0).getName());
        //设置recycleView适配器
        homeAdapter = new HomeAdapter(mContext, homeBean.getResult());
        rvFirst.setAdapter(homeAdapter);
        //设置布局管理器
        rvFirst.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
