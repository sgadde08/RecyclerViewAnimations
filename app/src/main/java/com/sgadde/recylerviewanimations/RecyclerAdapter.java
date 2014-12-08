package com.sgadde.recylerviewanimations;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by sandeepgadde on 12/2/14.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(layoutInflater.inflate(R.layout.recycler_item,viewGroup, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder)viewHolder;
        Context context = ((RecyclerViewHolder) viewHolder).imageView.getContext();
        recyclerViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher));
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    private class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.image_view);
        }
    }
}
