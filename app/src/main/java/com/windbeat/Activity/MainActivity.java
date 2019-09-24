package com.windbeat.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.windbeat.R;
import com.windbeat.Utility.SendSMSWhatsApp;

public class MainActivity extends AppCompatActivity {

    private BottomSheetBehavior sheetBehaviorr;
    DrawerLayout drawer_layout;
    ImageView img_drawer_icon;
    LinearLayout lnr_nav, lnr_home, lnr_whatsapp, lnr_whatsappnumber, lnr_call, lnr_callnumber;
    boolean doubleBackToExitPressedOnce = false;
    private LinearLayout layoutBottomSheetContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onClick();
    }

    public void init() {
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        img_drawer_icon = (ImageView) findViewById(R.id.img_drawer_icon);
        lnr_nav = (LinearLayout) findViewById(R.id.lnr_nav);
        lnr_home = (LinearLayout) findViewById(R.id.lnr_home);
        lnr_whatsapp = (LinearLayout) findViewById(R.id.lnr_whatsapp);
        lnr_whatsappnumber = (LinearLayout) findViewById(R.id.lnr_whatsappnumber);
        lnr_call = (LinearLayout) findViewById(R.id.lnr_call);
        lnr_callnumber = (LinearLayout) findViewById(R.id.lnr_callnumber);
        layoutBottomSheetContact = findViewById(R.id.bottom_sheet_contact);


    }

    public void onClick() {
        sheetBehaviorr = BottomSheetBehavior.from(layoutBottomSheetContact);

        img_drawer_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.openDrawer(lnr_nav);
            }
        });

        lnr_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = null;

                lnr_whatsappnumber.setVisibility(View.VISIBLE);

                sheetBehaviorr.setState(BottomSheetBehavior.STATE_COLLAPSED);

                SendSMSWhatsApp sendSMSWhatsApp = new SendSMSWhatsApp();
                sendSMSWhatsApp.sendWhatsApp(view.getContext(), message);

            }


        });

        lnr_whatsappnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse("tel:" + R.string.number));
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                        // TODO: Consider calling
//                        //    Activity#requestPermissions
//                        // here to request the missing permissions, and then overriding
//                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                        //                                          int[] grantResults)
//                        // to handle the case where the user grants the permission. See the documentation
//                        // for Activity#requestPermissions for more details.
//
//
//                        return;
//                    }
//                }
//                startActivity(callIntent);

            }
        });

        lnr_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lnr_callnumber.setVisibility(View.VISIBLE);
            }
        });

        lnr_callnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + R.string.number));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    Activity#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                        return;
                    }
                }
                startActivity(callIntent);

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    void backPressed() {

        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (drawer_layout.isDrawerOpen(lnr_nav)) {
            drawer_layout.closeDrawer(lnr_nav);
        } else {
            backPressed();
        }
    }
}
