package com.red_folder.phonegap.plugin.backgroundservice;

import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;

import android.util.Log;

// Cordova Versions
// V1.8.1
import com.phonegap.api.Plugin;
// V2.0.0
//import org.apache.cordova.api.Plugin;
// V2.2.0
//import org.apache.cordova.api.CordovaPlugin;

// Need to review the documentation in http://docs.phonegap.com/en/2.2.0/guide_plugin-development_android_index.md.html#Developing%20a%20Plugin%20on%20Android
// Seems like further changes in V2.2.0

public class BackgroundServicePlugin extends Plugin {

	private static final String TAG = BackgroundServicePlugin.class.getSimpleName();

	private BackgroundServicePluginLogic mLogic;
	
	@Override
	public PluginResult execute(String action, JSONArray data, String callback) {
		PluginResult result = null;

		if (this.mLogic == null)
			this.mLogic = new BackgroundServicePluginLogic(this.ctx.getContext());
		
		try {
			result = this.mLogic.execute(action, data);
		} catch (Exception ex) {
			result = new PluginResult(Status.ERROR);
			Log.d(TAG, "Exception - " + ex.getMessage());
		}

		return result;
	}
/*
	private Context getContext() {
		// I believe the below "deprecated" is incorrect
		// See comments @ July 15, 2012 8:57 PM in:
		// http://simonmacdonald.blogspot.co.uk/2012/07/phonegap-android-plugins-sometimes-we.html
		
		// Cordova Versions
		// Cordova 1.8.1
		//return ; 
		// Cordova 2.0.0
		return this.cordova.getContext(); 
	}
*/
	@Override
	public void onDestroy() {
		super.onDestroy();

		if (this.mLogic != null) {
			this.mLogic.onDestroy();
			this.mLogic = null;
		}
	}

}
