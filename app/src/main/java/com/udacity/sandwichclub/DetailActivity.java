package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView mName;
    TextView knownAs;
    TextView placeOrigin;
    TextView description;
    ImageView imageView;
    TextView ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mName=(TextView)findViewById(R.id.fill_name);
        knownAs=(TextView)findViewById(R.id.fill_known_as);
        placeOrigin=(TextView)findViewById(R.id.fill_origin);
        description= (TextView)findViewById(R.id.fill_description);
        ingredients=(TextView)findViewById(R.id.fill_ingredients);
        imageView=(ImageView)findViewById(R.id.image_iv);


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
        Sandwich sandwich = null;
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(imageView);

        setTitle(sandwich.getMainName());

        if(knownAs==null){
            knownAs.setText("No data available");
        }else {
            knownAs.setText(sandwich.getAlsoKnownAs().toString());
        }
        placeOrigin.setText(sandwich.getPlaceOfOrigin());
        description.setText(sandwich.getDescription());
        ingredients.setText(sandwich.getIngredients().toString());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
    }
}
