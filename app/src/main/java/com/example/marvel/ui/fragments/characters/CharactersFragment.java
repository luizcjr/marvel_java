package com.example.marvel.ui.fragments.characters;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.marvel.BR;
import com.example.marvel.R;
import com.example.marvel.api.model.SectionCharacters;
import com.example.marvel.databinding.CharactersBinding;
import com.example.marvel.ui.base.BaseFragment;

import java.util.ArrayList;

public class CharactersFragment extends BaseFragment<CharactersBinding, CharactersViewModel> {

    private SectionAdapter sectionAdapter;

    private Observer<ArrayList<SectionCharacters>> sectionLiveDataObserver = section -> {
        if (section != null && section.size() > 0) {
            initializeRecycler(section);
        }
    };

    public static CharactersFragment newInstance() {
        return new CharactersFragment();
    }

    @Override
    public int layoutId() {
        return R.layout.characters_fragment;
    }

    @Override
    public int binding() {
        return BR.viewModel;
    }

    @Override
    public CharactersViewModel viewModel() {
        return new ViewModelProvider(this).get(CharactersViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setupToolbar();
        setHasOptionsMenu(true);

        this.viewModel().getSectionCharacters().observe(getViewLifecycleOwner(), sectionLiveDataObserver);
        this.viewModel().loadError.observe(getViewLifecycleOwner(), errorLiveDataObserver);
        this.viewModel().loading.observe(getViewLifecycleOwner(), loadingLiveDataObserver);
        this.viewModel().context = getContext();
        this.viewModel().lifecycleOwner = this;
    }

    private void initializeRecycler(ArrayList<SectionCharacters> sectionCharacters) {
        sectionAdapter = new SectionAdapter(sectionCharacters, getContext());

        this.viewDataBinding().rvCharacters.setLayoutManager(new LinearLayoutManager(getContext()));
        this.viewDataBinding().rvCharacters.setAdapter(sectionAdapter);
    }

    private void setupToolbar() {
        getParentActivity().setSupportActionBar(this.viewDataBinding().toolbar);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_characters, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        }

        super.onCreateOptionsMenu(menu, inflater);
    }
}