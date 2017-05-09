package cn.wjdiankong.hookdemo;

import android.app.Application;
import android.content.Context;
import cn.wjdiankong.hookpms.ServiceManagerWraper;

public class MyApplication extends Application{
	
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
	}

	@Override
	public void onCreate() {
		ServiceManagerWraper.hookPMS(this, "308201fb30820164a003020102020450", 1338303158);
		super.onCreate();
	}


}
