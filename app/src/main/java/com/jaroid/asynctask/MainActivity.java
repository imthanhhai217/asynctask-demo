package com.jaroid.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoadImageAsyncTask.ILoadingImageAsyncListener {

    private LoadImageAsyncTask loadImageAsyncTask;
    private ImageView imgHeader;
    private TextView tvContent, tvError;
    private Button btnLoadImage, btnLoadContent;
    private View vLoading;

    public static final String IMAGE_URL = "https://picsum.photos/seed/22444/1920/270";
    public static final String CONTENT_API = "https://mockend.com/imthanhhai217/demo_api/posts/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        imgHeader = findViewById(R.id.imgHeader);
        tvContent = findViewById(R.id.tvContent);
        tvError = findViewById(R.id.tvError);
        btnLoadImage = findViewById(R.id.btnLoadImage);
        btnLoadContent = findViewById(R.id.btnLoadContent);
        vLoading = findViewById(R.id.vLoading);

        btnLoadImage.setOnClickListener(this);
        btnLoadContent.setOnClickListener(this);
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
        }
    }

    private void loadContent() {
        hideErrorMessage();

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
}