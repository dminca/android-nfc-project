package org.dminca.transferescu;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager pm = this.getPackageManager();
//        check if NFC is available on device
        if (!pm.hasSystemFeature(PackageManager.FEATURE_NFC)) {
            Toast.makeText(this,
                    "The device doesnt have NFC hardware",
                    Toast.LENGTH_SHORT).show();
        }
        else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            Toast.makeText(this,
                    "Android Beam is not supported",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,
                    "Android Beam is supported on your device",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void sendFile(View view) {
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        // check if NFC enabled
        if (!nfcAdapter.isEnabled()) {
            Toast.makeText(this,
                    "Please enable NFC",
                    Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Settings.ACTION_NFCSHARING_SETTINGS));
        }
        else {
            // NFC & Android Beam are supported

            // File to be transfered
            // TODO: Add `wallpaper.jpg` in the Downloads directory before this
            String fileName = "wallpaper.jpg";

            // retrieve path to public Downloads directory
            File fileDirectory = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS
            );

            // create a new file using the specified dir and name
            File fileToTransfer = new File(fileDirectory, fileName);
            fileToTransfer.setReadable(true, false);

            nfcAdapter.setBeamPushUris(
                    new Uri[]{Uri.fromFile(fileToTransfer)}, this
            );
        }
    }
}
