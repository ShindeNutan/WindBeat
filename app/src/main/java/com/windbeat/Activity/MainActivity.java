package com.windbeat.Activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.windbeat.R;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer_layout;
    ImageView img_drawer_icon;
    LinearLayout lnr_nav, lnr_home, lnr_whatsapp, lnr_whatsappnumber, lnr_call, lnr_callnumber;
    boolean doubleBackToExitPressedOnce = false;

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

    }

    public void onClick() {

        img_drawer_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.openDrawer(lnr_nav);
            }
        });

        lnr_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lnr_whatsappnumber.setVisibility(View.VISIBLE);

            }
        });

        lnr_whatsappnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String no= "8291497459";
                checkWhatsApp(no);

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

//    public void sentToWhatsapp(File imageurl){
//        Uri imgUri = Uri.parse(imageurl.getAbsolutePath());
//        //Uri imgUri = Uri.parse(pictureFile.getAbsolutePath());
//        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
//        whatsappIntent.setType("text/plain");
//        whatsappIntent.setPackage("com.whatsapp");
//        whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Please post it.");
//        whatsappIntent.putExtra(Intent.EXTRA_STREAM, imgUri);
//        whatsappIntent.setType("image/jpeg");
//        whatsappIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//
//        try {
//            startActivity(whatsappIntent);
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(MainActivity.this, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
//        }
//    }

    private void checkWhatsApp(String number) {

        String smsNumber = "91"+number;
        boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
        if (isWhatsappInstalled) {
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.setType("text/plain");
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(smsNumber) + "@s.whatsapp.net");
            startActivity(sendIntent);
        } else {
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            Toast.makeText(this, "WhatsApp not Installed",
                    Toast.LENGTH_SHORT).show();
            startActivity(goToMarket);
        }
    }

    private boolean whatsappInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}
