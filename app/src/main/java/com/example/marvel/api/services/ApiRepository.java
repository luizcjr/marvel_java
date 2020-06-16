package com.example.marvel.api.services;

import com.example.marvel.api.response.CharactersResponse;
import com.example.marvel.di.DaggerApiComponent;

import javax.inject.Inject;

import io.reactivex.Single;

public class ApiRepository {

    @Inject
    public ApiDataSource api;

    public ApiRepository() {
        DaggerApiComponent.create().inject(this);
    }

    public Single<CharactersResponse> getAllCharacters() {
        return api.getCharacters();
    }
}
