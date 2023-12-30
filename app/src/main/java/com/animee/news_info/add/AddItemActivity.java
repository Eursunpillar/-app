package com.animee.news_info.add;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.animee.news_info.R;
import com.animee.news_info.bean.TypeBean;
import com.animee.news_info.db.DBManager;

import java.util.List;

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView backIv;
    ListView addLv;
// 数据源
    List<TypeBean>mDatas;
    private AddItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
//        查找控件
        backIv = findViewById(R.id.add_iv_back);
        addLv = findViewById(R.id.add_lv);
        backIv.setOnClickListener(this);
        mDatas = DBManager.getAllTypeList();
//        创建适配器对象
        adapter = new AddItemAdapter(this, mDatas);
//        设置适配器
        addLv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_iv_back:
                finish();  //销毁当前的activity，返回上一级界面
                break;
        }
    }

//    onCreate (创建了)   onStart(启动了)  onResume(获取焦点)  onPause(失去焦点)  onStop(停止)   onDestory(销毁)   onRestart(重新启动)
    @Override
    protected void onPause() {
        super.onPause();
        DBManager.updateTypeList(mDatas);
    }
}
