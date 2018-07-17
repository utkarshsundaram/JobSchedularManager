package jobschedularmanager.jobschedularmanager.view;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jobschedularmanager.jobschedularmanager.R;
import jobschedularmanager.jobschedularmanager.databinding.ActivityMainBinding;
import jobschedularmanager.jobschedularmanager.viewModel.JobSchedularViewModel;

public class MainActivity extends AppCompatActivity implements LifecycleObserver {
    private ActivityMainBinding binding;
    private JobSchedularViewModel mJobSchedularModel;
    @Override
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        mJobSchedularModel=new JobSchedularViewModel(this);
        binding.setJobSchedular(mJobSchedularModel);
    }
}
