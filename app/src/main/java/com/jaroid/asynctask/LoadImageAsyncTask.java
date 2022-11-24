package com.jaroid.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class LoadImageAsyncTask extends AsyncTask<String, Integer, Bitmap> {

    private ILoadingImageAsyncListener listener;
    private String message;

    public LoadImageAsyncTask(ILoadingImageAsyncListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("TAG", "onPreExecute: ");
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String url = strings[0];
        Bitmap mBitmap = null;

        try {
            InputStream inputStream = new URL(url).openStream();
            mBitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            message = e.getMessage();
            e.printStackTrace();
        }

        return mBitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if (bitmap != null) {
            listener.loadImageSuccess(bitmap);
        }
        if (message != null) listener.loadImageError(message);
    }

    public interface ILoadingImageAsyncListener {

        void loadImageSuccess(Bitmap bitmap);

        void loadImageError(String message);
    }

}
