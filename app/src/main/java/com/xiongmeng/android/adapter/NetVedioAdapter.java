package com.xiongmeng.android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiongmeng.android.R;
import com.xiongmeng.android.bean.MediaItem;
import com.xiongmeng.android.utils.DensityUtil;
import com.xiongmeng.android.utils.Utils;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 熊猛 on 2017/3/9.
 */

public class NetVedioAdapter extends BaseAdapter {
    private final Context mContext;
    private final ArrayList<MediaItem> datas;
    private final ImageOptions imageOptions;
    private Utils utils;

    public NetVedioAdapter(Context mContext, ArrayList<MediaItem> mediaItems) {
        this.mContext = mContext;
        this.datas = mediaItems;
        utils = new Utils();
        imageOptions = new ImageOptions.Builder()
                .setSize(DensityUtil.dip2px(mContext, 120), DensityUtil.dip2px(mContext, 120))
                .setRadius(DensityUtil.dip2px(mContext, 5))
                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                .setCrop(true) // 很多时候设置了合适的scaleType也不需要它.
                // 加载中或错误图片的ScaleType
                //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.drawable.video_default)//加载过程中的默认图片
                .setFailureDrawableId(R.drawable.video_default)//就挨着出错的图片
                .build();
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
            convertView = View.inflate(mContext, R.layout.item_net_video, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        MediaItem mediaItem = datas.get(position);
        viewHolder.tvName.setText(mediaItem.getName());//设置名称
        //设置文件大小
        viewHolder.tvSize.setText(mediaItem.getDuration() + "秒");
        //设置时间
        viewHolder.tvDuration.setText(mediaItem.getDesc());
        //请求图片
        //Glide.with(mContext).load(Constants.NET_URL+mediaItem.getImageUrl()).into(viewHolder.ivIcon);
        x.image().bind(viewHolder.ivIcon,mediaItem.getImageUrl(),imageOptions);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.fl)
        FrameLayout fl;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_duration)
        TextView tvDuration;
        @BindView(R.id.tv_size)
        TextView tvSize;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}