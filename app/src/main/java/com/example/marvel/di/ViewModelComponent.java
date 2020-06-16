package com.example.marvel.di;

import com.example.marvel.ui.base.BaseViewModel;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ViewModelComponent {
    void inject(BaseViewModel viewModel);
}