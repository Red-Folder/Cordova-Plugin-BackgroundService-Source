package com.red_folder.phonegap.plugin.backgroundservice;

import org.apache.cordova.api.PluginResult;
import org.apache.cordova.api.PluginResult.Status;
import org.json.JSONArray;

import android.util.Log;

import org.apache.cordova.api.Plugin;

public class BackgroundServicePlugin extends Plugin {

	private static final String TAG = BackgroundServicePlugin.class.getSimpleName();

	private BackgroundServicePluginLogic mLogic;
	
	@Override
	public PluginResult execute(String action, JSONArray data, String callback) {
		PluginResult result = null;

		if (this.mLogic == null)
			this.mLogic = new BackgroundServicePluginLogic(this.cordova.getContext());
		
		try {
			result = this.mLogic.execute(action, data);
		} catch (Exception ex) {
			result = new PluginResult(Status.ERROR);
			Log.d(TAG, "Exception - " + ex.getMessage());
		}

		return result;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		if (this.mLogic != null) {
			this.mLogic.onDestroy();
			this.mLogic = null;
		}
	}

}
