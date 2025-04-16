package imgr.com.iManager_App.ui.pojos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_WLEligibleScrips
{
    private List<String> inactiveScrips = new ArrayList<String>();
    private List<String> eligibleScrips = new ArrayList<String>();
}
