package com.orionhealth.contactlist.network;

import android.os.AsyncTask;

import com.orionhealth.contactlist.interfaces.AsyncResponse;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetService extends AsyncTask<Void, Void, JSONArray> {

    public AsyncResponse delegate = null;

	@Override
	protected JSONArray doInBackground(Void... voids) {
		URL url;
		HttpURLConnection urlConnection = null;

        JSONArray responseContent = null;

        try {

            //Thread.sleep(5000);

            url = new URL("http://jsonplaceholder.typicode.com/users");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(15 * 1000);
            urlConnection.setReadTimeout(15 * 1000);
            urlConnection.connect();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            responseContent = readStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null)
                urlConnection.disconnect();
        }

		return responseContent;
	}

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    private static JSONArray readStream(InputStream in) throws IOException {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);

        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray(responseStrBuilder.toString());
        } catch (Exception ex)
        {
            System.out.println(" - ex: " + ex.toString());
        }

        return jsonArray;

    }

	@Override
	protected void onPostExecute(JSONArray result) {
		super.onPostExecute(result);
        delegate.processFinish(result);
	}
}