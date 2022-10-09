package com.developermano.tamildictionary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.danialgoodwin.globaloverlay.GlobalOverlay;

public class MySimpleOverlayService extends Service {

    private GlobalOverlay mGlobalOverlay;

    @Override
    public void onCreate() {
        super.onCreate();
        mGlobalOverlay = new GlobalOverlay(this);

        ImageView view = new ImageView(this);
        view.setImageResource(R.mipmap.ic_launcher_round);
        mGlobalOverlay.addOverlayView(view, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show toast with clipboard text
//                ClipboardManager clipboardManager=(ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
//                ClipData pasteData = clipboardManager.getPrimaryClip(); ClipData.Item item = pasteData.getItemAt(0);
//                String paste = item.getText().toString();
//                Toast.makeText(getBaseContext(),paste,Toast.LENGTH_LONG).show();

                Intent intent=new Intent(getBaseContext(),DialogActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                stopSelf(); // Stop service not needed.
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}

