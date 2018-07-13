package jobschedularmanager.jobschedularmanager.service;

import android.annotation.SuppressLint;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import jobschedularmanager.jobschedularmanager.model.DashboardSummary;
import jobschedularmanager.jobschedularmanager.network.ApiClient;
import jobschedularmanager.jobschedularmanager.network.NetworkInteface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static jobschedularmanager.jobschedularmanager.application.JobSchedularApplication.getCommonHeaders;

/**
 * Created by user on 12/7/18.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class APICallService extends JobService {
    @Override
    public boolean onStartJob(final JobParameters params)
    {
        //new JobTask(this,this).execute(params);
        NetworkInteface apiService =
                ApiClient.getClient().create(NetworkInteface.class);
        Call<DashboardSummary>call=apiService.checkForServiceResponse(getCommonHeaders());
        call.enqueue(new Callback<DashboardSummary>() {
            @Override
            public void onResponse(Call<DashboardSummary> call, final Response<DashboardSummary> response) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(APICallService.this,response.message(),Toast.LENGTH_LONG).show();
                    }
                });
                APICallService.this.jobFinished(params, true);
                // Toast.makeText(mContext,response.message(),Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<DashboardSummary> call, final Throwable t) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(APICallService.this,""+t.toString(),Toast.LENGTH_LONG).show();
                    }
                });
                APICallService.this.jobFinished(params, true);
                //Toast.makeText(APICallService.this,""+t.toString(),Toast.LENGTH_LONG).show();
            }
        });
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
   /* private static class JobTask extends AsyncTask<JobParameters, Void, JobParameters> {
        @SuppressLint("StaticFieldLeak")
        private final JobService jobService;
        @SuppressLint("StaticFieldLeak")
        private Context mContext;
        public JobTask(JobService jobService,Context mContext) {
            this.jobService = jobService;
            this.mContext=mContext;

        }

        @Override
        protected JobParameters doInBackground(JobParameters... params)
        {
            NetworkInteface apiService =
                    ApiClient.getClient().create(NetworkInteface.class);
            Call<DashboardSummary>call=apiService.checkForServiceResponse(getCommonHeaders());
            call.enqueue(new Callback<DashboardSummary>() {
                @Override
                public void onResponse(Call<DashboardSummary> call, Response<DashboardSummary> response) {
                    Toast.makeText(mContext,response.message(),Toast.LENGTH_LONG).show();
                }
                @Override
                public void onFailure(Call<DashboardSummary> call, Throwable t) {
                    Toast.makeText(mContext,""+t.toString(),Toast.LENGTH_LONG).show();
                }
            });
            return params[0];
        }

        @Override
        protected void onPostExecute(JobParameters jobParameters)
        {
            Toast.makeText(mContext,"the job is finished",Toast.LENGTH_LONG).show();
            jobService.jobFinished(jobParameters, true);
        }*/
    }



