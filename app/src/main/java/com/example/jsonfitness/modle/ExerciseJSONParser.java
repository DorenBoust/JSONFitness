package com.example.jsonfitness.modle;

import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

import com.example.jsonfitness.data.FinalEx;
import com.example.jsonfitness.data.FitnessExersice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ExerciseJSONParser extends AsyncTask<String,Integer,ArrayList<FinalEx>> {

    MutableLiveData<List<FinalEx>> mLiveDate;
    AsyncTask<String, Integer, ArrayList<FitnessExersice>> innerExersiceParameters;


    public ExerciseJSONParser(MutableLiveData<List<FinalEx>> mLiveData) {
        this.mLiveDate = mLiveData;
    }

    public ExerciseJSONParser() {

    }


    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This will normally run on a background thread. But to better
     * support testing frameworks, it is recommended that this also tolerates
     * direct execution on the foreground thread, as part of the {@link #execute} call.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param strings The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected ArrayList<FinalEx> doInBackground(String... strings) {
        try {
            URL url = new URL("http://appfitness.boust.me/wp-json/acf/v3/trainers?appConnection=1111");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            InputStream inputStream = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }

            ArrayList<FinalEx> result = new ArrayList<>();

            String json = sb.toString();
            JSONArray rootJSONArray = new JSONArray(json);
            JSONObject rootJSONObject = rootJSONArray.getJSONObject(0);

            JSONObject acf = rootJSONObject.getJSONObject("acf");
            JSONObject training = acf.getJSONObject("training");

            JSONObject sunday = training.getJSONObject("sunday");
            String numberOfExercies = sunday.getString("numberOfExercies");
            int numberOfExerciesInt = Integer.parseInt(numberOfExercies);

            for (int i = 0; i < numberOfExerciesInt ; i++) {
                //On the JSON the JSONObject write like : ex1 {}, ex2 {}, ex3 {}.
                String exNumber = "ex" + (i + 1);
                JSONObject ex = sunday.getJSONObject(exNumber);

                String exName = ex.getString("exName");
                String sets = ex.getString("sets");
                String repitition = ex.getString("repitition");
                String rest = ex.getString("rest");

                URL exImageUrl = new URL(exName);
                URLConnection urlConnection = exImageUrl.openConnection();

                InputStream inputStream1 = urlConnection.getInputStream();
                BufferedReader read = new BufferedReader(new InputStreamReader(inputStream1));

                StringBuilder sbnew = new StringBuilder();

                String line1 = null;

                while ((line1 = read.readLine()) != null){
                    sbnew.append(line1);
                }

                String exImage = sbnew.toString();

                FinalEx fitnessExersice = new FinalEx(exName,sets,repitition,rest,exImage);
                result.add(fitnessExersice);

            }

            return result;

        }catch (IOException e){

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<FinalEx> result) {
        System.out.println(result);
        mLiveDate.setValue(result);



    }


}
