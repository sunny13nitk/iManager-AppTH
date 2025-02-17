package imgr.com.iManager_App.srv.intf;

import imgr.com.iManager_App.exceptions.handler.EX_APIClient;
import imgr.com.iManager_App.ui.pojos.TY_Credentials;

public interface IF_APIClient
{
    public String getAuthToken(TY_Credentials credentials) throws EX_APIClient, Exception;
}
