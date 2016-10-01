package com.aztecasoftware.kernel.security;

import com.google.gson.Gson;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Ricardo on 30/09/2016.
 */

public class SessionInfo {
    public UUID SessionID;
    public UsuarioInfo Usuario;
    public String WorkStation;
    public Date BusinessDate;
    public Date LoginDate;
    public Integer TimezoneOffset;

    public String toJSON(){
        Gson gson=new Gson();
        return gson.toJson(this);
    }

    public static SessionInfo fromJson(String json){
        Gson gson=new Gson();
        return gson.fromJson(json, SessionInfo.class);
    }
}
