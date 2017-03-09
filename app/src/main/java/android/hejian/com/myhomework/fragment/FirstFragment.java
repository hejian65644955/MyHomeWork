package android.hejian.com.myhomework.fragment;

import android.hejian.com.myhomework.R;
import android.hejian.com.myhomework.utils.Constants;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by 何健 on 2017/3/9.
 */

public class FirstFragment extends BaseFragment {

    @InjectView(R.id.rv_first)
    RecyclerView rvFirst;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_frist, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
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
                        Log.e("TAG", "联网请求成功"+response);
                       // processData(response);

                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
