package com.jaroid.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class LoadArrayAsyncTask extends AsyncTask<String, Integer, String> {

    private LoadContentAsyncTask.ILoadingContentListener listener;

    public LoadArrayAsyncTask(LoadContentAsyncTask.ILoadingContentListener listener) {
        this.listener = listener;
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
//            message = e.getMessage();
            e.printStackTrace();
        }

        Log.d("TAG", "doInBackground: \n" + content);
        return content.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("TAG", "onPostExecute: " + s);
        try {
            JSONArray jsonArray = new JSONArray(s);

            String output = "";
            Log.d("TAG", "onPostExecute: " + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String content = jsonObject.getString("body");
                String cover = jsonObject.getString("cover");
                String title = jsonObject.getString("title");
                int id = jsonObject.getInt("id");
                output += title+" ";
                Log.d("TAG", "onPostExecute: " + cover + " \n id : " + id);
                listener.onLoadingContentSuccess(output);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
