package com.translate.ozh2zh.Uitls;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import java.util.ArrayList;
import java.util.List;

/**
 * @author whs
 * @date 2020/5/20 0020 下午 3:44
 */
public class PutMessageUitls {

    public static void main(String[] args) {
        try {

            CloseableHttpClient client = null;
            CloseableHttpResponse response = null;
            try {
                List<BasicNameValuePair> formparams = new ArrayList<>();
                formparams.add(new BasicNameValuePair("sid","70b48807412f4f626e1f8681c1694809"));
                formparams.add(new BasicNameValuePair("token","4bb8d7de48326c4ffcf14cc2473cd476"));//登录后台 首页显示
                formparams.add(new BasicNameValuePair("appid","b7023785dc4049c994f9f4f8ca90084b"));
                formparams.add(new BasicNameValuePair("templateid","544815"));
                formparams.add(new BasicNameValuePair("param","333333"));//登录后台 添加签名获取id
                formparams.add(new BasicNameValuePair("mobie","15847658498"));
                formparams.add(new BasicNameValuePair("uid","2d92c6132139467b989d087c84a365d8"));


                HttpPost httpPost = new HttpPost("https://open.ucpaas.com/ol/sms/sendsms");
                httpPost.setEntity(new UrlEncodedFormEntity(formparams,"UTF-8"));
                client = HttpClients.createDefault();
                response = client.execute(httpPost);
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity);
                System.out.println("result "+result);
            } finally {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
