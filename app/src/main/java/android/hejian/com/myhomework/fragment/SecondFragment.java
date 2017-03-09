package android.hejian.com.myhomework.fragment;

import android.hejian.com.myhomework.R;
import android.hejian.com.myhomework.adapter.ChannelAdapter;
import android.hejian.com.myhomework.bean.HomeBean;
import android.hejian.com.myhomework.utils.Constants;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.extras.SoundPullEventListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by 何健 on 2017/3/9.
 */

public class SecondFragment extends BaseFragment {
    private PullToRefreshListView refreshListView;
    private ListView lv_channel;

    private HomeBean homeBean;
    private String url =Constants.HOME_URL;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.item_second, null);
        refreshListView = (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);//refreshListView被实例化了
        lv_channel =refreshListView.getRefreshableView();

        /**
         * Add Sound Event Listener
         */
        SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(mContext);
        soundListener.addSoundEvent(PullToRefreshBase.State.PULL_TO_REFRESH, R.raw.pull_event);
        soundListener.addSoundEvent(PullToRefreshBase.State.RESET, R.raw.reset_sound);
        soundListener.addSoundEvent(PullToRefreshBase.State.REFRESHING, R.raw.refreshing_sound);
        refreshListView.setOnPullEventListener(soundListener);

        //设置下拉和上拉刷新
        refreshListView.setOnRefreshListener(new MyOnRefreshListener2());

        return view;
    }

    class MyOnRefreshListener2 implements PullToRefreshBase.OnRefreshListener2<ListView> {

        @Override
        public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
            Toast.makeText(mContext, "下拉刷新", Toast.LENGTH_SHORT).show();
            getDataFromNet(url);

        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
            Toast.makeText(mContext, "上拉刷新", Toast.LENGTH_SHORT).show();
            getDataFromNet(url);
        }
    }

    @Override
    protected void initData() {
        super.initData();
        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get()
                .url(url)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "联网请求失败"+e.getMessage());
                        refreshListView.onRefreshComplete();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("TAG", "联网请求成功"+response);
                        processData(response);
                        refreshListView.onRefreshComplete();

                    }
                });
    }

    private void processData(String json) {
        homeBean = JSON.parseObject(json, HomeBean.class);
        //设置listView适配器
        //设置Gridview适配器
        ChannelAdapter channelAdapter = new ChannelAdapter(mContext, homeBean.getResult().getChannel_info());
        lv_channel.setAdapter(channelAdapter);

        lv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
