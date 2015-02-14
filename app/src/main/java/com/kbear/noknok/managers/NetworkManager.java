package com.kbear.noknok.managers;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

/**
 * Created by allen on 2/13/15.
 */
public final class NetworkManager {

    private static final String TAG = "NetworkManager";

    private static final AsyncHttpClient client = new AsyncHttpClient();
    private static final SyncHttpClient syncClient = new SyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.d(TAG, "Sending out a GET request, URL: " + url);
        client.get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.d(TAG, "Sending out a POST request, URL: " + url);
        client.post(url, params, responseHandler);
    }

    public static void syncGet(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.d(TAG, "Sending out a syncGET request, URL: " + url);
        syncClient.get(url, params, responseHandler);
    }

    public static void syncPost(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        Log.d(TAG, "Sending out a SyncPOST request, URL: " + url);
        syncClient.post(url, params, responseHandler);
    }
}
