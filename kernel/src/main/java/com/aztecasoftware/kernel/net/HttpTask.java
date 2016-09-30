package com.aztecasoftware.kernel.net;

import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.ObservableEmitter;

/**
 * Created by Ricardo on 29/09/2016.
 */

class HttpTask extends AsyncTask<HttpRequest, Integer, HttpResponse> {

    ObservableEmitter<HttpResponse> Observer;

    public  HttpTask(ObservableEmitter<HttpResponse> observer){
        this.Observer=observer;
    }

    @Override
    protected HttpResponse doInBackground(HttpRequest... params) {
        HttpResponse response=new HttpResponse();
        try
        {
            HttpRequest request=params[0];
            URL url = request.GetUrl();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

        }
        catch (Exception ex)
        {

        }
        return response;
    }

    @Override
    protected void onPostExecute(HttpResponse httpResponse) {

        super.onPostExecute(httpResponse);
    }
}
