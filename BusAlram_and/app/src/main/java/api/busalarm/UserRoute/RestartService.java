package api.busalarm.UserRoute;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RestartService extends BroadcastReceiver {
    public static final String ACTION_RESTART_PERSISTENTSERVICE = "ACTION.Restart. PersistentService";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("RestartService", "RestartService called!@!@@@@@#$@$@#$@#$@#");
        if (intent.getAction().equals(ACTION_RESTART_PERSISTENTSERVICE)) {
            Intent i = new Intent(context, UserRoute.class);
            context.startService(i);
        }
    }
}
