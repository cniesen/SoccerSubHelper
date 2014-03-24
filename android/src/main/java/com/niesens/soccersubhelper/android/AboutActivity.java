package com.niesens.soccersubhelper.android;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import com.niesens.R;

public class AboutActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        setTitle(getResources().getString(R.string.app_name) + " - " +
                getResources().getString(R.string.about_titleSuffix));
        ((TextView) findViewById(R.id.about_appVersion)).setText(getVersion());
    }

    private String getVersion() {
        String version = "not available";

        Context context = getApplicationContext();
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();

        try {
            version = packageManager.getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }
}