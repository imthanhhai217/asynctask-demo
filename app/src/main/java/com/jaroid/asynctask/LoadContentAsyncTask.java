package com.jaroid.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class LoadContentAsyncTask extends AsyncTask<String, Integer, String> {

    private ILoadingContentListener listener;
    private String message;

    public LoadContentAsyncTask(ILoadingContentListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];
        StringBuilder content = new StringBuilder();
        try {
            InputStream inputStream = new URL(url).openStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            int charactor;
            while ((charactor = bufferedReader.read()) != -1) {
                content.append((char) charactor);
            }
        } catch (IOException e) {
            message = e.getMessage();
            e.printStackTrace();
        }

        Log.d("TAG", "doInBackground: \n" + content);
        return content.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            String content = jsonObject.getString("body");

            listener.onLoadingContentSuccess(content);
        } catch (JSONException e) {
            message = e.getMessage();
            e.printStackTrace();
        }

        if (message != null) {
            listener.onLoadingContentError(message);
        }
    }

    public interface ILoadingContentListener {

        void onLoadingContentSuccess(String content);

        void onLoadingContentError(String message);
    }
}
