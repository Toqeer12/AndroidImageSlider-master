package utilities;

import android.content.Context;
import android.content.Intent;

public final class CommonUtilities {
	
	// give your server registration url here
    public static final String SERVER_URL = "http://pdlapps.com/crictoday/register.php";
    public static final String SERVER_URL2 = "http://pdlapps.com/crictoday/registerAll.php";
    // Google project id
    public static final String SENDER_ID = "444210346725";

    /**
     * Tag used on log messages.
     */
    static final String TAG = "AndroidHive GCM";

    public static final String DISPLAY_MESSAGE_ACTION =
            "com.androidhive.pushnotifications.DISPLAY_MESSAGE";

    public static final String EXTRA_MESSAGE = "message";

    /**
     * Notifies UI to display a message.
     * <p>
     * This method is defined in the common helper because it's used both by
     * the UI and the background service.
     *
     * @param context application's context.
     * @param message message to be displayed.
     */
    public static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
    }

    public static void displayMessage2(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(EXTRA_MESSAGE, message);
        context.sendBroadcast(intent);
    }
}
