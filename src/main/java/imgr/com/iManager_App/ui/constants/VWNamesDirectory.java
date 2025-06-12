package imgr.com.iManager_App.ui.constants;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.util.StringUtils;

import imgr.com.iManager_App.ui.enums.EnumVWNames;
import imgr.com.iManager_App.ui.pojos.TY_ViewMappings;

public class VWNamesDirectory
{

    public static final String gc_redirect = "redirect:";
    public static final String gc_paramPattern = "{";
    public static final String gc_paramSplit = "\\{";

    public static ArrayList<TY_ViewMappings> getViewsMap()
    {
        ArrayList<TY_ViewMappings> viewsDescMap = new ArrayList<TY_ViewMappings>();

        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.Home, "/", "homeM"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.Principal, "/pp", "pp"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.Error, null, "error"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.Login, "/login", "loginbs"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.Tokens, "/wl/token", "tokens"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.WatchlistDashboard, "/wl/db", "watchlistdashboard"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.WLHeaderEdit, "/wl/upd/{scrip}", "wlHeaderEdit"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.WLDetailsScreener, "/wl/db/{scrip}", "wlSCDetails"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.WLRefAdd, "/wl/ref/{scrip}", "wlrefAdd"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.TriggerAdd, "/wl/tg/{scrip}", "wltgAdd"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.TagAdd, "/wl/tag/{scrip}", "wltagAdd"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.PortfolioOverview, "/pf/", "pfOvw"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.PFHoldingDetail, "/wl/pfScan{scrip}", "pfDetails"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.UploadPF, "/pf/upload", "pfUpload"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.Add2WL, "/wl/addNew", "wlAddNew"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.ScripsHC, "/utils/hc", "scHC"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.HCSel, "/hc/", "hc"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.StgyList, "/strategies/list", "stgyList"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.StgyResults, "/strategies/exec/{stgyName}", "stgyResults"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.ConsolPF, "/consolwl/", "pfConsOvw"));
        viewsDescMap.add(new TY_ViewMappings(EnumVWNames.OppCost, "/pf/ai", "OppCostVw"));

        return viewsDescMap;
    }

    public static String getViewName(EnumVWNames vwTag, boolean isRedirect, String... params)
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
                        if (vwO.get().getPath().contains(gc_paramPattern))
                        {
                            String[] pathParts = vwO.get().getPath().split(gc_paramSplit);

                            vwName = gc_redirect + pathParts[0];
                            for (int i = 0; i < params.length; i++)
                            {
                                if (params.length > 0)
                                {
                                    vwName = vwName + params[i];
                                }
                            }
                        }
                        else
                        {
                            vwName = gc_redirect + vwO.get().getPath();
                        }

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
