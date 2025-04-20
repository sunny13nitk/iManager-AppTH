package imgr.com.iManager_App.srv.impl;

import java.net.IDN;
import java.net.URI;
import java.net.URL;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import imgr.com.iManager_App.srv.intf.IF_ScreenerSrvClient;
import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.ui.pojos.TY_DestinationsSuffix;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CL_ScreenerSrvClient implements IF_ScreenerSrvClient
{

    private final TY_DestinationsSuffix dS;
    private final IF_UserSessionSrv userSessionSrv;

    @Override
    public boolean updateData4Scrips(List<String> scrips) throws Exception
    {
        boolean scUpdated = true;

        if (!CollectionUtils.isEmpty(scrips) && dS != null && userSessionSrv != null)
        {
            String bearer = userSessionSrv.getDecryptedKey();

            HttpResponse response = null;
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();

            if (StringUtils.hasText(bearer) && StringUtils.hasText(dS.getBaseurl())
                    && StringUtils.hasText(dS.getScreenerloadselscrips()))
            {
                String pfUploadUrl = dS.getBaseurl() + dS.getScreenerloadselscrips()
                        + userSessionSrv.getScreenerToken();
                if (StringUtils.hasText(pfUploadUrl))
                {
                    URL url = new URL(pfUploadUrl);
                    URI uri = new URI(url.getProtocol(), url.getUserInfo(), IDN.toASCII(url.getHost()), url.getPort(),
                            url.getPath(), url.getQuery(), url.getRef());
                    String correctEncodedURL = uri.toASCIIString();

                    try
                    {
                        HttpPost httpPost = new HttpPost(correctEncodedURL);
                        httpPost.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearer);
                        httpPost.addHeader("Content-Type", "application/json");

                        StringEntity entity = new StringEntity(new ObjectMapper().writeValueAsString(scrips),
                                ContentType.APPLICATION_JSON);

                        httpPost.setEntity(entity);

                        // Fire the Url
                        response = httpClient.execute(httpPost);
                        int statusCodeWLDB = response.getStatusLine().getStatusCode();
                        if (statusCodeWLDB != org.apache.http.HttpStatus.SC_OK)
                        {
                            scUpdated = false;
                            log.info("Scrip Update failed.... for Scrips : " + scrips);
                            log.info("Status Code : " + statusCodeWLDB);
                        }

                        else if (statusCodeWLDB == org.apache.http.HttpStatus.SC_OK)
                        {
                            log.info("Scrip Update successful.... for Scrips : " + scrips);
                            scUpdated = true;

                        }

                    }
                    catch (Exception e)
                    {
                        log.info("Scrip Update failed.... for Scrips : " + scrips);
                        e.printStackTrace();
                    }
                    finally
                    {
                        if (httpClient != null)
                        {
                            httpClient.close();
                        }
                    }

                }
            }
        }

        return scUpdated;
    }

}
