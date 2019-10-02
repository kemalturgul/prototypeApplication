package com.turgul.kemal.cron;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author kemalturgul
 *
 */
public class SchedulerThread extends Thread {

    private static Logger logger = LogManager.getLogger(SchedulerThread.class);

    @Override
    public void run() {
        try {
            logger.info("Starting Scheduler Thread");
            //Set job details.
            JobDetail job = JobBuilder.newJob(WriteCacheDataToFileJob.class).withIdentity("writeCacheDataToFileJob",
                                                                                          "dataGroup1").build();

            //Set the scheduler timings.
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("cronTrigger",
                                                                       "dataGroup1").withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?")).build();

            //Execute the job.
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);

            logger.info("Scheduler Thread started successfully !!");

        } catch (Exception e) {
            logger.error("An Exception occurred while configuring Cron Job Thread", e);
        }

    }
}
