package imgr.com.iManager_App.srv.intf;

import java.util.List;

import imgr.com.iManager_App.ui.pojos.TY_WLDB;

public interface IF_WatchlistSrvClient
{
    public List<TY_WLDB> getWatchlistDb(String token) throws Exception;
}
