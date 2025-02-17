package imgr.com.iManager_App.ui.constants;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.util.StringUtils;

import imgr.com.iManager_App.ui.enums.EnumVWNames;
import imgr.com.iManager_App.ui.pojos.TY_ViewMappings;

public class VWNamesDirectory
{

    public static final String gc_redirect = "redirect:";

    public static ArrayList<TY_ViewMappings> getViewsMap()
    {
        ArrayList<TY_ViewMappings> viewsDescMap = new ArrayList<TY_ViewMappings>();

        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.Home, "/", "homeM"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.Principal, "/pp", "pp"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.Login, "/login", "loginbs"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.Tokens, "/wl/token", "tokens"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.WatchlistDashboard, "/wl/db", "watchlistdashboard"));

        return viewsDescMap;
    }

    public static String getViewName(EnumVWNames vwTag, boolean isRedirect)
    {
        String vwName = null;
        if (StringUtils.hasText(vwTag.toString()))
        {
            Optional<TY_ViewMappings> vwO = getViewsMap().stream()
                    .filter(v -> v.getVwTag().toString().equals(vwTag.toString())).findFirst();
            {
                if (vwO.isPresent())
                {
                    if (isRedirect)
                    {
                        vwName = gc_redirect + vwO.get().getPath();
                    }
                    else
                    {
                        vwName = vwO.get().getVwName();
                    }
                }
            }

        }

        return vwName;
    }
}
