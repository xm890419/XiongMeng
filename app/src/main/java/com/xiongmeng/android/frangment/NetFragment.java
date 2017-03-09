package com.xiongmeng.android.frangment;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.xiongmeng.android.R;
import com.xiongmeng.android.adapter.NetVedioAdapter;
import com.xiongmeng.android.bean.MediaItem;
import com.xiongmeng.android.utils.CacheUtils;
import com.xiongmeng.android.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

import static com.xiongmeng.android.R.id.tv_no_media;

/**
 * Created by 熊猛 on 2017/3/9.
 */

public class NetFragment extends BaseFrgment {
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.refresh)
    MaterialRefreshLayout refresh;
    @BindView(tv_no_media)
    TextView tvNoMedia;
    private ArrayList<MediaItem> mediaItems;
    private MediaItem mediaItem;
    private NetVedioAdapter adapter;


    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_net_video, null);
        ButterKnife.bind(this, view);
        listview.setOnItemClickListener(new MyOnItemClickListener());
        //监听上拉和下拉刷新
        refresh.setMaterialRefreshListener(new MyMaterialRefreshListener());
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "网络数据初始化了");
        String json = CacheUtils.getString(mContext,Constants.NET_URL);
        if(!TextUtils.isEmpty(json)){
            parsedJson(json);
        }

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
                //Log.e("TAG", "联网成功=="+response);
                CacheUtils.putString(mContext,Constants.NET_URL,response);
                processData(response);
                if(!isloadMore){
                    //完成刷新
                    refresh.finishRefresh();
                }else {
                    //把上拉刷新隐藏
                    refresh.finishRefreshLoadMore();
                }
            }
        });
    }

    private void processData(String response) {

        if(!isloadMore){
            mediaItems = parsedJson(response);
            if(mediaItems != null && mediaItems.size() >0){
                //有数据
                tvNoMedia.setVisibility(View.GONE);;
                adapter = new NetVedioAdapter(mContext,mediaItems);
                //设置适配器
                listview.setAdapter(adapter);

            }else{
                tvNoMedia.setVisibility(View.VISIBLE);
            }
        }else {
            //加载更多
            ArrayList<MediaItem> mediaItem = parsedJson(response);
            mediaItems.addAll(mediaItem);
            //刷新适配器
            adapter.notifyDataSetChanged();
        }

    }
    /**
     * 使用系统的接口解析json数据
     * @param json
     * @return
     */
    private ArrayList<MediaItem> parsedJson(String json) {
        ArrayList<MediaItem> mediaItems = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("trailers");


            for (int i=0;i<jsonArray.length();i++){

                MediaItem mediaItem = new MediaItem();

                mediaItems.add(mediaItem);//添加到集合中

                JSONObject jsonObjectItem = (JSONObject) jsonArray.get(i);
                String name = jsonObjectItem.optString("movieName");
                mediaItem.setName(name);
                String desc = jsonObjectItem.optString("videoTitle");
                mediaItem.setDesc(desc);
                String url = jsonObjectItem.optString("url");
                mediaItem.setData(url);
                String hightUrl = jsonObjectItem.optString("hightUrl");
                mediaItem.setHeightUrl(hightUrl);
                String coverImg = jsonObjectItem.optString("coverImg");
                mediaItem.setImageUrl(coverImg);
                int videoLength = jsonObjectItem.optInt("videoLength");
                mediaItem.setDuration(videoLength);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mediaItems;
    }


    //是否加载更多
    private boolean isloadMore = false;

    class MyMaterialRefreshListener extends MaterialRefreshListener {

        @Override
        public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
            //Toast.makeText(mContext,"下拉刷新",Toast.LENGTH_SHORT).show();
            isloadMore = false;
            getDataFromNet();
        }

        @Override
        public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
            super.onRefreshLoadMore(materialRefreshLayout);
            isloadMore = true;
            getDataFromNet();
            //Toast.makeText(mContext,"上拉刷新",Toast.LENGTH_SHORT).show();
        }
    }

    class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           mediaItem = mediaItems.get(position);
            //1.调起系统的播放器播放视频--隐式意图
            Intent intent = new Intent();
            //第一参数：播放路径
            //第二参数：路径对应的类型
            intent.setDataAndType(Uri.parse(mediaItem.getData()), "video/*");
            startActivity(intent);

        }
    }


}
