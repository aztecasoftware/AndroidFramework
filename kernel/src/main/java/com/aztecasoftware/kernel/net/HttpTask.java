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

    HttpRequest request=null;
    ObservableEmitter<HttpResponse> observer;

    public  HttpTask(ObservableEmitter<HttpResponse> observer){
        this.observer=observer;
    }

    @Override
    protected HttpResponse doInBackground(HttpRequest... params) {
        HttpResponse response=new HttpResponse();
        try
        {
            this.request=params[0];
            URL url = request.getUrl();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(request.Method);
            conn.setRequestProperty("Accept", "application/json");
            response.statusCode=conn.getResponseCode();
            if (response.statusCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream=new BufferedInputStream((conn.getInputStream()));
                response.content = new Scanner(inputStream).useDelimiter("\\A").next();
            }
            else{
                InputStream inputStream=new BufferedInputStream((conn.getErrorStream()));
                response.content = new Scanner(inputStream).useDelimiter("\\A").next();
            }
            conn.disconnect();
            return response;
        }
        catch (Exception ex)
        {
            response.content=ex.getMessage();
        }
        return response;
    }


    @Override
    protected void onPostExecute(HttpResponse httpResponse) {
        if (httpResponse.statusCode == HttpURLConnection.HTTP_OK) {
            this.observer.onNext(httpResponse);
            this.observer.onComplete();
        }
        else{
            this.observer.onError(new HttpException(httpResponse.content, httpResponse.statusCode, this.request, null));
        }
        super.onPostExecute(httpResponse);
    }
}
