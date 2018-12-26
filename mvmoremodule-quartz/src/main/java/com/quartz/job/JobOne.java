package com.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobOne implements Job {
    @Override
    public void execute(JobExecutionContext jobCtx) throws JobExecutionException {
        System.out.println("!!!");
        System.out.println(jobCtx.getJobDetail());
    }
}
