package com.udacity.sandwichclub.utils;

import android.text.TextUtils;

import com.udacity.sandwichclub.model.Sandwich;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        // Initialize a Sandwich Object
        Sandwich currentSandwich = new Sandwich();

        return currentSandwich;
    }
}
