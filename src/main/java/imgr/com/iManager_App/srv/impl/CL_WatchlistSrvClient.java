package imgr.com.iManager_App.srv.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.srv.intf.IF_WatchlistSrvClient;
import imgr.com.iManager_App.ui.pojos.TY_DestinationsSuffix;
import imgr.com.iManager_App.ui.pojos.TY_WLDB;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CL_WatchlistSrvClient implements IF_WatchlistSrvClient
{

    private final TY_DestinationsSuffix dS;

    private final IF_UserSessionSrv userSessionSrv;

    @Override
    public List<TY_WLDB> getWatchlistDb(String token) throws Exception
    {
        List<TY_WLDB> wlDB = null;
        if (dS != null && userSessionSrv != null)
        {
            String key = userSessionSrv.getDecryptedKey();
            if (StringUtils.hasText(key))
            {
                log.info(key);
            }
        }

        return wlDB;
    }

}
