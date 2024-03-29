package com.udacity.popularmovies;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmovies.model.MovieListings;
import com.udacity.popularmovies.utils.NetworkUtils;

import java.util.List;

public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesAdapterViewHolder> {

    private MovieListings mMovieListings;
    private List<MovieListings.Movie> mMoviesList;

    /*
     * An on-click handler that we've defined to make it easy for an Activity to interface with
     * our RecyclerView
     */
    private final PopularMoviesAdapterOnClickHandler mClickHandler;

    /**
     * The interface that receives onClick messages.
     */
    public interface PopularMoviesAdapterOnClickHandler {
        void onClick(String selectedMovie);
    }

    /**
     * Creates a ForecastAdapter.
     *
     * @param clickHandler The on-click handler for this adapter. This single handler is called
     *                     when an item is clicked.
     */
    public PopularMoviesAdapter(PopularMoviesAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    /**
     * Cache of the children views for a forecast list item.
     */
    public class PopularMoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView mMovieImageView;
        //private final TextView mMovieTitle;

        public ImageView getmMovieImageView() {
            return mMovieImageView;
        }

        //private final TextView mMovieOverview;

        private Context mContext;

        public PopularMoviesAdapterViewHolder(View view) {
            super(view);
            mMovieImageView = (ImageView) view.findViewById(R.id.tv_movie_poster);
            //mMovieTitle = (TextView) view.findViewById(R.id.tv_original_title_list);
            //mMovieOverview = (TextView) view.findViewById(R.id.tv_release_date_list);
            mContext = view.getContext();
            view.setOnClickListener(this);
        }

        /**
         * This gets called by the child views during a click.
         *
         * @param v The View that was clicked
         */
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            //MovieListings.Movie movieSelected = moviesListData.get(adapterPosition);
            mClickHandler.onClick(adapterPosition+"");
        }

        public Context getmContext() {
            return mContext;
        }

    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new ForecastAdapterViewHolder that holds the View for each list item
     */
    @Override
    public PopularMoviesAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new PopularMoviesAdapterViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the movie
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param  popularMoviesAdapterViewHolder The ViewHolder which should be updated to represent the
     *                                  contents of the item at the given position in the data set.
     * @param position                  The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(PopularMoviesAdapterViewHolder popularMoviesAdapterViewHolder, int position) {
        if(mMovieListings!=null && mMovieListings.getMoviesList()!=null) {
            MovieListings.Movie selectedMovie = mMovieListings.getMoviesList().get(position);
            Picasso.with(popularMoviesAdapterViewHolder.getmContext()).load(
                    NetworkUtils.getImageUrl(selectedMovie.getPosterPath())).into(popularMoviesAdapterViewHolder.getmMovieImageView());
            //popularMoviesAdapterViewHolder.getmMovieTitle().setText(selectedMovie.getTitle());
            //popularMoviesAdapterViewHolder.getmMovieOverview().setText(selectedMovie.getOverview());
        }
    }

    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        if (null == mMovieListings || null == mMovieListings.getMoviesList()) return 0;
        //In this iteration of code, I have not fetched all pages from the movies database. Only the first page with 20 movies will be fetched and displayed.
        return mMovieListings.getMoviesList().size();
    }

    /**
     * This method is used to set the movies data on a PopularMoviesAdapter if we've already
     * created one. This is handy when we get new data from the web but don't want to create a
     * new PopularMoviesAdapter to display it.
     *
     * @param movieListings The new weather data to be displayed.
     */
    public void setMoviesData(MovieListings movieListings) {
        this.mMovieListings = movieListings;
        notifyDataSetChanged();
    }

    public MovieListings.Movie getMovieDetails(int index) {
        if(mMovieListings!=null && mMovieListings.getMoviesList()!=null)
            return mMovieListings.getMoviesList().get(index);
        else
            return null;
    }

}
