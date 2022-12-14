package com.jaroid.asynctask;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        LoadImageAsyncTask.ILoadingImageAsyncListener, LoadArrayAsyncTask.ILoadingContentsListener {

    private LoadImageAsyncTask loadImageAsyncTask;
    //    private LoadContentAsyncTask loadContentAsyncTask;
    private LoadArrayAsyncTask loadContentAsyncTask;
    private ImageView imgHeader;
    private TextView tvContent, tvError;
    private Button btnLoadImage, btnLoadContent, btnSum, btnNull;
    private View vLoading;
    private RecyclerView rvContent;

    private ArrayList<Content> mListContents;
    private ContentAdapter mContentAdapter;

    public static final String IMAGE_URL = "https://picsum.photos/seed/22444/1920/270";
    public static final String CONTENT_API = "https://mockend.com/imthanhhai217/demo_api/posts/1";
    public static final String CONTENTS_API = "https://mockend.com/imthanhhai217/demo_api/posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        mListContents = new ArrayList<>();
        mListContents.clear();
    }

    private void initView() {
        imgHeader = findViewById(R.id.imgHeader);
        tvContent = findViewById(R.id.tvContent);
        tvError = findViewById(R.id.tvError);
        btnSum = findViewById(R.id.btnSum);
        btnNull = findViewById(R.id.btnNull);
        btnLoadImage = findViewById(R.id.btnLoadImage);
        btnLoadContent = findViewById(R.id.btnLoadContent);
        vLoading = findViewById(R.id.vLoading);
        rvContent = findViewById(R.id.rvContent);

        mContentAdapter = new ContentAdapter(mListContents);
        rvContent.setAdapter(mContentAdapter);

        btnLoadImage.setOnClickListener(this);
        btnLoadContent.setOnClickListener(this);
        btnSum.setOnClickListener(this);
        btnNull.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoadImage:
                loadImage();
                break;
            case R.id.btnLoadContent:
                loadContent();
                break;
            case R.id.btnSum:
                sumAsyncTask();
                break;
            case R.id.btnNull:
//                InputStream inputStream = null;
//                try {
//                    inputStream = new URL(IMAGE_URL).openStream();
//                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                    imgHeader.setImageBitmap(null);
//                    imgHeader.setImageBitmap(bitmap);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

//                StringBuilder content = new StringBuilder();
//                try {
//                    InputStream inputStream = new URL(CONTENT_API).openStream();
//                    InputStreamReader reader = new InputStreamReader(inputStream);
//                    BufferedReader bufferedReader = new BufferedReader(reader);
//
//                    int charactor;
//                    while ((charactor = bufferedReader.read()) != -1) {
//                        content.append((char) charactor);
//                    }
//                    Log.d("TAG", "onClick: "+content);
//                } catch (IOException e) {
////                    message = e.getMessage();
//                    e.printStackTrace();
//                }

                new DemoAsyncTask().execute(IMAGE_URL);

                break;
        }
    }

    private void sumAsyncTask() {
        CalculatorAsyncTask calculatorAsyncTask = new CalculatorAsyncTask(tvContent, this);
        calculatorAsyncTask.execute(20000);
    }

    private void loadContent() {
        hideErrorMessage();
        loadContentAsyncTask = new LoadArrayAsyncTask(this);
        loadContentAsyncTask.execute(CONTENTS_API);
    }

    private void loadImage() {
        hideErrorMessage();
        showLoading();

        loadImageAsyncTask = new LoadImageAsyncTask(this);
        loadImageAsyncTask.execute(IMAGE_URL);
    }

    @Override
    public void loadImageSuccess(Bitmap bitmap) {
        hideLoading();
        imgHeader.setImageBitmap(null);
        imgHeader.setImageBitmap(bitmap);
    }

    @Override
    public void loadImageError(String message) {
        hideLoading();
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(message);
    }

    private void hideErrorMessage() {
        tvError.setVisibility(View.INVISIBLE);
    }

    private void showLoading() {
        vLoading.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        vLoading.setVisibility(View.GONE);
    }

    @Override
    public void onLoadingContentsSuccess(ArrayList<Content> contents) {
        hideLoading();
        if (contents.size() > 0){
            mContentAdapter.updateData(contents);
        }
    }

    @Override
    public void onLoadingContentsError(String message) {

    }
}