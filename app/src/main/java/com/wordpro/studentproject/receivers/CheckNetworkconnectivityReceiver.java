package com.wordpro.studentproject.receivers;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.provider.Settings;
import android.widget.Toast;

public class CheckNetworkconnectivityReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alert = builder.create();
        if (isNetworkAvailable(context)){
            alert.cancel();
        }else {

            // Set the Alert Dialog Message
            builder.setMessage("Please check your Internet")
                    .setCancelable(false)
                    .setPositiveButton("Retry",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                                    alert.cancel();
                                    Toast.makeText(context, "Please check your internet connectivity", Toast.LENGTH_SHORT).show();

                                }
                            });
            alert.show();
        }

        isNetworkAvailable(context);
    }

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo() !=null && connectivityManager.getActiveNetworkInfo().isConnected()){
            return true;
        }else {
            return false;
        }
    }
}
