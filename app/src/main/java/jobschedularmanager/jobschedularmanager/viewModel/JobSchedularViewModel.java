package jobschedularmanager.jobschedularmanager.viewModel;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import java.util.concurrent.TimeUnit;

import jobschedularmanager.jobschedularmanager.service.APICallService;

import static android.content.Context.JOB_SCHEDULER_SERVICE;

/**
 * Created by user on 12/7/18.
 */

public class JobSchedularViewModel extends BaseObservable {
    public Context mContext;

    public JobSchedularViewModel(Context mContext) {
        this.mContext = mContext;
    }

    public View.OnClickListener onScheduleJob() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //a periodic job which executes every 1 hours and a linear back off policy which executes 20 min * number fails in case the job fails
                JobScheduler jobScheduler = (JobScheduler)mContext.getApplicationContext().getSystemService(JOB_SCHEDULER_SERVICE);
                ComponentName componentName = new ComponentName(mContext, APICallService.class);
                JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                        .setPeriodic(TimeUnit.MINUTES.toMillis(1))
                        .setRequiresDeviceIdle(false)
                        .setRequiresCharging(false)
                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                        .setPersisted(true)
                        .setBackoffCriteria(1000, JobInfo.BACKOFF_POLICY_LINEAR)
                        .build();
                assert jobScheduler != null;
                jobScheduler.schedule(jobInfo);

            }
        };
    }
}
