package com.turgul.kemal.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author kemalturgul
 *
 */
public class FileOperations {

    private static Logger          logger           = LogManager.getLogger(FileOperations.class);

    public static final Properties configProperties = new Properties();

    public static void writeToFile(final String filePath, final String outputData)
            throws IOException {
        FileChannel fileChannel = null;
        try {

            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Set<OpenOption> openSet = new HashSet<>();
            openSet.add(StandardOpenOption.CREATE);
            openSet.add(StandardOpenOption.WRITE);
            openSet.add(StandardOpenOption.SYNC);

            fileChannel = FileChannel.open(path, openSet);

            if (outputData != null) {
                byte[] outputBytes = outputData.getBytes();
                ByteBuffer buffer = ByteBuffer.wrap(outputBytes);
                fileChannel.write(buffer);
            }
        } catch (Exception e) {
            logger.error("An Exception occurred while writing data:{} to file:{}",
                         outputData,
                         filePath,
                         e);
        } finally {
            if (fileChannel != null) {
                fileChannel.close();
            }
        }
    }

    public static String readFromFile(String path) throws IOException {
        StringBuilder data = new StringBuilder();
        Path filePath = Paths.get(path);
        if (!Files.exists(filePath)) {
            logger.warn("File path:{} does not exist !!!", path);
            return data.toString();
        }

        try (FileReader fr = new FileReader(path); BufferedReader br = new BufferedReader(fr);) {

            String textRead = br.readLine();
            while (textRead != null) {
                data.append(textRead);
                textRead = br.readLine();
            }

        } catch (Exception e) {
            logger.error("An Exception occurred while reading from file:{}", filePath, e);
        }
        return data.toString();
    }

    public static void loadConfigFile(File configFile) {

        try (FileInputStream inStream = new FileInputStream(configFile);) {

            configProperties.load(inStream);

        } catch (Exception e) {
            logger.error("An Exception occurred while loading configFile:{}",
                         configFile.getAbsolutePath(),
                         e);
        }
    }

}