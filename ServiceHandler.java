package com.utilities.dhananjayan.sampleasnktaskexample;
/**
 * Created by dhananjayan on 3/31/2016.
 */

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class ServiceHandler {
    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;
    JSONObject JsonObj;
    public ServiceHandler() {
    }

    /**
     * Making service call
     * @url - url to make request
     * @method - http request method
     * @userID - User ID
     * @password - User Password
     * @password - User Authorization token
     */
    public String makeServiceCall(String url, int method, String uName, String pass, String Token) {
        return this.makeServiceCall(url, method, null,uName, pass,Token);
    }

    /**
     * Making service call
     * @url - url to make request
     * @method - http request method
     * @params - http request params
     * @userID - User ID
     * @password - User Password
     * @password - User Authorization token
     */
    public String makeServiceCall(String url, int method,
                                  List<NameValuePair> params,String userID, String Password,String authToken) {
        try {
            // http client xgdfg
            Log.d("TAG", "in make Service makeServiceCall");
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpEntity httpEntity = null;
            String message;
            HttpResponse httpResponse = null;
            // Checking http request method type
            if (method == POST) {
                HttpPost httpPost = new HttpPost(url);
                JSONObject object = new JSONObject();
                object.put("username", userID);
                object.put("password", Password);
                message = object.toString();
                httpPost.setEntity(new StringEntity(message, "UTF8"));
                httpPost.setHeader("Content-Type", "application/json");
                httpPost.setHeader("Authorization", authToken);
                httpResponse = httpClient.execute(httpPost);
            } else if (method == GET){
                // appending params to url
                if (params != null) {
                    String paramString = URLEncodedUtils
                            .format(params, "utf-8");
                    url += "?" + paramString;
                }
                HttpGet httpGet = new HttpGet(url);
                httpResponse = httpClient.execute(httpGet);
            }
            httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity);
            Log.d("TAG", "in make Service makeServiceCall - response"+response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }
}
