package com.jaroid.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

//                                AsyncTask <Params, Progress, Result>
public class DemoAsyncTask extends AsyncTask<String, Integer, Bitmap> {


    // Hàm xử lý chính của AsyncTask
    // return ra kiểu của tham số thứ 3  ex: bitmap, String, boolean
    // Không cho phép tương tác với giao diện người dùng
    @Override
    protected Bitmap doInBackground(String... strings) {
        String url = strings[0];
        Bitmap bitmap = null;
        try {
            InputStream inputStream = new URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    //Nhận giá trị trả về của doInBackground
    // Có thể tương tác với giao diện tại hàm này
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        Log.d("TAG", "onPostExecute: " + bitmap);
    }
}
