package com.example.marvel.api.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Caracteristics implements Parcelable {
    private String birth;
    private String universe;
    private Measures weight;
    private Measures height;

    protected Caracteristics(Parcel in) {
        birth = in.readString();
        universe = in.readString();
        weight = in.readParcelable(Measures.class.getClassLoader());
        height = in.readParcelable(Measures.class.getClassLoader());
    }

    public static final Creator<Caracteristics> CREATOR = new Creator<Caracteristics>() {
        @Override
        public Caracteristics createFromParcel(Parcel in) {
            return new Caracteristics(in);
        }

        @Override
        public Caracteristics[] newArray(int size) {
            return new Caracteristics[size];
        }
    };

    public String getBirth() {
        return birth;
    }

    public String getUniverse() {
        return universe;
    }

    public Measures getWeight() {
        return weight;
    }

    public Measures getHeight() {
        return height;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(birth);
        dest.writeString(universe);
        dest.writeParcelable(weight, flags);
        dest.writeParcelable(height, flags);
    }
}

