package com.arhiser.cookbook.screens.details;


import android.app.Activity;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.arhiser.cookbook.R;
import com.arhiser.cookbook.model.Recept;

public class SelectActivity extends AppCompatActivity {

    private static final String KEY = "NoteDetailsActivity.EXTRA_NOTE";

    private Recept recept;
    private TextView tv_NameDish, tv_Description, tv_Preparation, tv_Ingredients, tv_Time;
    private TextView name, description, preparation, ingredients, time;
    private SharedPreferences def_pref;


    public static void start(Activity caller, Recept recept) {
        Intent intent = new Intent(caller, SelectActivity.class);
        if (recept != null) {
            intent.putExtra(KEY, recept);
        }
        caller.startActivity(intent);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle(getString(R.string.select_details_title));



        tv_NameDish = findViewById(R.id.tv_nameDish);
        tv_Description = findViewById(R.id.tv_description);
        tv_Preparation = findViewById(R.id.tv_preparation);
        tv_Ingredients = findViewById(R.id.tv_ingredients);
        tv_Time = findViewById(R.id.tv_time);

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        preparation = findViewById(R.id.preparation);
        ingredients = findViewById(R.id.ingredients);
        time = findViewById(R.id.time);

        def_pref = PreferenceManager.getDefaultSharedPreferences(this);

        String text_size  = def_pref.getString("textSize","Средний");




        switch (text_size){
            case "Большой":
            {
                tv_NameDish.setTextSize(25);
                tv_Description.setTextSize(25);
                tv_Preparation.setTextSize(25);
                tv_Ingredients.setTextSize(25);
                tv_Time.setTextSize(25);

                name.setTextSize(25);
                description.setTextSize(25);
                preparation.setTextSize(25);
                ingredients.setTextSize(25);
                time.setTextSize(25);

                break;
            }

            case "Средний":
            {
                tv_NameDish.setTextSize(20);
                tv_Description.setTextSize(20);
                tv_Preparation.setTextSize(20);
                tv_Ingredients.setTextSize(20);
                tv_Time.setTextSize(20);

                name.setTextSize(20);
                description.setTextSize(20);
                preparation.setTextSize(20);
                ingredients.setTextSize(20);
                time.setTextSize(20);

                break;
            }

            case "Маленький":
            {
                tv_NameDish.setTextSize(15);
                tv_Description.setTextSize(15);
                tv_Preparation.setTextSize(15);
                tv_Ingredients.setTextSize(15);
                tv_Time.setTextSize(15);

                name.setTextSize(15);
                description.setTextSize(15);
                preparation.setTextSize(15);
                ingredients.setTextSize(15);
                time.setTextSize(15);

                break;
            }

        }




        if (getIntent().hasExtra(KEY)) {
            recept = getIntent().getParcelableExtra(KEY);
            tv_NameDish.setText(recept.name_dish);
            tv_Description.setText(recept.description);
            tv_Preparation.setText(recept.preparation);
            tv_Ingredients.setText(recept.ingredients);
            tv_Time.setText(recept.time + " мин.");

        } else {
            recept = new Recept();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
