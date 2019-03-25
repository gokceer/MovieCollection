package com.example.user.moviecollection;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private ListView dataList;
    private String name;
    private String title, year, director, category;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList = findViewById(R.id.list);
        db = new DatabaseHelper(this);

        showList();

        Button newRecord = findViewById(R.id.addMovieBtn);
        newRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(startIntent);
            }
        });

        dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = dataList.getAdapter().getItem(position);

                Movie m = (Movie) item;

                Intent contentActivity = new Intent(view.getContext(), ThirdActivity.class);
                contentActivity.putExtra("id", m.id);
                contentActivity.putExtra("title", m.title);
                contentActivity.putExtra("year", m.year);
                contentActivity.putExtra("director", m.director);
                contentActivity.putExtra("category", m.category);
                startActivity(contentActivity);

            }
        });

    }

    protected void onResume() {
        super.onResume();
        showList();
    }

    public void showList() {
        List<Movie> allMovies = db.getAllMovie();
        //create the list adapter and set the adapter
        //dataList.setAdapter(new CustomAdapter(MainActivity.this, allMovies));
        /*for (Movie mv : allMovies) {
           id = mv.getId();
           title = mv.getTitle();
           year = mv.getYear();
           director = mv.getDirector();
           category = mv.getCategory();
        }*/
        dataList.setAdapter(new CustomAdapter(MainActivity.this, allMovies));
    }
}
