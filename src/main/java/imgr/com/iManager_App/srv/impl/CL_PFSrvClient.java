package imgr.com.iManager_App.srv.impl;

import java.io.IOException;
import java.net.IDN;
import java.net.URI;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import imgr.com.iManager_App.exceptions.EX_UserSession;
import imgr.com.iManager_App.srv.intf.IF_PFSrvClient;
import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.srv.intf.IF_WatchlistSrvClient;
import imgr.com.iManager_App.ui.constants.GC_Constants;
import imgr.com.iManager_App.ui.enums.EnumAllocations;
import imgr.com.iManager_App.ui.pojos.CSVPF;
import imgr.com.iManager_App.ui.pojos.EN_Watchlist;
import imgr.com.iManager_App.ui.pojos.TY_ConsolPF;
import imgr.com.iManager_App.ui.pojos.TY_ConsolPFWLItem;
import imgr.com.iManager_App.ui.pojos.TY_DestinationsSuffix;
import imgr.com.iManager_App.ui.pojos.TY_OppcostPFReport;
import imgr.com.iManager_App.ui.pojos.TY_PF;
import imgr.com.iManager_App.ui.pojos.TY_PFItem;
import imgr.com.iManager_App.ui.pojos.TY_PFSubs;
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

    @Override
    public TY_OppcostPFReport getOppCostPFReport(String token, boolean refresh) throws Exception
    {

        TY_OppcostPFReport oppCostReport = null;
        if (userSessionSrv != null)
        {
            // Mandatory to have User Session Watchlist and Watchlist Entitities details
            // loaded
            if (CollectionUtils.isNotEmpty(userSessionSrv.getUserSessionInformation().getWlEntities())
                    && CollectionUtils.isNotEmpty(userSessionSrv.getUserSessionInformation().getWlDBList()))
            {
                oppCostReport = processOppCostReport(token, oppCostReport);
            }

            else
            {

                userSessionSrv.setWLDB(wlSrvClient.getWatchlistDb(token));
                userSessionSrv.setWLFundamentals(wlSrvClient.getWLFundamentalAnalysis());
                userSessionSrv.setWLThesis(wlSrvClient.getWatchlistThesis());
                oppCostReport = processOppCostReport(token, oppCostReport);
            }
        }

        return oppCostReport;

    }

    private TY_OppcostPFReport processOppCostReport(String token, TY_OppcostPFReport oppCostReport)
    {
        TY_ConsolPF pf;
        try
        {
            pf = userSessionSrv.getPFWLConsol();
            if (pf == null)
            {
                pf = this.getConsolidatedPF(token, true);
                userSessionSrv.setPFWLConsol(pf);
            }

            if (pf != null)
            {
                oppCostReport = new TY_OppcostPFReport();
                log.info("Portfolio bound.. for user");
                pf.getPfItems().sort(Comparator.comparing(TY_ConsolPFWLItem::getAvgReturns));
                // Get Under Allocations Scrip[s]
                List<TY_ConsolPFWLItem> reversedSortedPFItems = pf.getPfItems().stream()
                        .filter(e -> e.getAllocStatus().equalsIgnoreCase(EnumAllocations.UNDER.toString()))
                        .collect(Collectors.toList());
                // Sort by Avg Returns in descending order
                reversedSortedPFItems.stream().sorted(Comparator.comparing(TY_ConsolPFWLItem::getAvgReturns).reversed())
                        .collect(Collectors.toList());
                for (TY_ConsolPFWLItem pfwlItem : pf.getPfItems())
                {
                    // Get Avg returns of Each Scrip in PF and add the threshold needed to replace
                    if (pfwlItem.getAvgReturns() < GC_Constants.replRetThreshold)
                    {

                        double avgReturns2CMP = pfwlItem.getAvgReturns() + GC_Constants.minmRetGap;
                        boolean isEligible = false;

                        // Get Scrips that have higher Avg. Returns
                        List<TY_ConsolPFWLItem> options2Replace = reversedSortedPFItems.stream()
                                .filter(e -> e.getAvgReturns() >= avgReturns2CMP).collect(Collectors.toList());

                        // For Each of replacement candidates
                        for (TY_ConsolPFWLItem pfwlItem2 : options2Replace)
                        {
                            isEligible = true;
                            // Get Watchlist DB details for Span,Conviction, Catg. etc..
                            Optional<EN_Watchlist> wlTHtemO = userSessionSrv.getUserSessionInformation().getWlEntities()
                                    .stream().filter(w -> w.getScrip().equals(pfwlItem2.getScrip())).findFirst();

                            if (wlTHtemO.isPresent())
                            {
                                // Seek if the Scrip is already in Unallocated PF replacements list
                                Optional<TY_PFSubs> pfSubsO = oppCostReport.getPfReplCurrPFUnAlloc().stream()
                                        .filter(s -> s.getScrip().equals(pfwlItem2.getScrip())).findFirst();
                                // In case Not present, add to Unallocated PF replacements list
                                if (!pfSubsO.isPresent())
                                {
                                    oppCostReport.getPfReplCurrPFUnAlloc()
                                            .add(getSubs4PFItem(pfwlItem2, wlTHtemO.get()));
                                }
                            }
                        }

                        // REmove the scrips from WL that are already in PF
                        List<TY_WLDB> wlDBList = userSessionSrv.getUserSessionInformation().getWlDBList();
                        List<TY_ConsolPFWLItem> pfItems = pf.getPfItems();
                        wlDBList = wlDBList.stream()
                                .filter(wl -> pfItems.stream()
                                        .noneMatch(pfItem -> pfItem.getScrip().equalsIgnoreCase(wl.getScrip())))
                                .collect(Collectors.toList());

                        // Now, For Each in Watchlist that have avg returns more than the PF Item
                        // Get from /user session the Watchlist and scan for scrips having returns more
                        // than the PF Item
                        List<TY_WLDB> wloptions2Replace = wlDBList.stream()
                                .filter(e -> e.getAvgReturns() >= avgReturns2CMP).collect(Collectors.toList());
                        // For Each of replacement candidates
                        for (TY_WLDB wlItem : wloptions2Replace)
                        {
                            isEligible = true;
                            // Get Watchlist DB details for Span,Conviction, Catg. etc..
                            Optional<EN_Watchlist> wlTHtemO = userSessionSrv.getUserSessionInformation().getWlEntities()
                                    .stream().filter(w -> w.getScrip().equals(wlItem.getScrip())).findFirst();
                            // Seek if the Scrip is already in Undeployed WL replacements list
                            Optional<TY_PFSubs> pfSubsO = oppCostReport.getPfReplWLUndeployed().stream()
                                    .filter(s -> s.getScrip().equals(wlItem.getScrip())).findFirst();
                            // In case Not present, add to Undeployed WL replacements list
                            if (!pfSubsO.isPresent())
                            {
                                oppCostReport.getPfReplWLUndeployed().add(
                                        getSubs4PFItem(wlItem, wlTHtemO.get(), pf.getPfHeader().getTotalInvestment()));
                            }
                        }

                        if (isEligible)
                        {
                            Optional<EN_Watchlist> wlTHtemO = userSessionSrv.getUserSessionInformation().getWlEntities()
                                    .stream().filter(w -> w.getScrip().equals(pfwlItem.getScrip())).findFirst();

                            if (wlTHtemO.isPresent())
                            {
                                oppCostReport.getPfRCandidates().add(getSubs4PFItem(pfwlItem, wlTHtemO.get()));
                            }
                        }

                    }
                }

            }
        }
        catch (Exception e)
        {
            throw new EX_UserSession(e.getLocalizedMessage());
        }
        return oppCostReport;
    }

    private TY_PFSubs getSubs4PFItem(TY_WLDB wlItem, EN_Watchlist wlEnt, double totalInv)
    {
        TY_PFSubs pfSubs = new TY_PFSubs();
        pfSubs.setScrip(wlItem.getScrip());
        pfSubs.setRating(wlItem.getRating());
        pfSubs.setAvgReturns(wlItem.getAvgReturns());
        pfSubs.setErr(wlItem.getErr());
        pfSubs.setConviction(wlEnt.getConviction().toString());
        pfSubs.setSpan(wlEnt.getLongevitygr());
        pfSubs.setGrowth(wlEnt.getGrowth().toString());
        pfSubs.setCusSegment(wlEnt.getCusSegment().toString());
        pfSubs.setAlloc(wlEnt.getSize());
        pfSubs.setInvestmentsM(GC_Constants.gc_NA);
        pfSubs.setPerInvPF(0);
        pfSubs.setCurrValM(GC_Constants.gc_NA);
        pfSubs.setPerCMPPF(0);
        pfSubs.setAllocStatus(EnumAllocations.EQUI.toString());
        pfSubs.setDeltaallocAmntM(
                UtilDecimaltoMoneyString.getMoneyStringforDecimal(totalInv * wlEnt.getSize() * .01, 0));
        pfSubs.setNettPLPer(0);
        pfSubs.setNettPLAbsM(GC_Constants.gc_NA);

        return pfSubs;
    }

    private TY_PFSubs getSubs4PFItem(TY_ConsolPFWLItem pfwlItem, EN_Watchlist wlEnt)
    {
        TY_PFSubs pfSubs = new TY_PFSubs();
        pfSubs.setScrip(pfwlItem.getScrip());
        pfSubs.setRating(pfwlItem.getRating());
        pfSubs.setAvgReturns(pfwlItem.getAvgReturns());
        pfSubs.setErr(pfwlItem.getErr());
        pfSubs.setConviction(wlEnt.getConviction().toString());
        pfSubs.setSpan(wlEnt.getLongevitygr());
        pfSubs.setGrowth(wlEnt.getGrowth().toString());
        pfSubs.setCusSegment(wlEnt.getCusSegment().toString());
        pfSubs.setAlloc(pfwlItem.getAlloc());
        pfSubs.setInvestmentsM(pfwlItem.getInvestmentsM());
        pfSubs.setPerInvPF(pfwlItem.getPerInvPF());
        pfSubs.setCurrValM(pfwlItem.getCurrValM());
        pfSubs.setPerCMPPF(pfwlItem.getPerCMPPF());
        pfSubs.setAllocStatus(pfwlItem.getAllocStatus());
        pfSubs.setDeltaallocAmntM(pfwlItem.getDeltaallocAmntM());
        pfSubs.setNettPLPer(pfwlItem.getNettPLPer());
        pfSubs.setNettPLAbsM(pfwlItem.getNettPLAbsM());

        return pfSubs;
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
