package imgr.com.iManager_App.ui.pojos;

import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String urllink;
    @NotNull
    private String title;
}
