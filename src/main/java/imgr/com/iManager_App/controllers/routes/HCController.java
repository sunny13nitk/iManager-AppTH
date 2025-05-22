package imgr.com.iManager_App.controllers.routes;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import imgr.com.iManager_App.exceptions.EX_UserSession;
import imgr.com.iManager_App.srv.intf.IF_ScreenerSrvClient;
import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.srv.intf.IF_UtilitiesSrvClient;
import imgr.com.iManager_App.ui.constants.VWNamesDirectory;
import imgr.com.iManager_App.ui.enums.EnumVWNames;
import imgr.com.iManager_App.ui.pojos.TY_HC_Results;
import imgr.com.iManager_App.ui.pojos.TY_Scripsel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/hc")
public class HCController
{

    private final IF_UtilitiesSrvClient utilSrvClient;
    private final IF_ScreenerSrvClient screenerSrvClient;
    private final IF_UserSessionSrv userSessSrv;

    @GetMapping("/")
    public String showHC(Model model)
    {
        if (utilSrvClient != null)
        {
            try
            {
                model.addAttribute("selScrip", new TY_Scripsel());
                model.addAttribute("scrips", utilSrvClient.getScripCodes());
                model.addAttribute("userDetails", userSessSrv.getUserDetails());

            }
            catch (Exception e)
            {
                log.error("Error in HCController: ", e);
            }
        }
        return VWNamesDirectory.getViewName(EnumVWNames.HCSel, false);
    }

    @PostMapping("/selScrip")
    public String proceedHC(@Valid @ModelAttribute("selScrip") TY_Scripsel selScrip, Model model)
    {
        if (StringUtils.hasText(selScrip.getScripName()) && screenerSrvClient != null)
        {
            log.info("Scrip Selected for health checkup .... " + selScrip.getScripName());
            // Update Scrip details first from screener before health checkup
            try
            {
                List<String> scrips = List.of(selScrip.getScripName());
                boolean scUpdated = screenerSrvClient.updateData4Scrips(scrips);
                if (scUpdated && utilSrvClient != null)
                {
                    // Add selected scrip for session
                    userSessSrv.add2CurrScrip(selScrip.getScripName());
                    // Now proceed with health checkup
                    try
                    {
                        TY_HC_Results scHC = utilSrvClient.getHealthCheckResults(scrips);
                        if (scHC != null)
                        {
                            model.addAttribute("hcResults", scHC);
                            model.addAttribute("add2Wl", false);
                            model.addAttribute("userDetails", userSessSrv.getUserDetails());
                        }
                    }
                    catch (Exception e)
                    {
                        throw new EX_UserSession(e.getLocalizedMessage());
                    }

                }

            }
            catch (Exception e)
            {
                throw new EX_UserSession(e.getLocalizedMessage());
            }
        }

        return VWNamesDirectory.getViewName(EnumVWNames.ScripsHC, false);
    }

    @GetMapping("/nav/{scrip}")
    public String navHC(@PathVariable("scrip") String scrip, Model model)
    {
        if (StringUtils.hasText(scrip) && screenerSrvClient != null)
        {
            log.info("Scrip Selected for health checkup .... " + scrip);
            // Update Scrip details first from screener before health checkup
            try
            {
                List<String> scrips = List.of(scrip);
                boolean scUpdated = screenerSrvClient.updateData4Scrips(scrips);
                if (scUpdated && utilSrvClient != null)
                {
                    // Add selected scrip for session
                    userSessSrv.add2CurrScrip(scrip);
                    // Now proceed with health checkup
                    try
                    {
                        TY_HC_Results scHC = utilSrvClient.getHealthCheckResults(scrips);
                        if (scHC != null)
                        {
                            model.addAttribute("hcResults", scHC);
                            model.addAttribute("add2Wl", false);
                            model.addAttribute("userDetails", userSessSrv.getUserDetails());
                        }
                    }
                    catch (Exception e)
                    {
                        throw new EX_UserSession(e.getLocalizedMessage());
                    }

                }

            }
            catch (Exception e)
            {
                throw new EX_UserSession(e.getLocalizedMessage());
            }
        }

        return VWNamesDirectory.getViewName(EnumVWNames.ScripsHC, false);
    }

}
