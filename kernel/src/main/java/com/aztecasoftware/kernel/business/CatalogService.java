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
    protected WebService ApiService;
    private Class<catalog> CatalogClass;

    public CatalogService(String serviceUrl){
        this.ApiService=new WebService(App.getApiBaseUrl() + "/" + serviceUrl);
    }

    private catalog fromJson(String json){
        Gson gson=new Gson();
        catalog Info=gson.fromJson(json, CatalogClass);
        return Info;
    }

    @Override
    public abstract catalog Create();

    @Override
    public Observable<catalog> GetDetail(Integer itemID) {
        UrlParams Params=new UrlParams();
        Params.put("sessionID", App.getSession().SessionID.toString());
        Params.put("itemID", itemID.toString());
        Params.put("checkStatus", "false");

        return ApiService.Get("GetDetailByID", Params)
                .map(response -> fromJson(response.Content));
    }

    @Override
    public Observable<catalog> GetDetail(Integer idBranch, String code) {
        UrlParams Params=new UrlParams();
        Params.put("sessionID", App.getSession().SessionID.toString());
        Params.put("idBranch", idBranch.toString());
        Params.put("code", code);

        return ApiService.Get("GetDetailByID", Params)
                .map(response -> fromJson(response.Content));

    }

    @Override
    public Observable<Boolean> Delete(Integer itemID) {
        return null;
    }

    @Override
    public Observable<Boolean> Exists(int itemID) {
        return null;
    }

    @Override
    public Observable<Boolean> ChangeStatus(Integer itemID, boolean active) {
        return null;
    }

    @Override
    public Observable<List<catalog>> Sync(Date lastUpdate) {
        return null;
    }
}
