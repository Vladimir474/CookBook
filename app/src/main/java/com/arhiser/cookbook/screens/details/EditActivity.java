package com.arhiser.cookbook.screens.details;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.arhiser.cookbook.App;
import com.arhiser.cookbook.R;
import com.arhiser.cookbook.model.Recept;

public class EditActivity extends AppCompatActivity {

    private static final String KEY = "EditActivity.EXTRA_NOTE";

    private Recept recept;
    private SharedPreferences def_pref;
    private EditText editNameDish, editDescription, editPreparation, editIngredients, editTime;




    public static void start(Activity caller, Recept recept) {
        Intent intent = new Intent(caller, EditActivity.class);
        if (recept != null) {
            intent.putExtra(KEY, recept);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setTitle(getString(R.string.note_details_title));

        editNameDish = findViewById(R.id.nameDish);
        editDescription = findViewById(R.id.description);
        editPreparation = findViewById(R.id.preparation);
        editIngredients = findViewById(R.id.ingredients);
        editTime = findViewById(R.id.time);

        def_pref = PreferenceManager.getDefaultSharedPreferences(this);
        String text_size  = def_pref.getString("textSize","Средний");

        switch (text_size){
            case "Большой":
            {
                editNameDish.setTextSize(25);
                editDescription.setTextSize(25);
                editPreparation.setTextSize(25);
                editIngredients.setTextSize(25);
                editTime.setTextSize(25);
                break;
            }

            case "Средний":
            {
                editNameDish.setTextSize(20);
                editDescription.setTextSize(20);
                editPreparation.setTextSize(20);
                editIngredients.setTextSize(20);
                editTime.setTextSize(20);
                break;
            }

            case "Маленький":
            {
                editNameDish.setTextSize(15);
                editDescription.setTextSize(15);
                editPreparation.setTextSize(15);
                editIngredients.setTextSize(15);
                editTime.setTextSize(15);
                break;
            }

        }

        if (getIntent().hasExtra(KEY)) {
            recept = getIntent().getParcelableExtra(KEY);
            editNameDish.setText(recept.name_dish);
            editDescription.setText(recept.description);
            editPreparation.setText(recept.preparation);
            editIngredients.setText(recept.ingredients);
            editTime.setText(recept.time);
        } else {
            recept = new Recept();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_save:
                if (editNameDish.getText().length() > 0 && editTime.getText().length() > 0) {
                    recept.name_dish = editNameDish.getText().toString();
                    recept.description = editDescription.getText().toString();
                    recept.preparation = editPreparation.getText().toString();
                    recept.ingredients = editIngredients.getText().toString();
                    recept.time = editTime.getText().toString();
                    recept.favorite = false;
                    if (getIntent().hasExtra(KEY)) {
                        App.getInstance().getNoteDao().update(recept);
                    } else {
                        App.getInstance().getNoteDao().insert(recept);
                    }
                    finish();
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Не введено имя или время готовки", Toast.LENGTH_SHORT);
                    toast.show();

                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
