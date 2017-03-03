package com.louis.ayn.ofsta;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class CapturedImageActivity extends AppCompatActivity {

    Bitmap image;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captured_image);

        imageView = (ImageView) findViewById(R.id.capturedImage);

        Intent getIntent = getIntent();
        Bundle bundle = getIntent.getExtras();
        image = (Bitmap) bundle.get("data");
        imageView.setImageBitmap(image);

    }

    public void bottomImageOptions(View view) {
        int id = view.getId();
        if (id == R.id.btnImageShare) {
            Intent shareImage = new Intent();
            shareImage.setAction(Intent.ACTION_SEND);
            shareImage.setType("image/*");
            startActivity(Intent.createChooser(shareImage, "Share Image to..."));
        } else if (id == R.id.btnImageSave) {
            // TODO: Delete Image
        } else if (id == R.id.btnImageInfo) {

        } else {
            Toast.makeText(this, "-=BUG #3=-", Toast.LENGTH_LONG).show();
        }
    }
}
