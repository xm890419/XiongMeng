package com.xiongmeng.android.frangment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.xiongmeng.android.R;
import com.xiongmeng.android.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 熊猛 on 2017/3/9.
 */

public class TypeFragment extends BaseFrgment {
    @BindView(R.id.tl_1)
    SegmentTabLayout tl1;
    @BindView(R.id.iv_type_search)
    ImageView ivTypeSearch;
    @BindView(R.id.fl_type)
    FrameLayout flType;
    private String[] titles = {"网络","百思"};
    private List<BaseFrgment> fragments;
    private Fragment tempFragment;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_type, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        initListener();
        Log.e("TAG", "分类数据初始化了");
        initFragment();
        switchFragment(fragments.get(0));
    }
    private void switchFragment(Fragment currentFragment) {
        MainActivity activity = (MainActivity) mContext;
        if(tempFragment != currentFragment) {

            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
            if(!currentFragment.isAdded()) {
                if(tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.add(R.id.fl_type,currentFragment);
            }else {
                if(tempFragment != null) {
                    ft.hide(tempFragment);
                }
                ft.show(currentFragment);
            }
            ft.commit();

            tempFragment = currentFragment;
        }
    }
    private void initListener() {
        tl1.setTabData(titles);
        tl1.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                switchFragment(fragments.get(position));
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new NetFragment());
        fragments.add(new BaiSiFragment());
    }
    @OnClick(R.id.iv_type_search)
    public void onClick() {
        Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
    }

}
