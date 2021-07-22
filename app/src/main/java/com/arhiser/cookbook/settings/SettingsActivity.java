package com.arhiser.cookbook.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.preference.SwitchPreference;
import android.text.style.BackgroundColorSpan;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.arhiser.cookbook.R;


public class SettingsActivity extends AppCompatActivity {


    public static void start(Activity caller) {
        Intent intent = new Intent(caller, SettingsActivity.class);
        caller.startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null)
        {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

            setTitle(getString(R.string.settings));

        }

        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
