package com.xiongmeng.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class SeckillAdapter extends RecyclerView.Adapter <SeckillAdapter.ViewHolder> {

    private final Context mContext;
    private final List<HomeBean.ResultBean.SeckillInfoBean.ListBean> datas;


    public SeckillAdapter(Context mContext, HomeBean.ResultBean.SeckillInfoBean seckill_info) {
        this.mContext = mContext;
        this.datas = seckill_info.getList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(mContext, R.layout.item_seckill, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //1.根据位置得到数据
        HomeBean.ResultBean.SeckillInfoBean.ListBean listBean = datas.get(position);
        //2.绑定数据
        holder.tvCoverPrice.setText("￥"+listBean.getCover_price());
        holder.tvOriginPrice.setText("￥"+listBean.getOrigin_price());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+listBean.getFigure()).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.ivFigure);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_figure)
        ImageView ivFigure;
        @BindView(R.id.tv_cover_price)
        TextView tvCoverPrice;
        @BindView(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @BindView(R.id.ll_root)
        LinearLayout llRoot;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        listener.onItemClick(itemView,getLayoutPosition());
                    }
                }
            });
        }

    }
    public interface OnItemClickListener{
        public void onItemClick(View view, int position);
    }
    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
