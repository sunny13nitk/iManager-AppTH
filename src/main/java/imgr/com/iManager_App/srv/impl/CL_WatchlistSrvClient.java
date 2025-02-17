package imgr.com.iManager_App.srv.impl;

import java.io.IOException;
import java.net.IDN;
import java.net.URI;
import java.net.URL;
import java.util.List;

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
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import imgr.com.iManager_App.srv.intf.IF_APIClient;
import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.srv.intf.IF_WatchlistSrvClient;
import imgr.com.iManager_App.ui.pojos.TY_Credentials;
import imgr.com.iManager_App.ui.pojos.TY_DestinationsSuffix;
import imgr.com.iManager_App.ui.pojos.TY_WLDB;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CL_WatchlistSrvClient implements IF_WatchlistSrvClient
{

    private final TY_DestinationsSuffix dS;

    private final IF_UserSessionSrv userSessionSrv;

    private final IF_APIClient apiSrv;

    @Override
    public List<TY_WLDB> getWatchlistDb(String token) throws Exception
    {
        List<TY_WLDB> wlDB = null;
        HttpResponse response = null;
        String bearer = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        if (dS != null && userSessionSrv != null)
        {
            String key = userSessionSrv.getDecryptedKey();
            if (StringUtils.hasText(key)
                    && StringUtils.hasText(userSessionSrv.getUserSessionInformation().getUserName())
                    && StringUtils.hasText(dS.getBaseurl()) && StringUtils.hasText(dS.getAuthurl())
                    && StringUtils.hasText(dS.getWatchlistdburl()) && apiSrv != null)
            {
                if (!StringUtils.hasText(userSessionSrv.getUserSessionInformation().getBearer()))
                {
                    bearer = apiSrv
                            .getAuthToken(new TY_Credentials(userSessionSrv.getUserSessionInformation().getUserName(),
                                    userSessionSrv.getDecryptedKey()));
                }
                else
                {
                    bearer = userSessionSrv.getUserSessionInformation().getBearer();
                }
                if (StringUtils.hasText(bearer))
                {
                    String wlUrl = dS.getBaseurl() + dS.getWatchlistdburl() + token;
                    // Now le's trigger the WL DB Call
                    URL url = new URL(wlUrl);
                    URI uri = new URI(url.getProtocol(), url.getUserInfo(), IDN.toASCII(url.getHost()), url.getPort(),
                            url.getPath(), url.getQuery(), url.getRef());
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
                        log.info("WL DB succ executed....");
                        HttpEntity entityWLDB = response.getEntity();
                        String apiOutput = EntityUtils.toString(entityWLDB);
                        ObjectMapper objectMapper = new ObjectMapper();
                        try
                        {
                            TypeFactory typeFactory = objectMapper.getTypeFactory();
                            CollectionType collectionType = typeFactory.constructCollectionType(List.class,
                                    TY_WLDB.class);
                            wlDB = objectMapper.readValue(apiOutput, collectionType);
                            if (wlDB != null)
                            {
                                log.info("Wl Node Bound with ... " + wlDB.size() + " entities...");

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
        return wlDB;
    }
}
