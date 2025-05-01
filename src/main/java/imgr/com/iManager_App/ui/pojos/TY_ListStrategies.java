package imgr.com.iManager_App.ui.pojos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_ListStrategies
{
    private List<CUS_StgyBean> strategies = new ArrayList<CUS_StgyBean>(); // List of Strategies
}
