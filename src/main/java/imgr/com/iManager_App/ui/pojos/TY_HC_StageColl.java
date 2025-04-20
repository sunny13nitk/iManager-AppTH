package imgr.com.iManager_App.ui.pojos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_HC_StageColl
{
    private String stageName;
    private boolean pass;
    private List<TY_HC_StageDetails> stageDetails = new ArrayList<TY_HC_StageDetails>();
}