package com.movielist.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MoviesTrackerFragment extends Fragment {

    TextView txtTotal, txtWatched, txtPlan;

    public MoviesTrackerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movies_tracker, container, false);


        // CONNECT UI

        txtTotal = view.findViewById(R.id.txt_total_movies);
        txtWatched = view.findViewById(R.id.txt_watched_movies);
        txtPlan = view.findViewById(R.id.txt_plan_movies);

        // Load stats when screen opens
        loadStats();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadStats(); // refresh when user comes back to this tab
    }


    // CALCULATE AND DISPLAY STATS

    private void loadStats() {

        if (getContext() == null) return;

        ArrayList<Movie> movies =
                MovieManager.getInstance(getContext()).getMovies();

        int total = movies.size();
        int watched = 0;
        int plan = 0;

        for (Movie movie : movies) {
            if (movie.isWatched()) {
                watched++;
            } else if (movie.isPlanToWatch()) {
                plan++;
            }
        }


        // UPDATE UI TEXT

        txtTotal.setText("Total Movies: " + total);
        txtWatched.setText("Watched: " + watched);
        txtPlan.setText("Plan to Watch: " + plan);
    }
}