package com.example.marvel.ui.fragments.detailcharacter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvel.R;
import com.example.marvel.databinding.ItemsMoviesBinding;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private List<String> movies;

    public MoviesAdapter(List<String> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsMoviesBinding itemsMoviesBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.items_movies, parent, false);
        return new MoviesViewHolder(itemsMoviesBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        holder.view.setImage(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MoviesViewHolder extends RecyclerView.ViewHolder {

        public ItemsMoviesBinding view;

        public MoviesViewHolder(@NonNull ItemsMoviesBinding itemView) {
            super(itemView.getRoot());

            this.view = itemView;
        }
    }
}
