package imgr.com.iManager_App.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.srv.intf.IF_WatchlistSrvClient;
import imgr.com.iManager_App.srv.pojos.TY_SCToken;
import imgr.com.iManager_App.ui.constants.VWNamesDirectory;
import imgr.com.iManager_App.ui.enums.EnumVWNames;
import imgr.com.iManager_App.ui.pojos.EN_Watchlist;
import imgr.com.iManager_App.ui.pojos.TY_ScripAnalysisData;
import imgr.com.iManager_App.ui.pojos.TY_WLDB;
import imgr.com.iManager_App.utilities.TestUtility;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/wl")
public class WatchlistController
{

    private final IF_UserSessionSrv userSessSrv;

    private final IF_WatchlistSrvClient wlSrv;

    @GetMapping("/db")
    public String showWlDB(Model model) throws Exception
    {
        if (userSessSrv != null && wlSrv != null)
        {

            if (!StringUtils.hasText(userSessSrv.getDecryptedKey()))
            {
                // Navigate to Principal Propogation View
                userSessSrv.setParentView4Navigation(EnumVWNames.WatchlistDashboard);
                return VWNamesDirectory.getViewName(EnumVWNames.Principal, true);

            }
            else
            {
                String token = userSessSrv.getScreenerToken();
                if (StringUtils.hasText(token))
                {
                    List<TY_WLDB> wlDBList = null;
                    List<TY_ScripAnalysisData> wlF = null;
                    List<EN_Watchlist> wlT = null;

                    if (CollectionUtils.isNotEmpty(userSessSrv.getWlDB()))
                    {
                        wlDBList = wlSrv.refreshWatchlistDb(userSessSrv.getWlDB(), token);
                        wlF = userSessSrv.getUserSessionInformation().getWlFInfo();
                        wlT = userSessSrv.getUserSessionInformation().getWlEntities();
                    }
                    else // Only complete Load if WLlist not present is session
                    {
                        // wlDBList = wlSrv.getWatchlistDb(token);
                        wlDBList = TestUtility.getWLDB4mJSON();
                        wlF = wlSrv.getWLFundamentalAnalysis();
                        wlT = wlSrv.getWatchlistThesis();

                    }
                    model.addAttribute("wlList", wlDBList);
                    model.addAttribute("wlF", wlF);
                    model.addAttribute("wlT", wlT);
                    userSessSrv.setWLDB(wlDBList);
                    userSessSrv.setWLFundamentals(wlF);
                    userSessSrv.setWLThesis(wlT);
                }
            }

        }
        return VWNamesDirectory.getViewName(EnumVWNames.WatchlistDashboard, false);
    }

    @GetMapping("/db/{scrip}")
    public String showWLScripDetails(Model model, @PathVariable String scrip) throws Exception
    {
        if (StringUtils.hasText(scrip) && userSessSrv != null)
        {
            // Get details for Selected Scrip from session
            if (CollectionUtils.isNotEmpty(userSessSrv.getWlDB()))
            {
                Optional<TY_WLDB> wlItemO = userSessSrv.getWlDB().stream().filter(w -> w.getScrip().equals(scrip))
                        .findFirst();
                if (wlItemO.isPresent())
                {
                    model.addAttribute("wlItem", wlItemO.get());
                }

                Optional<TY_ScripAnalysisData> wlFItemO = userSessSrv.getUserSessionInformation().getWlFInfo().stream()
                        .filter(w -> w.getSccode().equals(scrip)).findFirst();
                if (wlFItemO.isPresent())
                {
                    model.addAttribute("wlF", wlFItemO.get());
                }

                Optional<EN_Watchlist> wlTHtemO = userSessSrv.getUserSessionInformation().getWlEntities().stream()
                        .filter(w -> w.getScrip().equals(scrip)).findFirst();
                if (wlTHtemO.isPresent())
                {
                    model.addAttribute("wlT", wlTHtemO.get());
                }

            }

            // Add WL Model view to User Session
            ModelAndView mvNav = new ModelAndView(VWNamesDirectory.getViewName(EnumVWNames.WatchlistDashboard, false));
            mvNav.addObject("wlList", userSessSrv.getWlDB());
            mvNav.addObject("wlF", userSessSrv.getUserSessionInformation().getWlFInfo());
            mvNav.addObject("wlT", userSessSrv.getUserSessionInformation().getWlEntities());
            userSessSrv.setParentViewModel4Navigation(mvNav);

        }
        return VWNamesDirectory.getViewName(EnumVWNames.WLDetailsScreener, false);
    }

    @GetMapping("/token")
    public String showTokenVW(Model model, @ModelAttribute("message") String message,
            @ModelAttribute("tokenInfo") TY_SCToken tokenInfo)
    {
        TY_SCToken token = null;
        if (tokenInfo == null)
        {
            token = new TY_SCToken();
        }
        else
        {
            token = tokenInfo;
        }
        model.addAttribute("sctoken", token);
        if (StringUtils.hasText(message))
        {
            model.addAttribute("message", message);
        }
        return VWNamesDirectory.getViewName(EnumVWNames.Tokens, false);
    }

    @PostMapping("/token")
    public ModelAndView updateScreenerToken(@Valid @ModelAttribute("sctoken") TY_SCToken tokenInfo,
            RedirectAttributes attributes)
    {
        ModelAndView mv = null;

        return mv;
    }
}
