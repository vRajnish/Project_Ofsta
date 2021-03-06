package com.louis.ayn.ofsta;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class CapturedImageActivity extends AppCompatActivity {

    Bitmap image;
    ImageView imageView;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captured_image);

        imageView = (ImageView) findViewById(R.id.capturedImage);

        Intent getIntent = getIntent();
        Bundle bundle = getIntent.getExtras();
        path = bundle.getString("path");
        Toast.makeText(this, path, Toast.LENGTH_LONG).show();

        image = BitmapFactory.decodeFile(path);
        imageView.setImageBitmap(image);

    }

    public void bottomImageOptions(View view) {
        int id = view.getId();
        if (id == R.id.btnImageShare) {
            Intent shareImage = new Intent();
            shareImage.setAction(Intent.ACTION_SEND);
            shareImage.setType("*/*");
            String path = MediaStore.Images.Media.insertImage(getContentResolver(), image, "Title", "Description");
            Uri bitUri = Uri.parse(path);
            shareImage.putExtra(Intent.EXTRA_STREAM, bitUri);
            shareImage.putExtra(Intent.EXTRA_TEXT, "This image is click and sent by Ofsta");
            startActivity(Intent.createChooser(shareImage, "Share Image to..."));
        } else if (id == R.id.btnImageDelete) {
            // TODO: Delete Image
            File imageDelete = new File(path);
            if (imageDelete.exists()) {
                if (imageDelete.delete()) {
                    Toast.makeText(this, "Deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Can't Delete", Toast.LENGTH_LONG).show();
                }
            }
        } else if (id == R.id.btnImageInfo) {
            // TODO: Image Info
        }
    }
}
