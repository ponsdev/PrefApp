package com.example.vinicius.prefapp.app;

import android.content.Context;
import android.support.v7.app.AlertDialog;

/**
 * Created by vinic on 20/06/2017.
 */

public class MsgBox {

    public static void show(Context context, String title, String msg) {

        show(context, title, msg, android.R.drawable.ic_dialog_alert);
    }

    public static void show(Context context, String title, String msg, int iconId) {

        AlertDialog.Builder dlg = new AlertDialog.Builder(context);
        dlg.setIcon(iconId);
        dlg.setTitle(title);
        dlg.setMessage(msg);
        dlg.setNeutralButton("OK", null);
        dlg.show();

    }

}
