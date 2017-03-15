package com.addosso.Storer;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.SharedPreferences;

import android.content.Context;

/**
 * The Class MacAddressPlugin.
 */
public class StorerPlugin extends CordovaPlugin {


    private final String MY_PREFERENCES = "storer@1";
    /*
     * (non-Javadoc)
     *
     * @see org.apache.cordova.api.Plugin#execute(java.lang.String,
     * org.json.JSONArray, java.lang.String)
     */
    @Override
    public boolean execute(String action, JSONArray args,
                           CallbackContext callbackContext) {

        if (action.equals("read")) {

                JSONObject JSONresult = new JSONObject();
                try {

                    String prop = getProperty(args.getString(0));
                    JSONresult.put("settings", prop);
                    PluginResult r = new PluginResult(PluginResult.Status.OK,
                            JSONresult);
                    callbackContext.success(prop);
                    r.setKeepCallback(true);
                    callbackContext.sendPluginResult(r);
                    return true;
                } catch (JSONException jsonEx) {
                    PluginResult r = new PluginResult(
                            PluginResult.Status.JSON_EXCEPTION);
                    callbackContext.error("error");
                    r.setKeepCallback(true);
                    callbackContext.sendPluginResult(r);
                    return true;
                }
            }
            if(action.equals("write")){

                JSONObject JSONresult = new JSONObject();
                try {
                    saveProperty(args.getString(0), args.getString(1));
                    JSONresult.put("settings", "ok");
                    PluginResult r = new PluginResult(PluginResult.Status.OK,
                            JSONresult);
                    callbackContext.success("ok");
                    r.setKeepCallback(true);
                    callbackContext.sendPluginResult(r);
                    return true;
                } catch (JSONException jsonEx) {
                    PluginResult r = new PluginResult(
                            PluginResult.Status.JSON_EXCEPTION);
                    callbackContext.error("error");
                    r.setKeepCallback(true);
                    callbackContext.sendPluginResult(r);
                    return true;
                }
            }
            return false;
        }


        private String getProperty(String name){
            SharedPreferences prefs = this.cordova.getActivity().getApplicationContext().getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
            return prefs.getString(name, "");


        }

        private void saveProperty(String key, String value){

            SharedPreferences prefs = this.cordova.getActivity().getApplicationContext().getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
                editor.putString(key, value);
                editor.commit();
        }

    }