package imgr.com.iManager_App.ui.pojos;

import imgr.com.iManager_App.ui.enums.EnumWLPart;
import imgr.com.iManager_App.ui.enums.EnumWLPartOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TY_WLPartRequest
{
    private String scrip;
    private EnumWLPart entityName;
    private EnumWLPartOperation operation;
    private EN_SCReferences reference;
    private EN_SCTriggers trigger;
}