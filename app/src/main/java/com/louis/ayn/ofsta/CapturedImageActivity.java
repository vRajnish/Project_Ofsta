package com.louis.ayn.ofsta;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CapturedImageActivity extends AppCompatActivity {

    Bitmap image;
    ImageView imageView;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captured_image);

        imageView = (ImageView) findViewById(R.id.capturedImage);

        Intent getIntent = getIntent();
        Bundle bundle = getIntent.getExtras();
        image = (Bitmap) bundle.get("imageBitmap");
        imageView.setImageBitmap(image);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        fileName = simpleDateFormat.format(new Date());
    }

    public void bottomImageOptions(View view) {
        int id = view.getId();
        if (id == R.id.btnImageShare) {
            Intent shareImage = new Intent();
            shareImage.setAction(Intent.ACTION_SEND);
            shareImage.setType("image/*");
            startActivity(Intent.createChooser(shareImage, "Share Image to..."));
        } else if (id == R.id.btnImageSave) {
            saveImageToInternalStorage();
        } else if (id == R.id.btnImageInfo) {

        } else {
            Toast.makeText(this, "-=BUG #3=-", Toast.LENGTH_LONG).show();
        }
    }

    public void saveImageToInternalStorage() {



    }
}
