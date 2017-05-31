package cn.wjdiankong.hookdemo;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }
    
    private void getSignature() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            Log.i("jw", "len:"+packageInfo.signatures.length);
            if (packageInfo.signatures != null) {
            	Log.i("jw", "sig:"+packageInfo.signatures[0].toCharsString());
            }
        } catch (Exception e2) {
        } 
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	getSignature();
            }
        });
        
    }
    
}
