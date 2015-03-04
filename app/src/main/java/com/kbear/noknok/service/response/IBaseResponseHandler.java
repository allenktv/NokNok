package com.kbear.noknok.service.response;

import org.json.JSONObject;

/**
 * Created by allen on 3/3/15.
 */
public interface IBaseResponseHandler {

    public void onResponseReceived(JSONObject response);
}
