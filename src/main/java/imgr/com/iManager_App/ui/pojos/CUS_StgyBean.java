package imgr.com.iManager_App.ui.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CUS_StgyBean
{
    private String strategy; // Strategy Name
    private String serviceName; // Actual Service IMPl Mapped
    private String tags; // Tags Describing Strategy
    private boolean staged; // Evaluation consists of multiple Stages
    private String pseudoCode; // Pseudo Code
}
