package com.example.daniela.teachersjob.events;

import android.Manifest;
import android.content.ContentProvider;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class EventSelecionarFoto implements EventoApplication {

    private final AppCompatActivity activity;
    private final static int REQUEST_IMAGE_CAPTURE = 1;
    private final static int REQUEST = 2;
    private ContentProvider contentResolver;
    private ActivityCompat context;

    public EventSelecionarFoto(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void executarEvento() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    public void onRequestPermessionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_IMAGE_CAPTURE: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    // permissao concedida
                        ) {

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

}

