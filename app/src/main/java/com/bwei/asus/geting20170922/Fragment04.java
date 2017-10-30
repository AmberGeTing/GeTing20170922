package com.bwei.asus.geting20170922;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2017/9/22.
 */

public class Fragment04 extends Fragment {
    private List<MyFragment> list = new ArrayList<MyFragment>();
    private String[] names = {"沪深","板块","指数","港股","新三板","商品","体育","新闻"};
    private ViewPager pager;
    private TabLayout layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment04,container,false);
        pager = (ViewPager) view.findViewById(R.id.vp_view);
        layout = (TabLayout) view.findViewById(R.id.tabs);
        //循环创建tab布局
        for (int i = 0;i<names.length;i++){
            MyFragment fragment = new MyFragment();
            Bundle bundle = new Bundle();
            //传值
            bundle.putString("urls","https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10");
            fragment.setArguments(bundle);
            //添加到集合
            list.add(fragment);
            //显示
            layout.addTab(layout.newTab().setText(names[i]));
        }
        //创建适配器
        FragmentManager manager = getActivity().getSupportFragmentManager();
        MyAdapter myAdapter = new MyAdapter(manager,list);
        //关联
        layout.setupWithViewPager(pager);
        layout.setTabsFromPagerAdapter(myAdapter);
        pager.setAdapter(myAdapter);

        return view;
    }
    //适配器
    class MyAdapter extends FragmentPagerAdapter{
       private List<MyFragment> list;
        public MyAdapter(FragmentManager fm,List<MyFragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return names[position];
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
       //super.onSaveInstanceState(outState);
    }
}
