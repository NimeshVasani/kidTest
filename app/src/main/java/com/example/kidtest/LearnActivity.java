package com.example.kidtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidtest.adapter.LearnAdapter;
import com.example.kidtest.adapter.QuizAdapter;
import com.example.kidtest.services.BackgroundSoundServices;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;
import java.util.List;

public class LearnActivity extends AppCompatActivity {

    LearnAdapter learnAdapter;
    QuizAdapter quizAdapter;
    RecyclerView recyclerView;
    Toolbar toolbar;
    ImageButton toolBack;
    TextView toolText;
    SwitchCompat music, sound;
    String data;
    Intent name;
    SharedPreferences settings;
    private List<Integer> highScoreList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        recyclerView = findViewById(R.id.recycler_items_learn);
        name = new Intent(this, BackgroundSoundServices.class);

        toolbar = findViewById(R.id.my_toolbar);
        toolText = findViewById(R.id.toolText);
        toolBack = findViewById(R.id.toolBack);
        music = findViewById(R.id.music);
        sound = findViewById(R.id.sound);

        data = getIntent().getExtras().getString("type");
        MobileAds.initialize(this, initializationStatus -> {
        });

        AdView mAdView = findViewById(R.id.adView);
//        mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        if (data.equalsIgnoreCase("learn")) {
            toolText.setText("learn");
            learnAdapter = new LearnAdapter(this);
            recyclerView.setAdapter(learnAdapter);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            toolText.setText("Quiz");
            quizAdapter = new QuizAdapter(this, highScoreList);
            recyclerView.setAdapter(quizAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        toolBack.setOnClickListener(v -> onBackPressed());
        SharedPreferences myPref = getSharedPreferences("switch", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = myPref.edit();

        music.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                myEdit.putBoolean("music", isChecked);
                myEdit.apply();
            }
        });
        sound.setOnCheckedChangeListener((buttonView, isChecked) -> {
            myEdit.putBoolean("sound", isChecked);
            myEdit.apply();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //  unbindService(connection);
        //mBound=false;
    }

    @Override
    protected void onStart() {
        super.onStart();

        settings = getSharedPreferences("switch", 0);
        boolean silent = settings.getBoolean("music", true);
        boolean silent2 = settings.getBoolean("sound", true);
        if (quizAdapter != null) {
            highScoreList.clear();
            highScoreList.add(settings.getInt("0", 0));
            highScoreList.add(settings.getInt("1", 0));
            highScoreList.add(settings.getInt("2", 0));
            highScoreList.add(settings.getInt("3", 0));
            highScoreList.add(settings.getInt("4", 0));
            highScoreList.add(settings.getInt("5", 0));
            highScoreList.add(settings.getInt("6", 0));
            highScoreList.add(settings.getInt("7", 0));
            highScoreList.add(settings.getInt("8", 0));
            quizAdapter.notifyDataSetChanged();
        }
        music.setChecked(silent);
        sound.setChecked(silent2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        settings = getSharedPreferences("switch", 0);
        // startService(name);
    }
}