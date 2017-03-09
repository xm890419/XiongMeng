package com.xiongmeng.android.frangment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xiongmeng.android.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import bean.HomeBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import utils.Constants;

/**
 * Created by 熊猛 on 2017/3/9.
 */

public class HomeFragment extends BaseFrgment {

    @BindView(R.id.ll_main_scan)
    LinearLayout llMainScan;
    @BindView(R.id.tv_search_home)
    TextView tvSearchHome;
    @BindView(R.id.tv_message_home)
    TextView tvMessageHome;
    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    private HomeBean homeBean;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "主页数据初始化了");
        getDataFromNet();

    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.HOME_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "联网失败==" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                //Log.e("TAG", "联网成功=="+response);
                processData(response);
            }
        });
    }

    private void processData(String response) {
        homeBean = JSON.parseObject(response, HomeBean.class);
        Log.e("TAG", "解析数据成功==" + homeBean.getResult().getHot_info().get(0).getName());
    }

    @OnClick({R.id.ll_main_scan, R.id.tv_search_home, R.id.tv_message_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_main_scan:
                Toast.makeText(mContext, "扫一扫", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_search_home:
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_message_home:
                Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
