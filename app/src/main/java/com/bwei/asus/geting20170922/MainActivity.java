package com.bwei.asus.geting20170922;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private ImageView img;
    private ViewPager pager;
    private RadioGroup radio;
    private List<Fragment> list = new ArrayList<Fragment>();
    private List<String> llist = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radio = (RadioGroup) findViewById(R.id.group);
        //初始化组件
        lv = (ListView) findViewById(R.id.left_drawer);
        img = (ImageView) findViewById(R.id.l_img);
        pager = (ViewPager) findViewById(R.id.pager);
        //加载界面
        Fragment01 fragment01 = new Fragment01();
        Fragment02 fragment02 = new Fragment02();
        Fragment03 fragment03 = new Fragment03();
        Fragment04 fragment04 = new Fragment04();
        //添加到集合
        list.add(fragment01);
        list.add(fragment02);
        list.add(fragment03);
        list.add(fragment04);
        //创建适配器\
        FragmentManager manager = getSupportFragmentManager();
        MyAdpater myAdpater = new MyAdpater(manager,list);
        pager.setAdapter(myAdpater);
        //按钮的点击
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //判断是哪一个按钮也
                switch (checkedId){
                    case R.id.btn1:
                    pager.setCurrentItem(0);
                    break;
                    case R.id.btn2:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.btn3:
                        pager.setCurrentItem(2);
                        break;
                    case R.id.btn4:
                        pager.setCurrentItem(3);
                        break;
                }
            }
        });
        //设置侧啦
        //点击图片弹出侧啦
        llist.add("客服热线");
        llist.add("营业部网点");
        llist.add("咨询");
        llist.add("换肤");
        //创建适配器展示
        LeftAdpater leftAdpater = new LeftAdpater(llist, MainActivity.this);
        //关联
        lv.setAdapter(leftAdpater);
    }

    //适配器
    class MyAdpater extends FragmentPagerAdapter{
        private List<Fragment> list;
        public MyAdpater(FragmentManager fm,List<Fragment> list) {
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
    }
}
