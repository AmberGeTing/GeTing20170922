package com.bwei.asus.geting20170922;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ASUS on 2017/9/22.
 */

public class NetworkUtils {
    //获取网络的数据
    public String getJson(String urlStr){
        URL url = null;
        HttpURLConnection http = null;
        InputStream inputStream = null;
        String jsonString = "";
        //实例化对象
        try {
            url = new URL(urlStr);
            http = (HttpURLConnection) url.openConnection();
            //获得结果码
            int responseCode = http.getResponseCode();
            //判断是否获取成功
            if(responseCode == 200){
                //读取数据
                inputStream = http.getInputStream();
                int len = 0;
                byte[] bt = new byte[1024];
                //循环读取
                while((len = inputStream.read(bt))!= -1){
                    jsonString += new String(bt,0,len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(inputStream!= null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonString;
    }
}
