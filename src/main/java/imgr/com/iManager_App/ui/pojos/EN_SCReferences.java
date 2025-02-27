package imgr.com.iManager_App.ui.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EN_SCReferences
{

    private long linkid;
    private EN_Watchlist watchlistref;
    private String urllink;
    private String title; // EQ, BE and BSE - for only BSE Scrips added manually
}
