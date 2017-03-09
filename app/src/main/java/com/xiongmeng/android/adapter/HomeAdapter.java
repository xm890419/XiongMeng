package com.xiongmeng.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xiongmeng.android.R;
import com.xiongmeng.android.activity.GoodsInfoActivity;
import com.xiongmeng.android.activity.WebViewActivity;
import com.xiongmeng.android.bean.GoodsBean;
import com.xiongmeng.android.bean.HomeBean;
import com.xiongmeng.android.bean.WebViewBean;
import com.xiongmeng.android.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;
import com.youth.banner.transformer.ScaleInOutTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;


/**
 * Created by 熊猛 on 2017/3/9.
 */

public class HomeAdapter extends RecyclerView.Adapter {
    public static final String GOODS_BEAN = "goodsBean";
    public static final String WEB_VIEW_BEAN = "webViewBean";
    private final Context mContext;
    private final HomeBean.ResultBean result;
    private final LayoutInflater inflater;
    /**
     * 六种类型
     */
    /**
     * 横幅广告
     */
    public static final int BANNER = 0;
    /**
     * 频道
     */
    public static final int CHANNEL = 1;

    /**
     * 活动
     */
    public static final int ACT = 2;

    /**
     * 秒杀
     */
    public static final int SECKILL = 3;
    /**
     * 推荐
     */
    public static final int RECOMMEND = 4;
    /**
     * 热卖
     */
    public static final int HOT = 5;


    /**
     * 当前类型
     */
    public int currentType = BANNER;



