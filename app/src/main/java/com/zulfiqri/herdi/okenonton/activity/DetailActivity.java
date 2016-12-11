package com.zulfiqri.herdi.okenonton.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zulfiqri.herdi.okenonton.R;

public class DetailActivity extends AppCompatActivity {

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }



    public static class PlaceholderFragment extends Fragment {


        public PlaceholderFragment() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail_activity, container, false);



            TextView movie_title = (TextView) rootView.findViewById(R.id.movie_title);
            TextView release_date = (TextView) rootView.findViewById(R.id.release_date);
            TextView rating = (TextView) rootView.findViewById(R.id.rating);
            TextView overview = (TextView) rootView.findViewById(R.id.overview);
            ImageView poster_path = (ImageView) rootView.findViewById(R.id.poster_path);

            Intent intent = getActivity().getIntent();

            //setTitle in actionBar
            String title_bar = intent.getStringExtra("title_bar");
            getActivity().setTitle(title_bar);

            movie_title.setText(intent.getStringExtra("title"));

             release_date.setText(intent.getStringExtra("release_date"));
            rating.setText(intent.getStringExtra("vote_average"));

            overview.setText(intent.getStringExtra("overview"));


            //add data poster_path
            Picasso.with(context)
                    .load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + intent.getStringExtra("poster_path"))
                    .placeholder(R.drawable.ic_empty)
                    .error(R.drawable.ic_empty)
                    .resize(200,300)
                    .into(poster_path);


            return rootView;

        }
    }
}
