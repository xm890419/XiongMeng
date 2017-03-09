package com.xiongmeng.android.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xiongmeng.android.bean.HomeBean;
import com.xiongmeng.android.utils.Constants;

import java.util.List;



/**
 * Created by 熊猛 on 2017/3/9.
 */

public class ActAdapter extends PagerAdapter {
    private final Context mContext;
    private final List<HomeBean.ResultBean.ActInfoBean> datas;

    public ActAdapter(Context mContext, List<HomeBean.ResultBean.ActInfoBean> act_info) {
        this.mContext = mContext;
        this.datas = act_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        HomeBean.ResultBean.ActInfoBean actInfoBean = datas.get(position);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+actInfoBean.getIcon_url()).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
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

    public interface OnItemClickListener{
        public void onItemClick(View view, int position);
    }
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
