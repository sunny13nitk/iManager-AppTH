package imgr.com.iManager_App.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.srv.intf.IF_WatchlistSrvClient;
import imgr.com.iManager_App.ui.constants.VWNamesDirectory;
import imgr.com.iManager_App.ui.enums.EnumVWNames;
import imgr.com.iManager_App.ui.model.entity.TY_SCToken;
import imgr.com.iManager_App.ui.model.repo.RepoSCToken;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/wl")
public class WatchlistController
{

    private final RepoSCToken repoSCToken;

    private final IF_UserSessionSrv userSessSrv;

    private final IF_WatchlistSrvClient wlSrv;

    @GetMapping("/db")
    public String showWlDB(Model model) throws Exception
    {
        if (userSessSrv != null && wlSrv != null)
        {
            String token = userSessSrv.getScreenerToken();
            if (StringUtils.hasText(token))
            {
                // TEst
                userSessSrv.encryptSessionKey("admin-db");
                model.addAttribute("wlList", wlSrv.getWatchlistDb(token));
            }
        }
        return VWNamesDirectory.getViewName(EnumVWNames.WatchlistDashboard, false);
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
        if (tokenInfo != null && repoSCToken != null && userSessSrv != null)
        {

            try
            {
                repoSCToken.deleteAll();
                repoSCToken.save(tokenInfo);
                userSessSrv.resetScreenerToken(tokenInfo);
                mv = new ModelAndView((VWNamesDirectory.getViewName(EnumVWNames.Home, true)));
            }
            catch (Exception e)
            {
                attributes.addFlashAttribute("message", e.getMessage());
                attributes.addFlashAttribute("tokenInfo", tokenInfo);
                mv = new ModelAndView(VWNamesDirectory.getViewName(EnumVWNames.Tokens, true));
            }

        }

        return mv;
    }
}
