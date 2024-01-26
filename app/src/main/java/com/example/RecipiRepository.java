package com.example;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

//192.168.56.1
public class RecipiRepository {
    public void getAllRecipe(ExecutorService srv, Handler uiHandler) {
        srv.submit(() -> {

            try {
                List<Recipe> data = new ArrayList<>();

                URL url = new URL("http://192.168.56.1:8080/recipi/recipes");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                JSONArray arr = new JSONArray(buffer.toString());
                for (int i = 0; i < arr.length(); i++) {

                    JSONObject current = arr.getJSONObject(i);


                    Recipe rec = new Recipe(current.getString("id"),
                            current.getString("imageURL"),
                            current.getString("rName")
                    );

                    data.add(rec);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                Log.e("DEV", e.getMessage());
            } catch (IOException e) {
                Log.e("DEV", e.getMessage());
            } catch (JSONException e) {
                Log.e("DEV", e.getMessage());
            }


        });
    }

    public void getRecipeById(ExecutorService srv, Handler uiHandler, String id) {

        srv.execute(() -> {
            try {

                URL url = new URL("http://192.168.56.1:8080/recipi/recipes/" + id + "/details");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                JSONObject current = new JSONObject(buffer.toString());

                Nutrition n = new Nutrition(current.getJSONObject("nutrition").getInt("kcal"),
                        current.getJSONObject("nutrition").getString("fat"),
                        current.getJSONObject("nutrition").getString("protein"),
                        current.getJSONObject("nutrition").getString("carbs"),
                        current.getJSONObject("nutrition").getString("sugars"),
                        current.getJSONObject("nutrition").getString("salt"));

                Recipe rec = new Recipe(current.getString("id"),
                        current.getString("imageURL"),
                        current.getString("rName"),
                        current.getString("ingredients"),
                        current.getString("description"),
                        current.getString("type"),
                        n
                );

                Message msg = new Message();
                msg.obj = rec;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });


    }

    public void saveRecipe(ExecutorService srv, String imageURL, String rName,
                           String ingredients, String description,
                           String type, int kcal,
                           String fat, String protein,
                           String carbs, String sugars, String salt) {
        srv.execute(() -> {

            try {

                URL url = new URL("http://192.168.56.1:8080/recipi/addrecipe");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/JSON");


                //Nutrition n = new Nutrition(kcal, fat, protein, carbs, sugars, salt);
                JSONObject nutritionData = new JSONObject();
                nutritionData.put("kcal", kcal);
                nutritionData.put("fat", fat);
                nutritionData.put("protein", protein);
                nutritionData.put("carbs", carbs);
                nutritionData.put("sugars", sugars);
                nutritionData.put("salt", salt);


                JSONObject outputData = new JSONObject();
                outputData.put("imageURL", imageURL);
                outputData.put("rName", rName);
                outputData.put("ingredients", ingredients);
                outputData.put("description", description);
                outputData.put("type", type);
                outputData.put("nutrition", nutritionData);

                BufferedOutputStream writer =
                        new BufferedOutputStream(conn.getOutputStream());


                writer.write(outputData.toString().getBytes(StandardCharsets.UTF_8));
                writer.flush();

                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();

                String line = "";

                while ((line = reader.readLine()) != null) {

                    buffer.append(line);

                }

                int responseCode = conn.getResponseCode();
                Log.d("SAVE_RECIPE", "Response Code: " + responseCode);

                conn.disconnect();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });
    }

    public void getAllReview(ExecutorService srv, Handler uiHandler, String id) {
        srv.submit(() -> {

            try {
                List<Review> data = new ArrayList<>();

                URL url = new URL("http://192.168.56.1:8080/recipi/recipes/" + id + "/details/reviews");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                JSONArray arr = new JSONArray(buffer.toString());
                for (int i = 0; i < arr.length(); i++) {

                    JSONObject current = arr.getJSONObject(i);


                    Review rev = new Review(current.getString("uName"),
                            current.getInt("rate"),
                            current.getString("comment")
                    );

                    data.add(rev);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                Log.e("DEV", e.getMessage());
            } catch (IOException e) {
                Log.e("DEV", e.getMessage());
            } catch (JSONException e) {
                Log.e("DEV", e.getMessage());
            }


        });
    }

    public void saveReview(ExecutorService srv, String uName, int rate, String comment, String id) {
        srv.execute(() -> {

            try {

                URL url = new URL("http://192.168.56.1:8080/recipi/recipes/" + id + "/details/reviews/addreview");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/JSON");


                JSONObject objToSend = new JSONObject();

                objToSend.put("uName", uName);
                objToSend.put("rate", rate);
                objToSend.put("comment", comment);

                BufferedOutputStream writer =
                        new BufferedOutputStream(conn.getOutputStream());


                writer.write(objToSend.toString().getBytes(StandardCharsets.UTF_8));
                writer.flush();

                BufferedReader reader
                        = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();

                String line = "";

                while ((line = reader.readLine()) != null) {

                    buffer.append(line);

                }

                conn.disconnect();
                /*JSONObject retVal = new JSONObject(buffer.toString());
                String retValStr = retVal.getString("result");
                 */

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        });
    }

    public void filterByType(ExecutorService srv, Handler uiHandler, String type) {
        srv.submit(() -> {

            try {
                List<Recipe> data = new ArrayList<>();

                URL url = new URL("http://192.168.56.1:8080/recipi/recipes/type/" + type);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                JSONArray arr = new JSONArray(buffer.toString());
                for (int i = 0; i < arr.length(); i++) {

                    JSONObject current = arr.getJSONObject(i);


                    Recipe rec = new Recipe(current.getString("id"),
                            current.getString("imageURL"),
                            current.getString("rName")
                    );

                    data.add(rec);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                Log.e("DEV", e.getMessage());
            } catch (IOException e) {
                Log.e("DEV", e.getMessage());
            } catch (JSONException e) {
                Log.e("DEV", e.getMessage());
            }


        });
    }

    public void filterByKcal(ExecutorService srv, Handler uiHandler, int kcal) {
        srv.submit(() -> {

            try {
                List<Recipe> data = new ArrayList<>();

                URL url = new URL("http://192.168.56.1:8080/recipi/recipes/kcal/" + kcal);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                JSONArray arr = new JSONArray(buffer.toString());
                for (int i = 0; i < arr.length(); i++) {

                    JSONObject current = arr.getJSONObject(i);


                    Recipe rec = new Recipe(current.getString("id"),
                            current.getString("imageURL"),
                            current.getString("rName")
                    );

                    data.add(rec);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                Log.e("DEV", e.getMessage());
            } catch (IOException e) {
                Log.e("DEV", e.getMessage());
            } catch (JSONException e) {
                Log.e("DEV", e.getMessage());
            }


        });
    }

    public void search(ExecutorService srv, Handler uiHandler, String input) {
        srv.submit(() -> {

            try {
                List<Recipe> data = new ArrayList<>();

                URL url = new URL("http://192.168.56.1:8080/recipi/recipes/" + input);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                JSONArray arr = new JSONArray(buffer.toString());
                for (int i = 0; i < arr.length(); i++) {

                    JSONObject current = arr.getJSONObject(i);


                    Recipe rec = new Recipe(current.getString("id"),
                            current.getString("imageURL"),
                            current.getString("rName")
                    );

                    data.add(rec);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                Log.e("DEV", e.getMessage());
            } catch (IOException e) {
                Log.e("DEV", e.getMessage());
            } catch (JSONException e) {
                Log.e("DEV", e.getMessage());
            }


        });
    }
}