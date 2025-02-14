package imgr.com.iManager_App.srv.intf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;

import imgr.com.iManager_App.srv.pojos.TY_UserSessionInfo;
import imgr.com.iManager_App.ui.model.entity.TY_SCToken;
import imgr.com.iManager_App.ui.pojos.TY_WLDB;

public interface IF_UserSessionSrv
{
    public void initialize(Authentication auth);

    public TY_UserSessionInfo getUserSessionInformation();

    public void resetScreenerToken(TY_SCToken token);

    public String getScreenerToken();

    public void setWLDB(List<TY_WLDB> wlDbList);

    public List<TY_WLDB> getWlDB();

}
