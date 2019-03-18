package com.udacity.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.popularmovies.model.MovieListings;
import com.udacity.popularmovies.utils.NetworkUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        MovieListings.Movie selectedMovieObj = (MovieListings.Movie) getIntent().getSerializableExtra("serialize_data");
        if (selectedMovieObj == null) {
            // Movie data unavailable
            closeOnError();
            return;
        }


        populateUI(selectedMovieObj);

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(MovieListings.Movie selectedMovie) {

        ImageView moviePosterIV = findViewById(R.id.iv_movie_poster);
        Picasso.with(this.getApplicationContext()).load(
                NetworkUtils.getImageUrl(selectedMovie.getPosterPath())).into(moviePosterIV);

        TextView originalTitleTV = findViewById(R.id.tv_original_title);
        originalTitleTV.setText(selectedMovie.getOriginalTitle());

        TextView movieOverviewTV = findViewById(R.id.tv_movie_overview);
        movieOverviewTV.setText(selectedMovie.getOverview());

        TextView userRatingTV = findViewById(R.id.tv_user_rating);
        userRatingTV.setText(String.valueOf(selectedMovie.getVoteAverage())+"/10");

        TextView releaseDateTV = findViewById(R.id.tv_release_date);
        releaseDateTV.setText(selectedMovie.getReleaseDate());

    }
}
