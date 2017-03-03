package com.louis.ayn.ofsta;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
            shareImage.setType("image/*");
            String path = MediaStore.Images.Media.insertImage(getContentResolver(), image, "Title", "Description");
            Uri bitUri = Uri.parse(path);
            shareImage.putExtra(Intent.EXTRA_STREAM, bitUri);
            startActivity(Intent.createChooser(shareImage, "Share Image to..."));
        } else if (id == R.id.btnImageDelete) {
            // TODO: Delete Image
        } else if (id == R.id.btnImageInfo) {

        } else {
            Toast.makeText(this, "-=BUG #3=-", Toast.LENGTH_LONG).show();
        }
    }
}
