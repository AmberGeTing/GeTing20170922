package com.bwei.asus.geting20170922;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static android.R.id.list;

/**
 * Created by ASUS on 2017/9/22.
 */

public class MyMainAdpater extends BaseAdapter {
    private List<Newslist> list;
    private LayoutInflater inflater;
    private Context context;

    public MyMainAdpater(List<Newslist> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.myfragmentitem,null);
            holder.tv = (TextView) convertView.findViewById(R.id.name);
            holder.img = (ImageView) convertView.findViewById(R.id.f_img);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Newslist newslist = list.get(position);
        holder.tv.setText(newslist.getDescription());
        ImageLoader.getInstance().displayImage(newslist.getPicUrl(),holder.img);
        return convertView;
    }
    class ViewHolder{
        TextView tv;
        ImageView img;
    }
}
