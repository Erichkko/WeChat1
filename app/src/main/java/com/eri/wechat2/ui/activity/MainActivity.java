package com.eri.wechat2.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.eri.wechat2.R;
import com.eri.wechat2.task.AsyncLoadingTask;
import com.eri.wechat2.ui.activity.base.BaseActivity;



public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        initData();
    }

    private void initView(){
        ImageView iv = (ImageView)findViewById(R.id.iv_image);
        Glide.with(this)
                .load("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png")
                .into(iv);
    }
    private void initData(){
        showToast("56666....");
        final String url = "http://qpi.zhenghongwy.com:5095/qpi/rest/ownerDynamicsInfo/getDynamicsList?pageNum=1&perSize=5&userId=95327&projectId=7";
        AsyncLoadingTask<Void, Void, Integer> task = new AsyncLoadingTask<Void,Void,Integer>(this){
            @Override
            protected Integer doInBackground(Void... params) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                showToast("success....");
            }

        };
        task.setMessage("666");
        task.execute();
    }
}
