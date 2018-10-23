package com.wearapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wearapp.BuildConfig;
import com.wearapp.R;

import java.io.File;

public class uploadActivity extends AppCompatActivity {

    LinearLayout gallery, upload;
    ImageView imageview;
    Button upload_button;

    private String imagepath=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Upload Image ");

        gallery = (LinearLayout) findViewById(R.id.go_to_gallery);
        upload = (LinearLayout) findViewById(R.id.upload);
        imageview = (ImageView) findViewById(R.id.view_image);
        upload_button = (Button) findViewById(R.id.upload_button);

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);
            }
        });

        upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(uploadActivity.this, "Image Uploading...", Toast.LENGTH_LONG).show();
                gallery.setVisibility(View.VISIBLE);
                upload.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
                if (requestCode == 1 && resultCode == RESULT_OK) {

                    Uri selectedImageUri = data.getData();
                    //imagepath = getPath(selectedImageUri);

                    Bitmap bitmap;
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);

                    //Bitmap bitmap= BitmapFactory.decodeFile(imagepath);
                    imageview.setImageBitmap(bitmap);

                    gallery.setVisibility(View.GONE);
                    upload.setVisibility(View.VISIBLE);

                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "canceled", Toast.LENGTH_SHORT).show();
                }

        } catch (Exception e) {
            Log.e("tag", "Exception in onActivityResult : " + e.getMessage());
        }
    }
    

}
