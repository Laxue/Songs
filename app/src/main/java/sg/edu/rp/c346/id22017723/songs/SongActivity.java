package sg.edu.rp.c346.id22017723.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SongActivity extends AppCompatActivity {

    Button btnStars;
    ListView lv;

    ArrayList<Song> al;
//    ArrayAdapter<Song> adapter;
    CustomAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        btnStars = findViewById(R.id.btnShow5);
        lv = findViewById(R.id.lv);

        al = new ArrayList<>();
        //adapter = new ArrayAdapter<>(SongActivity.this, android.R.layout.simple_list_item_1, al);
        adapter = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(adapter);

                DBHelper db = new DBHelper(SongActivity.this);

                al = db.getSongs();
                db.close();

                //ArrayAdapter adapter = new ArrayAdapter(SongActivity.this, android.R.layout.simple_list_item_1,al );
        adapter = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(adapter);

//            }
//        });


        btnStars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(SongActivity.this);
                ArrayList<Song> data = db.getSongsByRating(5); // Retrieve songs with 5-star rating
                db.close();

                //ArrayAdapter<Song> adapter = new ArrayAdapter<>(SongActivity.this, android.R.layout.simple_list_item_1, data);
                adapter = new CustomAdapter(SongActivity.this, R.layout.row, data);
                lv.setAdapter(adapter);


            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song selectedSong =  al.get(position);
                // Start the ThirdActivity and pass the selected song ID as an extra
                Intent intent = new Intent(SongActivity.this, EditActivity.class);
                intent.putExtra("song",selectedSong);
                startActivity(intent);


            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the ListView here
        DBHelper db = new DBHelper(SongActivity.this);
        al = db.getSongs();
        db.close();

        //ArrayAdapter adapter = new ArrayAdapter(SongActivity.this, android.R.layout.simple_list_item_1,al );
        adapter = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(adapter);
    }

}