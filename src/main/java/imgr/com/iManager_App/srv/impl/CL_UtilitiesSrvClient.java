package imgr.com.iManager_App.srv.impl;

import java.io.IOException;
import java.net.IDN;
import java.net.URI;
import java.net.URL;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
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

import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.srv.intf.IF_UtilitiesSrvClient;
import imgr.com.iManager_App.ui.pojos.TY_DestinationsSuffix;
import imgr.com.iManager_App.ui.pojos.TY_HC_Results;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CL_UtilitiesSrvClient implements IF_UtilitiesSrvClient
{

    private final TY_DestinationsSuffix dS;

    private final IF_UserSessionSrv userSessionSrv;

    @Override
    public TY_HC_Results getHealthCheckResults(List<String> scrips) throws Exception
    {

        TY_HC_Results hcResults = null;
        HttpResponse response = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        if (CollectionUtils.isNotEmpty(scrips) && dS != null && userSessionSrv != null)
        {
            String bearer = userSessionSrv.getDecryptedKey();
            String hcUrl = dS.getBaseurl() + dS.getHc();
            for (String scrip : scrips)
            {
                hcUrl = hcUrl + scrip;
                if (scrips.indexOf(scrip) != scrips.size() - 1)
                {
                    hcUrl = hcUrl + ",";
                }
            }

            if (StringUtils.hasText(bearer) && StringUtils.hasText(hcUrl))
            {
                // Now le's trigger the WL DB Call
                URL url = new URL(hcUrl);
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
                    log.info("Health check for scrips obtained....");
                    HttpEntity entityWLDB = response.getEntity();
                    String apiOutput = EntityUtils.toString(entityWLDB);
                    ObjectMapper objectMapper = new ObjectMapper();
                    try
                    {

                        hcResults = objectMapper.readValue(apiOutput, TY_HC_Results.class);
                        if (hcResults != null)
                        {
                            log.info("Health check obtained for Scrips:  " + hcResults.getScResults().size());

                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
            }
        }

        return hcResults;
    }

    @Override
    public List<String> getScripCodes() throws Exception
    {
        List<String> scrips = null;

        HttpResponse response = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        if ( dS != null && userSessionSrv != null)
        {
            String bearer = userSessionSrv.getDecryptedKey();
            String hcUrl = dS.getBaseurl() + dS.getScrips();

            if (StringUtils.hasText(bearer) && StringUtils.hasText(hcUrl))
            {
                // Now le's trigger the WL DB Call
                URL url = new URL(hcUrl);
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
                    log.info("List of scrips obtained....");
                    HttpEntity entityWLDB = response.getEntity();
                    String apiOutput = EntityUtils.toString(entityWLDB);
                    ObjectMapper objectMapper = new ObjectMapper();
                    try
                    {
                        TypeFactory typeFactory = objectMapper.getTypeFactory();
                        CollectionType collectionType = typeFactory.constructCollectionType(List.class, String.class);
                        scrips = objectMapper.readValue(apiOutput, collectionType);
                        if (scrips != null)
                        {
                            log.info("# Scrips Found : " + scrips.size());

                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
            }
        }

        return scrips;
    }

}
