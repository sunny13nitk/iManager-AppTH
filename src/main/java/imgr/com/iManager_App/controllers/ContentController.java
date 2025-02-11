package imgr.com.iManager_App.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController
{
    @GetMapping("/")
    public String home()
    {
        return "homeM";
    }

    @GetMapping("/home")
    public String homeAlias()
    {
        return "home";
    }

    @GetMapping("/login")
    public String login()
    {
        return "loginbs";
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