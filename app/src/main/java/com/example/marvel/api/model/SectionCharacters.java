package com.example.marvel.api.model;

import java.util.ArrayList;

public class SectionCharacters {

    private String headerTitle;
    private ArrayList<Character> characters;

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.characters = characters;
    }
}
