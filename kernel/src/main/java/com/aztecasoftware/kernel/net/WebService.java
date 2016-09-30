package com.aztecasoftware.kernel.net;

import android.os.AsyncTask;

import java.util.Dictionary;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Ricardo on 28/09/2016.
 */

public class WebService {
    public String Url;

    public WebService(String url) {
        this.Url = url;
    }

    private HttpRequest CreateRequest(String method, String action, Map<String, String> params){
        HttpRequest request = new HttpRequest(this.Url);
        return request;
    }

    public Observable<HttpResponse> Get(String action, Map<String, String> params) {
        HttpRequest request = CreateRequest("GET", action, params);

        return Observable.create(new ObservableOnSubscribe<HttpResponse>() {
            @Override
            public void subscribe(ObservableEmitter<HttpResponse> e) throws Exception {
                new HttpTask(e).execute(request);
            }
        });

    }

}
