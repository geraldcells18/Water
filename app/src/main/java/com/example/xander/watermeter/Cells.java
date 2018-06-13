package com.example.xander.watermeter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class Cells {

    //This method use to change the current activity
    public void changeActivity(Context CurrentActivity, Class NewActivity, boolean CanBack) {
        Intent intent = new Intent(CurrentActivity, NewActivity);
        CurrentActivity.startActivity(intent);
        if (!CanBack) {
            Activity activity;
            activity = (Activity) CurrentActivity;  //Cast the current context to activity.
            activity.finish();
        }
    }

    public void MessageBox(String message, String title, Context context) {
        AlertDialog.Builder ab = new AlertDialog.Builder(context);
        ab.setTitle(title);
        ab.setMessage(message);
        ab.setCancelable(true);

        ab.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog ad = ab.create();
        ad.show();
    }
}
