package imgr.com.iManager_App.ui.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_PFSubs
{
    private String scrip;
    private String rating;
    private double avgReturns;
    private double err;
    private String cusSegment;
    private String growth;
    private int span;
    private String conviction;
    private int alloc;
    private String investmentsM;
    private double perInvPF;
    private String currValM;
    private double perCMPPF;
    private String allocStatus;
    private String deltaallocAmntM;
    private double nettPLPer;
    private String nettPLAbsM;

}
