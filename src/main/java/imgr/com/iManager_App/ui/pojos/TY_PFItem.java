package imgr.com.iManager_App.ui.pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TY_PFItem
{
    private String scrip;
    private double units;
    private double ppu;
    private double cmp;
    private String daysChg;
    private double daysChgD;
    private double inv;
    private String invM;
    private double perByInv;
    private double curVal;
    private String curValM;
    private double perByCurVal;
    private double plPer;
    private double plAbs;
    private String plAbsM;
    private double plPerDay;
    private String plPerDayM;
    private String notesUrl;
}
