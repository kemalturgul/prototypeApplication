package com.turgul.kemal.cron;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.turgul.kemal.cache.InMemoryCache;
import com.turgul.kemal.constant.Constants;
import com.turgul.kemal.io.FileOperations;
import com.turgul.kemal.model.Subscriber;

/**
 * @author kemalturgul
 *
 */
public class WriteCacheDataToFileJob implements Job {

    private static Logger logger = LogManager.getLogger(WriteCacheDataToFileJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            logger.debug("Starting to write in memory (cache) data to a file !!");
            
            List<Subscriber> allCacheData = InMemoryCache.getInstance().getAllCacheData();
            StringBuilder subscriberDataJson = new StringBuilder();
            for (Subscriber subscriber : allCacheData) {
                subscriberDataJson.append(Constants.COMMA).append(subscriber.getJsonString());
            }
            String subscriberJson = subscriberDataJson.toString();

            if (subscriberJson.length() > 0) {
                subscriberJson = subscriberDataJson.toString().replaceFirst(Constants.COMMA, "");
            }

            StringBuilder allJson = new StringBuilder("{\"subscribers\":[");
            allJson.append(subscriberJson).append("]}");

            FileOperations.writeToFile(FileOperations.configProperties.getProperty(Constants.CONFIG_PARAM_DATA_FILE_PATH),
                                       allJson.toString());

            logger.info("Write cache data to a file completed successfully !!");

        } catch (Exception e) {
            logger.error("An Exception occurred while writing cache data to file", e);
        }
    }

}