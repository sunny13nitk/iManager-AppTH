package imgr.com.iManager_App.ui.pojos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_Cases
{
    private String caseid;
    private String status;
    private String subject;
    private List<TY_KeyValueDouble> exitKeyVals = new ArrayList<TY_KeyValueDouble>();

}
