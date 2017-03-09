package com.xiongmeng.android.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xiongmeng.android.R;
import com.xiongmeng.android.adapter.HomeAdapter;
import com.xiongmeng.android.bean.GoodsBean;
import com.xiongmeng.android.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsInfoActivity extends AppCompatActivity {

    @BindView(R.id.ib_good_info_back)
    ImageButton ibGoodInfoBack;
    @BindView(R.id.ib_good_info_more)
    ImageButton ibGoodInfoMore;
    @BindView(R.id.iv_good_info_image)
    ImageView ivGoodInfoImage;
    @BindView(R.id.tv_good_info_name)
    TextView tvGoodInfoName;
    @BindView(R.id.tv_good_info_desc)
    TextView tvGoodInfoDesc;
    @BindView(R.id.tv_good_info_price)
    TextView tvGoodInfoPrice;
    @BindView(R.id.tv_origin_price)
    TextView tvOriginPrice;
    @BindView(R.id.rl_origin_price)
    RelativeLayout rlOriginPrice;
    @BindView(R.id.tv_good_info_store)
    TextView tvGoodInfoStore;
    @BindView(R.id.tv_good_info_style)
    TextView tvGoodInfoStyle;
    @BindView(R.id.wb_good_info_more)
    WebView wbGoodInfoMore;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.tv_good_info_callcenter)
    TextView tvGoodInfoCallcenter;
    @BindView(R.id.tv_good_info_collection)
    TextView tvGoodInfoCollection;
    @BindView(R.id.tv_good_info_cart)
    TextView tvGoodInfoCart;
    @BindView(R.id.btn_good_info_addcart)
    Button btnGoodInfoAddcart;
    @BindView(R.id.ll_goods_root)
    LinearLayout llGoodsRoot;
    @BindView(R.id.tv_more_share)
    TextView tvMoreShare;
    @BindView(R.id.tv_more_search)
    TextView tvMoreSearch;
    @BindView(R.id.tv_more_home)
    TextView tvMoreHome;
    @BindView(R.id.btn_more)
    Button btnMore;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    @BindView(R.id.activity_goods_info)
    LinearLayout activityGoodsInfo;
    private GoodsBean goodsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.bind(this);
        getData();
    }

    private void getData() {
        // GoodsBean goodsBean = (GoodsBean) getIntent().getSerializableExtra(HomeAdapter.GOODS_BEAN);
        goodsBean = (GoodsBean) getIntent().getSerializableExtra(HomeAdapter.GOODS_BEAN);
        //Toast.makeText(this, "" + goodsBean.toString(), Toast.LENGTH_SHORT).show();
        int a = getIntent().getIntExtra("1", 0);
        if (a == 1) {
            rlOriginPrice.setVisibility(View.VISIBLE);
            tvOriginPrice.setText("￥" + goodsBean.getOrigin_price());
        }
        setData();

    }

    private void setData() {
        //1.设置图片
        Glide.with(this).load(Constants.BASE_URL_IMAGE + goodsBean.getFigure()).placeholder(R.drawable.new_user_icon_background).into(ivGoodInfoImage);
        //2.设置名称和价格
        tvGoodInfoName.setText(goodsBean.getName());
        tvGoodInfoPrice.setText("￥" + goodsBean.getCover_price());

        //3.设置加载网页
        loadWeb("http://mp.weixin.qq.com/s/Cf3DrW2lnlb-w4wYaxOEZg");
    }

    private void loadWeb(String url) {
        WebSettings webSetting = wbGoodInfoMore.getSettings();
        //设置支持js
        webSetting.setJavaScriptEnabled(true);
        //设置添加缩放按钮
        webSetting.setBuiltInZoomControls(true);
        //设置双击单击
        webSetting.setUseWideViewPort(true);
        //设置WebViewClient,如果没有设置，调起系统的浏览器打开新的连接
        wbGoodInfoMore.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressbar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().toString());
                }
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wbGoodInfoMore.loadUrl(url);
    }

    @OnClick({R.id.ib_good_info_back, R.id.ib_good_info_more, R.id.tv_good_info_callcenter, R.id.tv_good_info_collection, R.id.tv_good_info_cart, R.id.btn_good_info_addcart, R.id.tv_more_share, R.id.tv_more_search, R.id.tv_more_home, R.id.btn_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_good_info_back:
                finish();
                break;
            case R.id.ib_good_info_more:
                //Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
                if (llRoot.isShown()) {
                    llRoot.setVisibility(View.GONE);
                } else {
                    llRoot.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_good_info_callcenter:
                Toast.makeText(this, "客服中心", Toast.LENGTH_SHORT).show();

                break;
            case R.id.tv_good_info_collection:
                Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_good_info_cart:
                Toast.makeText(this, "购物车", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_good_info_addcart:
                Toast.makeText(this, "添加到购物车", Toast.LENGTH_SHORT).show();

                break;
            case R.id.tv_more_share:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();

                break;
            case R.id.tv_more_search:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_more_home:
                Toast.makeText(this, "主页", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_more:
                //Toast.makeText(this, "消失更多页面", Toast.LENGTH_SHORT).show();
                llRoot.setVisibility(View.GONE);
                break;
        }
    }
}
