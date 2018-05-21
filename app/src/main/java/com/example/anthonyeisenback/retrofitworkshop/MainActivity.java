package com.example.anthonyeisenback.retrofitworkshop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static final String ARTIST_NAME = "artist_name";
    public static final String SONG_TITLE = "song_title";


    @BindView(R.id.input_artist_edittext)
    protected EditText artistNameEditText;

    @BindView(R.id.input_song_edittext)
    protected TextInputEditText songEditText;

    private LyricsFragment lyricsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    public void onBackPressed() {

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.getBackStackEntryCount() > 0) {

            fragmentManager.popBackStackImmediate();


        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("exit?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.finish();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }
    }

    @OnClick(R.id.submit_button)
    protected void submitClicked() {

        if (artistNameEditText.getText().toString().isEmpty() || songEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Both Fields Are Required", Toast.LENGTH_LONG).show();
        } else {
            lyricsFragment = LyricsFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString(ARTIST_NAME, artistNameEditText.getText().toString());
            bundle.putString(SONG_TITLE, songEditText.getText().toString());
            lyricsFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, lyricsFragment).commit();
        }




    }


}
