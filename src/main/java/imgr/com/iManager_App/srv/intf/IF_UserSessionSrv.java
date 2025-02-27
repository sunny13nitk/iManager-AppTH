package imgr.com.iManager_App.srv.intf;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.ModelAndView;

import imgr.com.iManager_App.srv.pojos.TY_Login;
import imgr.com.iManager_App.srv.pojos.TY_SCToken;
import imgr.com.iManager_App.srv.pojos.TY_UserSessionInfo;
import imgr.com.iManager_App.ui.enums.EnumVWNames;
import imgr.com.iManager_App.ui.pojos.EN_Watchlist;
import imgr.com.iManager_App.ui.pojos.TY_ScripAnalysisData;
import imgr.com.iManager_App.ui.pojos.TY_UserRole;
import imgr.com.iManager_App.ui.pojos.TY_WLDB;

public interface IF_UserSessionSrv
{
    public void initialize(TY_Login login) throws Exception;

    public TY_UserSessionInfo getUserSessionInformation();

    public void resetScreenerToken(TY_SCToken token);

    public String getScreenerToken();

    public void setWLDB(List<TY_WLDB> wlDbList);

    public void setWLFundamentals(List<TY_ScripAnalysisData> wlFundamentals);

    public void setWLThesis(List<EN_Watchlist> wlThesis);

    public void encryptSessionKey(String toencrypt) throws Exception;

    public String getDecryptedKey() throws Exception;

    public List<TY_WLDB> getWlDB();

    public void setParentView4Navigation(EnumVWNames vwName);

    public String getRedirectedParentView();

    public void setAccessBearer(String bearer);

    public void setParentViewModel4Navigation(ModelAndView mv);

    public ModelAndView getRedirectedParentViewModel();

    public void setLoggedinUserRole(String role);

    public String getLoggedinUserRole();

    public TY_UserRole getUserDetails();
}
