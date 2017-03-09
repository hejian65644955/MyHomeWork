package android.hejian.com.myhomework.adapter;

import android.content.Context;
import android.hejian.com.myhomework.R;
import android.hejian.com.myhomework.bean.HomeBean;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.zhy.magicviewpager.transformer.RotateYTransformer;

import java.util.List;

/**
 * Created by 何健 on 2017/3/9.
 */

public class HomeAdapter extends RecyclerView.Adapter {

    /**
     * 频道
     */
    public static final int CHANNEL = 0;

    /**
     * 活动
     */
    public static final int ACT = 1;
    private final Context mContext;
    private final HomeBean.ResultBean datas;

    //当前类型
    public int currentType = CHANNEL;
    private final LayoutInflater inflater;



    public HomeAdapter(Context mContext, HomeBean.ResultBean result) {
        this.mContext = mContext;
        this.datas = result;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;

        }
        return currentType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case CHANNEL:
                return new ChannelViewHolder(inflater.inflate(R.layout.channel_item, null), mContext);
            case ACT:
                return new ActViewHolder(inflater.inflate(R.layout.act_item,null),mContext);
        }
        return null;
    }

    class ActViewHolder extends RecyclerView.ViewHolder{

        private final Context mContext;
        private ViewPager act_viewpager;
        private ViewPagerAdapter viewPagerAdapter;

        public ActViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext=mContext;
            act_viewpager = (ViewPager) itemView.findViewById(R.id.act_viewpager);
        }

        public void setData(List<HomeBean.ResultBean.ActInfoBean> act_info) {
            viewPagerAdapter = new ViewPagerAdapter(mContext, act_info);
            act_viewpager.setAdapter(viewPagerAdapter);

            act_viewpager.setPageMargin(20);//设置page间间距，自行根据需求设置
            act_viewpager.setOffscreenPageLimit(3);//>=3
            act_viewpager.setAdapter(viewPagerAdapter);
            //setPageTransformer 决定动画效果
            act_viewpager.setPageTransformer(true, new
                    RotateYTransformer());

            viewPagerAdapter.setOnItemClickListener(new ViewPagerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(mContext, ""+position, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private GridView gvChannel;
        private final Context mContext;

        public ChannelViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
            gvChannel = (GridView) itemView.findViewById(R.id.gv_channel);
        }

        public void setData(List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {
            //设置Gridview适配器
            ChannelAdapter channelAdapter = new ChannelAdapter(mContext, channel_info);
            gvChannel.setAdapter(channelAdapter);

            gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(position<9){
                        Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case CHANNEL:
                ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
                channelViewHolder.setData(datas.getChannel_info());
                break;
            case ACT:
                ActViewHolder actViewHolder = (ActViewHolder) holder;
                actViewHolder.setData(datas.getAct_info());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
