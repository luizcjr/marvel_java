package com.example.marvel.api.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Abilities implements Parcelable {

    private int force;
    private int intelligence;
    private int agility;
    private int endurance;
    private int velocity;

    protected Abilities(Parcel in) {
        force = in.readInt();
        intelligence = in.readInt();
        agility = in.readInt();
        endurance = in.readInt();
        velocity = in.readInt();
    }

    public static final Creator<Abilities> CREATOR = new Creator<Abilities>() {
        @Override
        public Abilities createFromParcel(Parcel in) {
            return new Abilities(in);
        }

        @Override
        public Abilities[] newArray(int size) {
            return new Abilities[size];
        }
    };

    public int getForce() {
        return force;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getAgility() {
        return agility;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getVelocity() {
        return velocity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(force);
        dest.writeInt(intelligence);
        dest.writeInt(agility);
        dest.writeInt(endurance);
        dest.writeInt(velocity);
    }
}

