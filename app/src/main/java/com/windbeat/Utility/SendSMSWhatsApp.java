package com.windbeat.Utility;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.net.URLEncoder;

/**
 * Created by Nutan on 24/09/2019.
 */

public class SendSMSWhatsApp {


//        public void sendMessage(Context context, String number, String msg) {
//
//            String msg_mob = chkMobNo(number);
//            String msg_sms = chkSMS(msg);
//
//            if (msg_mob.length() > 0)
//                Toast.makeText(context, msg_mob, Toast.LENGTH_LONG).show();
//
//            else if (msg_sms.length() > 0)
//                Toast.makeText(context, msg_sms, Toast.LENGTH_LONG).show();
//
//            else {
//                String sms_status = sendSMS(context,number, msg);
//                //Toast.makeText(context, sms_status, Toast.LENGTH_LONG).show();
//            }
//        }

        public String sendWhatsApp(Context context, String msg){

            String wh_msg = "";

            boolean isWhatsappInstalled = whatsappInstalledOrNot(context, "com.whatsapp");
            if (isWhatsappInstalled) {

                PackageManager packageManager = context.getPackageManager();
                Intent i = new Intent(Intent.ACTION_VIEW);
                try {
                    String url = "https://api.whatsapp.com/send?phone="
                            + "+918291497459&text=" + URLEncoder.encode(msg, "UTF-8");
                    i.setPackage("com.whatsapp");
                    i.setData(Uri.parse(url));

                    if (i.resolveActivity(packageManager) != null)
                        context.startActivity(i);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Uri uri = Uri.parse("market://details?id=com.whatsapp");
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                wh_msg = "WhatsApp not Installed";
                context.startActivity(goToMarket);
            }
            return wh_msg;
        }

        private boolean  whatsappInstalledOrNot(Context context, String uri) {

            PackageManager pm = context.getPackageManager();
            boolean app_installed = false;
            try {
                pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
                app_installed = true;
            } catch (PackageManager.NameNotFoundException e) {
                app_installed = false;
            }
            return app_installed;
        }

//        public void callMobNo(Context context, String mob) {
//
//            String msg_mob = chkMobNo(mob);
//            if (msg_mob.length() == 0) {
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse("tel:" + mob));
//                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    return;
//                }
//                context.startActivity(callIntent);
//            }else{
//                Toast.makeText(context,msg_mob, Toast.LENGTH_LONG).show();
//            }
//        }

//        public String sendSMS(Context context,String mobno, String text) {
//
//            String msg = "";
//            try {
////            SmsManager smsdiv = SmsManager.getDefault();
////            ArrayList<String> parts = smsdiv.divideMessage(text);
////            smsdiv.sendMultipartTextMessage( mobno,null, parts,
////                    null, null);
//
//                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
//                smsIntent.setType("vnd.android-dir/mms-sms");
//                smsIntent.putExtra("address", mobno);
//                smsIntent.putExtra("sms_body", text);
//                context.startActivity(smsIntent);
//
//                msg = "SMS Sent.!";
//            } catch (Exception e){
//                msg = "SMS Not Sent.!";
//                e.toString();
//            }
//            return msg;
//        }

//        public String chkMobNo(String mobno){
//            String msg = "";
//
//            if( mobno != null && mobno.length() == 0 )
//                msg = "Mobile No Required..!";
//
//            return msg;
//        }
//
//        public String chkSMS(String sms){
//            String msg = "";
//
//            if( sms != null && sms.length() == 0 )
//                msg = "Enter text to send sms";
//
//            return msg;
//        }
//
//        public String saveFavourite(Context context, String fullname, String fullnamemar,
//                                    String mobile, String mobile2, int tablecd, String tableName){
//            String msg = "";
//
//            ContentValues values = new ContentValues();
//
//            values.put("Favourite_Cd", "");
//            values.put("FullName", fullname);
//            values.put("FullName_Mar", fullnamemar);
//            values.put("Mobile_No_1", mobile);
//            values.put("Mobile_No_2", mobile2);
//            values.put("Table_Cd", tablecd);
//            values.put("TableName", tableName);
//
//            String query =  "Select Table_Cd, TableName From Favourite " +
//                    "Where Table_Cd = '" + tablecd + "' AND " +
//                    "TableName = '"+ tableName +"'";
//
//            SQLiteDatabase sqLiteDatabase = (new DataBaseHelper(context)).getWritableDatabase();
//            long rowCnt = 0;
//            try {
//                Cursor c = sqLiteDatabase.rawQuery(query, null);
//                if (c.getCount() > 0) {
//
//                    rowCnt = sqLiteDatabase.update("Favourite",
//                            values, "Table_Cd = '" + tablecd + "' AND " +
//                                    "TableName = '"+ tableName +"'", null);
//
//                    Log.d("Data", "Updated Favourite::"
//                            + rowCnt + " Table Cd : " + tablecd + "Table Name : " + tableName);
//                    msg = "Favourite Contact Added";
//                } else {
//
//                    rowCnt = sqLiteDatabase.insert("Favourite",
//                            null, values);
//                    Log.d("Data", "Inserted Favourite::" + rowCnt);
//                    msg = "Favourite Contact Added";
//                }
//                c.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                sqLiteDatabase.close();
//            }
//
//            return  msg;
//        }
//    }

}
