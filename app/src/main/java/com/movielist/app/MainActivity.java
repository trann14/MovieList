package com.movielist.app;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Keep the "EdgeToEdge" logic so the app looks nice
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Find your Bottom Navigation Bar
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        // 2. Set the default screen (Watched List) when the app first opens
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new WatchedFragment())
                    .commit();
        }

        // 3. The logic to switch screens when you tap a tab
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if (id == R.id.nav_watched) {
                selectedFragment = new WatchedFragment();
            } else if (id == R.id.nav_plan) {
                selectedFragment = new PlanToWatchFragment();
            } else if (id == R.id.nav_add) {
                selectedFragment = new AddMovieFragment();
            } else if (id == R.id.nav_tracker) {
                selectedFragment = new MoviesTrackerFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });
    }
}