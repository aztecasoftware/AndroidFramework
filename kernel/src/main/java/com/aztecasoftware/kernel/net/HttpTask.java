package com.aztecasoftware.kernel.net;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import io.reactivex.ObservableEmitter;

/**
 * Created by Ricardo on 29/09/2016.
 */

class HttpTask extends AsyncTask<HttpRequest, Integer, HttpResponse> {

    HttpRequest Request=null;
    ObservableEmitter<HttpResponse> Observer;

    public  HttpTask(ObservableEmitter<HttpResponse> observer){
        this.Observer=observer;
    }

    @Override
    protected HttpResponse doInBackground(HttpRequest... params) {
        HttpResponse response=new HttpResponse();
        try
        {
            this.Request=params[0];
            URL url = Request.GetUrl();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(Request.Method);
            conn.setRequestProperty("Accept", "application/json");
            response.StatusCode=conn.getResponseCode();
            if (response.StatusCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream=new BufferedInputStream((conn.getInputStream()));
                response.Content = new Scanner(inputStream).useDelimiter("\\A").next();
            }
            else{
                InputStream inputStream=new BufferedInputStream((conn.getErrorStream()));
                response.Content = new Scanner(inputStream).useDelimiter("\\A").next();
            }
            conn.disconnect();
            return response;
        }
        catch (Exception ex)
        {
            response.Content=ex.getMessage();
        }
        return response;
    }


    @Override
    protected void onPostExecute(HttpResponse httpResponse) {
        if (httpResponse.StatusCode == HttpURLConnection.HTTP_OK) {
            this.Observer.onNext(httpResponse);
            this.Observer.onComplete();
        }
        else{
            this.Observer.onError(new HttpException(httpResponse.Content, httpResponse.StatusCode, this.Request, null));
        }
        super.onPostExecute(httpResponse);
    }
}
