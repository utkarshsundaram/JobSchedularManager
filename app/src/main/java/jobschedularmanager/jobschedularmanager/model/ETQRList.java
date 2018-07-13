package jobschedularmanager.jobschedularmanager.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 12/7/18.
 */

public class ETQRList{
    @SerializedName("etqrd_year")
    private Integer etqrdYear;
    public Integer getEtqrdYear() {
        return etqrdYear;
    }

    public void setEtqrdYear(Integer etqrdYear) {
        this.etqrdYear = etqrdYear;
    }

}

