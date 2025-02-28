package imgr.com.iManager_App.srv.impl;

import java.io.IOException;
import java.net.IDN;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import imgr.com.iManager_App.exceptions.handler.EX_APIClient;
import imgr.com.iManager_App.srv.intf.IF_APIClient;
import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.srv.pojos.TY_SCToken;
import imgr.com.iManager_App.ui.pojos.TY_Credentials;
import imgr.com.iManager_App.ui.pojos.TY_DestinationsSuffix;
import imgr.com.iManager_App.utilities.JSONUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CL_APIClient implements IF_APIClient
{

    private final TY_DestinationsSuffix dS;

    private final IF_UserSessionSrv userSessSrv;

    @Override
    public String getAuthToken(TY_Credentials credentials) throws Exception
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
                    ObjectMapper objMapper = new ObjectMapper();
                    try
                    {
                        String requestBody = objMapper.writeValueAsString(credentials);
                        StringEntity entity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
                        httpPost.setEntity(entity);
                        try
                        {
                            // Fire the Url
                            response = httpClient.execute(httpPost);
                            int statusCode = response.getStatusLine().getStatusCode();
                            if (statusCode != HttpStatus.SC_OK)
                            {
                                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
                            }
                            // Try and Get Entity from Response
                            HttpEntity entityResp = response.getEntity();
                            String apiOutput = EntityUtils.toString(entityResp);
                            ObjectMapper mapper = new ObjectMapper();
                            JsonNode jsonNode = mapper.readTree(apiOutput);
                            if (jsonNode != null)
                            {
                                token = JSONUtility.readPropertyValue(jsonNode.toString(), "/token");
                                if (StringUtils.hasText(token))
                                {
                                    userSessSrv.setAccessBearer(token); // Also set in session
                                }

                            }

                        }
                        catch (IOException e)
                        {
                            log.error(e.getLocalizedMessage());
                        }
                    }
                    catch (JsonProcessingException e)
                    {
                        log.error(e.getLocalizedMessage());
                    }

                    finally
                    {
                        httpClient.close();
                    }

                }

            }
        }

        return token;
    }

    @Override
    public void initializeScreenerToken() throws EX_APIClient, Exception
    {

        if (userSessSrv != null)
        {

            if (StringUtils.hasText(dS.getScreenertoken()) && StringUtils.hasText(dS.getBaseurl())
                    && userSessSrv != null)
            {
                HttpResponse response = null;
                CloseableHttpClient httpClient = HttpClientBuilder.create().build();

                String authUrl = dS.getBaseurl() + dS.getScreenertoken();

                try
                {
                    URL url = new URL(authUrl);
                    URI uri = new URI(url.getProtocol(), url.getUserInfo(), IDN.toASCII(url.getHost()), url.getPort(),
                            url.getPath(), url.getQuery(), url.getRef());
                    String correctEncodedURL = uri.toASCIIString();
                    HttpGet httpGet = new HttpGet(correctEncodedURL);
                    String bearer = userSessSrv.getDecryptedKey();

                    if (StringUtils.hasText(bearer))
                    {
                        httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearer);
                        httpGet.addHeader("accept", "application/json");
                        try
                        {
                            // Fire the Url
                            response = httpClient.execute(httpGet);
                            int statusCode = response.getStatusLine().getStatusCode();
                            if (statusCode != HttpStatus.SC_OK)
                            {
                                throw new EX_APIClient(
                                        "Failed with HTTP error code while retrieving Screener Token : " + statusCode);
                            }
                            // Try and Get Entity from Response
                            HttpEntity entityResp = response.getEntity();
                            String apiOutput = EntityUtils.toString(entityResp);
                            ObjectMapper mapper = new ObjectMapper();
                            JsonNode jsonNode = mapper.readTree(apiOutput);
                            if (jsonNode != null)
                            {

                                TY_SCToken token = mapper.readValue(apiOutput, TY_SCToken.class);

                                if (token != null)
                                {
                                    userSessSrv.resetScreenerToken(token);
                                }

                            }

                        }
                        catch (IOException e)
                        {
                            log.error(e.getLocalizedMessage());
                        }
                    }

                }
                catch (JsonProcessingException e)
                {
                    log.error(e.getLocalizedMessage());
                }

                finally
                {
                    httpClient.close();
                }

            }
        }

    }

    @Override
    public void refreshScreenerToken(TY_SCToken scToken) throws EX_APIClient, Exception
    {

        if (scToken != null)
        {
            if (StringUtils.hasText(scToken.getSessionid()) && StringUtils.hasText(scToken.getToken())
                    && userSessSrv != null)
            {
                if (StringUtils.hasText(dS.getScreenertoken()) && StringUtils.hasText(dS.getBaseurl()))
                {
                    HttpResponse response = null;

                    String bearer = userSessSrv.getDecryptedKey();

                    if (StringUtils.hasText(bearer))
                    {
                        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

                        String authUrl = dS.getBaseurl() + dS.getScreenertoken();
                        HttpPost httpPost = new HttpPost(authUrl);
                        httpPost.addHeader("Content-Type", "application/json");
                        httpPost.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearer);
                        ObjectMapper objMapper = new ObjectMapper();
                        try
                        {
                            String requestBody = objMapper.writeValueAsString(scToken);
                            StringEntity entity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
                            httpPost.setEntity(entity);
                            try
                            {
                                // Fire the Url
                                response = httpClient.execute(httpPost);
                                int statusCode = response.getStatusLine().getStatusCode();
                                if (statusCode != HttpStatus.SC_CREATED)
                                {
                                    throw new EX_APIClient("Failed with HTTP error code : " + statusCode
                                            + " while Resetting Screener Token");
                                }
                                // Try and Get Entity from Response
                                HttpEntity entityResp = response.getEntity();
                                String apiOutput = EntityUtils.toString(entityResp);
                                ObjectMapper mapper = new ObjectMapper();
                                JsonNode jsonNode = mapper.readTree(apiOutput);
                                if (jsonNode != null)
                                {
                                    TY_SCToken token = mapper.readValue(apiOutput, TY_SCToken.class);

                                    if (token != null)
                                    {
                                        userSessSrv.resetScreenerToken(token);
                                    }
                                }

                            }
                            catch (IOException e)
                            {
                                log.error(e.getLocalizedMessage());
                            }
                        }

                        catch (JsonProcessingException e)
                        {
                            log.error(e.getLocalizedMessage());
                        }

                        finally
                        {
                            httpClient.close();
                        }
                    }

                }

            }
        }

    }

}
