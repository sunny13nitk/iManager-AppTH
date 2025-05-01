package imgr.com.iManager_App.controllers;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import imgr.com.iManager_App.srv.intf.IF_StrategiesSrvClient;
import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.ui.constants.VWNamesDirectory;
import imgr.com.iManager_App.ui.enums.EnumVWNames;
import imgr.com.iManager_App.ui.pojos.CUS_StgyBean;
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

            }
        }
        return VWNamesDirectory.getViewName(EnumVWNames.StgyList, false);

    }

}
