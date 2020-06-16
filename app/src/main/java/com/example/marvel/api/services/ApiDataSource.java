package com.example.marvel.api.services;

import com.example.marvel.api.constants.Constants;
import com.example.marvel.api.response.CharactersResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiDataSource {

    @GET(Constants.CHARACTERS)
    Single<CharactersResponse> getCharacters();
}
