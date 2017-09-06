package com.aztecasoftware.kernel.business;


import com.aztecasoftware.kernel.net.WebService;
import com.google.gson.Gson;

/**
 * Created by Ricardo on 28/09/2016.
 */

public class CatalogInfo {
    public int identity;
    public String code;


    public String toJSON(){
        Gson gson=new Gson();
        return gson.toJson(this);
    }


}
