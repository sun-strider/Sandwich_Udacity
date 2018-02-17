package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {


    public static final String EXTRA_POSITION = "extra_position";
    private static final String LOG_TAG = DetailActivity.class.getName();
    private static final int DEFAULT_POSITION = -1;

    private List<String> mAlsoKnownAs;
    private List<String> mIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView sandwichImage_iv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        // Get the raw JSON to look at it in a JSON formatter
        Log.i("JSON String", json);

        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);

        if (sandwich.getImage() != null) {
            Picasso.with(this)
                    .load(sandwich.getImage())
                    .into(sandwichImage_iv);

        }

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        TextView alsoKnownAs_tv = findViewById(R.id.also_known_tv);
        TextView ingredients_tv = findViewById(R.id.ingredients_tv);
        TextView placeOfOrigin_tv = findViewById(R.id.origin_tv);
        TextView description_tv = findViewById(R.id.description_tv);

        mAlsoKnownAs = sandwich.getAlsoKnownAs();

        for (int i = 0; i < mAlsoKnownAs.size(); i++) {
            alsoKnownAs_tv.append(mAlsoKnownAs.get(i));
            alsoKnownAs_tv.append("\n");
        }

        mIngredients = sandwich.getIngredients();

        for (int i = 0; i < mIngredients.size(); i++) {
            ingredients_tv.append(mIngredients.get(i));
            ingredients_tv.append("\n");
        }

        placeOfOrigin_tv.setText(sandwich.getPlaceOfOrigin());

        description_tv.setText(sandwich.getDescription());
    }
}
