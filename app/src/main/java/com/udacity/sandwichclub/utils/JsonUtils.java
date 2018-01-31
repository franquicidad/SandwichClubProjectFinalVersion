package com.udacity.sandwichclub.utils;

import android.util.Log;
import android.widget.Toast;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = "DEBUGGING";

    public JsonUtils() throws JSONException {


    }

    public static Sandwich parseSandwichJson(String json) {


        try {
            JSONObject rootObject = new JSONObject(json);

            /** Access Name*/
            JSONObject subObj = rootObject.getJSONObject("name");
            Log.v(TAG, "JSON FOR NAMEEE" + subObj);
            /** Retrieve Main Name*/
            String mainName = subObj.getString("mainName");
            Log.v(TAG, "JSON FOR MAINNAME" + mainName);

            /** Retrieve Place of origin*/
            String origin = rootObject.getString("placeOfOrigin");
            Log.v(TAG, "JSON FOR ORIGIN" + origin);

            /** Retrieve Description*/
            String des = rootObject.getString("description");
            Log.v(TAG, "JSON FOR DESCRIPTION" + des);

            /** Image*/
            String image = rootObject.getString("image");


            /** Retrieve also known as from the JSON Array*/
            JSONArray setArray = subObj.getJSONArray("alsoKnownAs");
            List<String> alsoKnownList = new ArrayList<>();
            for (int i = 0; i < setArray.length(); i++) {
                String alsoKnown = setArray.getString(i);
                Log.v(TAG, "JSON FOR alsoknown" + alsoKnown);
                alsoKnownList.add(alsoKnown);
            }



                /** Retrieve for ingredients*/
                JSONArray ingreArray = rootObject.getJSONArray("ingredients");

                List<String> ingreList = new ArrayList<>();

                for (int i = 0; i < ingreArray.length(); i++) {
                    ingreList.add(ingreArray.getString(i));
                }
                /** Create Sandwich Object*/
                Sandwich sObject = new Sandwich(mainName, alsoKnownList, origin, des, image, ingreList);
                return sObject;
            } catch (JSONException e1) {
            e1.printStackTrace();
        }


        return null;


    }
}