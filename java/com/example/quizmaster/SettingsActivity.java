package com.example.quizmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity
{
    EditText durationText;
    SharedPreferences myPreferences;
    Spinner modesSpinner, categoriesSpinner;
    ArrayAdapter<CharSequence> modesAdapter, categoriesAdapter;
    private final String LOG_TAG = SettingsActivity.class.getSimpleName();
    private String sharedPrefFile = "com.example.quizmaster";
    public static String playerMode = "2", questionsCategory = "Geography";
    public static int duration = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        durationText = findViewById(R.id.input_time_text);
        modesSpinner = findViewById(R.id.mode_spinner);
        categoriesSpinner = findViewById(R.id.category_spinner);
        myPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        modesAdapter = ArrayAdapter.createFromResource(this, R.array.modes_array, android.R.layout.simple_spinner_item);
        categoriesAdapter = ArrayAdapter.createFromResource(this, R.array.categories_array, android.R.layout.simple_spinner_item);

        modesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        modesSpinner.setAdapter(modesAdapter);
        categoriesSpinner.setAdapter(categoriesAdapter);

        modesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                playerMode = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                playerMode = adapterView.getItemAtPosition(0).toString();
            }
        });

        categoriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                questionsCategory = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                questionsCategory = adapterView.getItemAtPosition(0).toString();
            }
        });
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

        SharedPreferences.Editor preferencesEditor = myPreferences.edit();
        preferencesEditor.apply();
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

    public void SaveSettings(View view)
    {
        if (durationText.getText().toString().isEmpty()) {return;}
        duration = Integer.parseInt(durationText.getText().toString());
    }
}