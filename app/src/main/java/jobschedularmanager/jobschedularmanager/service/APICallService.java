package jobschedularmanager.jobschedularmanager.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.nfc.Tag;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import jobschedularmanager.jobschedularmanager.model.MovieResponse;
import jobschedularmanager.jobschedularmanager.network.ApiClient;
import jobschedularmanager.jobschedularmanager.network.NetworkInteface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by UtkarshSundaram on 12/7/18.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class APICallService extends JobService
{
    public static final String TAG="APICallService";
    @Override
    public boolean onStartJob(final JobParameters params)
    {
        Log.d(TAG,"job started");
        NetworkInteface apiService =
                ApiClient.getClient().create(NetworkInteface.class);
        Call<MovieResponse>call=apiService.checkForServiceResponse();
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, final Response<MovieResponse> response) {
                Log.d(TAG,"api response success");
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(APICallService.this,response.message(),Toast.LENGTH_LONG).show();
                    }
                });
                APICallService.this.jobFinished(params, true);
            }
            @Override
            public void onFailure(Call<MovieResponse> call, final Throwable t) {
                Log.d(TAG,"api response failure");
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(APICallService.this,""+t.toString(),Toast.LENGTH_LONG).show();
                    }
                });
                APICallService.this.jobFinished(params, true);
            }
        });
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params)
    {
        Log.d(TAG,"job stopped");
        return true;
    }
    }



