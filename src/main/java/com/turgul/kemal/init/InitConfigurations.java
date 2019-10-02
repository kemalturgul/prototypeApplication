package com.turgul.kemal.init;

import java.io.File;
import java.net.URL;
import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.turgul.kemal.cache.InMemoryCache;
import com.turgul.kemal.constant.Constants;
import com.turgul.kemal.cron.SchedulerThread;
import com.turgul.kemal.io.FileOperations;
import com.turgul.kemal.model.Subscriber;
import com.turgul.kemal.util.JsonUtil;

/**
 * @author kemalturgul
 *
 */

@Startup
@Singleton
public class InitConfigurations {

    private static Logger logger = LogManager.getLogger(InitConfigurations.class);

    public InitConfigurations() {
        readFromConfigFile();
        readFromDataFile();
        startScheduler();
    }

    private void readFromConfigFile() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();

            URL resource = classLoader.getResource(Constants.CONFIG_FILE_NAME);
            if (resource == null) {
                logger.warn("Could not find resource file:{} at src/main/resources directory",
                            Constants.CONFIG_FILE_NAME);
            } else {
                FileOperations.loadConfigFile(new File(resource.getFile()));
                logger.info("Config file:{} at src/main/resources read successfully",
                            Constants.CONFIG_FILE_NAME);
            }

        } catch (Exception e) {
            logger.error("An Exception occurred while reading from config file", e);
        }

    }

    private void readFromDataFile() {
        try {
            String dataFilePath = FileOperations.configProperties.getProperty(Constants.CONFIG_PARAM_DATA_FILE_PATH);
            if (dataFilePath == null || dataFilePath.trim().length() == 0) {
                logger.warn("There is not any parameter:{} or its value is empty in {} file",
                            Constants.CONFIG_PARAM_DATA_FILE_PATH,
                            Constants.CONFIG_FILE_NAME);
                return;
            }
            String data = FileOperations.readFromFile(dataFilePath);

            if (data.length() > 0) {
                logger.info("Adding subscribers to cache from data file !!!");
                List<Subscriber> subscribersList = JsonUtil.convertJsonArrayToList(data,
                                                                                   Constants.SUBSCRIBERS_JSON_ARRAY_NAME);
                for (Subscriber subscriber : subscribersList) {
                    InMemoryCache.getInstance().add(subscriber.getId(), subscriber);
                }
            } else {
                logger.info("Subscriber file is empty !!");
            }

        } catch (Exception e) {
            logger.error("An Exception occurred while reading from subscriber data file", e);
        }
    }

    private void startScheduler() {
        new SchedulerThread().start();
    }
}
