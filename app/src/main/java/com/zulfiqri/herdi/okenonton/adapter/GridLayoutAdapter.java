package com.zulfiqri.herdi.okenonton.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.zulfiqri.herdi.okenonton.DB.DatabaseHelper;
import com.zulfiqri.herdi.okenonton.R;
import com.zulfiqri.herdi.okenonton.activity.DetailActivity;
import com.zulfiqri.herdi.okenonton.model.Movie;

import java.util.List;


public class GridLayoutAdapter extends RecyclerView.Adapter<GridLayoutAdapter.MovieViewHolder>{


    private DatabaseHelper db;
    private List<Movie>movies;
    private int rowLayout;
    private Context context;
    private static final String LOG_TAG = GridLayoutAdapter.class.getSimpleName();

    public GridLayoutAdapter(List<Movie> movies,int rowLayout, Context context) {
        this.movies = movies;
        this.context = context ;
        this.rowLayout = rowLayout;

    }



    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        LinearLayout moviesLayout;
        /*
        TextView movieTitle;
        TextView rating;

        TextView data;
        TextView movieDescription;

        */
        ImageView backbg;




        public MovieViewHolder(View itemView) {
            super(itemView);

            moviesLayout = (LinearLayout) itemView.findViewById(R.id.movies_layout);
            backbg = (ImageView) itemView.findViewById(R.id.ivItemGridImage);
        }



    }

    @Override
    public GridLayoutAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int position) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);

        //to show startActivity
        context = view.getContext();


        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {


        //instansiasi DatabaseHelper
        db =  new DatabaseHelper(context);

        //to add cursor database
        final Cursor cursor = new Cursor() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public int getPosition() {
                return 0;
            }

            @Override
            public boolean move(int offset) {
                return false;
            }

            @Override
            public boolean moveToPosition(int position) {
                return false;
            }

            @Override
            public boolean moveToFirst() {
                return false;
            }

            @Override
            public boolean moveToLast() {
                return false;
            }

            @Override
            public boolean moveToNext() {
                return false;
            }

            @Override
            public boolean moveToPrevious() {
                return false;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean isBeforeFirst() {
                return false;
            }

            @Override
            public boolean isAfterLast() {
                return false;
            }

            @Override
            public int getColumnIndex(String columnName) {
                return 0;
            }

            @Override
            public int getColumnIndexOrThrow(String columnName) throws IllegalArgumentException {
                return 0;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return null;
            }

            @Override
            public String[] getColumnNames() {
                return new String[0];
            }

            @Override
            public int getColumnCount() {
                return 0;
            }

            @Override
            public byte[] getBlob(int columnIndex) {
                return new byte[0];
            }

            @Override
            public String getString(int columnIndex) {
                return null;
            }

            @Override
            public void copyStringToBuffer(int columnIndex, CharArrayBuffer buffer) {

            }

            @Override
            public short getShort(int columnIndex) {
                return 0;
            }

            @Override
            public int getInt(int columnIndex) {
                return 0;
            }

            @Override
            public long getLong(int columnIndex) {
                return 0;
            }

            @Override
            public float getFloat(int columnIndex) {
                return 0;
            }

            @Override
            public double getDouble(int columnIndex) {
                return 0;
            }

            @Override
            public int getType(int columnIndex) {
                return 0;
            }

            @Override
            public boolean isNull(int columnIndex) {
                return false;
            }

            @Override
            public void deactivate() {

            }

            @Override
            public boolean requery() {
                return false;
            }

            @Override
            public void close() {

            }

            @Override
            public boolean isClosed() {
                return false;
            }

            @Override
            public void registerContentObserver(ContentObserver observer) {

            }

            @Override
            public void unregisterContentObserver(ContentObserver observer) {

            }

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void setNotificationUri(ContentResolver cr, Uri uri) {

            }

            @Override
            public Uri getNotificationUri() {
                return null;
            }

            @Override
            public boolean getWantsAllOnMoveCalls() {
                return false;
            }

            @Override
            public void setExtras(Bundle extras) {

            }

            @Override
            public Bundle getExtras() {
                return null;
            }

            @Override
            public Bundle respond(Bundle extras) {
                return null;
            }
        };

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),DetailActivity.class);
                intent.putExtra("title_bar","Detail Movies");
                intent.putExtra("poster_path",movies.get(position).getPosterPath());
                intent.putExtra("title", movies.get(position).getTitle());
                intent.putExtra("release_date",movies.get(position).getReleaseDate());
                intent.putExtra("vote_average",movies.get(position).getVoteAverage().toString());
                intent.putExtra("overview", movies.get(position).getOverview());
                context.startActivity(intent);

                //to add data to sqllite
                String photo =  cursor.getString(cursor.getColumnIndex(db.COLUMN_PHOTO));
                db.addPhoto(photo);

            }
        });
      /*
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());

*/


        Picasso.with(context).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + (movies.get(position)).getPosterPath())
                .error(R.drawable.ic_empty)
                .placeholder(R.drawable.ic_empty)
                .tag(context)
                .fit()
                .into(holder.backbg);
    }




    @Override
    public int getItemCount() {
        return movies.size();
    }


}
