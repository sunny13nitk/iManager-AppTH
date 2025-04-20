package imgr.com.iManager_App.ui.pojos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_HC_Results
{
    private List<TY_HC_Scrip> scResults = new ArrayList<TY_HC_Scrip>();
    private Map<String, String> errors = new HashMap<String, String>();
}
