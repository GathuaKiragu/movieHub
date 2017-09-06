package com.example.kiragu.moviehub.Ui.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kiragu.moviehub.R;
import com.example.kiragu.moviehub.Ui.model.MovieSearch;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kiragu on 9/5/17.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MoviesViewHolder> {
    private ArrayList<MovieSearch> mMovieSearch = new ArrayList<>();
    private Context mContext;

    public MovieListAdapter(Context context, ArrayList<MovieSearch> movieSearches){
        mContext = context;
        mMovieSearch = movieSearches;
    }

        @Override
        public MovieListAdapter.MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
            MoviesViewHolder viewHolder = new MoviesViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MovieListAdapter.MoviesViewHolder holder, int position){
            holder.bindMovies(mMovieSearch.get(position));
        }

        @Override
        public int getItemCount() {
            return mMovieSearch.size();
        }
        public class MoviesViewHolder extends RecyclerView.ViewHolder{
             @Bind(R.id.posterImageView)
             ImageView mPosterImageView;
             @Bind(R.id.movieTitle)
             TextView mMovieTitle;
             @Bind(R.id.overViewTextView) TextView mOverView;
             @Bind(R.id.votesTextView) TextView mVotesTextView;
             @Bind(R.id.releaseDateTextView) TextView mReleaseDateTextView;
             private Context mContext;

            public MoviesViewHolder(View itemView){
                super(itemView);
                ButterKnife.bind(this, itemView);
                mContext = itemView.getContext();
        }


        public void bindMovies(MovieSearch movieSearch){
            Picasso.with(mContext).load("http://image.tmdb.org/t/p/w185/"+movieSearch.getPoster()).into(mPosterImageView);
            mMovieTitle.setText(movieSearch.getTitle());
            mOverView.setText(movieSearch.getOverview());
            mVotesTextView.setText(String.valueOf(movieSearch.getVotes()));
            mReleaseDateTextView.setText(movieSearch.getReleaseDate());
        }

    }

}

