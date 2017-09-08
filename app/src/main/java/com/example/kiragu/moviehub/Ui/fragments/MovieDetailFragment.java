package com.example.kiragu.moviehub.Ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kiragu.moviehub.R;
import com.example.kiragu.moviehub.Ui.model.MovieSearch;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {
    @Bind(R.id.wallpaperImageView)
    ImageView mImageLabel;
    @Bind(R.id.movieTitleTextView)
    TextView mMovieTitle;
    @Bind(R.id.voteTextView) TextView mVotes;
    @Bind(R.id.overView) TextView mOverViewTexView;
    @Bind(R.id.releaseDateTextView1) TextView mRelaseDateText;

    private MovieSearch mMovieSearch;

    public static MovieDetailFragment newInstance(MovieSearch movieSearch) {
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("movieSearch", Parcels.wrap(movieSearch));
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovieSearch = Parcels.unwrap(getArguments().getParcelable("movieSearch"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);


        Picasso.with(view.getContext()).load("http://image.tmdb.org/t/p/w185/"+mMovieSearch.getPoster()).into(mImageLabel);
        mMovieTitle.setText(mMovieSearch.getTitle());
        mMovieTitle.setText(mMovieSearch.getName());
        mVotes.setText(String.valueOf(mMovieSearch.getVotes()));
        mOverViewTexView.setText(mMovieSearch.getOverview());
        mRelaseDateText.setText(mMovieSearch.getReleaseDate());

        return view;


    }

}
