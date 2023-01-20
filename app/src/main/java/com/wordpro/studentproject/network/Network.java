package com.wordpro.studentproject.network;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by wccs1980 on 05-09-2017.
 */

public class Network {

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo() !=null && connectivityManager.getActiveNetworkInfo().isConnected()){
            return true;
        }else {
            return false;
        }
    }

 /*   // Private class isNetworkAvailable
    private boolean isNetworkAvailable() {
        // Using ConnectivityManager to check for Network Connection
        ConnectivityManager connectivityManager = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }*/
}
