package com.example.user.moviecollection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        db = new DatabaseHelper(getApplicationContext());

        id = getIntent().getExtras().getInt("id");
        final String title = getIntent().getExtras().getString("title");
        final String year = getIntent().getExtras().getString("year");
        final String director = getIntent().getExtras().getString("director");
        final String category = getIntent().getExtras().getString("category");

        final EditText titleInput = findViewById(R.id.titleInput);
        titleInput.setText(title);
        final EditText yearInput = findViewById(R.id.yearInput);
        yearInput.setText(year);
        final EditText directorInput = findViewById(R.id.directorInput);
        directorInput.setText(director);
        final EditText categoryInput = findViewById(R.id.categoryInput);
        categoryInput.setText(category);


        Button deleteBtn = findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteMovie(id);
                toastMessage("Successfully deleted");
            }
        });

        Button updateBtn = findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(!titleInput.getText().toString().equals("") && !yearInput.getText().toString().equals("") && !categoryInput.getText().toString().equals("")
                    && !directorInput.getText().toString().equals("")) {
                    Movie mv = new Movie();
                    mv.setId(id);
                    mv.setTitle(titleInput.getText().toString());
                    mv.setYear(yearInput.getText().toString());
                    mv.setCategory(categoryInput.getText().toString());
                    mv.setDirector(directorInput.getText().toString());

                    db.updateMovie(mv);

                    toastMessage("Successfully updated");
                } else {
                    toastMessage("Please fill all of the areas!");
                }
            }
        });

    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
