package imgr.com.iManager_App.ui.pojos;

import imgr.com.iManager_App.ui.enums.EnumTrigger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor

public class EN_SCTriggers
{

    private EN_Watchlist triggerref;

    private EnumTrigger triggertype;
    private String details;
}