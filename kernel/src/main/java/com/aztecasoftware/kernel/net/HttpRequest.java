package com.aztecasoftware.kernel.net;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by Ricardo on 29/09/2016.
 */

public class HttpRequest {
    private String Url;
    public String Method;
    public Byte[] Content;
    public Map<String, String> Headers;

    public HttpRequest(String url){
        this.Url=url;
    }

    public URL GetUrl() throws MalformedURLException{
        URL url = new URL(this.Url);
        return url;
    }

}
