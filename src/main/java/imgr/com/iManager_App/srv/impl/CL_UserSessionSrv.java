package imgr.com.iManager_App.srv.impl;

import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import imgr.com.iManager_App.exceptions.EX_UnauthorizedLogin;
import imgr.com.iManager_App.exceptions.EX_UserSession;
import imgr.com.iManager_App.srv.intf.IF_APIClient;
import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.srv.pojos.TY_Login;
import imgr.com.iManager_App.srv.pojos.TY_SCToken;
import imgr.com.iManager_App.srv.pojos.TY_UserSessionInfo;
import imgr.com.iManager_App.ui.constants.GC_Constants;
import imgr.com.iManager_App.ui.constants.VWNamesDirectory;
import imgr.com.iManager_App.ui.enums.EnumVWNames;
import imgr.com.iManager_App.ui.pojos.CUS_StgyBean;
import imgr.com.iManager_App.ui.pojos.EN_Watchlist;
import imgr.com.iManager_App.ui.pojos.TY_ConsolPF;
import imgr.com.iManager_App.ui.pojos.TY_Credentials;
import imgr.com.iManager_App.ui.pojos.TY_DestinationsSuffix;
import imgr.com.iManager_App.ui.pojos.TY_PF;
import imgr.com.iManager_App.ui.pojos.TY_PFItem;
import imgr.com.iManager_App.ui.pojos.TY_ScripAnalysisData;
import imgr.com.iManager_App.ui.pojos.TY_UserRole;
import imgr.com.iManager_App.ui.pojos.TY_WLDB;
import imgr.com.iManager_App.utilities.EncryptUtility;
import imgr.com.iManager_App.utilities.JSONUtility;
import imgr.com.iManager_App.utilities.StringsUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@SessionScope
@RequiredArgsConstructor
@Slf4j
public class CL_UserSessionSrv implements IF_UserSessionSrv
{

    private final CL_APIClient CL_APIClient;

    private TY_UserSessionInfo userInfo;

    private final TY_DestinationsSuffix dS;

    private final IF_APIClient authSrv;

    private final MessageSource msgSrc;

    @Override
    public void initialize(TY_Login login) throws Exception
    {
        if (login != null)
        {
            if (StringUtils.hasText(login.getUsername()) && StringUtils.hasText(login.getPassword()))
            {
                userInfo = new TY_UserSessionInfo();
                // First Get the auth Token for the User
                try
                {
                    String token = authSrv.getAuthToken(new TY_Credentials(login.getUsername(), login.getPassword()));
                    if (StringUtils.hasText(token))
                    {
                        userInfo.setUserName(login.getUsername());
                        userInfo.setKey(EncryptUtility.generateKey(128));
                        userInfo.setIvParameterSpec(EncryptUtility.generateIv());
                        this.encryptSessionKey(token); // encrypt and save the Bearer Token in session
                        Base64.Decoder decoder = Base64.getUrlDecoder();
                        String[] chunks = token.split("\\.");
                        String payload = new String(decoder.decode(chunks[1]));
                        String scope = JSONUtility.readPropertyValue(payload, "/scope");
                        this.setLoggedinUserRole(scope);
                        // Initialize Screener Token
                        authSrv.initializeScreenerToken();

                    }
                }
                catch (Exception e)
                {
                    throw new EX_UnauthorizedLogin(msgSrc.getMessage("ERR_INVALID_LOGIN", null, Locale.getDefault()));
                }

            }

        }
    }

    @Override
    public TY_UserSessionInfo getUserSessionInformation()
    {
        return this.userInfo;
    }

    @Override
    public void resetScreenerToken(TY_SCToken token)
    {
        if (token != null)
        {
            userInfo.setScToken(token);
        }
    }

    @Override
    public String getScreenerToken()
    {
        String token = null;
        if (dS != null)
        {
            if (StringUtils.hasText(dS.getCsrfsess()) && this.userInfo.getScToken() != null)
            {
                try
                {
                    token = StringsUtility.replaceURLwithParams(dS.getCsrfsess(), new String[]
                    { this.getUserSessionInformation().getScToken().getToken(),
                            this.getUserSessionInformation().getScToken().getSessionid() }, GC_Constants.gc_Repl);
                }
                catch (Exception e)
                {
                    throw new EX_UserSession(msgSrc.getMessage("ERR_DES", new Object[]
                    { "csrfsess", e.getLocalizedMessage() }, Locale.getDefault()));
                }
            }
        }
        return token;
    }

