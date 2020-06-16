package com.example.marvel.ui.fragments.detailcharacter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvel.R;
import com.example.marvel.api.model.Character;
import com.example.marvel.databinding.DetailsCharacterBinding;

import java.util.List;

public class DetailCharacterFragment extends Fragment {

    private DetailsCharacterBinding binding;
    private Character character;
    private MoviesAdapter moviesAdapter;

    public static DetailCharacterFragment newInstance() {
        return new DetailCharacterFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = DataBindingUtil.inflate(inflater, R.layout.detail_character_fragment, container, false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            character = DetailCharacterFragmentArgs.fromBundle(getArguments()).getCharacter();
            binding.setCharacter(character);
            binding.setFragment(this);

            initializeRecycler(character.getMovies());
        }
    }

    private void initializeRecycler(List<String> movies) {
        moviesAdapter = new MoviesAdapter(movies);


        binding.rvMovies.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        binding.rvMovies.setAdapter(moviesAdapter);
        binding.rvMovies.setOnFlingListener(null);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.rvMovies);
    }

    public void onBackPressed() {
        getActivity().onBackPressed();
    }
}