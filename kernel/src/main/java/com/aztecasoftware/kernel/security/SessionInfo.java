package com.aztecasoftware.kernel.security;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Ricardo on 30/09/2016.
 */

public class SessionInfo {
    public UUID sessionID;
    public UsuarioInfo user;
    public String workStation;
    public Date businessDate;
    public Date loginDate;
    public Integer timezoneOffset;

    public String toJSON(){
        Gson gson=new Gson();
        return gson.toJson(this);
    }

    public static SessionInfo fromJson(String json){
        Gson gson=new Gson();
        return gson.fromJson(json, SessionInfo.class);
    }
}
