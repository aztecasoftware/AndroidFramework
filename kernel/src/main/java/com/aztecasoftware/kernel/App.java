package com.aztecasoftware.kernel;

import com.aztecasoftware.kernel.security.SessionInfo;

/**
 * Created by Ricardo on 30/09/2016.
 */

public final class App {
    private static SessionInfo session;
    private static String apiBaseUrl="http://www.embotelladoraaga.com.mx:9093/api";

    public static SessionInfo getSession(){
        return session;
    }

    public static void setSession(SessionInfo session){
        session=session;
    }

    public static String getApiBaseUrl(){
        return apiBaseUrl;
    }

}
