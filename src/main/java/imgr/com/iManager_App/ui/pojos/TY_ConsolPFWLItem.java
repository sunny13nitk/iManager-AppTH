package imgr.com.iManager_App.ui.pojos;

import imgr.com.iManager_App.ui.enums.EnumAllocations;
import imgr.com.iManager_App.ui.enums.EnumWLRating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_ConsolPFWLItem
{
    private String scrip;
    private EnumWLRating rating; // EnumWLRating
    private double avgReturns;
    private double err;
    private double dailyPL;
    private int alloc;
    private String investmentsS;
    private double perInvPF;
    private String currValS;
    private double perCMPPF;
    private EnumAllocations allocStatus;

}
