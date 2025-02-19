package imgr.com.iManager_App.srv.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.springframework.web.servlet.ModelAndView;

import imgr.com.iManager_App.ui.enums.EnumVWNames;
import imgr.com.iManager_App.ui.model.entity.TY_SCToken;
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
    private TY_SCToken scToken;
    private List<TY_WLDB> wlDBList = new ArrayList<TY_WLDB>();
    private SecretKey key;
    private IvParameterSpec ivParameterSpec;
    private String cipher;
    private EnumVWNames vwName;
    private String bearer;
    private ModelAndView mv;
}
