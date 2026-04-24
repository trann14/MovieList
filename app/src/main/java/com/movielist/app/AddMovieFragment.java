package com.movielist.app;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class AddMovieFragment extends Fragment {

    EditText editTitle, editNotes;
    Spinner spinnerCategory;
    RadioButton radioPlan, radioWatched;
    LinearLayout layoutWatchedDetails;
    RatingBar ratingBar;
    Button btnSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_movie, container, false);

        // CONNECT UI

        editTitle = view.findViewById(R.id.edit_movie_title);
        editNotes = view.findViewById(R.id.edit_movie_notes);
        spinnerCategory = view.findViewById(R.id.spinner_category);

        radioPlan = view.findViewById(R.id.radio_plan);
        radioWatched = view.findViewById(R.id.radio_watched);

        layoutWatchedDetails = view.findViewById(R.id.layout_watched_details);

        ratingBar = view.findViewById(R.id.rating_bar);
        btnSave = view.findViewById(R.id.btn_save_movie);


        // SHOW / HIDE WATCHED SECTION

        radioWatched.setOnClickListener(v ->
                layoutWatchedDetails.setVisibility(View.VISIBLE)
        );

        radioPlan.setOnClickListener(v ->
                layoutWatchedDetails.setVisibility(View.GONE)
        );

        // SAVE MOVIE
        btnSave.setOnClickListener(v -> {

            String title = editTitle.getText().toString().trim();
            String notes = editNotes.getText().toString().trim();
            String category = spinnerCategory.getSelectedItem().toString();

            if (title.isEmpty()) {
                Toast.makeText(getContext(), "Enter movie title", Toast.LENGTH_SHORT).show();
                return;
            }

            Movie movie = new Movie(title);
            movie.setCategory(category);
            movie.setNotes(notes);

            if (radioWatched.isChecked()) {
                movie.setWatched(true);
                movie.setPlanToWatch(false);
                movie.setRating(ratingBar.getRating());
            } else {
                movie.setWatched(false);
                movie.setPlanToWatch(true);
            }

            // SAVE TO STORAGE
            MovieManager.getInstance(getContext()).addMovie(movie);

            // CLEAR UI
            editTitle.setText("");
            editNotes.setText("");
            ratingBar.setRating(0);
            radioPlan.setChecked(true);
            layoutWatchedDetails.setVisibility(View.GONE);

            Toast.makeText(getContext(), "Movie added!", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}