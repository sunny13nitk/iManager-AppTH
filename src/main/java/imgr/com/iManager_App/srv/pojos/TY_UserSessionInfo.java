package imgr.com.iManager_App.srv.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.springframework.web.servlet.ModelAndView;

import imgr.com.iManager_App.ui.enums.EnumVWNames;
import imgr.com.iManager_App.ui.pojos.EN_Watchlist;
import imgr.com.iManager_App.ui.pojos.TY_PF;
import imgr.com.iManager_App.ui.pojos.TY_ScripAnalysisData;
import imgr.com.iManager_App.ui.pojos.TY_WLDB;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_UserSessionInfo
{
    private String userName;
    private String currScrip;
    private TY_SCToken scToken;
    private List<TY_WLDB> wlDBList = new ArrayList<TY_WLDB>();
    private List<TY_ScripAnalysisData> wlFInfo = new ArrayList<TY_ScripAnalysisData>();
    private List<EN_Watchlist> wlEntities = new ArrayList<EN_Watchlist>();
    private TY_PF userPF;
    private SecretKey key;
    private IvParameterSpec ivParameterSpec;
    private EnumVWNames vwName;
    private String bearer;
    private String role;
    private ModelAndView mv;
}
