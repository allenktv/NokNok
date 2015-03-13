package com.kbear.noknok.bo;

import com.kbear.noknok.common.ServiceConstants;
import com.kbear.noknok.managers.SocketManager;
import com.kbear.noknok.service.response.IBaseResponseHandler;

import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by allen on 3/12/15.
 */
public class SocketBO {

    private final SocketManager mSocketManager;

    @Inject
    public SocketBO(SocketManager socketManager) {
        mSocketManager = socketManager;
    }

    public void handleOnDisconnect() {
        mSocketManager.on(ServiceConstants.DISCONNECTED, new IBaseResponseHandler() {
            @Override
            public void onResponseReceived(JSONObject response) {
                mSocketManager.reconnectSocket();
            }
        });
    }
}
