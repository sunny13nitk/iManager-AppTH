package imgr.com.iManager_App.ui.pojos;

import imgr.com.iManager_App.ui.enums.EnumVWNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_ViewMappings
{
    private EnumVWNames vwTag;
    private String path;
    private String vwName;
}
