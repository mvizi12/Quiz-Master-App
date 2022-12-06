package com.example.quizmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity
{
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    public void StartGame(View view)
    {
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }

    public void OpenSettings(View view)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}