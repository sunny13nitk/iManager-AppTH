package imgr.com.iManager_App.srv.impl;

import java.util.List;
import java.util.Locale;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.SessionScope;

import imgr.com.iManager_App.exceptions.handler.EX_UserSession;
import imgr.com.iManager_App.srv.intf.IF_UserSessionSrv;
import imgr.com.iManager_App.srv.pojos.TY_UserSessionInfo;
import imgr.com.iManager_App.ui.constants.GC_Constants;
import imgr.com.iManager_App.ui.model.entity.TY_SCToken;
import imgr.com.iManager_App.ui.model.repo.RepoSCToken;
import imgr.com.iManager_App.ui.pojos.TY_DestinationsSuffix;
import imgr.com.iManager_App.ui.pojos.TY_WLDB;
import imgr.com.iManager_App.utilities.EncryptUtility;
import imgr.com.iManager_App.utilities.StringsUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@SessionScope
@RequiredArgsConstructor
@Slf4j
public class CL_UserSessionSrv implements IF_UserSessionSrv
{

    private TY_UserSessionInfo userInfo;

    private final RepoSCToken repoSCToken;

    private final TY_DestinationsSuffix dS;

    private final MessageSource msgSrc;

    @Override
    public void initialize(Authentication auth) throws Exception
    {
        if (auth != null)
        {
            if (auth.isAuthenticated() && repoSCToken != null)
            {
                userInfo = new TY_UserSessionInfo();
                userInfo.setUserName(auth.getName());
                userInfo.setScToken(repoSCToken.findAll().get(0));
                userInfo.setKey(EncryptUtility.generateKey(128));
                userInfo.setIvParameterSpec(EncryptUtility.generateIv());

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
    @PreAuthorize("hasAuthority('ADMIN')")
    public void setWLDB(List<TY_WLDB> wlDbList)
    {
        if (CollectionUtils.isNotEmpty(wlDbList))
        {
            this.userInfo.setWlDBList(wlDbList);
        }
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<TY_WLDB> getWlDB()
    {
        return userInfo.getWlDBList();
    }

    @Override
    public void encryptSessionKey(String toencrypt) throws Exception
    {
        // Only once per session
        if (StringUtils.hasText(toencrypt) && userInfo.getCipher() == null)
        {
            userInfo.setCipher(EncryptUtility.encrypt(GC_Constants.algorithm, toencrypt, userInfo.getKey(),
                    userInfo.getIvParameterSpec()));
            // #Test
            log.info("Cipher Set : " + userInfo.getCipher());
        }
    }

    @Override
    public String getDecryptedKey() throws Exception
    {
        String key = null;
        if (StringUtils.hasText(userInfo.getCipher()))
        {
            EncryptUtility.decrypt(GC_Constants.algorithm, userInfo.getCipher(), userInfo.getKey(),
                    userInfo.getIvParameterSpec());
        }
        return key;
    }

}
