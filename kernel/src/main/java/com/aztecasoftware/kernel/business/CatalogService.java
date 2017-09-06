package com.aztecasoftware.kernel.business;

import com.aztecasoftware.kernel.App;
import com.aztecasoftware.kernel.net.UrlParams;
import com.aztecasoftware.kernel.net.WebService;
import com.google.gson.Gson;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by Ricardo on 30/09/2016.
 */

public abstract class CatalogService<catalog extends CatalogInfo> implements ICatalogService<catalog> {
    protected WebService apiService;
    private Class<catalog> catalogClass;

    public CatalogService(String serviceUrl){
        this.apiService=new WebService(App.getApiBaseUrl() + "/" + serviceUrl);
    }

    private catalog fromJson(String json){
        Gson gson=new Gson();
        catalog Info=gson.fromJson(json, catalogClass);
        return Info;
    }

    @Override
    public abstract catalog create();

    @Override
    public Observable<catalog> getDetail(Integer itemID) {
        UrlParams Params=new UrlParams();
        Params.put("sessionID", App.getSession().sessionID.toString());
        Params.put("itemID", itemID.toString());
        Params.put("checkStatus", "false");

        return apiService.get("GetDetailByID", Params)
                .map(response -> fromJson(response.content));
    }

    @Override
    public Observable<catalog> getDetail(Integer idBranch, String code) {
        UrlParams Params=new UrlParams();
        Params.put("sessionID", App.getSession().sessionID.toString());
        Params.put("idBranch", idBranch.toString());
        Params.put("code", code);

        return apiService.get("GetDetailByID", Params)
                .map(response -> fromJson(response.content));

    }

    @Override
    public Observable<Boolean> delete(Integer itemID) {
        return null;
    }

    @Override
    public Observable<Boolean> exists(int itemID) {
        return null;
    }

    @Override
    public Observable<Boolean> changeStatus(Integer itemID, boolean active) {
        return null;
    }

    @Override
    public Observable<List<catalog>> sync(Date lastUpdate) {
        return null;
    }
}
