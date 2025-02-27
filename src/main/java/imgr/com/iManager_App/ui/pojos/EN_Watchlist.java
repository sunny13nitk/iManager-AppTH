package imgr.com.iManager_App.ui.pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Range;

import imgr.com.iManager_App.ui.enums.EnumCS;
import imgr.com.iManager_App.ui.enums.EnumLevels;
import imgr.com.iManager_App.ui.enums.EnumWLStatus;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EN_Watchlist
{

    private String scrip;
    private boolean standalone;
    @PastOrPresent
    private Date dateIncl;
    @PastOrPresent
    private Date dateUpdated;
    private EnumWLStatus status;
    private List<EN_SCReferences> references = new ArrayList<EN_SCReferences>();
    private List<EN_SCTriggers> triggers = new ArrayList<EN_SCTriggers>();

    private EnumCS cusSegment;
    private int size;
    private EnumLevels growth;
    private EnumLevels conviction;
    @Range(max = 10, min = 0)
    private int longevitygr; // Longevity of Growth CAGR in years
    @Range(max = 100, min = 0)
    private double fwdepsgrbase;
    @Range(max = 100, min = 0)
    private double fwdpebase;
    @Range(max = 100, min = 0)
    private double fwdepsgrbull;
    @Range(max = 100, min = 0)
    private double fwdpebull;
    @Range(max = 1, min = 0)
    private double bullWtRatio;
    private List<String> tags = new ArrayList<String>();

}
