package com.kbear.noknok.factories;

import com.kbear.noknok.service.request.RequestParameters;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by allen on 3/3/15.
 */
public class JsonFactory {

    public static JSONObject buildFromRequestParameters(final RequestParameters requestParameters) {
        JSONObject jsonObject = new JSONObject();
        try {
            Map<String, String> reqParams = requestParameters.getStringMap();
            Map<String, Object> objParams = requestParameters.getObjectMap();
            for (Map.Entry<String, String> entry : reqParams.entrySet()) {
                jsonObject.put(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<String, Object> entry : objParams.entrySet()) {
                jsonObject.put(entry.getKey(), entry.getValue());
            }
            return jsonObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
