package imgr.com.iManager_App.ui.pojos;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = CL_ScripAnalysisData.class)
public interface IF_ScripAnalysisData
{
    String getSccode();

    Double getMcapcr();

    Double getYoysalesgr();

    Double getQoqsalesgr();

    Double getSalesgrttm();

    Double getYoypatgr();

    Double getQoqpatgr();

    Double getPatgrttm();

    Double getPatgr3y();

    Double getSalesgr3y();

    Double getRet3m();

    Double getRet6m();

    Double getRet1y();

    Double getRet3y();

    Double getRoe();

    Double getRoce();

    Double getPe3y();

    Double getPe5y();

    Double getPeg();

    Double getNpmttm();

    Double getMcap2sales();

    Double getMcap2sales3y();

    Double getCurrratio();

    Double getIntcovg();

    Double getWc2rev();

    Double getCc();

    Double getDivyield();

    Double getUph();

    Double getChph3y();

    Double getIndpe();

    Double getAtr();

    Double getNpmlastq();

    Double getNpmpyq();

    Double getHigh52w();

    Double getLow52w();

    Double getDe();

    Double getSrvr();

    Double getRetidx();

}
