package com.example.user.moviecollection;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    List<Movie> movie;
    private Context context;
    private LayoutInflater inflater = null;
    private String search_string="";

    public CustomAdapter(Context context, List<Movie> movie){
        this.context = context;
        this.movie = movie;
    }

    @Override
    public int getCount() {
        return movie.size();
    }

    @Override
    public Object getItem(int position) {
        return movie.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.movie_list,null);
        }

        TextView title = row.findViewById(R.id.titleV);
        TextView content = row.findViewById(R.id.contentV);

        String cont = movie.get(position).getYear() + ", " + movie.get(position).getDirector();
        title.setText(movie.get(position).getTitle());
        content.setText(cont);

        String str = (movie.get(position).getTitle());
        final String[] splited = str.split("\\s+");


        Button youtubeBtn = row.findViewById(R.id.youtubeBtn);
        youtubeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < splited.length; i++){
                    search_string = search_string + splited[i] + "+";
                }
                Intent ytbIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/results?search_query=" + search_string + "trailer"));
                context.startActivity(ytbIntent);
            }
        });

        notifyDataSetChanged();


        return row;
    }
}
