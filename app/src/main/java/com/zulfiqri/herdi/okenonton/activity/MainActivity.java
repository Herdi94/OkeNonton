package com.zulfiqri.herdi.okenonton.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.zulfiqri.herdi.okenonton.DB.DatabaseHelper;
import com.zulfiqri.herdi.okenonton.R;
import com.zulfiqri.herdi.okenonton.adapter.GridLayoutAdapter;
import com.zulfiqri.herdi.okenonton.model.Movie;
import com.zulfiqri.herdi.okenonton.model.MovieResponse;
import com.zulfiqri.herdi.okenonton.rest.ApiClient;
import com.zulfiqri.herdi.okenonton.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zulfiqri.herdi.okenonton.R.layout.list_image;

public class MainActivity extends AppCompatActivity {


    //to add database helper;
    private DatabaseHelper db;

    //add imageview photo
    private ImageView img;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private RecyclerView.LayoutManager mLayoutManager;

    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "4b96d7f1939feb66c1e2cef49242c74d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //to instansiasi Database Helper
        final DatabaseHelper db = new DatabaseHelper(this);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getPopularMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                Log.d(LOG_TAG, "Number of movies received: " + movies.size());
                Toast.makeText(MainActivity.this, "Number of movies: " + movies.size(), Toast.LENGTH_LONG).show();
                //to cut screen example: 2 screen / 3 screen
                mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(new GridLayoutAdapter(movies, list_image,getApplicationContext()));


                //to view data photo from sqllite
                ImageView img = (ImageView)findViewById(list_image);
                //showPhoto(img.getDrawable().get);

            }

            private void showPhoto(int id){
                Cursor c = db.getPhoto(id);
                c.moveToFirst();

                    //recyclerView.setLayoutManager(c.getString(c.getColumnIndex(DatabaseHelper.COLUMN_PHOTO)));
                        img.setImageResource(Integer.parseInt(c.getString(c.getColumnIndex(DatabaseHelper.COLUMN_PHOTO))));

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(LOG_TAG,t.toString());
            }
        });
    }
}