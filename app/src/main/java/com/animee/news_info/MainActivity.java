package com.animee.news_info;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.animee.news_info.add.AddItemActivity;
import com.animee.news_info.bean.TypeBean;
import com.animee.news_info.db.DBManager;
import com.animee.news_info.frag.NewsInfoAdapter;
import com.animee.news_info.frag.NewsInfoFragment;
import com.animee.news_info.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ViewPager mainVp;
    PagerSlidingTabStrip tabStrip;
    ImageView addIv;
    List<Fragment>fragmentList;  //viewpager所显示的内容
    List<TypeBean>selectTypeList;  //所选中的类型的集合
    private NewsInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainVp = findViewById(R.id.main_vp);
        tabStrip = findViewById(R.id.main_tabstrip);
        addIv = findViewById(R.id.main_iv_add);
        addIv.setOnClickListener(this);  //添加点击事件的监听
        fragmentList = new ArrayList<>();
        selectTypeList =new ArrayList<>();
        initPager();   //初始化页面
//        创建适配器对象
        adapter = new NewsInfoAdapter(getSupportFragmentManager(), this, fragmentList, selectTypeList);
//        设置适配器
        mainVp.setAdapter(adapter);
//      关联TabStrip和ViewPager
        tabStrip.setViewPager(mainVp);
    }

    private void initPager() {
        /* 初始化页面的函数*/
        List<TypeBean> typeList = DBManager.getSelectedTypeList();
        selectTypeList.addAll(typeList);
        for (int i = 0; i < selectTypeList.size(); i++) {
            TypeBean typeBean = selectTypeList.get(i); //得到每一个栏目的信息对象
            NewsInfoFragment infoFragment = new NewsInfoFragment();
//            向fragment当中传递数据
            Bundle bundle = new Bundle();
            bundle.putSerializable("type",typeBean);
            infoFragment.setArguments(bundle);
            fragmentList.add(infoFragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_iv_add:
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fragmentList.clear();
        selectTypeList.clear();

        initPager();  //重新加载viewpager的显示页
        adapter.notifyDataSetChanged();
        tabStrip.notifyDataSetChanged();
    }
}
