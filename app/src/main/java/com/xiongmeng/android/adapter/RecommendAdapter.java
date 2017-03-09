package com.xiongmeng.android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiongmeng.android.R;
import com.xiongmeng.android.bean.HomeBean;
import com.xiongmeng.android.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 熊猛 on 2017/3/9.
 */

public class RecommendAdapter extends BaseAdapter {
    private final List<HomeBean.ResultBean.RecommendInfoBean> datas;
    private final Context mContext;

    public RecommendAdapter(Context mContext, List<HomeBean.ResultBean.RecommendInfoBean> recommend_info) {
        this.mContext = mContext;
        this.datas = recommend_info;
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
            convertView = View.inflate(mContext, R.layout.item_recommend, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        HomeBean.ResultBean.RecommendInfoBean recommendInfoBean = datas.get(position);
        viewHolder.tvName.setText(recommendInfoBean.getName());
        viewHolder.tvPrice.setText("￥"+recommendInfoBean.getCover_price());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+recommendInfoBean.getFigure()).into(viewHolder.ivRecommend);
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_recommend)
        ImageView ivRecommend;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
