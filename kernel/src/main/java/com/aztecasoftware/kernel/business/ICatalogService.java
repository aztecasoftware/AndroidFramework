package com.aztecasoftware.kernel.business;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Ricardo on 28/09/2016.
 */

public interface ICatalogService<catalog extends CatalogInfo> {
    catalog create();

    Observable<catalog> getDetail(Integer itemID);

    Observable<catalog> getDetail(Integer idBranch, String code);

    Observable<Boolean> delete(Integer itemID);

    Observable<Boolean> exists(int itemID);

    Observable<Boolean> changeStatus(Integer itemID, boolean active);

    Observable<List<catalog>> sync(Date lastUpdate);
}