    //根据位置得到对应的类型

    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            currentType = BANNER;
        } else if (position == CHANNEL) {
            currentType = CHANNEL;
        } else if (position == ACT) {
            currentType = ACT;
        } else if (position == SECKILL) {
            currentType = SECKILL;
        } else if (position == RECOMMEND) {
            currentType = RECOMMEND;
        } else if (position == HOT) {
            currentType = HOT;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        //所有的类型写完后改成6
        return 6;
    }


    public HomeAdapter(Context mContext, HomeBean.ResultBean result) {
        this.mContext = mContext;
        this.result = result;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mContext, inflater.inflate(R.layout.banner_viewpager, null));
        } else if (viewType == CHANNEL) {
            return new ChannelViewHolder(mContext, inflater.inflate(R.layout.channel_gridview, null));
        } else if (viewType == ACT) {
            return new ActViewHolder(mContext, inflater.inflate(R.layout.act_item, null));
        } else if (viewType == SECKILL) {
            return new SeckillViewHolder(mContext, inflater.inflate(R.layout.seckill_item, null));
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mContext, inflater.inflate(R.layout.recommend_item, null));
        } else if (viewType == HOT) {
            return new HotViewHolder(mContext, inflater.inflate(R.layout.hot_item, null));
        }
        return null;
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            //绑定数据
            bannerViewHolder.setData(result.getBanner_info());
        } else if (getItemViewType(position) == CHANNEL) {
            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
            channelViewHolder.setData(result.getChannel_info());
        } else if (getItemViewType(position) == ACT) {
            ActViewHolder actViewHolder = (ActViewHolder) holder;
            actViewHolder.setData(result.getAct_info());
        } else if (getItemViewType(position) == SECKILL) {
            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
            seckillViewHolder.setData(result.getSeckill_info());
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData(result.getRecommend_info());
        } else if (getItemViewType(position) == HOT) {
            HotViewHolder hotViewHolder = (HotViewHolder) holder;
            hotViewHolder.setData(result.getHot_info());

        }

    }


    private class BannerViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        //private TextView tv_banner;
        private Banner banner;
        public BannerViewHolder(Context mContext, View inflate) {
            super(inflate);
            this.mContext = mContext;
            banner = (Banner) inflate.findViewById(R.id.banner);
        }

        public void setData(final List<HomeBean.ResultBean.BannerInfoBean> banner_info) {
            //tv_banner.setText("banner图片") ;
            //1.得到数据
            //2.设置Banner的数据
            //简单使用
            List<String> images = new ArrayList<>();
            for (int i = 0; i < banner_info.size(); i++) {
                images.add(Constants.BASE_URL_IMAGE + banner_info.get(i).getImage());
            }
            banner.setImages(images).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).crossFade().into(imageView);
                }
            }).start();
            //设置样式
            banner.setBannerAnimation(BackgroundToForegroundTransformer.class);
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                   Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();

                }
            });
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    //int realPosition = position -1;
                    //Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();
                    int realPosition = position;
                    if(realPosition < banner_info.size()) {
                        String product_id = "";
                        String name = "";
                        String cover_price = "";
                        String image = "";
                        if(realPosition == 0) {
                            product_id = "627";
                            cover_price = "32.00";
                            name = "剑三T恤批发";
                        }else if(realPosition == 1) {
                            product_id = "21";
                            cover_price = "8.00";
                            name = "同人原创】剑网3 剑侠情缘叁 Q版成男 口袋胸针";
                        } else {
                            product_id = "1341";
                            cover_price = "50.00";
                            name = "【蓝诺】《天下吾双》 剑网3同人本";
                        }
                        image = banner_info.get(position).getImage();

                        GoodsBean goodsBean = new GoodsBean();
                        goodsBean.setName(name);
                        goodsBean.setFigure(image);
                        goodsBean.setCover_price(cover_price);
                        goodsBean.setProduct_id(product_id);

                        Intent intent = new Intent(mContext,GoodsInfoActivity.class);
                        intent.putExtra(GOODS_BEAN,goodsBean);
                        mContext.startActivity(intent);
                    }
                }
            });
        }

    }

    class ChannelViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        @BindView(R.id.gv_channel)
        GridView gvChannel;

        public ChannelViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
            this.mContext = mContext;
        }

        public void setData(List<HomeBean.ResultBean.ChannelInfoBean> channel_info) {
            //设置GridView的适配器
            ChannelAdapter channelAdapter = new ChannelAdapter(mContext, channel_info);
            gvChannel.setAdapter(channelAdapter);
            //设置item的点击事件
            gvChannel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    class ActViewHolder extends RecyclerView.ViewHolder {
        private final Context mContext;
        @BindView(R.id.act_viewpager)
        ViewPager actViewpager;

        public ActViewHolder(Context mContext, View inflate) {
            super(inflate);
            this.mContext = mContext;
            ButterKnife.bind(this, inflate);
        }

        public void setData(final List<HomeBean.ResultBean.ActInfoBean> act_info) {
            ActAdapter actAdapter = new ActAdapter(mContext, act_info);
            actViewpager.setAdapter(actAdapter);
            //设置每个页面的间距
            actViewpager.setPageMargin(20);
            //>=3
            actViewpager.setOffscreenPageLimit(3);
            //设置动画
            actViewpager.setPageTransformer(true, new ScaleInOutTransformer());

            actAdapter.setOnItemClickListener(new ActAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();

                    HomeBean.ResultBean.ActInfoBean actInfoBean = act_info.get(position);

                    WebViewBean webViewBean = new WebViewBean();
                    webViewBean.setName(actInfoBean.getName());
                    webViewBean.setUrl(actInfoBean.getUrl());
                    webViewBean.setIcon_url(actInfoBean.getIcon_url());

                    Intent intent = new Intent(mContext,WebViewActivity.class);
                    intent.putExtra(WEB_VIEW_BEAN,webViewBean);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    class SeckillViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.countdownview)
        CountdownView countdownview;
        @BindView(R.id.tv_more_seckill)
        TextView tvMoreSeckill;
        @BindView(R.id.rv_seckill)
        RecyclerView rvSeckill;

        public SeckillViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }

        public void setData(final HomeBean.ResultBean.SeckillInfoBean seckill_info) {
            SeckillAdapter seckillAdapter = new SeckillAdapter(mContext, seckill_info);
            rvSeckill.setAdapter(seckillAdapter);

            rvSeckill.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

            seckillAdapter.setOnItemClickListener(new SeckillAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();

                    HomeBean.ResultBean.SeckillInfoBean.ListBean listBean = seckill_info.getList().get(position);
                    //商品新的的Bean对象
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setName(listBean.getName());
                    goodsBean.setCover_price(listBean.getCover_price());
                    goodsBean.setProduct_id(listBean.getProduct_id());
                    goodsBean.setFigure(listBean.getFigure());
                    goodsBean.setOrigin_price(listBean.getOrigin_price());

                    Intent intent = new Intent(mContext,GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN,goodsBean);
                    intent.putExtra("1",1);
                    mContext.startActivity(intent);
                }
            });
            //设置秒杀的时间
            countdownview.setTag("test1");
            long duration = Long.parseLong(seckill_info.getEnd_time()) - Long.parseLong(seckill_info.getStart_time());
            countdownview.start(duration);
        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_recommend)
        TextView tvRecommend;
        @BindView(R.id.gv_recommend)
        GridView gvRecommend;

        public RecommendViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.bind(this, inflate);
        }

        public void setData(final List<HomeBean.ResultBean.RecommendInfoBean> recommend_info) {
            RecommendAdapter recommendAdapter = new RecommendAdapter(mContext, recommend_info);
            gvRecommend.setAdapter(recommendAdapter);
            gvRecommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();

                    HomeBean.ResultBean.RecommendInfoBean recommendInfoBean = recommend_info.get(position);
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setName(recommendInfoBean.getName());
                    goodsBean.setCover_price(recommendInfoBean.getCover_price());
                    goodsBean.setProduct_id(recommendInfoBean.getProduct_id());
                    goodsBean.setFigure(recommendInfoBean.getFigure());

                    Intent intent = new Intent(mContext,GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN,goodsBean);
                    mContext.startActivity(intent);

                }
            });
        }
    }

    class HotViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_more_hot)
        TextView tvMoreHot;
        @BindView(R.id.gv_hot)
        GridView gvHot;
        public HotViewHolder(Context mContext, View inflate) {
            super(inflate);
            ButterKnife.bind(this,inflate);
        }

        public void setData(final List<HomeBean.ResultBean.HotInfoBean> hot_info) {
            HotAdapter hotAdapter = new HotAdapter(mContext,hot_info);
            gvHot.setAdapter(hotAdapter);

            gvHot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(mContext, "position==" + position, Toast.LENGTH_SHORT).show();

                    HomeBean.ResultBean.HotInfoBean hotInfoBean = hot_info.get(position);
                    //传递数据
                    GoodsBean goodsBean = new GoodsBean();
                    goodsBean.setName(hotInfoBean.getName());
                    goodsBean.setCover_price(hotInfoBean.getCover_price());
                    goodsBean.setFigure(hotInfoBean.getFigure());
                    goodsBean.setProduct_id(hotInfoBean.getProduct_id());

                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);
                    intent.putExtra(GOODS_BEAN,goodsBean);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
