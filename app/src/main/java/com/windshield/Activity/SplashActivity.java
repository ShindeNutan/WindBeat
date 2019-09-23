package com.windshield.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import com.windshield.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        splashScreen();
        init();
    }

    void init() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (
//                    checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
//                    checkSelfPermission(android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ||
                    /*checkSelfPermission(Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED ||*/
                    checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED ||

                    checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // We will need to request the permission
                requestPermissions(new String[]{
                                Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                                Manifest.permission.CALL_PHONE,},
                        1);
            } else {
                // The permission is granted, we can perform the action
                //    getImages();
                splashScreen();
            }
        } else {
            splashScreen();
        }
    }

    public void splashScreen(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        },1500);
    }
}
