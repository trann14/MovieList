#Movie List App

##Overview
The Movie List App is an Android application that allows users to manage movies they want to watch or have already watched.
Users can add movies, organize them into categories, rate them, and keep notes.
The app helps users track their movie-watching progress in one place.

---

## Features
- Add movies to a list
- Mark movies as "Watched" or "Plan to Watch"
- Add movie ratings (for watched movies)
- Add personal notes
- View watched and planned movies in separate lists
- Delete movies from lists
- Track total, watched, and planned movies

---

## Technologies Used
- Java (Android)
- Android Studio
- RecyclerView
- SharedPreferences (for local storage)
- Gson (for saving objects as JSON)
- XML for UI design

---

## App Structure
- MainActivity → Handles navigation between screens
- AddMovieFragment → Add new movies
- WatchedFragment → Displays watched movies
- PlanToWatchFragment → Displays movies to watch
- MoviesTrackerFragment → Shows movie statistics
- MovieAdapter → Handles RecyclerView display
- MovieManager → Handles data storage and retrieval

---

## How to Run the Project
1. Open Android Studio
2. Clone or download the project
3. Open the project folder in Android Studio
4. Sync Gradle files
5. Run the app using an emulator or physical device

---

## Project Purpose
This project was created to practice Android development, including UI design, fragment navigation, data storage, and RecyclerView implementation.

---

## Team Members
- Lucy — Backend logic, data handling, app functionality
- Addisen — UI/UX design, presentation, and project report

---

## Notes
- The app is designed to be simple, lightweight, and user-friendly.