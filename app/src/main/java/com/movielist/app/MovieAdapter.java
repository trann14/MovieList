package com.movielist.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ArrayList<Movie> movieList;
    private Context context;

    public MovieAdapter(ArrayList<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie_card, parent, false);

        return new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        Movie movie = movieList.get(position);


        // BASIC DATA

        holder.txtTitle.setText(movie.getTitle());
        holder.txtCategory.setText(movie.getCategory());
        holder.txtNotes.setText(movie.getNotes());

        //delete button
        holder.btnDelete.setOnClickListener(v -> {

            // remove from storage
            MovieManager.getInstance(context).deleteMovie(position);

            // remove from list
            movieList.remove(position);

            // refresh UI
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, movieList.size());
        });


        // RATING

        holder.ratingBar.setRating(movie.getRating());


        // EXPAND / COLLAPSE NOTES

        holder.btnExpand.setOnClickListener(v -> {

            if (holder.notesLayout.getVisibility() == View.GONE) {
                holder.notesLayout.setVisibility(View.VISIBLE);
                holder.btnExpand.setImageResource(android.R.drawable.arrow_up_float);
            } else {
                holder.notesLayout.setVisibility(View.GONE);
                holder.btnExpand.setImageResource(android.R.drawable.arrow_down_float);
            }
        });


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    // VIEW HOLDER

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtCategory, txtNotes;
        RatingBar ratingBar;
        ImageButton btnExpand;
        View notesLayout;

        Button btnDelete;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txt_movie_title);
            txtCategory = itemView.findViewById(R.id.txt_movie_category);
            txtNotes = itemView.findViewById(R.id.txt_movie_notes);

            ratingBar = itemView.findViewById(R.id.row_rating_bar);

            btnExpand = itemView.findViewById(R.id.btn_expand_notes);
            notesLayout = itemView.findViewById(R.id.layout_expandable_notes);
            btnDelete = itemView.findViewById(R.id.btn_delete_movie);
        }
    }
}