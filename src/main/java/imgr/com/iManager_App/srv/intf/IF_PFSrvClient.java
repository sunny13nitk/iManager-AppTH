package imgr.com.iManager_App.srv.intf;

import imgr.com.iManager_App.ui.pojos.TY_PF;

public interface IF_PFSrvClient
{
    public TY_PF getPortfolioDetails4User(String token) throws Exception;
}
