package com.example.marvel.api.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Measures implements Parcelable {

    private double value;
    private String unity;

    protected Measures(Parcel in) {
        value = in.readDouble();
        unity = in.readString();
    }

    public static final Creator<Measures> CREATOR = new Creator<Measures>() {
        @Override
        public Measures createFromParcel(Parcel in) {
            return new Measures(in);
        }

        @Override
        public Measures[] newArray(int size) {
            return new Measures[size];
        }
    };

    public double getValue() {
        return value;
    }

    public String getUnity() {
        return unity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(value);
        dest.writeString(unity);
    }
}

