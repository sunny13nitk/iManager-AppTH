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

import imgr.com.iManager_App.srv.intf.IF_StrategiesSrvClient;
import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.ui.pojos.CUS_StgyBean;
import imgr.com.iManager_App.ui.pojos.IF_ScripAnalysisData;
import imgr.com.iManager_App.ui.pojos.TY_DestinationsSuffix;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CL_StrategiesSrvClient implements IF_StrategiesSrvClient
{

    private final TY_DestinationsSuffix dS;

    private final IF_UserSessionSrv userSessionSrv;

    @Override
    public List<CUS_StgyBean> getUnstagedStrategies() throws Exception
    {
        List<CUS_StgyBean> unstagedStrategies = null;
        HttpResponse response = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        if (dS != null && userSessionSrv != null)
        {
            String bearer = userSessionSrv.getDecryptedKey();
            String hcUrl = dS.getBaseurl() + dS.getStgyunstaged();

            if (StringUtils.hasText(bearer) && StringUtils.hasText(hcUrl))
            {
                // Now let's trigger the WL DB Call
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
                    log.info(" Unstaged Strategies obtained ....");
                    HttpEntity entityWLDB = response.getEntity();
                    String apiOutput = EntityUtils.toString(entityWLDB);
                    ObjectMapper objectMapper = new ObjectMapper();
                    try
                    {

                        TypeFactory typeFactory = objectMapper.getTypeFactory();
                        CollectionType collectionType = typeFactory.constructCollectionType(List.class,
                                CUS_StgyBean.class);
                        unstagedStrategies = objectMapper.readValue(apiOutput, collectionType);

                        log.info("# Unstaged Strategies obtained :  " + unstagedStrategies.size());

                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
            }

        }

        return unstagedStrategies;
    }

    @Override
    public List<IF_ScripAnalysisData> executeStrategy(String strategyName) throws Exception
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executeStrategy'");
    }

}
