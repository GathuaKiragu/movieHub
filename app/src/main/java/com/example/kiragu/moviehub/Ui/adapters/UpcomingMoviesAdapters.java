package com.example.kiragu.moviehub.Ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kiragu.moviehub.R;
import com.example.kiragu.moviehub.Ui.Ui.MovieDetails;
import com.example.kiragu.moviehub.Ui.model.MovieSearch;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kiragu on 9/13/17.
 */

public class UpcomingMoviesAdapters extends RecyclerView.Adapter<UpcomingMoviesAdapters.UpcomingViewHolder> {
        private ArrayList<MovieSearch> mMovieSearch = new ArrayList<>();
        private Context mContext;

        public UpcomingMoviesAdapters(Context context, ArrayList<MovieSearch> movieSearches){
            mContext = context;
            mMovieSearch = movieSearches;
        }

        /**
         Inflating the movie_List_layout and returns it as a viewholder
         */



        @Override
        public UpcomingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_list_item, parent, false);
            UpcomingViewHolder viewHolder = new UpcomingViewHolder(view);
            return viewHolder;
        }

        /**
         updating the contents of the ItemView to reflect the movie in the given position.
         */
        @Override
        public void onBindViewHolder(UpcomingMoviesAdapters.UpcomingViewHolder holder, int position){
            holder.bindMovies(mMovieSearch.get(position));
        }

        /**
         Returns the number of items the recyclerView will display
         */
        @Override
        public int getItemCount() {
            return mMovieSearch.size();
        }


        /**
         * Rendering objects to their respective views in their layouts
         */
        public class UpcomingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            @Bind(R.id.posterImageView)
            ImageView mPosterImageView;
            @Bind(R.id.movieTitle)
            TextView mMovieTitle;
            @Bind(R.id.votesTextView)
            TextView mVotesTextView;
            @Bind(R.id.releaseDateTextView) TextView mReleaseDateTextView;
            private Context mContext;

            public UpcomingViewHolder(View itemView){
                super(itemView);
                ButterKnife.bind(this, itemView);
                mContext = itemView.getContext();
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, MovieDetails.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("movieSearch", Parcels.wrap(mMovieSearch));
                mContext.startActivity(intent);
            }

            public void bindMovies(MovieSearch movieSearch){
                Picasso.with(mContext).load("http://image.tmdb.org/t/p/w185/"+movieSearch.getPoster()).into(mPosterImageView);
                mMovieTitle.setText(movieSearch.getTitle());
                mVotesTextView.setText(String.valueOf(movieSearch.getVotes()));
                mReleaseDateTextView.setText(movieSearch.getReleaseDate());
            }
        }
    }

