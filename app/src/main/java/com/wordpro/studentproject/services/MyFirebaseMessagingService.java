package com.wordpro.studentproject.services;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.wordpro.studentproject.activities.MainActivity;
import com.wordpro.studentproject.activities.NotificationActivity;
import com.wordpro.studentproject.app.Config;
import com.wordpro.studentproject.utils.NotificationUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    //This class receives the firebase messages into onMessageReceived() method.
    //> When notification type message is sent, firebase automatically shows the notification when the app is in background. If the app is in foreground, handleNotification() method handles the notification message.
    //
    //> When data type message is sent, handleDataMessage() method is used to handle the payload irrespective of app state (foreground / background).
    //
    //> LocalBroadcastManager is used to broadcast the message to all the activities which are registered for the broadcast receiver.

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    private NotificationUtils notificationUtils;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage == null)
            return;

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
            handleNotification(remoteMessage.getNotification().getBody());
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            //   Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
            Log.e(TAG, "Data Payload: " + remoteMessage.getData());
            try {
                JSONObject json = new JSONObject(remoteMessage.getData());
                // JSONObject json=new JSONObject("{\"data\":{\"title\":\"Notification message demo\",\"message\":\"Notification with data and image\",\"image\":\"https://upload.wikimedia.org/wikipedia/en/7/7d/Minions_characters.png\",\"timestamp\":\"28/01/2019\",\"is_backgroud\":\"Y\"}}");
                handleDataMessage(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
    }

    // When notification type message is sent, firebase automatically shows the notification when the app is in background.
    // If the app is in foreground, handleNotification() method handles the notification message.

    private void handleNotification(String message) {
        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
            // app is in foreground, broadcast the push message
            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
            pushNotification.putExtra("message", message);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

            // play notification sound
            NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            notificationUtils.playNotificationSound();
        }else{
            // If the app is in background, firebase itself handles the notification

        }
    }


    // When data type message is sent,
    // handleDataMessage() method is used to handle the payload irrespective of app state (foreground / background).

    private void handleDataMessage(JSONObject json) {
        Log.e(TAG, "push json: " + json);

        try {

           /* JSONObject imageObject=json.getJSONObject("image");
            JSONObject titleObject=json.getJSONObject("title");
            JSONObject messageObject=json.getJSONObject("message");*/

            String imageUrl= String.valueOf(json.getString("image"));
            String title= String.valueOf(json.getString("title"));
            String message= String.valueOf(json.getString("message"));
            String timestamp= "29-1-2019";




            // JSONObject data = json.getJSONObject("data");

//            String title = data.getString("title");
////            String message = data.getString("message");
////           // boolean isBackground = data.getBoolean("is_background");
////           //  boolean isBackground = true;
////            String imageUrl = data.getString("image");
////            String timestamp = data.getString("timestamp");
////            //JSONObject payload = data.getJSONObject("payload");

            Log.e(TAG, "title: " + title);
            Log.e(TAG, "message: " + message);
            //Log.e(TAG, "isBackground: " + isBackground);
            //Log.e(TAG, "payload: " + payload.toString());
            Log.e(TAG, "imageUrl: " + imageUrl);
            //Log.e(TAG, "timestamp: " + timestamp);


            if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {

                // app is in background, show the notification in notification tray
                Intent resultIntent = new Intent(getApplicationContext(), NotificationActivity.class);
                resultIntent.putExtra("message", message);

                // check for image attachment
                if (TextUtils.isEmpty(imageUrl)) {
                    showNotificationMessage(getApplicationContext(), title, message, timestamp, resultIntent);
                } else {
                    // image is present, show notification with image
                    showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, resultIntent, imageUrl);
                }
              /*  // app is in foreground, broadcast the push message
                Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
                pushNotification.putExtra("message", message);
                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

                // play notification sound
                NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
                notificationUtils.playNotificationSound();*/
            } else {
                // app is in background, show the notification in notification tray
                Intent resultIntent = new Intent(getApplicationContext(), NotificationActivity.class);
                resultIntent.putExtra("message", message);

                // check for image attachment
                if (TextUtils.isEmpty(imageUrl)) {
                    showNotificationMessage(getApplicationContext(), title, message, timestamp, resultIntent);
                } else {
                    // image is present, show notification with image
                    showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, resultIntent, imageUrl);
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    /**
     * Showing notification with text only
     */
    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }

    /**
     * Showing notification with text and image
     */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
    }
}
