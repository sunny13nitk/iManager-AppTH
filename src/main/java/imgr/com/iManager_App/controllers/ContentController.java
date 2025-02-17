package imgr.com.iManager_App.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.ui.constants.VWNamesDirectory;
import imgr.com.iManager_App.ui.enums.EnumVWNames;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ContentController
{

    private final IF_UserSessionSrv userSessSrv;

    @GetMapping("/")
    public String home(Authentication auth) throws Exception
    {
        if (auth.isAuthenticated())
        {
            userSessSrv.initialize(auth);
            return VWNamesDirectory.getViewName(EnumVWNames.Home, false);
        }

        return "error";
    }

    @GetMapping("/login")
    public String login()
    {
        return VWNamesDirectory.getViewName(EnumVWNames.Login, false);
    }

    @GetMapping("/admin/home")
    public String adminHome()
    {
        return "adminhome";
    }

    @GetMapping("/user/home")
    public String userHome()
    {
        return "userhome";
    }
}