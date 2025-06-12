package imgr.com.iManager_App.controllers.routes;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import imgr.com.iManager_App.exceptions.EX_UserSession;
import imgr.com.iManager_App.srv.intf.IF_PFSrvClient;
import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.ui.constants.VWNamesDirectory;
import imgr.com.iManager_App.ui.enums.EnumVWNames;
import imgr.com.iManager_App.ui.pojos.TY_ConsolPF;
import imgr.com.iManager_App.ui.pojos.TY_OppcostPFReport;
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

    @GetMapping("/upload")
    public String showPFUpload(Model model)
    {
        if (pfSrv != null && userSessionSrv != null)
        {
            String token = userSessionSrv.getScreenerToken();
            model.addAttribute("userDetails", userSessionSrv.getUserDetails());
            if (StringUtils.hasText(token))
            {
                return VWNamesDirectory.getViewName(EnumVWNames.UploadPF, false);
            }
        }
        return VWNamesDirectory.getViewName(EnumVWNames.Home, true);
    }

    @GetMapping("/consolwl")
    public String showPFWLConsol(Model model)
    {
        if (pfSrv != null && userSessionSrv != null)
        {
            String token = userSessionSrv.getScreenerToken();
            if (StringUtils.hasText(token))
            {
                TY_ConsolPF pf = null;
                try
                {
                    pf = pfSrv.getConsolidatedPF(token, false);
                    userSessionSrv.setPFWLConsol(pf);

                    if (pf != null)
                    {
                        log.info("Portfolio bound.. for user");
                        model.addAttribute("pf", pf);
                        model.addAttribute("userDetails", userSessionSrv.getUserDetails());
                        return VWNamesDirectory.getViewName(EnumVWNames.ConsolPF, false);
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

    @GetMapping("/refreshCons")
    public String refreshPFWLConsol(Model model)
    {
        if (pfSrv != null && userSessionSrv != null)
        {
            String token = userSessionSrv.getScreenerToken();
            if (StringUtils.hasText(token))
            {
                TY_ConsolPF pf = null;
                try
                {
                    pf = pfSrv.getConsolidatedPF(token, true);
                    userSessionSrv.setPFWLConsol(pf);

                    if (pf != null)
                    {
                        log.info("Portfolio bound.. for user");
                        model.addAttribute("pf", pf);
                        model.addAttribute("userDetails", userSessionSrv.getUserDetails());
                        return VWNamesDirectory.getViewName(EnumVWNames.ConsolPF, false);
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

    @GetMapping("/ai")
    public String showOppCostAnalysis(Model model)
    {
        if (pfSrv != null && userSessionSrv != null)
        {
            String token = userSessionSrv.getScreenerToken();
            if (StringUtils.hasText(token))
            {
                TY_OppcostPFReport oppCostPFReport = null;
                try
                {
                    oppCostPFReport = pfSrv.getOppCostPFReport(token, false);
                    if (oppCostPFReport != null)
                    {
                        log.info("Opp Cost Analysis bound.. for user");
                        model.addAttribute("oppCostPFReport", oppCostPFReport);
                        model.addAttribute("userDetails", userSessionSrv.getUserDetails());
                        return VWNamesDirectory.getViewName(EnumVWNames.OppCost, false);
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

    @PostMapping("/uploadPF")
    public String handlePFUpload(@RequestParam("file") MultipartFile file, Model model) throws Exception
    {
        String vwName = VWNamesDirectory.getViewName(EnumVWNames.PortfolioOverview, true);
        if (file != null && userSessionSrv != null)
        {
            if (!file.isEmpty())
            {
                log.info("File Uploaded successfully : " + file.getOriginalFilename());
                try
                {
                    if (file.getBytes() != null)
                    {
                        if (pfSrv.updateUserPortfolio(file))
                        {
                            log.info("File uploaded successfully at PF Controller: " + file.getOriginalFilename());
                            vwName = VWNamesDirectory.getViewName(EnumVWNames.PortfolioOverview, true);
                            return vwName;
                        }
                        else
                        {
                            model.addAttribute("message", "Portoflio Update failed. Please try again.");
                            model.addAttribute("userDetails", userSessionSrv.getUserDetails());
                            return VWNamesDirectory.getViewName(EnumVWNames.UploadPF, false);
                        }
                    }
                }
                catch (IOException e)
                {
                    model.addAttribute("message", "File exception : " + e.getLocalizedMessage());
                    model.addAttribute("userDetails", userSessionSrv.getUserDetails());
                    return VWNamesDirectory.getViewName(EnumVWNames.UploadPF, false);
                }
            }
            else
            {
                model.addAttribute("message", "File is empty. Please upload a valid file.");
                model.addAttribute("userDetails", userSessionSrv.getUserDetails());
                return VWNamesDirectory.getViewName(EnumVWNames.UploadPF, false);
            }
        }
        return vwName;
    }

}
