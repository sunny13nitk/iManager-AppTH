package imgr.com.iManager_App.exceptions.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.ui.constants.VWNamesDirectory;
import imgr.com.iManager_App.ui.enums.EnumVWNames;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHdlr
{
    private final IF_UserSessionSrv userSessionSrv;

    @ExceptionHandler(EX_UserSession.class)
    public ModelAndView handleNotFound(Exception ex)
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        log.error("User Session exception occured with error details as : " + ex.getLocalizedMessage());
        log.error(ex.getStackTrace().toString());
        mv.addObject("formError", ex.getMessage());
        mv.addObject("userDetails", userSessionSrv.getUserDetails());
        return mv;
    }

    @ExceptionHandler(EX_UnauthorizedLogin.class)
    public ModelAndView handleUnauthorizedLogin(Exception ex)
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(VWNamesDirectory.getViewName(EnumVWNames.Login, false));
        log.error("User Session exception occured with error details as : " + ex.getLocalizedMessage());
        log.error(ex.getStackTrace().toString());
        mv.addObject("formError", ex.getMessage());
        return mv;
    }
}
