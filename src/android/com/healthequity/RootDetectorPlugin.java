package com.healthequity;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.File;

public class RootDetectorPlugin extends CordovaPlugin {
	public static final String ACTION_ROOT_DETECTOR = "checkForRoot";
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		boolean result = false;
		
	    try {
	    	if (ACTION_ROOT_DETECTOR.equals(action)) {
	    		result = findBinary("su");
				
				callbackContext.success("true");
			}
	    }
	    catch (Exception ex) {
	    	System.err.println("Exception: " + ex.getMessage());
	    	callbackContext.error("Exception occured.  " + ex.getMessage());
	    }
	    
	    return result;
	}
	
	private boolean findBinary(String binaryName) {
	    boolean found = false;
	    
	    if (!found) {
	        String[] places = {"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/",
	                "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};
	        for (String where : places) {
	            if ( new File( where + binaryName ).exists() ) {
	                found = true;
	                
	                break;
	            }
	        }
	    }
	    
	    return found;
	}
}