package com.louis.ayn.ofsta;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    int appCAMERA_REQUEST_CODE = 1;

    ImageButton btnGallery, btnCamera, btnStar;
    String imageAbsolutePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGallery = (ImageButton) findViewById(R.id.btnGallery);
        btnCamera = (ImageButton) findViewById(R.id.btnCamera);
        btnStar = (ImageButton) findViewById(R.id.btnStar);

        btnGallery.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        btnCamera.setBackgroundColor(getResources().getColor(R.color.transparent));
        btnStar.setBackgroundColor(getResources().getColor(R.color.transparent));

    }

    public void bottomNavBarCall(View view) {
        int id = view.getId();
        if (id == R.id.btnGallery) {
            btnGallery.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            btnCamera.setBackgroundColor(getResources().getColor(R.color.transparent));
            btnStar.setBackgroundColor(getResources().getColor(R.color.transparent));
        } else if (id == R.id.btnCamera) {
            btnGallery.setBackgroundColor(getResources().getColor(R.color.transparent));
            btnCamera.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            btnStar.setBackgroundColor(getResources().getColor(R.color.transparent));

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = getImageFile();
                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this, "com.louis.ayn.ofsta", photoFile);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(cameraIntent, appCAMERA_REQUEST_CODE);
                }

            }


        } else if (id == R.id.btnStar) {
            btnGallery.setBackgroundColor(getResources().getColor(R.color.transparent));
            btnCamera.setBackgroundColor(getResources().getColor(R.color.transparent));
            btnStar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == appCAMERA_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Intent saveImageIntent = new Intent(this, CapturedImageActivity.class);
                Bundle extras = new Bundle();
                extras.putString("path", imageAbsolutePath);
                saveImageIntent.putExtras(extras);

                startActivity(saveImageIntent);
            } else if (resultCode == RESULT_CANCELED) {
                btnGallery.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                btnCamera.setBackgroundColor(getResources().getColor(R.color.transparent));
                btnStar.setBackgroundColor(getResources().getColor(R.color.transparent));
                File tempDelete = new File(imageAbsolutePath);
                if (tempDelete.exists()) {
                    if (!tempDelete.delete()) {
                        Toast.makeText(this, "WARNING: Can't Delete Temp File.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    public File getImageFile() {

        String fileName = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss ").format(new Date());
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(fileName, ".jpg", storageDir);
            imageAbsolutePath = image.getAbsolutePath();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return  image;
    }

}
