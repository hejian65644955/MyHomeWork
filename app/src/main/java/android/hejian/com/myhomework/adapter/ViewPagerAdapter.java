package android.hejian.com.myhomework.adapter;

import android.content.Context;
import android.hejian.com.myhomework.bean.HomeBean;
import android.hejian.com.myhomework.utils.Constants;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 何健 on 2017/3/9.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private final Context mContext;
    private final List<HomeBean.ResultBean.ActInfoBean> datas;

    public ViewPagerAdapter(Context mContext, List<HomeBean.ResultBean.ActInfoBean> act_info) {
        this.mContext =mContext;
        this.datas =act_info;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext)
                .load(Constants.BASE_URL_IMAGE+datas.get(position).getIcon_url())
                .into(imageView);
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onItemClick(v,position);
                }
            }
        });

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==object;
    }

    //设置item的点击事件
    public interface OnItemClickListener{
        public void onItemClick(View view,int position);
    }
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener =listener;
    }
}
