package imgr.com.iManager_App.exceptions.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHdlr
{
    @ExceptionHandler(EX_UserSession.class)
    public ModelAndView handleNotFound(Exception ex)
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        log.error("User Session exception occured with error details as : " + ex.getLocalizedMessage());
        log.error(ex.getStackTrace().toString());
        mv.addObject("formError", ex.getMessage());
        return mv;
    }
}
