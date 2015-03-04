package com.kbear.noknok.factories;

import com.kbear.noknok.common.ServiceConstants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by allen on 3/3/15.
 */
public class JsonFactory {

    public static JSONObject AccountJsonBuilder(String username, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(ServiceConstants.REQUEST_PARAMETER_USERNAME, username);
            jsonObject.put(ServiceConstants.REQUEST_PARAMETER_PASSWORD, password);
            return jsonObject;
        } catch (JSONException e) {
            return null;
        }
    }

    public static JSONObject MessageJsonBuilder(String msg) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(ServiceConstants.REQUEST_PARAMETER_MESSAGE, msg);
            return jsonObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
