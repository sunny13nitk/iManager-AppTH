package imgr.com.iManager_App.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/mv")
@RequiredArgsConstructor
public class MVController
{
    private final IF_UserSessionSrv userSessionSrv;

    @GetMapping("/back2Wl")
    public ModelAndView navigateBack2WLDb()
    {
        ModelAndView mv = null;
        mv = userSessionSrv.getRedirectedParentViewModel();
        return mv;
    }
}
