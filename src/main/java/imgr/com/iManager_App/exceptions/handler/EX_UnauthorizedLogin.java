package imgr.com.iManager_App.exceptions.handler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EX_UnauthorizedLogin extends RuntimeException
{

    public EX_UnauthorizedLogin(String message)
    {

        super(message);
        log.error(message);
    }

}