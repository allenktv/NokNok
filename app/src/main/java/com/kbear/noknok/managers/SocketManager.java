package com.kbear.noknok.managers;

import android.os.Handler;
import android.os.Looper;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.kbear.noknok.ServerConstants;
import com.kbear.noknok.service.response.IBaseResponseHandler;

import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by allen on 3/2/15.
 */
public class SocketManager {

    private static final SocketManager INSTANCE = new SocketManager();

    private static Socket sSocket;
    private SocketManager() {
        try {
            sSocket = IO.socket(ServerConstants.BASE_SERVER_URL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if (sSocket != null) {
            sSocket.connect();
        }
    }

    public static Socket getSocket() {
        return sSocket;
    }

    public static void emit(String event, Object data, final IBaseResponseHandler responseHandler) {
        sSocket.emit(event, data, new Ack() {
            @Override
            public void call(final Object... args) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    responseHandler.onResponseReceived((JSONObject)args[0]);
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

    public static void emitSync(String event, Object data, final IBaseResponseHandler responseHandler) {
        sSocket.emit(event, data, new Ack() {
            @Override
            public void call(Object... args) {
                responseHandler.onResponseReceived((JSONObject) args[0]);
            }
        });
    }

    public static void on(String event, final IBaseResponseHandler responseHandler) {
        sSocket.on(event, new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    responseHandler.onResponseReceived((JSONObject)args[0]);
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
