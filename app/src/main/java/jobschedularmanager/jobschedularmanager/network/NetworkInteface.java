package jobschedularmanager.jobschedularmanager.network;
import java.util.Map;
import jobschedularmanager.jobschedularmanager.model.DashboardSummary;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Url;

import static jobschedularmanager.jobschedularmanager.network.NetworkApi.DEMO;

/**
 * Created by user on 10/4/18.
 */

public interface NetworkInteface
{
    @GET(DEMO)
    Call<DashboardSummary> checkForServiceResponse(@HeaderMap Map<String,String> getHeader);

}
