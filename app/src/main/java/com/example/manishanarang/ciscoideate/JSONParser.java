package com.example.manishanarang.ciscoideate;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class JSONParser {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String json ="";
    StringBuilder sb;

    // constructor
    public JSONParser() {

    }

    // function get json from url
    // by making HTTP POST or GET mehtod
    public JSONObject makeHttpRequest(String url, String method) {

        // Making HTTP request
        try {

            // check for request method
            if(method.equals("POST")){
                // request method is POST
                // defaultHttpClient
                Log.i("post method","entering post method");
                DefaultHttpClient httpClient = new DefaultHttpClient();

                HttpPost httpPost = new HttpPost ( "http://192.168.1.102:80/ideate/try.php");

                Log.i("url=",url);
                // httpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                Log.i("url=",url);

            }else if(method.equals("GET")){
                // request method is GET
                Log.i("get method","entering get method");
                DefaultHttpClient httpClient = new DefaultHttpClient();
                //String paramString = URLEncodedUtils.format(params, "utf-8");
                //url += "?" + paramString;
                //HttpGet httpGet = new HttpGet(url);
                HttpPost httpGet = new HttpPost ( "http://192.168.1.102:3306/ideate/try.php");

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                Log.i("url=",url);
                Log.i("IS = ",is.toString());
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e("Unsupp Enc exc", "Error converting result " + e.toString());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            Log.e("Client Protocol Exc", "Error converting result " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("IOS exception", "Error converting result " + e.toString());
        }


        try {

            Log.i("is is not null","move");
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            Log.i("json=",json);
           // json="hello";

            Log.i("try ","trying");

        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result "  +e.toString());
            Log.i("catch ","catching ");
        }

        // try parse the string to a JSON object
        try {

            jObj = new JSONObject(json);
        } catch (Exception e) {
            e.getMessage();
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObj;

    }
}