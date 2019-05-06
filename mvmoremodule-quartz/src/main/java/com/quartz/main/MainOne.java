package com.quartz.main;

import com.quartz.job.JobOne;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class MainOne {
    public static void main(String[] args) throws SchedulerException {
        test2();
    }

    public static void test1() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(JobOne.class).withIdentity("jobOne", "jGroup1").build();
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(4);
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger_1", "jGroup1").withSchedule(simpleScheduleBuilder).build();
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }

    public static void test2() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(JobOne.class).withIdentity("jobOne", "jGroup1").build();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger_1", "jGroup1").withSchedule(cronScheduleBuilder).build();
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();

    }

}