    @Override
    public void setWLDB(List<TY_WLDB> wlDbList)
    {
        if (CollectionUtils.isNotEmpty(wlDbList))
        {
            this.userInfo.setWlDBList(wlDbList);
        }
    }

    @Override
    public List<TY_WLDB> getWlDB()
    {
        return userInfo.getWlDBList();
    }

    @Override
    public void encryptSessionKey(String toencrypt) throws Exception
    {
        // Only once per session
        if (StringUtils.hasText(toencrypt))
        {
            userInfo.setBearer(EncryptUtility.encrypt(GC_Constants.algorithm, toencrypt, userInfo.getKey(),
                    userInfo.getIvParameterSpec()));

        }
    }

    @Override
    public String getDecryptedKey() throws Exception
    {
        String key = null;
        if (StringUtils.hasText(userInfo.getBearer()))
        {
            key = EncryptUtility.decrypt(GC_Constants.algorithm, userInfo.getBearer(), userInfo.getKey(),
                    userInfo.getIvParameterSpec());
        }
        return key;
    }

    @Override
    public void setParentView4Navigation(EnumVWNames vwName)
    {
        userInfo.setVwName(vwName);
    }

    @Override
    public String getRedirectedParentView()
    {
        String redVW = VWNamesDirectory.getViewName(userInfo.getVwName(), true);
        userInfo.setVwName(null);
        return redVW;
    }

    @Override
    public void setAccessBearer(String bearer)
    {
        if (StringUtils.hasText(bearer))
        {
            userInfo.setBearer(bearer);
        }
    }

    @Override
    public void setParentViewModel4Navigation(ModelAndView mv)
    {
        userInfo.setMv(mv);
    }

    @Override
    public ModelAndView getRedirectedParentViewModel()
    {
        return userInfo.getMv();
    }

    @Override
    public void setWLFundamentals(List<TY_ScripAnalysisData> wlFundamentals)
    {
        userInfo.setWlFInfo(wlFundamentals);
    }

    @Override
    public void setWLThesis(List<EN_Watchlist> wlThesis)
    {
        userInfo.setWlEntities(wlThesis);
    }

    @Override
    public void setLoggedinUserRole(String role)
    {
        userInfo.setRole(role);
    }

    @Override
    public String getLoggedinUserRole()
    {
        return userInfo.getRole();
    }

    @Override
    public TY_UserRole getUserDetails()
    {
        return new TY_UserRole(this.getUserSessionInformation().getUserName(), getLoggedinUserRole());
    }

    @Override
    public void setUserPF(TY_PF userPF)
    {
        this.userInfo.setUserPF(userPF);
    }

    @Override
    public TY_PFItem getPFScan4Scrip(String scrip)
    {
        TY_PFItem pfItem = null;
        if (StringUtils.hasText(scrip))
        {
            if (userInfo.getUserPF() != null)
            {
                if (CollectionUtils.isNotEmpty(userInfo.getUserPF().getPfItems()))
                {
                    Optional<TY_PFItem> pfItemO = userInfo.getUserPF().getPfItems().stream()
                            .filter(i -> i.getScrip().toLowerCase().equals(scrip.toLowerCase())).findFirst();
                    if (pfItemO.isPresent())
                    {
                        pfItem = pfItemO.get();

                    }

                }
            }

        }

        return pfItem;
    }

    @Override
    public void add2CurrScrip(String scrip)
    {
        userInfo.setCurrScrip(scrip);
    }

    @Override
    public String getCurrScrip()
    {
        return userInfo.getCurrScrip();
    }

    @Override
    public void setStrategies(List<CUS_StgyBean> strategies)
    {
        this.userInfo.setStrategies(strategies);
    }

    @Override
    public List<CUS_StgyBean> getStrategies()
    {
        return this.userInfo.getStrategies();
    }

    @Override
    public void setPFWLConsol(TY_ConsolPF pfWLConsol)
    {
        this.userInfo.setPfWLConsol(pfWLConsol);
    }

    @Override
    public TY_ConsolPF getPFWLConsol()
    {
        return this.userInfo.getPfWLConsol();
    }

    @Override
    public boolean setMV(ModelAndView mv)
    {
        boolean isSet = false;
        if (mv != null)
        {
            if (mv.getModel() != null)
            {
                this.userInfo.setMv(mv);
                isSet = true;
            }

        }
        return isSet;
    }

    @Override
    public ModelAndView getMV()
    {
        return this.userInfo.getMv();
    }

}
