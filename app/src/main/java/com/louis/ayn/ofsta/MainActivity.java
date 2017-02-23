package com.louis.ayn.ofsta;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int appCAMERA_REQUEST_CODE = 1;

    GridView gridView;
    ImageButton btnGallery, btnCamera, btnStar;

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



        displayImages();

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
            startActivityForResult(cameraIntent, appCAMERA_REQUEST_CODE);

        } else if (id == R.id.btnStar) {
            btnGallery.setBackgroundColor(getResources().getColor(R.color.transparent));
            btnCamera.setBackgroundColor(getResources().getColor(R.color.transparent));
            btnStar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        } else {
            Toast.makeText(this, "-=BUG #1=-", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == appCAMERA_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Intent saveImageIntent = new Intent(this, CapturedImageActivity.class);
                Bundle extras = data.getExtras();
                saveImageIntent.putExtras(extras);

                startActivity(saveImageIntent);
            } else {
                btnGallery.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                btnCamera.setBackgroundColor(getResources().getColor(R.color.transparent));
                btnStar.setBackgroundColor(getResources().getColor(R.color.transparent));

            }
        } else {
            Toast.makeText(this, "-=BUG #2=-", Toast.LENGTH_LONG).show();
        }
    }

    public void displayImages() {

    }

    public void doTest(View view) {

    }

}
