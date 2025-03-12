package imgr.com.iManager_App.ui.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TY_PFHeader
{
    private String user;
    private double totalInvestment;
    private String totalInvestmentM;
    private double totalCurrentValue;
    private String totalCurrentValueM;
    private double totalPL;
    private String totalPLM;
    private double totalPLPer;
    private double dayPL;
    private String dayPLM;
    private double dayPLPer;
}
