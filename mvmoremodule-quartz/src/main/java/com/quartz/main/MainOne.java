package com.quartz.main;

import com.quartz.job.JobOne;
import org.apache.commons.lang3.RandomUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class MainOne {
    public static void main(String[] args) throws SchedulerException {
        test2();
    }

    public static void test1() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(JobOne.class).withIdentity("jobOne", "jGroup1").build();
        //设置一个参数传给任务
        jobDetail.getJobDataMap().put("paramMap", RandomUtils.nextInt(1,100));
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(4);
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger_1", "jGroup1").withSchedule(simpleScheduleBuilder).build();
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }

    public static void test2() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(JobOne.class).withIdentity("jobOne", "jGroup1").build();
        //设置一个参数传给任务
        jobDetail.getJobDataMap().put("paramMap", RandomUtils.nextInt(1,100));
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger_1", "jGroup1").withSchedule(cronScheduleBuilder).build();
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();

    }

}
