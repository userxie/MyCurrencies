package com.springwind.phone.newcurrencies;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Main2Activity extends Activity {
public static final String URL_CODES =
        "http://openexchangerates.org/api/currencies.json";
    public static final String KEY_ARRAYLIST = "key_arraylist";
    private ArrayList<String> mCurrencies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);
        new FetchCodesTask().execute(URL_CODES);
    }

    private class FetchCodesTask extends AsyncTask<String ,Void,JSONObject>{

        @Override
        protected JSONObject doInBackground(String... strings) {
            return new JSONParser().getJSONFromUrl(strings[0]);
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try{
                if(jsonObject == null){
                    throw new JSONException("no data available");
                }
                Iterator iterator = jsonObject.keys();
                String key = "";
                mCurrencies = new ArrayList<String>();
                while(iterator.hasNext()){
                    key = (String)iterator.next();
                    mCurrencies.add(key+"|"+jsonObject.getString(key));
                }
                Intent mainintent = new Intent(Main2Activity.this,MainActivity.class);
                mainintent.putStringArrayListExtra(KEY_ARRAYLIST,mCurrencies);
                startActivity(mainintent);
                finish();
            }catch (JSONException e) {
                Toast.makeText(Main2Activity.this,"There's been a JSON exception:"
                        +e.getMessage(),Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}
