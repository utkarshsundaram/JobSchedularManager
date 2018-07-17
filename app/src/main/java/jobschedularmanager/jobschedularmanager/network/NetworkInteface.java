package jobschedularmanager.jobschedularmanager.network;
import java.util.Map;
import jobschedularmanager.jobschedularmanager.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;

import static jobschedularmanager.jobschedularmanager.network.NetworkApi.DEMO;

/**
 * Created by user on 10/4/18.
 */

public interface NetworkInteface
{
    @GET(DEMO)
    Call<MovieResponse> checkForServiceResponse();

}
