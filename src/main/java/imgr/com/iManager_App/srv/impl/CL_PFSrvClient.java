package imgr.com.iManager_App.srv.impl;

import java.io.IOException;
import java.net.IDN;
import java.net.URI;
import java.net.URL;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import imgr.com.iManager_App.srv.intf.IF_PFSrvClient;
import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.srv.intf.IF_WatchlistSrvClient;
import imgr.com.iManager_App.ui.constants.GC_Constants;
import imgr.com.iManager_App.ui.enums.EnumAllocations;
import imgr.com.iManager_App.ui.pojos.CSVPF;
import imgr.com.iManager_App.ui.pojos.TY_ConsolPF;
import imgr.com.iManager_App.ui.pojos.TY_ConsolPFWLItem;
import imgr.com.iManager_App.ui.pojos.TY_DestinationsSuffix;
import imgr.com.iManager_App.ui.pojos.TY_PF;
import imgr.com.iManager_App.ui.pojos.TY_PFItem;
import imgr.com.iManager_App.ui.pojos.TY_WLDB;
import imgr.com.iManager_App.utilities.CSV2POJOUtility;
import imgr.com.iManager_App.utilities.StringsUtility;
import imgr.com.iManager_App.utilities.UtilDecimaltoMoneyString;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CL_PFSrvClient implements IF_PFSrvClient
{

    private final TY_DestinationsSuffix dS;

    private final IF_WatchlistSrvClient wlSrvClient;

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

    @Override
    public boolean updateUserPortfolio(MultipartFile file) throws Exception
    {
        boolean isUpdated = false;
        if (file != null && userSessionSrv != null && dS != null)
        {
            log.info("file uploaded successfully at PF Client Service: " + file.getOriginalFilename());

            List<CSVPF> csvPF = CSV2POJOUtility.convertToModel(file, CSVPF.class);
            if (csvPF != null)
            {
                if (CollectionUtils.isNotEmpty(csvPF))
                {

                    HttpResponse response = null;
                    String bearer = null;
                    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                    bearer = userSessionSrv.getDecryptedKey();
                    if (StringUtils.hasText(bearer) && StringUtils.hasText(dS.getBaseurl())
                            && StringUtils.hasText(dS.getPfUpload()))
                    {
                        String pfUploadUrl = dS.getBaseurl() + dS.getPfUpload()
                                + userSessionSrv.getUserDetails().getUsername();
                        if (StringUtils.hasText(pfUploadUrl))
                        {
                            URL url = new URL(pfUploadUrl);
                            URI uri = new URI(url.getProtocol(), url.getUserInfo(), IDN.toASCII(url.getHost()),
                                    url.getPort(), url.getPath(), url.getQuery(), url.getRef());
                            String correctEncodedURL = uri.toASCIIString();

                            try
                            {
                                HttpPost httpPost = new HttpPost(correctEncodedURL);
                                httpPost.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearer);
                                httpPost.addHeader("Content-Type", "application/json");

                                ObjectMapper objMapper = new ObjectMapper();
                                String requestBody = objMapper.writeValueAsString(csvPF);
                                StringEntity entity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
                                httpPost.setEntity(entity);

                                // Fire the Url
                                response = httpClient.execute(httpPost);
                                int statusCodeWLDB = response.getStatusLine().getStatusCode();
                                if (statusCodeWLDB != org.apache.http.HttpStatus.SC_OK)
                                {
                                    isUpdated = false;
                                    log.info("Portfolio Update failed.... for User : "
                                            + userSessionSrv.getUserDetails().getUsername());
                                    log.info("Status Code : " + statusCodeWLDB);
                                }

                                else if (statusCodeWLDB == org.apache.http.HttpStatus.SC_OK)
                                {
                                    log.info("Portfolio Update succ executed.... for User : "
                                            + userSessionSrv.getUserDetails().getUsername());
                                    isUpdated = true;

                                }
                            }
                            catch (Exception e)
                            {
                                log.info("PF Upload failed.... for User : "
                                        + userSessionSrv.getUserDetails().getUsername());
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
            }

            else
            {
                log.info("PF Upload failed.... for User : " + userSessionSrv.getUserDetails().getUsername());
            }

        }

        return isUpdated;
    }

    @Override
    public TY_ConsolPF getConsolidatedPF(String token, boolean refresh) throws Exception
    {
        TY_ConsolPF consolPF = null;
        if (StringUtils.hasText(token) && userSessionSrv != null)
        {
            if (!refresh)
            {
                if (userSessionSrv.getPFWLConsol() != null)
                {
                    return userSessionSrv.getPFWLConsol();
                }

                if (CollectionUtils.isNotEmpty(userSessionSrv.getUserSessionInformation().getWlDBList())
                        && userSessionSrv.getUserSessionInformation().getUserPF() != null)
                {
                    consolPF = prepareConsolidatedPF(userSessionSrv.getUserSessionInformation().getWlDBList(),
                            userSessionSrv.getUserSessionInformation().getUserPF());
                }
                else
                {
                    if (userSessionSrv.getUserSessionInformation().getUserPF() == null)
                    {
                        userSessionSrv.setUserPF(getPortfolioDetails4User(token));
                    }
                    if (CollectionUtils.isEmpty(userSessionSrv.getUserSessionInformation().getWlDBList()))
                    {
                        userSessionSrv.setWLDB(wlSrvClient.getWatchlistDb(token));
                    }
                    consolPF = prepareConsolidatedPF(userSessionSrv.getUserSessionInformation().getWlDBList(),
                            userSessionSrv.getUserSessionInformation().getUserPF());
                }

            }
            else
            {
                userSessionSrv.setUserPF(getPortfolioDetails4User(token));
                userSessionSrv.setWLDB(wlSrvClient.getWatchlistDb(token));
                consolPF = prepareConsolidatedPF(userSessionSrv.getUserSessionInformation().getWlDBList(),
                        userSessionSrv.getUserSessionInformation().getUserPF());
            }
        }

        return consolPF;
    }

    private TY_ConsolPF prepareConsolidatedPF(List<TY_WLDB> wlDBList, TY_PF userPF)
    {
        TY_ConsolPF pfWLConsol = new TY_ConsolPF();
        if (wlDBList != null && userPF != null)
        {
            pfWLConsol.setPfHeader(userPF.getPfHeader());

            int maxsize = userPF.getPfItems().size();
            int i = 1;
            for (TY_PFItem pfItem : userPF.getPfItems())
            {
                log.info("Scanning " + i + " of " + maxsize);
                i++;
                TY_ConsolPFWLItem pfConItem = new TY_ConsolPFWLItem();
                TY_WLDB wlDB = wlDBList.stream().filter(wl -> wl.getScrip().equalsIgnoreCase(pfItem.getScrip()))
                        .findFirst().orElse(null);
                if (wlDB != null)
                {
                    pfConItem.setScrip(wlDB.getScrip());
                    pfConItem.setRating(wlDB.getRating());
                    pfConItem.setAvgReturns(wlDB.getAvgReturns());
                    pfConItem.setErr(wlDB.getErr());
                    pfConItem.setDailyPL(pfItem.getDaysChgD());
                    pfConItem.setAlloc(wlDB.getSize());
                    pfConItem.setInvestmentsM(pfItem.getInvM());
                    pfConItem.setPerInvPF(pfItem.getPerByInv());
                    pfConItem.setCurrValM(pfItem.getCurValM());
                    pfConItem.setPerCMPPF(pfItem.getPerByCurVal());

                    if (pfItem.getCurVal() < pfItem.getInv())
                    {
                        if (Double.compare(wlDB.getSize(), pfItem.getPerByInv()) > 0)
                        {
                            pfConItem.setAllocStatus(EnumAllocations.UNDER.toString());
                        }
                        else
                        {
                            pfConItem.setAllocStatus(EnumAllocations.OVER.toString());
                        }

                        double deltaallocAmnt = Math.abs(wlDB.getSize() - pfItem.getPerByInv())
                                * userPF.getPfHeader().getTotalInvestment() * .01;
                        String amntDelta = UtilDecimaltoMoneyString.getMoneyStringforDecimal(deltaallocAmnt, 1);
                        if (pfConItem.getAllocStatus().equalsIgnoreCase(EnumAllocations.OVER.toString()))
                        {
                            amntDelta = "-" + amntDelta;
                        }
                        pfConItem.setDeltaallocAmntM(amntDelta);
                    }
                    else
                    {
                        if (Double.compare(wlDB.getSize(), pfItem.getPerByCurVal()) > 0)
                        {
                            pfConItem.setAllocStatus(EnumAllocations.UNDER.toString());
                        }
                        else
                        {
                            pfConItem.setAllocStatus(EnumAllocations.OVER.toString());
                        }

                        double deltaallocAmnt = Math.abs(wlDB.getSize() - pfItem.getPerByCurVal())
                                * userPF.getPfHeader().getTotalInvestment() * .01;
                        String amntDelta = UtilDecimaltoMoneyString.getMoneyStringforDecimal(deltaallocAmnt, 1);
                        if (pfConItem.getAllocStatus().equalsIgnoreCase(EnumAllocations.OVER.toString()))
                        {
                            amntDelta = "-" + amntDelta;
                        }
                        pfConItem.setDeltaallocAmntM(amntDelta);
                    }

                    pfConItem.setNettPLPer(pfItem.getPlPer());
                    pfConItem.setNettPLAbsM(pfItem.getPlAbsM());
                    pfConItem.setNotesUrl(wlDB.getNotesLink());

                    pfWLConsol.getPfItems().add(pfConItem);
                }

            }
        }
        return pfWLConsol;
    }

}
