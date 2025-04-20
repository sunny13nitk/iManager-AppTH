package imgr.com.iManager_App.srv.intf;

import java.util.List;

import imgr.com.iManager_App.ui.pojos.TY_HC_Results;

public interface IF_UtilitiesSrvClient
{
    public TY_HC_Results getHealthCheckResults(List<String> scrips) throws Exception;
}
