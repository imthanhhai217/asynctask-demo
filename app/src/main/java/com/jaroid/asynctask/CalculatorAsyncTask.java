package com.jaroid.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorAsyncTask extends AsyncTask<Integer, Integer, Integer> {

    TextView tvMainActivity;
    private Context mContext;

    public CalculatorAsyncTask(TextView tvMainActivity, Context context) {
        this.tvMainActivity = tvMainActivity;
        this.mContext = context;
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        int inputNumber = integers[0];
        int value = 0;
        for (int i = 0; i <= inputNumber; i++) {
            value += i;
        }
        Toast.makeText(mContext, ""+value, Toast.LENGTH_SHORT).show();
        return value;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        Log.d("TAG", "onPostExecute: "+integer);
        Toast.makeText(mContext, ""+integer, Toast.LENGTH_SHORT).show();
//        tvMainActivity.setText(integer);
    }
}
