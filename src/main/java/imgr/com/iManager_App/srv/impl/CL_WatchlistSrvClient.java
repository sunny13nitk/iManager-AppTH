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
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.srv.intf.IF_WatchlistSrvClient;
import imgr.com.iManager_App.ui.pojos.EN_Watchlist;
import imgr.com.iManager_App.ui.pojos.TY_DestinationsSuffix;
import imgr.com.iManager_App.ui.pojos.TY_ScripAnalysisData;
import imgr.com.iManager_App.ui.pojos.TY_ScripCMPResponse;
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

    @Override
    public List<TY_WLDB> getWatchlistDb(String token) throws Exception
    {
        List<TY_WLDB> wlDB = null;
        HttpResponse response = null;
        String bearer = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        if (dS != null && userSessionSrv != null)
        {
            bearer = userSessionSrv.getDecryptedKey();
            if (StringUtils.hasText(bearer) && StringUtils.hasText(dS.getBaseurl())
                    && StringUtils.hasText(dS.getWatchlistdburl()))
            {

                String wlUrl = dS.getBaseurl() + dS.getWatchlistdburl() + token;
                // Now let's trigger the WL DB Call
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
                        CollectionType collectionType = typeFactory.constructCollectionType(List.class, TY_WLDB.class);
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
        return wlDB;
    }

    @Override
    public TY_ScripCMPResponse getCMP4WLScrips(String token) throws Exception
    {
        TY_ScripCMPResponse resp = null;

        HttpResponse response = null;
        String bearer = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        if (dS != null && userSessionSrv != null)
        {
            bearer = userSessionSrv.getDecryptedKey();
            if (StringUtils.hasText(bearer) && StringUtils.hasText(dS.getBaseurl())
                    && StringUtils.hasText(dS.getWatchlistcmpurl()))
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
                    log.info("WL CMP succ executed....");
                    HttpEntity entityWLDB = response.getEntity();
                    String apiOutput = EntityUtils.toString(entityWLDB);
                    ObjectMapper objectMapper = new ObjectMapper();
                    try
                    {
                        // TypeFactory typeFactory = objectMapper.getTypeFactory();
                        // CollectionType collectionType =
                        // typeFactory.constructCollectionType(List.class,
                        // TY_WLDB.class);
                        resp = objectMapper.readValue(apiOutput, TY_ScripCMPResponse.class);
                        if (resp != null)
                        {
                            log.info("Wl CMP Node Bound with ... " + resp.getScCMPs().size() + " entities...");

                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }

            }

        }

        return resp;
    }

    @Override
    public List<TY_WLDB> refreshWatchlistDb(List<TY_WLDB> exsWLDB, String token) throws Exception
    {
        List<TY_WLDB> wlDB = null;
        HttpResponse response = null;
        String bearer = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        if (dS != null && userSessionSrv != null)
        {
            bearer = userSessionSrv.getDecryptedKey();
            if (StringUtils.hasText(bearer) && StringUtils.hasText(dS.getBaseurl())
                    && StringUtils.hasText(dS.getWatchlistdburl()))
            {

                String wlUrl = dS.getBaseurl() + dS.getWatchlistdburl() + token;
                // Now le's trigger the WL DB Call
                URL url = new URL(wlUrl);
                URI uri = new URI(url.getProtocol(), url.getUserInfo(), IDN.toASCII(url.getHost()), url.getPort(),
                        url.getPath(), url.getQuery(), url.getRef());
                String correctEncodedURL = uri.toASCIIString();
                HttpPatch httpPatch = new HttpPatch(correctEncodedURL);
                httpPatch.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearer);
                httpPatch.addHeader("accept", "application/json");

                ObjectMapper objMapper = new ObjectMapper();

                String requestBody = objMapper.writeValueAsString(exsWLDB);

                StringEntity entity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
                httpPatch.setEntity(entity);

                // Fire the Url
                response = httpClient.execute(httpPatch);
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
                        CollectionType collectionType = typeFactory.constructCollectionType(List.class, TY_WLDB.class);
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

        return wlDB;
    }

    @Override
    public List<TY_ScripAnalysisData> getWLFundamentalAnalysis() throws Exception
    {
        List<TY_ScripAnalysisData> wlF = null;
        HttpResponse response = null;
        String bearer = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        if (dS != null && userSessionSrv != null)
        {
            bearer = userSessionSrv.getDecryptedKey();
            if (StringUtils.hasText(bearer) && StringUtils.hasText(dS.getBaseurl())
                    && StringUtils.hasText(dS.getWatchlistdburl()))
            {

                String wlUrl = dS.getBaseurl() + dS.getWatchlistfundamentals();
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
                                TY_ScripAnalysisData.class);
                        wlF = objectMapper.readValue(apiOutput, collectionType);
                        if (wlF != null)
                        {
                            log.info("Wl Fundamentals Node Bound with ... " + wlF.size() + " entities...");

                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
            }

        }
        return wlF;
    }

    @Override
    public List<EN_Watchlist> getWatchlistThesis() throws Exception
    {
        List<EN_Watchlist> wlT = null;
        HttpResponse response = null;
        String bearer = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        if (dS != null && userSessionSrv != null)
        {
            bearer = userSessionSrv.getDecryptedKey();
            if (StringUtils.hasText(bearer) && StringUtils.hasText(dS.getBaseurl())
                    && StringUtils.hasText(dS.getWatchlistdburl()))
            {

                String wlUrl = dS.getBaseurl() + dS.getWatchlistthesis();
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
                    log.info("WL Thesis Fetch succ executed....");
                    HttpEntity entityWLDB = response.getEntity();
                    String apiOutput = EntityUtils.toString(entityWLDB);
                    ObjectMapper objectMapper = new ObjectMapper();
                    try
                    {
                        TypeFactory typeFactory = objectMapper.getTypeFactory();
                        CollectionType collectionType = typeFactory.constructCollectionType(List.class,
                                EN_Watchlist.class);
                        wlT = objectMapper.readValue(apiOutput, collectionType);
                        if (wlT != null)
                        {
                            log.info("Wl thesis Node Bound with ... " + wlT.size() + " entities...");

                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
            }

        }
        return wlT;
    }

}
