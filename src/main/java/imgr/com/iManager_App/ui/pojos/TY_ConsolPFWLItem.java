package imgr.com.iManager_App.ui.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_ConsolPFWLItem
{
    private String scrip;
    private String rating; // EnumWLRating
    private double avgReturns;
    private double err;
    private double dailyPL;
    private int alloc;
    private String investmentsM;
    private double perInvPF;
    private String currValM;
    private double perCMPPF;
    private String allocStatus;
    private String deltaallocAmntM;
    private double nettPLPer;
    private String nettPLAbsM;
    private String notesUrl;

}
