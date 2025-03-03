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

import imgr.com.iManager_App.exceptions.handler.EX_UserSession;
import imgr.com.iManager_App.srv.intf.IF_APIClient;
import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.srv.intf.IF_WatchlistSrvClient;
import imgr.com.iManager_App.srv.pojos.TY_SCToken;
import imgr.com.iManager_App.ui.constants.VWNamesDirectory;
import imgr.com.iManager_App.ui.enums.EnumVWNames;
import imgr.com.iManager_App.ui.pojos.EN_Watchlist;
import imgr.com.iManager_App.ui.pojos.TY_ScripAnalysisData;
import imgr.com.iManager_App.ui.pojos.TY_WLDB;
import imgr.com.iManager_App.utilities.TestUtility;
import imgr.com.iManager_App.utilities.UtilDurations;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/wl")
public class WatchlistController
{

    private final IF_UserSessionSrv userSessSrv;

    private final IF_WatchlistSrvClient wlSrv;

    private final IF_APIClient apiClntSrv;

    @GetMapping("/db")
    public String showWlDB(Model model) throws Exception
    {
        if (userSessSrv != null && wlSrv != null)
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
                model.addAttribute("userDetails", userSessSrv.getUserDetails());
                userSessSrv.setWLDB(wlDBList);
                userSessSrv.setWLFundamentals(wlF);
                userSessSrv.setWLThesis(wlT);
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

                model.addAttribute("userDetails", userSessSrv.getUserDetails());

            }

            // Add WL Model view to User Session
            ModelAndView mvNav = new ModelAndView(VWNamesDirectory.getViewName(EnumVWNames.WatchlistDashboard, false));
            mvNav.addObject("wlList", userSessSrv.getWlDB());
            mvNav.addObject("wlF", userSessSrv.getUserSessionInformation().getWlFInfo());
            mvNav.addObject("wlT", userSessSrv.getUserSessionInformation().getWlEntities());
            mvNav.addObject("userDetails", userSessSrv.getUserDetails());

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
        model.addAttribute("userDetails", userSessSrv.getUserDetails());

        return VWNamesDirectory.getViewName(EnumVWNames.Tokens, false);
    }

    @GetMapping("/upd/{scrip}")
    public String editWatchlistHeader(Model model, @PathVariable String scrip) throws Exception
    {
        if (StringUtils.hasText(scrip) && userSessSrv != null)
        {
            // Get details for Selected Scrip from session
            if (CollectionUtils.isNotEmpty(userSessSrv.getUserSessionInformation().getWlEntities()))
            {
                Optional<EN_Watchlist> wlTHtemO = userSessSrv.getUserSessionInformation().getWlEntities().stream()
                        .filter(w -> w.getScrip().equals(scrip)).findFirst();
                if (wlTHtemO.isPresent())
                {
                    model.addAttribute("wlT", wlTHtemO.get());
                }

                model.addAttribute("userDetails", userSessSrv.getUserDetails());

            }

            // // Add WL Model view to User Session
            // ModelAndView mvNav = new
            // ModelAndView(VWNamesDirectory.getViewName(EnumVWNames.WatchlistDashboard,
            // false));
            // mvNav.addObject("wlList", userSessSrv.getWlDB());
            // mvNav.addObject("wlF", userSessSrv.getUserSessionInformation().getWlFInfo());
            // mvNav.addObject("wlT",
            // userSessSrv.getUserSessionInformation().getWlEntities());
            // mvNav.addObject("userDetails", userSessSrv.getUserDetails());

            // userSessSrv.setParentViewModel4Navigation(mvNav);

        }
        return VWNamesDirectory.getViewName(EnumVWNames.WLHeaderEdit, false);
    }

    @PostMapping("/updateWLScrip")
    public ModelAndView updateScripinWL(@Valid @ModelAttribute("wlT") EN_Watchlist wlT) throws Exception
    {
        ModelAndView mv = new ModelAndView();
        if (wlT != null && userSessSrv != null)
        {

            // Update Watchlist Entity
            if (wlT != null)
            {
                List<EN_Watchlist> wlTList = userSessSrv.getUserSessionInformation().getWlEntities();
                if (CollectionUtils.isNotEmpty(wlTList))
                {
                    Optional<EN_Watchlist> wlTO = wlTList.stream().filter(w -> w.getScrip().equals(wlT.getScrip()))
                            .findFirst();
                    if (wlTO.isPresent())
                    {
                        EN_Watchlist wlExis = wlTO.get();
                        wlExis.setBullWtRatio(wlT.getBullWtRatio());
                        wlExis.setConviction(wlT.getConviction());
                        wlExis.setCusSegment(wlT.getCusSegment());
                        wlExis.setDateUpdated(UtilDurations.getTodaysDate());
                        wlExis.setFwdepsgrbase(wlT.getFwdepsgrbase());
                        wlExis.setFwdpebase(wlT.getFwdpebase());
                        wlExis.setFwdepsgrbull(wlT.getFwdepsgrbull());
                        wlExis.setFwdpebull(wlT.getFwdpebull());
                        wlExis.setGrowth(wlT.getGrowth());
                        wlExis.setLongevitygr(wlT.getLongevitygr());
                        wlExis.setSize(wlT.getSize());
                        wlExis.setStandalone(wlT.isStandalone());
                        wlExis.setStatus(wlT.getStatus());

                        TY_WLDB wlDBUpdated = wlSrv.updateWatchlistEntry(wlExis, true);
                        if (wlDBUpdated != null)
                        {
                            // Update for this WLDB in user session
                            // REplace wlDb in Current Session
                            if (wlDBUpdated != null)
                            {
                                userSessSrv.getUserSessionInformation().getWlDBList()
                                        .removeIf(wl -> wl.getScrip().equals(wlT.getScrip()));
                                userSessSrv.getUserSessionInformation().getWlDBList().add(wlDBUpdated);
                            }
                        }

                    }
                }
            }

            mv.setViewName(VWNamesDirectory.getViewName(EnumVWNames.WLDetailsScreener, true, new String[]
            { wlT.getScrip() }));
            mv.addObject("userDetails", userSessSrv.getUserDetails());
        }
        return mv;
    }

    @PostMapping("/token")
    public ModelAndView updateScreenerToken(@Valid @ModelAttribute("sctoken") TY_SCToken tokenInfo,
            RedirectAttributes attributes)
    {
        ModelAndView mv = new ModelAndView();

        if (tokenInfo != null && apiClntSrv != null)
        {
            try
            {
                apiClntSrv.refreshScreenerToken(tokenInfo);
                mv.setViewName(VWNamesDirectory.getViewName(EnumVWNames.Home, false));
                mv.addObject("userDetails", userSessSrv.getUserDetails());
            }
            catch (Exception e)
            {
                throw new EX_UserSession(e.getLocalizedMessage());
            }
        }

        return mv;
    }
}
