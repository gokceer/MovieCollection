package com.example.user.moviecollection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final EditText title = findViewById(R.id.titleInput);
        final EditText year = findViewById(R.id.yearInput);
        final EditText director = findViewById(R.id.directorInput);
        final EditText category = findViewById(R.id.categoryInput);

        db = new DatabaseHelper(getApplicationContext());

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mTitle = title.getText().toString();
                String mYear = year.getText().toString();
                String mDirector = director.getText().toString();
                String mCategory = category.getText().toString();

                if(mTitle.length() != 0 && mYear.length() != 0 && mDirector.length() != 0 && mCategory.length() != 0){
                    Movie movie = new Movie();
                    movie.setTitle(mTitle);
                    movie.setYear(mYear);
                    movie.setCategory(mCategory);
                    movie.setDirector(mDirector);
                    db.createMovie(movie);
                    toastMessage("Successfully created");
                } else {
                    toastMessage("Please fill the fields!!!");
                }
                finish();
            }
        });

    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
