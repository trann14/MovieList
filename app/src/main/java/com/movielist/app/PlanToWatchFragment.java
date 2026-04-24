package com.movielist.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class PlanToWatchFragment extends Fragment {

    RecyclerView recyclerView;
    MovieAdapter adapter;
    ArrayList<Movie> planList;

    public PlanToWatchFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_plan_to_watch, container, false);


        // CONNECT UI

        recyclerView = view.findViewById(R.id.recycler_plan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadData();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData(); // refresh when coming back
    }

    // LOAD PLAN TO WATCH MOVIES

    private void loadData() {

        if (getContext() == null) return;

        planList = MovieManager.getInstance(getContext()).getPlanMovies();

        adapter = new MovieAdapter(planList, getContext());
        recyclerView.setAdapter(adapter);
    }
}