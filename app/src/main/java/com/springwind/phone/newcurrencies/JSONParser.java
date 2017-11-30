package com.springwind.phone.newcurrencies;

import android.net.http.HttpResponseCache;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPostHC4;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Muzhou on 3/16/2017.
 */
public class JSONParser {
    static InputStream sInputStream =null;
    static String sRawJsonString ="";
    OkHttpClient client = new OkHttpClient();
    public JSONParser(){}

    public JSONObject getJSONFromUrl(String url) throws IOException, JSONException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            return new JSONObject(response.body().string());
        }else {
            throw new IOException("Unexpected code"+response);
        }
    }
}
