package com.example.marvel.di;

import com.example.marvel.api.services.ApiRepository;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ApiComponent {
    void inject(ApiRepository repository);
}
