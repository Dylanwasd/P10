package sg.edu.rp.c346.p10;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class Notification extends BroadcastReceiver {
    int reqCode=12345;
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new
                    NotificationChannel("default", "Default Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("This is for default notification");
            notificationManager.createNotificationChannel(channel);
        }

        Intent i = new Intent(context, MainActivity.class);
        String hi = i.getStringExtra("text");

        PendingIntent pIntent = PendingIntent.getActivity
                ( context, reqCode, i,
                        PendingIntent.FLAG_CANCEL_CURRENT);


        // Build notification
        NotificationCompat.Builder builder = new
                NotificationCompat.Builder(context, "default");
        builder.setContentTitle("Amazing Offer!");
        builder.setContentText("Subject");
        builder.setSmallIcon(android.R.drawable.ic_dialog_info);
        builder.setContentIntent(pIntent);
        builder.setAutoCancel(true);


        android.app.Notification n = builder.build();

        // An integer good to have, for you to programmatically cancel it
        notificationManager.notify(123, n);



    }
}
