package com.aztecasoftware.kernel.business;

import java.util.Date;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Ricardo on 28/09/2016.
 */

public interface ICatalogService<catalog extends CatalogInfo> {
    catalog Create();

    Observable<catalog> GetDetail(Integer itemID);

    Observable<catalog> GetDetail(Integer idBranch, String code);

    Observable<Boolean> Delete(Integer itemID);

    Observable<Boolean> Exists(int itemID);

    Observable<Boolean> ChangeStatus(Integer itemID, boolean active);

    Observable<List<catalog>> Sync(Date lastUpdate);
}
