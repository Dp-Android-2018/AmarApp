package dp.com.amarapp.notification;

import android.content.Context;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FirebaseMessageService extends FirebaseMessagingService {

    Context context;

    // Handler handler =null;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        System.out.println("Notification Log Title:"+remoteMessage.getNotification().getTitle());
        System.out.println("Notification Log Body:"+remoteMessage.getNotification().getBody());
        System.out.println("Notification Log Data :"+remoteMessage.getData());

//        try {
//          context = this; } catch (Exception e) {
//            System.out.println("Notification Log in firebaseMesage : " + e.getMessage());
//        }
    }


//    public void Notify(Intent intent, String messageTitle, String nb) {
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* request code */, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        long[] pattern = {500, 500, 500, 500, 500};
//
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentTitle(messageTitle)
//                .setContentText(nb)
//                .setAutoCancel(true)
//                .setVibrate(pattern)
//                .setLights(Color.BLUE, 1, 1)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent)
//                .setPriority(NotificationManager.IMPORTANCE_HIGH);
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
//    }
}


