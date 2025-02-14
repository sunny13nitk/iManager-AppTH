package imgr.com.iManager_App.srv.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import imgr.com.iManager_App.srv.intf.IF_WatchlistSrvClient;
import imgr.com.iManager_App.ui.pojos.TY_DestinationsSuffix;
import imgr.com.iManager_App.ui.pojos.TY_WLDB;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CL_WatchlistSrvClient implements IF_WatchlistSrvClient
{

    private final TY_DestinationsSuffix dS;

    @Override
    public List<TY_WLDB> getWatchlistDb(String token)
    {
        List<TY_WLDB> wlDB = null;
        if (dS != null)
        {
            
        }

        return wlDB;
    }

}
