package com.kbear.noknok.managers;

import android.os.Handler;
import android.os.Looper;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.kbear.noknok.ServerConstants;
import com.kbear.noknok.factories.JsonFactory;
import com.kbear.noknok.service.request.RequestParameters;
import com.kbear.noknok.service.response.IBaseResponseHandler;

import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by allen on 3/2/15.
 */
public class SocketManager {

    private Socket mSocket;

    public SocketManager() {
        connectSocket();
    }

    private void connectSocket() {
        try {
            IO.Options opts = new IO.Options();
            opts.secure = true;
            mSocket = IO.socket(ServerConstants.BASE_SERVER_URL, opts);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if (mSocket != null) {
            mSocket.connect();
        }
    }

    public void reconnectSocket() {
        if (!mSocket.connected()) {
            connectSocket();
        }
    }

    public void emit(String event, RequestParameters requestParameters, final IBaseResponseHandler responseHandler) {
        JSONObject data = JsonFactory.buildFromRequestParameters(requestParameters);
        mSocket.emit(event, data, new Ack() {
            @Override
            public void call(final Object... args) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    responseHandler.onResponseReceived((JSONObject) args[0]);
                } else {
                    Handler mainHandler = new Handler(Looper.getMainLooper());
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            responseHandler.onResponseReceived((JSONObject) args[0]);
                        }
                    });
                }
            }
        });
    }

    public void emitSync(String event, RequestParameters requestParameters, final IBaseResponseHandler responseHandler) {
        JSONObject data = JsonFactory.buildFromRequestParameters(requestParameters);
        mSocket.emit(event, data, new Ack() {
            @Override
            public void call(Object... args) {
                responseHandler.onResponseReceived((JSONObject) args[0]);
            }
        });
    }

    public void on(String event, final IBaseResponseHandler responseHandler) {
        mSocket.on(event, new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    responseHandler.onResponseReceived((JSONObject) args[0]);
                } else {
                    Handler mainHandler = new Handler(Looper.getMainLooper());
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            responseHandler.onResponseReceived((JSONObject) args[0]);
                        }
                    });
                }
            }
        });
    }
}
