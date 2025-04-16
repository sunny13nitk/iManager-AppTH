package imgr.com.iManager_App.srv.intf;

import java.util.List;

import org.apache.http.HttpStatus;

public interface IF_ScreenerSrvClient
{
    public HttpStatus updateData4Scrips(List<String> scrips) throws Exception;
}
