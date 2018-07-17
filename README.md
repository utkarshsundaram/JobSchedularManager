# JobSchedularManager Sample

Demonstration of the JobScheduler API, which provides an interface for scheduling background tasks when certain tasks apply.

# Pre-requisites

    Android SDK 27
    Android Build Tools v27.0.2
    Android Support Repository
    Android MinimumSDK version 21
    
# About JobSchedular 
Job schedular performs work based on conditions, not on time.JobScheduler is guaranteed to get your job done, but since it operates at the system level, it can also use several factors to intelligently schedule your background work to run with the jobs from other apps. This means we can minimize things like radio use, which is a clear battery win. And as of API 24, 
JobScheduler even considers memory pressure, which is a clear overall win for devices and their users.

# How to Use it
The work you are wanting to schedule should be defined in a JobService. Your JobService is actually going to be a Service that extends the JobService class. 
This is what enables the system to perform your work for you, regardless of whether your app is active
# Methods of JobService
# onStartJob()-
 
 If your task is short and simple, feel free to implement the logic directly in onStartJob() and return false when you are finished, to let the system know that all work has been completed

# onStopJob()-

 It is called by the system if the job is cancelled before being finished. This generally happens when your job conditions are no longer being met, such as when the device has been unplugged or if WiFi is no longer available. So use this method for any safety checks and clean up you may need to do in response to a half-finished job.
 
Add your service in android manifest with the permission  android:permission="android.permission.BIND_JOB_SERVICE"defined in it

# Method for defining JobInfo and parameters to be included 

# Network type (metered/unmetered)
If your job requires network access, you must include this condition. 

# Charging and Idle
If your app needs to do work that is resource-heavy, it is highly recommended that you wait until the device is plugged in and/or idle.

# Backoff criteria
You can specify your own back-off/retry policy. This defaults to an exponential policy, but if you set your own and then return true for rescheduling a job 

# Minimum latency and override deadline
If your job cannot start for at least X amount of time, or cannot be delayed past a specific time, you can specify those values here. Even if all conditions have not been met, your job will be run by the deadline 
# Periodic
If you have work that needs to be done regularly, you can set up a periodic job.
The peroidic job will start 15 min *(the time that you have provided) in the method

# Persistent
Any work that needs to be persisted across a reboot can be marked as such here. Once the device reboots, the job will be rescheduled according to the conditions

