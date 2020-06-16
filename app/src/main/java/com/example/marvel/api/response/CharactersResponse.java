package com.example.marvel.api.response;

import com.example.marvel.api.model.Character;

import java.util.List;

public class CharactersResponse {
    private List<Character> heroes;
    private List<Character> villains;
    private List<Character> antiHeroes;
    private List<Character> aliens;
    private List<Character> humans;

    public List<Character> getHeroes() {
        return heroes;
    }

    public List<Character> getVillains() {
        return villains;
    }

    public List<Character> getAntiHeroes() {
        return antiHeroes;
    }

    public List<Character> getAliens() {
        return aliens;
    }

    public List<Character> getHumans() {
        return humans;
    }
}
