package imgr.com.iManager_App.ui.pojos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TY_OppcostPFReport
{
    private List<TY_PFSubs> pfRCandidates = new ArrayList<TY_PFSubs>();
    private List<TY_PFSubs> pfReplCurrPFUnAlloc = new ArrayList<TY_PFSubs>();;
    private List<TY_PFSubs> pfReplWLUndeployed = new ArrayList<TY_PFSubs>();;
}
