package imgr.com.iManager_App.ui.pojos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_ScripCMPResponse
{
    private List<TY_SCCMP> scCMPs = new ArrayList<TY_SCCMP>();
    private List<String> invalidScrips = new ArrayList<String>();

}