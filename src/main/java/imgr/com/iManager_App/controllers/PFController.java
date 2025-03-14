package imgr.com.iManager_App.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import imgr.com.iManager_App.exceptions.EX_UserSession;
import imgr.com.iManager_App.srv.intf.IF_PFSrvClient;
import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.ui.constants.VWNamesDirectory;
import imgr.com.iManager_App.ui.enums.EnumVWNames;
import imgr.com.iManager_App.ui.pojos.TY_PF;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/pf")
@RequiredArgsConstructor
@Slf4j
public class PFController
{

    private final IF_UserSessionSrv userSessionSrv;
    private final IF_PFSrvClient pfSrv;

    @GetMapping("/")
    public String showMyPortfolio(Model model)
    {
        if (pfSrv != null && userSessionSrv != null)
        {
            String token = userSessionSrv.getScreenerToken();
            if (StringUtils.hasText(token))
            {
                TY_PF pf = null;
                try
                {
                    if (userSessionSrv.getUserSessionInformation().getUserPF() == null)
                    {
                        // pf = TestUtility.getPF4mJSON();
                        pf = pfSrv.getPortfolioDetails4User(token);
                        userSessionSrv.setUserPF(pf);
                    }
                    else
                    {
                        pf = userSessionSrv.getUserSessionInformation().getUserPF();
                    }

                    // pf = TestUtility.getPF4mJSON();
                    if (pf != null)
                    {
                        log.info("Portfolio bound.. for user");
                        model.addAttribute("pf", pf);
                        model.addAttribute("userDetails", userSessionSrv.getUserDetails());
                        return VWNamesDirectory.getViewName(EnumVWNames.PortfolioOverview, false);
                    }
                }
                catch (Exception e)
                {
                    throw new EX_UserSession(e.getLocalizedMessage());
                }

            }
        }
        return VWNamesDirectory.getViewName(EnumVWNames.Home, true);
    }

    @GetMapping("/refresh")
    public String refreshMyPortfolio(Model model)
    {
        {
            String token = userSessionSrv.getScreenerToken();
            if (StringUtils.hasText(token))
            {
                TY_PF pf = null;
                try
                {

                    pf = pfSrv.getPortfolioDetails4User(token);
                    userSessionSrv.setUserPF(pf);

                    // pf = TestUtility.getPF4mJSON();
                    if (pf != null)
                    {
                        log.info("Portfolio bound.. for user");
                        model.addAttribute("pf", pf);
                        model.addAttribute("userDetails", userSessionSrv.getUserDetails());
                        return VWNamesDirectory.getViewName(EnumVWNames.PortfolioOverview, false);
                    }
                }
                catch (Exception e)
                {
                    throw new EX_UserSession(e.getLocalizedMessage());
                }

            }
        }
        return VWNamesDirectory.getViewName(EnumVWNames.Home, true);
    }

}
