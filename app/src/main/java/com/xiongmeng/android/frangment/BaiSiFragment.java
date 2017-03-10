package com.xiongmeng.android.frangment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xiongmeng.android.R;
import com.xiongmeng.android.adapter.RecyclerViewAdpater;
import com.xiongmeng.android.bean.NetAudioBean;
import com.xiongmeng.android.utils.CacheUtils;
import com.xiongmeng.android.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 熊猛 on 2017/3/9.
 */

public class BaiSiFragment extends BaseFrgment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.tv_nomedia)
    TextView tvNomedia;
    private List<NetAudioBean.ListBean> datas;
    private RecyclerViewAdpater myAdapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_baisi, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        String saveJson = CacheUtils.getString(mContext, Constants.NET_AUDIO_URL);
        if(!TextUtils.isEmpty(saveJson)){
            processData(saveJson);
        }

        getDataFromNet();

    }

    private void getDataFromNet() {
        OkHttpUtils.get().url(Constants.NET_AUDIO_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG", "失败=="+e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                CacheUtils.putString(mContext,Constants.NET_AUDIO_URL,response);

                Log.e("TAG", "成功=="+response);
                processData(response);
            }
        });
    }
    private void processData(String json) {
        NetAudioBean netAudioBean = paraseJons(json);
//        LogUtil.e(netAudioBean.getList().get(0).getText()+"--------------");
        datas = netAudioBean.getList();
        if(datas != null && datas.size() >0){
            //有视频
            tvNomedia.setVisibility(View.GONE);
            //设置适配器
            myAdapter = new RecyclerViewAdpater(mContext,datas);
            recyclerview.setAdapter(myAdapter);

            //添加布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        }else{
            //没有视频
            tvNomedia.setVisibility(View.VISIBLE);
        }

        progressbar.setVisibility(View.GONE);

    }
    /**
     * json解析数据
     * @param json
     * @return
     */
    private NetAudioBean paraseJons(String json) {
        return new Gson().fromJson(json,NetAudioBean.class);
    }

}
