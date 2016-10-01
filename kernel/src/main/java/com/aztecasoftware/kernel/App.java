package com.aztecasoftware.kernel;

import com.aztecasoftware.kernel.security.SessionInfo;

/**
 * Created by Ricardo on 30/09/2016.
 */

public final class App {
    private static SessionInfo Session;
    private static String ApiBaseUrl="http://www.embotelladoraaga.com.mx:8095/frameworktest/api";

    public static SessionInfo getSession(){
        return Session;
    }

    public static void setSession(SessionInfo session){
        Session=session;
    }

    public static String getApiBaseUrl(){
        return ApiBaseUrl;
    }

}
