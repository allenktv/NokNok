package com.kbear.noknok.dtos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by allen on 2/13/15.
 */
public abstract class AbstractBaseDto implements Parcelable {

    private final String id;

    public AbstractBaseDto(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    protected AbstractBaseDto(Parcel in) {
    id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
