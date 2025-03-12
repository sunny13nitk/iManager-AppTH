package imgr.com.iManager_App.ui.pojos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_PF
{
    private TY_PFHeader pfHeader;
    private List<TY_PFItem> pfItems = new ArrayList<TY_PFItem>();
}