package imgr.com.iManager_App.controllers.routes;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import imgr.com.iManager_App.srv.intf.IF_StrategiesSrvClient;
import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.ui.constants.VWNamesDirectory;
import imgr.com.iManager_App.ui.enums.EnumVWNames;
import imgr.com.iManager_App.ui.pojos.CUS_StgyBean;
import imgr.com.iManager_App.ui.pojos.IF_ScripAnalysisData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/strategies")
public class StrategiesController
{
    private final IF_StrategiesSrvClient strategiesSrvClient;

    private final IF_UserSessionSrv userSessionSrv;

    @GetMapping("/list")
    public String showUnstagedStategies(Model model)
    {
        if (userSessionSrv != null && strategiesSrvClient != null)
        {
            try
            {
                if (CollectionUtils.isNotEmpty(userSessionSrv.getUserSessionInformation().getStrategies()))
                {
                    model.addAttribute("strategies", userSessionSrv.getUserSessionInformation().getStrategies());
                }
                else
                {
                    List<CUS_StgyBean> stgy = strategiesSrvClient.getUnstagedStrategies();
                    model.addAttribute("strategies", stgy);
                    userSessionSrv.getUserSessionInformation().setStrategies(stgy);
                }

                model.addAttribute("userDetails", userSessionSrv.getUserDetails());
            }
            catch (Exception e)
            {
                String msg = "Error fetching unstaged strategies: " + e.getMessage();
                model.addAttribute("message", msg);
                log.error("Error fetching unstaged strategies: {}", e.getMessage(), e);
                model.addAttribute("userDetails", userSessionSrv.getUserDetails());

            }
        }
        return VWNamesDirectory.getViewName(EnumVWNames.StgyList, false);

    }

    @GetMapping("/exec/{stgyName}")
    public String getMethodName(@PathVariable String stgyName, Model model)
    {

        if (StringUtils.hasText(stgyName) && strategiesSrvClient != null)
        {
            try
            {
                List<IF_ScripAnalysisData> stgyData = strategiesSrvClient.executeStrategy(stgyName);
                model.addAttribute("stgyData", stgyData);
                model.addAttribute("userDetails", userSessionSrv.getUserDetails());
            }
            catch (Exception e)
            {
                String msg = "Error executing strategy: " + e.getMessage();
                model.addAttribute("message", msg);
                log.error("Error executing strategy: {}", e.getMessage(), e);
                model.addAttribute("userDetails", userSessionSrv.getUserDetails());
            }
        }

        return VWNamesDirectory.getViewName(EnumVWNames.StgyResults, false);
    }

}
