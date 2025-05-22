package imgr.com.iManager_App.controllers.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.srv.pojos.TY_Login;
import imgr.com.iManager_App.ui.constants.VWNamesDirectory;
import imgr.com.iManager_App.ui.enums.EnumVWNames;
import imgr.com.iManager_App.ui.pojos.TY_Credentials;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ContentController
{

    private final IF_UserSessionSrv userSessSrv;

    @PostMapping("/login")
    public String processLogin(@Valid @ModelAttribute TY_Login login, Model model) throws Exception
    {
        if (login != null)
        {
            // Authorize as per logged in User
            userSessSrv.initialize(login);
            model.addAttribute("userDetails", userSessSrv.getUserDetails());
            return VWNamesDirectory.getViewName(EnumVWNames.Home, false);
        }

        return "error";
    }

    @GetMapping("/home")
    public String home(Model model) throws Exception
    {
        if (userSessSrv != null)
        {
            if (userSessSrv.getUserDetails() != null)
            {
                model.addAttribute("userDetails", userSessSrv.getUserDetails());
                return VWNamesDirectory.getViewName(EnumVWNames.Home, false);
            }

        }

        return "error";
    }

    @GetMapping("/")
    public String login(Model model)
    {
        TY_Login login = new TY_Login();
        model.addAttribute("login", login);
        model.addAttribute("formError", null);
        return VWNamesDirectory.getViewName(EnumVWNames.Login, false);
    }

    @GetMapping("/pp")
    public String showPP(Model model)
    {
        TY_Credentials cred = new TY_Credentials();
        model.addAttribute("credentials", cred);
        return VWNamesDirectory.getViewName(EnumVWNames.Principal, false);
    }

    @PostMapping("/pp")
    public String postMethodName(@ModelAttribute TY_Credentials cred) throws Exception
    {
        String viewName = VWNamesDirectory.getViewName(EnumVWNames.Home, false);
        if (cred != null)
        {
            if (StringUtils.hasText(cred.getPassword()))
            {
                userSessSrv.encryptSessionKey(cred.getPassword());
                viewName = userSessSrv.getRedirectedParentView();
            }
        }

        return viewName;
    }

    @GetMapping("/user/home")
    public String userHome()
    {
        return "userhome";
    }

    @GetMapping("/back")
    public ModelAndView navigateBackGlobal()
    {
        if (userSessSrv != null)
        {
            ModelAndView mv = userSessSrv.getMV();
            if (mv != null)
            {
                return mv;
            }
        }
        return new ModelAndView("error");
    }

}