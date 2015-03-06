package com.kbear.noknok.managers;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by allen on 3/4/15.
 */
public class LocationManager implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private static final LocationManager INSTANCE = new LocationManager();
    private static GoogleApiClient sGoogleApiClient;

    private LocationManager() {}

    public static void init(Context context) {
        buildAndConnectGoogleApiClient(context);
    }

    private static synchronized void buildAndConnectGoogleApiClient(Context context) {
        sGoogleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(INSTANCE)
                .addOnConnectionFailedListener(INSTANCE)
                .addApi(LocationServices.API)
                .build();
        sGoogleApiClient.connect();
    }

    public static Location getLastLocation() {
        return LocationServices.FusedLocationApi.getLastLocation(sGoogleApiClient);
    }

    @Override
    public void onConnected(Bundle bundle) {
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }
}
