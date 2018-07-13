package jobschedularmanager.jobschedularmanager.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;
/**
 * Created by user on 12/7/18.
 */

public class DashboardSummary {

    @SerializedName("ETQRList")
    private List<ETQRList> eTQRList = new ArrayList<ETQRList>();
    public List<ETQRList> getETQRList() {
        return eTQRList;
    }

    public void setETQRList(List<ETQRList> eTQRList) {
        this.eTQRList = eTQRList;
    }
}

