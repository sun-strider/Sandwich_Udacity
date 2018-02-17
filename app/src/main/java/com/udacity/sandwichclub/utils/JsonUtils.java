package com.udacity.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String LOG_TAG = JsonUtils.class.getName();

    private static String mMainName;
    private static List<String> mAlsoKnownAs;
    private static String mPlaceOfOrigin;
    private static String mDescription;
    private static String mImage;
    private static List<String> mIngredients;


    public static Sandwich parseSandwichJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(json);

            // Get name JSON Object
            if (baseJsonResponse.has("name")) {
                JSONObject name = baseJsonResponse.getJSONObject("name");

                //get the main name
                if (name.has("mMainName")) {
                    mMainName = name.getString("mMainName");
                }

                // get also known names JSON array
                if (name.has("mAlsoKnownAs")) {
                    JSONArray alsoKnownAsJsonArray = name.getJSONArray("mAlsoKnownAs");

                    // loop through the array to add to other names list
                    mAlsoKnownAs = new ArrayList<String>();
                    for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                        mAlsoKnownAs.add(alsoKnownAsJsonArray.getString(i));
                    }
                }

                // Get place of origin
                if (baseJsonResponse.has("mPlaceOfOrigin")) {
                    mPlaceOfOrigin = baseJsonResponse.getString("mPlaceOfOrigin");
                }

                // Get mDescription
                if (baseJsonResponse.has("mDescription")) {
                    mDescription = baseJsonResponse.getString("mDescription");
                }

                // Get mImage url
                if (baseJsonResponse.has("image")) {
                    mImage = baseJsonResponse.getString("image");
                }

                // get mIngredients JSON array
                if (name.has("mIngredients")) {
                    JSONArray ingredientsJsonArray = name.getJSONArray("mIngredients");

                    // loop through the array to add to other names list
                    mIngredients = new ArrayList<String>();
                    for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                        mIngredients.add(ingredientsJsonArray.getString(i));
                    }
                }

            }


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e(LOG_TAG, "Problem parsing the JSON results", e);
        }

        // Initialize a Sandwich Object
        Sandwich currentSandwich = new Sandwich(mMainName, mAlsoKnownAs, mPlaceOfOrigin, mDescription, mImage, mIngredients);

        return currentSandwich;
    }
}
