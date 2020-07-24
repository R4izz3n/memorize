package de.kulturbremen.memorize.ui;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class Util {

    static public void showToast(Context context, String message){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
