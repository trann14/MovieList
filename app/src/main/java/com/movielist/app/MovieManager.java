package com.movielist.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MovieManager {

    private static MovieManager instance;

    private static final String PREF_NAME = "movie_app";
    private static final String KEY_MOVIES = "movies";

    private SharedPreferences prefs;
    private Gson gson;
    private ArrayList<Movie> movieList;


    // Singleton

    public static MovieManager getInstance(Context context) {
        if (instance == null) {
            instance = new MovieManager(context);
        }
        return instance;
    }


    // Constructor

    private MovieManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
        movieList = loadMovies();
    }


    // ADD MOVIE

    public void addMovie(Movie movie) {
        movieList.add(movie);
        saveMovies();
    }


    // GET ALL MOVIES

    public ArrayList<Movie> getMovies() {
        return movieList;
    }


    // DELETE MOVIE

    public void deleteMovie(int position) {
        if (position >= 0 && position < movieList.size()) {
            movieList.remove(position);
            saveMovies();
        }
    }


    // FILTER: WATCHED

    public ArrayList<Movie> getWatchedMovies() {
        ArrayList<Movie> list = new ArrayList<>();
        for (Movie m : movieList) {
            if (m.isWatched()) {
                list.add(m);
            }
        }
        return list;
    }


    // FILTER: PLAN TO WATCH

    public ArrayList<Movie> getPlanMovies() {
        ArrayList<Movie> list = new ArrayList<>();
        for (Movie m : movieList) {
            if (m.isPlanToWatch()) {
                list.add(m);
            }
        }
        return list;
    }


    // SAVE DATA

    private void saveMovies() {
        SharedPreferences.Editor editor = prefs.edit();
        String json = gson.toJson(movieList);
        editor.putString(KEY_MOVIES, json);
        editor.apply();
    }


    // LOAD DATA

    private ArrayList<Movie> loadMovies() {
        String json = prefs.getString(KEY_MOVIES, null);

        if (json == null) {
            return new ArrayList<>();
        }

        Type type = new TypeToken<ArrayList<Movie>>() {}.getType();
        return gson.fromJson(json, type);
    }
}