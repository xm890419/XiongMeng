package com.xiongmeng.android.frangment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.xiongmeng.android.R;
import com.xiongmeng.android.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 熊猛 on 2017/3/9.
 */

public class NetFragment extends BaseFrgment {
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.refresh)
    MaterialRefreshLayout refresh;
    @BindView(R.id.tv_no_media)
    TextView tvNoMedia;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_net_video, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "网络数据初始化了");
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.NET_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "联网失败==" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("TAG", "联网成功=="+response);
            }
        });
    }

}
