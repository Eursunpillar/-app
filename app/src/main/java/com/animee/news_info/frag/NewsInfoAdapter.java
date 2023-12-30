package com.animee.news_info.frag;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.animee.news_info.bean.TypeBean;

import java.util.List;

public class NewsInfoAdapter extends FragmentStatePagerAdapter{
    Context context;
    List<Fragment>fragmentList;  //viewpager每个页面展示的fragment的集合
    List<TypeBean>typeBeanList;
    public NewsInfoAdapter(FragmentManager fm,Context context,List<Fragment>fragmentList, List<TypeBean>typeBeanList) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
        this.typeBeanList = typeBeanList;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
// 返回指定位置的标题
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        TypeBean typeBean = typeBeanList.get(position);
        String title = typeBean.getTitle();
        return title;
    }
}
