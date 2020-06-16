package com.example.marvel.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Character implements Parcelable {
    private String name;
    private String alterEgo;
    private String imagePath;
    private String biography;
    private Caracteristics caracteristics;
    private Abilities abilities;
    private List<String> movies;

    public Character() {
    }

    public Character(String name, String alterEgo, String imagePath, String biography, Caracteristics caracteristics, Abilities abilities, List<String> movies) {
        this.name = name;
        this.alterEgo = alterEgo;
        this.imagePath = imagePath;
        this.biography = biography;
        this.caracteristics = caracteristics;
        this.abilities = abilities;
        this.movies = movies;
    }

    protected Character(Parcel in) {
        name = in.readString();
        alterEgo = in.readString();
        imagePath = in.readString();
        biography = in.readString();
        caracteristics = in.readParcelable(Caracteristics.class.getClassLoader());
        abilities = in.readParcelable(Abilities.class.getClassLoader());
        in.readList(movies, String.class.getClassLoader());
    }

    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel in) {
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getAlterEgo() {
        return alterEgo;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getBiography() {
        return biography;
    }

    public Caracteristics getCaracteristics() {
        return caracteristics;
    }

    public Abilities getAbilities() {
        return abilities;
    }

    public List<String> getMovies() {
        return movies;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(alterEgo);
        dest.writeString(imagePath);
        dest.writeString(biography);
        dest.writeList(movies);
        dest.writeParcelable(caracteristics, flags);
        dest.writeParcelable(abilities, flags);
    }
}