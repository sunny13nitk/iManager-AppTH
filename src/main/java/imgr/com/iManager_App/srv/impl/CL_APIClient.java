package imgr.com.iManager_App.srv.impl;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import imgr.com.iManager_App.exceptions.handler.EX_APIClient;
import imgr.com.iManager_App.srv.intf.IF_APIClient;
import imgr.com.iManager_App.ui.pojos.TY_Credentials;
import imgr.com.iManager_App.ui.pojos.TY_DestinationsSuffix;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CL_APIClient implements IF_APIClient
{

    private final TY_DestinationsSuffix dS;

    @Override
    public String getAuthToken(TY_Credentials credentials) throws EX_APIClient
    {
        String token = null;
        if (credentials != null)
        {
            if (StringUtils.hasText(credentials.getUsername()) && StringUtils.hasText(credentials.getPassword()))
            {
                if (StringUtils.hasText(dS.getAuthurl()) && StringUtils.hasText(dS.getBaseurl()))
                {
                    HttpResponse response = null;
                    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                   
                    String authUrl = dS.getBaseurl() + dS.getAuthurl();
                    HttpPost httpPost = new HttpPost(authUrl);
                    httpPost.addHeader("Content-Type", "application/json");
                    

                }
            }
        }
        return token;
    }

}
