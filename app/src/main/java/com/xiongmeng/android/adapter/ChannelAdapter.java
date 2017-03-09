package com.xiongmeng.android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xiongmeng.android.R;
import com.xiongmeng.android.bean.HomeBean;
import com.xiongmeng.android.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 熊猛 on 2017/3/9.
 */

public class ChannelAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<HomeBean.ResultBean.ChannelInfoBean> datas;

    public ChannelAdapter(Context mContext, List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {
        this.mContext = mContext;
        this.datas = channel_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_channel, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //根据位置取对应的数据
        HomeBean.ResultBean.ChannelInfoBean channelInfoBean = datas.get(position);
        viewHolder.tvChannel.setText(channelInfoBean.getChannel_name());
        //Glide请求图片
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+channelInfoBean.getImage()).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.ivChannel);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_channel)
        ImageView ivChannel;
        @BindView(R.id.tv_channel)
        TextView tvChannel;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
