package imgr.com.iManager_App.ui.pojos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_ConsolPF
{

    private TY_PFHeader pfHeader;
    private List<TY_ConsolPFWLItem> pfItems = new ArrayList<TY_ConsolPFWLItem>();

}
