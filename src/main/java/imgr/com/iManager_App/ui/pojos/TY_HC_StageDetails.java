package imgr.com.iManager_App.ui.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_HC_StageDetails
{
    private boolean pass;
    private String attrbName;
    private Object valBase;
    private Object valActual;
}
