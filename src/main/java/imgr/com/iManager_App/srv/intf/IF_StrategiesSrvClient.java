package imgr.com.iManager_App.srv.intf;

import java.util.List;

import imgr.com.iManager_App.ui.pojos.CUS_StgyBean;
import imgr.com.iManager_App.ui.pojos.IF_ScripAnalysisData;

public interface IF_StrategiesSrvClient
{
    public List<CUS_StgyBean> getUnstagedStrategies() throws Exception;

    public List<IF_ScripAnalysisData> executeStrategy(String strategyName) throws Exception;

}
