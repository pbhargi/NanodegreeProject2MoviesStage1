package com.udacity.popularmovies.utils;

import com.udacity.popularmovies.model.MovieListings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    final static String MOVIE_LISTINGS_RESULTS = "results";
    final static String MOVIE_LISTINGS_PAGE = "page";
    final static String MOVIE_LISTINGS_TOTAL_RESULTS = "total_results";
    final static String MOVIE_LISTINGS_TOTAL_PAGES = "total_pages";
    final static String MOVIE_POSTER_PATH = "poster_path";
    final static String MOVIE_ADULT = "adult";
    final static String MOVIE_OVERVIEW = "overview";
    final static String MOVIE_RELEASE_DATE = "release_date";
    final static String MOVIE_GENRE_IDS = "genre_ids";
    final static String MOVIE_ID = "id";
    final static String MOVIE_ORIGINAL_TITLE = "original_title";
    final static String MOVIE_ORIGINAL_LANGUAGE = "original_language";
    final static String MOVIE_TITLE = "title";
    final static String MOVIE_BACKDROP_PATH = "backdrop_path";
    final static String MOVIE_POPULARITY = "popularity";
    final static String MOVIE_VOTE_COUNT = "vote_count";
    final static String MOVIE_VIDEO = "video";
    final static String MOVIE_VOTE_AVERAGE = "vote_average";


    public static MovieListings parseMovieListingsJson(String json) {
        JSONObject movieListingsJson = null;
        MovieListings movieListings = null;
        try {
            movieListingsJson = new JSONObject(json);
            movieListings = new MovieListings();
            //JSONObject movieListingsObject = movieListingsJson.getJSONObject();
            movieListings.setPage(movieListingsJson.getInt(MOVIE_LISTINGS_PAGE));
            movieListings.setTotalResults(movieListingsJson.getInt(MOVIE_LISTINGS_TOTAL_RESULTS));
            movieListings.setTotalPages(movieListingsJson.getInt(MOVIE_LISTINGS_TOTAL_PAGES));
            movieListings.setMoviesList(getMoviesListProperty(movieListingsJson, MOVIE_LISTINGS_RESULTS));

        } catch(JSONException jsonEx){
            jsonEx.printStackTrace();
        }
        return movieListings;
    }

    public static MovieListings.Movie parseMovieJson(String json) {
        JSONObject movieJson = null;
        MovieListings.Movie movie = null;
        try {
            movieJson = new JSONObject(json);
            movie = new MovieListings.Movie();

            movie.setPosterPath(movieJson.getString(MOVIE_POSTER_PATH));
            movie.setGenreIds(getIntegerListProperty(movieJson, MOVIE_GENRE_IDS));
            movie.setAdult(movieJson.getBoolean(MOVIE_ADULT));
            movie.setOverview(movieJson.getString(MOVIE_OVERVIEW));
            movie.setReleaseDate(movieJson.getString(MOVIE_RELEASE_DATE));
            movie.setId(movieJson.getInt(MOVIE_ID));
            movie.setOriginalTitle(movieJson.getString(MOVIE_ORIGINAL_TITLE));
            movie.setOriginalLanguage(movieJson.getString(MOVIE_ORIGINAL_LANGUAGE));
            movie.setTitle(movieJson.getString(MOVIE_TITLE));
            movie.setBackdropPath(movieJson.getString(MOVIE_BACKDROP_PATH));
            movie.setPopularity(movieJson.getInt(MOVIE_POPULARITY));
            movie.setVoteCount(movieJson.getInt(MOVIE_VOTE_COUNT));
            movie.setVideo(movieJson.getBoolean(MOVIE_VIDEO));
            movie.setVoteAverage(movieJson.getInt(MOVIE_VOTE_AVERAGE));
        } catch(JSONException jsonEx){
            jsonEx.printStackTrace();
        }
        return movie;
    }

    public static List<MovieListings.Movie> getMoviesListProperty(JSONObject jsonObj, String propertyName) throws JSONException {
        JSONArray propertyArray = jsonObj.getJSONArray(propertyName);
        List<MovieListings.Movie> objectProperty = new ArrayList<MovieListings.Movie>();

        for (int i = 0; i < propertyArray.length(); i++) {
            objectProperty.add(parseMovieJson(propertyArray.getString(i)));
        }
        return objectProperty;
    }

    public static List<String> getStringListProperty(JSONObject jsonObj, String propertyName) throws JSONException {
        JSONArray propertyArray = jsonObj.getJSONArray(propertyName);
        List<String> objectProperty = new ArrayList<String>();

        for (int i = 0; i < propertyArray.length(); i++) {
            objectProperty.add(propertyArray.getString(i));
        }
        return objectProperty;
    }

    public static List<Integer> getIntegerListProperty(JSONObject jsonObj, String propertyName) throws JSONException {
        JSONArray propertyArray = jsonObj.getJSONArray(propertyName);
        List<Integer> objectProperty = new ArrayList<Integer>();

        for (int i = 0; i < propertyArray.length(); i++) {
            objectProperty.add(propertyArray.getInt(i));
        }
        return objectProperty;
    }

}
