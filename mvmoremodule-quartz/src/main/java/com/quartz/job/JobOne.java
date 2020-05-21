package com.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.concurrent.TimeUnit;

public class JobOne implements Job {
    @Override
    public void execute(JobExecutionContext jobCtx) {
        Integer paramMap = (Integer)jobCtx.getMergedJobDataMap().get("paramMap");
        System.out.println(Thread.currentThread().getId() + " paramMap = " + paramMap + " jobDetail = " + jobCtx.getJobDetail());
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
