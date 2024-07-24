package com.example.assignment2;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CountryService {

    private static final String API_URL = "https://restcountries.com/v3.1/name/";

    public static String fetchCountryData(String country) {
        String apiUrl = API_URL + country;
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            JsonArray jsonArray = JsonParser.parseString(result.toString()).getAsJsonArray();
            JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

            String name = jsonObject.getAsJsonObject("name").get("common").getAsString();
            String capital = jsonObject.get("capital").getAsJsonArray().get(0).getAsString();
            String region = jsonObject.get("region").getAsString();
            String population = jsonObject.get("population").getAsString();

            return String.format("Country: %s\nCapital: %s\nRegion: %s\nPopulation: %s",
                    name, capital, region, population);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching data";
        }
    }
}
