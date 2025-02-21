package imgr.com.iManager_App.srv.intf;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.ModelAndView;

import imgr.com.iManager_App.srv.pojos.TY_UserSessionInfo;
import imgr.com.iManager_App.ui.enums.EnumVWNames;
import imgr.com.iManager_App.ui.model.entity.TY_SCToken;
import imgr.com.iManager_App.ui.pojos.TY_ScripCMPResponse;
import imgr.com.iManager_App.ui.pojos.TY_WLDB;

public interface IF_UserSessionSrv
{
    public void initialize(Authentication auth) throws Exception;

    public TY_UserSessionInfo getUserSessionInformation();

    public void resetScreenerToken(TY_SCToken token);

    public String getScreenerToken();

    public void setWLDB(List<TY_WLDB> wlDbList);

    public void encryptSessionKey(String toencrypt) throws Exception;

    public String getDecryptedKey() throws Exception;

    public List<TY_WLDB> getWlDB();

    public void setParentView4Navigation(EnumVWNames vwName);

    public String getRedirectedParentView();

    public void setAccessBearer(String bearer);

    public void setParentViewModel4Navigation(ModelAndView mv);

    public ModelAndView getRedirectedParentViewModel();

    public List<TY_WLDB> updateWatchlistwithCMP(TY_ScripCMPResponse wlCMPList);

}
