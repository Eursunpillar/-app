package com.animee.news_info.frag;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.animee.news_info.DescActivity;
import com.animee.news_info.R;
import com.animee.news_info.bean.InfoBean;
import com.animee.news_info.bean.TypeBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
/**
 * 展示新闻信息的fragment
 */
public class NewsInfoFragment extends BaseFragment {
    ListView infoLv;
    private String url;
//  ListView展示的数据源
    List<InfoBean.ResultBean.DataBean> mDatas;
    private InfoItemAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_info, container, false);
        infoLv = view.findViewById(R.id.newsfrag_lv);
//        获取activity传递的数据
        Bundle bundle = getArguments();
        TypeBean typeBean = (TypeBean) bundle.getSerializable("type");
        url = typeBean.getUrl();

        mDatas = new ArrayList<>();
//        创建ListView的适配器对象
        adapter = new InfoItemAdapter(getActivity(), mDatas);
        infoLv.setAdapter(adapter);
        loadData(url);
        setListener();
        return view;
    }
/* 设置ListView每一项点击事件的函数*/
    private void setListener() {
        infoLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InfoBean.ResultBean.DataBean dataBean = mDatas.get(position);
                String url = dataBean.getUrl();
                Intent intent = new Intent(getActivity(), DescActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });
    }

    //     获取数据成功时，会调用的函数
    @Override
    public void onResponse(String response) {
        InfoBean infoBean = new Gson().fromJson(response, InfoBean.class);
        try {
            List<InfoBean.ResultBean.DataBean> list = infoBean.getResult().getData();
//        添加到数据源当中
            mDatas.addAll(list);
//        提示adapter数据源发生变化了，更新数据
            adapter.notifyDataSetChanged();
        }catch (Exception e){
            Toast.makeText(getActivity(),"请求次数超出期限！",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
