package imgr.com.iManager_App.srv.intf;

import java.util.List;

import imgr.com.iManager_App.ui.pojos.EN_Watchlist;
import imgr.com.iManager_App.ui.pojos.TY_ScripAnalysisData;
import imgr.com.iManager_App.ui.pojos.TY_ScripCMPResponse;
import imgr.com.iManager_App.ui.pojos.TY_WLDB;
import imgr.com.iManager_App.ui.pojos.TY_WLEligibleScrips;
import imgr.com.iManager_App.ui.pojos.TY_WLPartRequest;

public interface IF_WatchlistSrvClient
{
    public List<TY_WLDB> getWatchlistDb(String token) throws Exception;

    public List<TY_WLDB> refreshWatchlistDb(List<TY_WLDB> exsWLDB, String token) throws Exception;

    public TY_ScripCMPResponse getCMP4WLScrips(String token) throws Exception;

    public List<TY_ScripAnalysisData> getWLFundamentalAnalysis() throws Exception;

    public List<EN_Watchlist> getWatchlistThesis() throws Exception;

    public TY_WLDB updateWatchlistEntry(EN_Watchlist wlBase, boolean isRecalcNeeded) throws Exception;

    public EN_Watchlist updateWatchlistPart(TY_WLPartRequest partReq) throws Exception;

    public TY_WLEligibleScrips getEligibleScrips() throws Exception;

    public boolean add2Watchlist(String scrip) throws Exception;
}
