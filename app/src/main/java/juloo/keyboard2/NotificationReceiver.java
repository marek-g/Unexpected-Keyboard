package juloo.keyboard2;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

public class NotificationReceiver extends BroadcastReceiver {
    static final String TAG = "Keyboard2/Notification";
    static public final String ACTION_SHOW = "juloo.keyboard2.SHOW";
    static public final String ACTION_SETTINGS = "juloo.keyboard2.SETTINGS";

    private Keyboard2 _keyboard2;

    NotificationReceiver(Keyboard2 keyboard2) {
        super();
        _keyboard2 = keyboard2;
        Log.i(TAG, "NotificationReceiver created");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i(TAG, "NotificationReceiver.onReceive called, action=" + action);

        if (action.equals(ACTION_SHOW)) {
            InputMethodManager imm = (InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                IBinder token = _keyboard2.getConnectionToken();
                imm.showSoftInputFromInputMethod(token, InputMethodManager.SHOW_FORCED);
            }
        } else if (action.equals(ACTION_SETTINGS)) {
            Intent intent2 = new Intent(_keyboard2, SettingsActivity.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _keyboard2.startActivity(intent2);
        }
    }
}