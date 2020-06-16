package com.example.marvel.ui.fragments.characters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marvel.R;
import com.example.marvel.api.model.SectionCharacters;
import com.example.marvel.databinding.ItemsHeaderBinding;

import java.util.ArrayList;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {

    private ArrayList<SectionCharacters> dataList;
    private Context mContext;

    public SectionAdapter(ArrayList<SectionCharacters> dataList, Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsHeaderBinding itemsHeaderBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.items_header, parent, false);
        return new SectionViewHolder(itemsHeaderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
        SectionCharacters itemAtual = dataList.get(position);
        holder.view.setSection(itemAtual);

        CharactersAdapter charactersAdapter = new CharactersAdapter(itemAtual.getCharacters(), mContext);
        holder.view.rvCharacters.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false));
        holder.view.rvCharacters.setAdapter(charactersAdapter);
        holder.view.rvCharacters.setOnFlingListener(null);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(holder.view.rvCharacters);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class SectionViewHolder extends RecyclerView.ViewHolder {

        public ItemsHeaderBinding view;

        public SectionViewHolder(@NonNull ItemsHeaderBinding itemView) {
            super(itemView.getRoot());

            this.view = itemView;
        }
    }
}
