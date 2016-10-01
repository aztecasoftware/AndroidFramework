package com.aztecasoftware.kernel.security;

import com.aztecasoftware.kernel.App;
import com.aztecasoftware.kernel.business.CatalogInfo;
import com.aztecasoftware.kernel.business.CatalogService;
import com.aztecasoftware.kernel.net.UrlParams;

import io.reactivex.Observable;

/**
 * Created by Ricardo on 30/09/2016.
 */

public class UsuarioService extends CatalogService<UsuarioInfo> {

    public UsuarioService(){
        super("kernel/security/usuarios");
    }

    @Override
    public UsuarioInfo Create() {
        return new UsuarioInfo();
    }

    public Observable<SessionInfo> Login(String userName, String password, String workstation){
        UrlParams Params=new UrlParams();
        Params.put("userName", userName);
        Params.put("password", password);
        Params.put("workstation", workstation);

        return ApiService.Get("login", Params)
                .map(response -> SessionInfo.fromJson(response.Content));

    }

}
