package imgr.com.iManager_App.srv.impl;

import java.io.IOException;
import java.net.IDN;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import imgr.com.iManager_App.srv.intf.IF_PFSrvClient;
import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.ui.constants.GC_Constants;
import imgr.com.iManager_App.ui.pojos.TY_DestinationsSuffix;
import imgr.com.iManager_App.ui.pojos.TY_PF;
import imgr.com.iManager_App.utilities.StringsUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CL_PFSrvClient implements IF_PFSrvClient
{

    private final TY_DestinationsSuffix dS;

    private final IF_UserSessionSrv userSessionSrv;

    @Override
    public TY_PF getPortfolioDetails4User(String token) throws Exception
    {
        TY_PF pf = null;
        if (StringUtils.hasText(token) && userSessionSrv != null)
        {
            HttpResponse response = null;
            String bearer = null;
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();

            if (dS != null && userSessionSrv != null)
            {
                bearer = userSessionSrv.getDecryptedKey();
                if (StringUtils.hasText(bearer) && StringUtils.hasText(dS.getBaseurl())
                        && StringUtils.hasText(dS.getPf()))
                {
                    String wlUrl = dS.getBaseurl() + dS.getPf() + token;
                    wlUrl = StringsUtility.replaceURLwithParams(wlUrl, new String[]
                    { userSessionSrv.getUserDetails().getUsername() }, GC_Constants.gc_Repl);
                    if (StringUtils.hasText(wlUrl))
                    {
                        URL url = new URL(wlUrl);
                        URI uri = new URI(url.getProtocol(), url.getUserInfo(), IDN.toASCII(url.getHost()),
                                url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                        String correctEncodedURL = uri.toASCIIString();
                        HttpGet httpGet = new HttpGet(correctEncodedURL);
                        httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearer);
                        httpGet.addHeader("accept", "application/json");

                        // Fire the Url
                        response = httpClient.execute(httpGet);
                        int statusCodeWLDB = response.getStatusLine().getStatusCode();
                        if (statusCodeWLDB != org.apache.http.HttpStatus.SC_OK)
                        {
                            return null;
                        }

                        else if (statusCodeWLDB == org.apache.http.HttpStatus.SC_OK)
                        {
                            log.info("Portfolio Fetch succ executed.... for User : "
                                    + userSessionSrv.getUserDetails().getUsername());
                            HttpEntity entityWLDB = response.getEntity();
                            String apiOutput = EntityUtils.toString(entityWLDB);
                            ObjectMapper objectMapper = new ObjectMapper();
                            try
                            {
                                pf = objectMapper.readValue(apiOutput, TY_PF.class);
                                if (pf != null)
                                {
                                    log.info("User PF has " + pf.getPfItems().size() + " holdings...");

                                }
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            }

        }

        return pf;
    }

}
