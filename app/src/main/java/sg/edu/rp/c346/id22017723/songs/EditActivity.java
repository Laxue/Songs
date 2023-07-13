package sg.edu.rp.c346.id22017723.songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

public class EditActivity extends AppCompatActivity implements Serializable {

    Button btnUpdate, btnDelete;
    Button btnCancel;
    EditText etSong, etSingers, etYear;
    RadioGroup ratings;
RadioButton rb;
    Song song;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        etSong = findViewById(R.id.etSong);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        ratings = findViewById(R.id.ratings);

        Intent i = getIntent();
        song = (Song) i.getSerializableExtra("song");
        etSong.setText(song.getTitle());
        etSingers.setText(song.getSingers());
        etYear.setText(""+song.getYear());
        if(song.getStar()==1){
            rb=findViewById(R.id.starOne);
            rb.setChecked(true);
        }else if(song.getStar()==2){
            rb=findViewById(R.id.starTwo);
            rb.setChecked(true);
        }else if(song.getStar()==3) {
            rb = findViewById(R.id.starThree);
            rb.setChecked(true);
        }else if(song.getStar()==4) {
            rb = findViewById(R.id.starFour);
            rb.setChecked(true);
        }else if(song.getStar()==5) {
            rb = findViewById(R.id.starFive);
            rb.setChecked(true);
        }



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                int selectedRadioButtonId = ratings.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                int starRating = Integer.parseInt(selectedRadioButton.getText().toString());

                song.setTitle(etSong.getText().toString());
                song.setSingers(etSingers.getText().toString());
                song.setYear(Integer.parseInt(etYear.getText().toString()));
                song.setStars(starRating);
                dbh.updateSong(song);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteSong(song.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the activity and return to the SongActivity (ListView)
                finish();
            }
        });

    }


}