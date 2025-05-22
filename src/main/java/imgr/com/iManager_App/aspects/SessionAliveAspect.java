package imgr.com.iManager_App.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.ui.constants.VWNamesDirectory;
import imgr.com.iManager_App.ui.enums.EnumVWNames;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class SessionAliveAspect
{
    private final IF_UserSessionSrv userSessSrv;

    @Around("anyControllerPublicMethods()")
    public Object validateLiveUserSession(ProceedingJoinPoint pjp) throws Throwable
    {
        log.info("SessionAliveAspect: validateLiveUserSession() called");

        String vwName = null;
        try
        {
            if (userSessSrv != null)
            {
                if (userSessSrv.getUserDetails() != null)
                {
                    log.info("SessionAliveAspect: User session is alive");
                    return pjp.proceed();

                }
                else
                {
                    vwName = VWNamesDirectory.getViewName(EnumVWNames.Login, false);
                    log.info("SessionAliveAspect: User session is NOT alive");
                }
            }
            else
            {
                vwName = VWNamesDirectory.getViewName(EnumVWNames.Login, false);
                log.info("SessionAliveAspect: User session is NOT alive");
            }

        }
        catch (Exception e)
        {
            log.error("Error in SessionAliveAspect: ", e);
            vwName = VWNamesDirectory.getViewName(EnumVWNames.Login, false);
        }

        return vwName;

    }

    @Pointcut("execution(public * imgr.com.iManager_App.controllers.routes.*.*(..))")
    public void anyControllerPublicMethods()
    {
        log.info("Checking if User session is alive.......");
    }

}
