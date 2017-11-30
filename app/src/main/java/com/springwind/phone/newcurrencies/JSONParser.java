package com.springwind.phone.newcurrencies;

import android.net.http.HttpResponseCache;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPostHC4;
import org.apache.http.client.methods.HttpRequestBaseHC4;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by Muzhou on 3/16/2017.
 */
public class JSONParser {
    static InputStream sInputStream =null;
    static JSONParser sReturnJsonObject =null;
    static String sRawJsonString ="";
    public JSONParser(){

    }
    public JSONObject getJSONFromUrl(String url){
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost postHC4 = new HttpPost(url);
            HttpResponse httpResponseCache = client.execute(postHC4);
            HttpEntity httpEntity = httpResponseCache.getEntity();
            sInputStream = httpEntity.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
