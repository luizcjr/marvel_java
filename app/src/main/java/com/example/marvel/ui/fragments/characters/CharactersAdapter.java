package com.example.marvel.ui.fragments.characters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvel.R;
import com.example.marvel.api.model.Character;
import com.example.marvel.databinding.ItemsCharacterBinding;
import com.example.marvel.ui.interfaces.CharacterListener;

import java.util.ArrayList;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder> implements CharacterListener {

    private ArrayList<Character> characters;
    private Context mContext;

    public CharactersAdapter(ArrayList<Character> characters, Context mContext) {
        this.characters = characters;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CharactersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsCharacterBinding itemsCharacterBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.items_character, parent, false);
        return new CharactersViewHolder(itemsCharacterBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CharactersViewHolder holder, int position) {
        Character itemAtual = characters.get(position);
        holder.view.setCharacter(itemAtual);
        holder.view.setListener(this);
    }

    @Override
    public int getItemCount() {
        return (null != characters ? characters.size() : 0);
    }

    @Override
    public void onClick(View view) {
        for(int i = 0; i < characters.size(); i++) {
            if(view.getTag().equals(characters.get(i).getName())) {
                NavDirections action = CharactersFragmentDirections.goToDetailFragment(characters.get(i));
                Navigation.findNavController(view).navigate(action);
            }
        }
    }

    static class CharactersViewHolder extends RecyclerView.ViewHolder {

        public ItemsCharacterBinding view;

        public CharactersViewHolder(@NonNull ItemsCharacterBinding itemView) {
            super(itemView.getRoot());

            this.view = itemView;
        }
    }
}
