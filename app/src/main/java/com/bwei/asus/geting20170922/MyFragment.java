package com.bwei.asus.geting20170922;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * Created by ASUS on 2017/9/22.
 */

public class MyFragment extends Fragment {
    private String urls;
    private PullToRefreshListView lv;
    private List<Newslist> list;
    private MyMainAdpater myMainAdpater;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        urls = bundle.getString("urls");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.myfragment,container,false);
        lv = (PullToRefreshListView) view.findViewById(R.id.pull);
        //初始化
        init();
        //获得数据
        getData();
        return view;
    }
    public void init(){
        //刷新的方法
       lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
           @Override
           public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                  refresh();
           }
       });
        //加载更多的监听
        lv.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() {
            @Override
            public void onLastItemVisible() {
                loadMore();
            }
        });
    }
    public void getData(){
        //获得数据--刷新
        refresh();
    }
    //刷新数据
    public void refresh(){
           //网络请求数据
        new AsyncTask<String,Integer,String>(){

            @Override
            protected String doInBackground(String... params) {
              String str = params[0];
                String string = new NetworkUtils().getJson(str);
                return string;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.i("TAG",s+"============");
                //开始解析数据
                Gson gson = new Gson();
                SupperClass supperClass = gson.fromJson(s, SupperClass.class);
               list = supperClass.getNewslist();
                //创建适配器
                  myMainAdpater = new MyMainAdpater(list,getActivity());
                //关联
                lv.setAdapter(myMainAdpater);
                //加载完成
                lv.onRefreshComplete();
            }
        }.execute(urls);
    }
    //加载更多
    public void loadMore(){
        //网络请求数据
        new AsyncTask<String,Integer,String>(){

            @Override
            protected String doInBackground(String... params) {
                String str = params[0];
                String string = new NetworkUtils().getJson(str);
                return string;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.i("TAG",s+"============");
                //开始解析数据
                Gson gson = new Gson();
                SupperClass supperClass = gson.fromJson(s, SupperClass.class);
                List<Newslist> newslist = supperClass.getNewslist();
                list.addAll(newslist);
                myMainAdpater.notifyDataSetChanged();
            }
        }.execute(urls);
    }
}
