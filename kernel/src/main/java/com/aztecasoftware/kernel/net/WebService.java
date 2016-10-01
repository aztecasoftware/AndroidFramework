package com.aztecasoftware.kernel.net;

import android.util.Log;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Ricardo on 28/09/2016.
 */

public class WebService {
    //CONSTANTES
    public static final String WEB_METHOD_GET="GET";
    public static final String WEB_METHOD_POST="POST";

    public String Url;

    public WebService(String url) {
        this.Url = url;
    }

    private String CreateQueryString(UrlParams params) throws UnsupportedEncodingException{
        StringBuffer queryString=new StringBuffer();
        if (params.size() > 0) {
            queryString.append("?");
            Iterator iterator = params.entrySet().iterator();
            Boolean firstItem=true;
            while (iterator.hasNext()) {
                if (!firstItem) queryString.append("&");
                Map.Entry pair = (Map.Entry)iterator.next();
                queryString.append(pair.getKey());
                queryString.append("=");
                queryString.append(pair.getValue());
                firstItem=false;
            }
            return URLEncoder.encode(queryString.toString(), "UTF-8");
        }
        else{
            return "";
        }
    }

    private HttpRequest CreateRequest(String method, String action, UrlParams params) throws UnsupportedEncodingException {
        String requestUrl = Url + "/" + action;
        String content="";
        if (method.equals(WEB_METHOD_GET)){
            requestUrl += CreateQueryString(params);
        }
        if (method.equals(WEB_METHOD_POST)){
            Gson gson=new Gson();
            content=gson.toJson(params);
        }
        HttpRequest request = new HttpRequest(requestUrl);
        request.Content=content;
        request.Parameters=params;
        return request;
    }

    public Observable<HttpResponse> Get(String action, UrlParams params) {
        Observable<HttpResponse> observable=null;
        try {
            HttpRequest request = CreateRequest(WEB_METHOD_GET, action, params);

            observable = Observable.create(new ObservableOnSubscribe<HttpResponse>() {
                @Override
                public void subscribe(ObservableEmitter<HttpResponse> e) throws Exception {
                    new HttpTask(e).execute(request);
                }
            });
        }
        catch (UnsupportedEncodingException encodingEx){
            Log.v("aztecasoftware", "Codificación UTF-8 no soportada");
        }
        catch (Exception ex){
            Log.v("aztecasoftware", "Error al crear observable para request GET: " + ex.getMessage());
        }
        return observable;
    }

    public Observable<HttpResponse> Post(String action, UrlParams params) {
        Observable<HttpResponse> observable=null;
        try {
            HttpRequest request = CreateRequest(WEB_METHOD_POST, action, params);

            observable = Observable.create(new ObservableOnSubscribe<HttpResponse>() {
                @Override
                public void subscribe(ObservableEmitter<HttpResponse> e) throws Exception {
                    new HttpTask(e).execute(request);
                }
            });
        }
        catch (UnsupportedEncodingException encodingEx){
            Log.v("aztecasoftware", "Codificación UTF-8 no soportada");
        }
        catch (Exception ex){
            Log.v("aztecasoftware", "Error al crear observable para request POST: " + ex.getMessage());
        }
        return observable;
    }
}
