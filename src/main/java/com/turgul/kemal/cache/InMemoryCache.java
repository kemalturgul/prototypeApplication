package com.turgul.kemal.cache;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.turgul.kemal.model.Subscriber;

/**
 * @author kemalturgul
 *
 */
public class InMemoryCache implements Cache {

    private static Logger                                logger   = LogManager.getLogger(InMemoryCache.class);

    private final ConcurrentHashMap<Integer, Subscriber> cache    = new ConcurrentHashMap<>();

    private final static InMemoryCache                   instance = new InMemoryCache();

    private InMemoryCache() {
    }

    public static InMemoryCache getInstance() {
        return instance;
    }

    @Override
    public void add(Integer key, Subscriber value) {
        logger.debug("Adding Subscriber to cache with key:{} value:{}", key, value);
        if (key == null) {
            return;
        }
        if (value == null) {
            cache.remove(key);
            logger.debug("Adding Subscriber removed from cache with key:{} value:{} since value is null",
                         key,
                         value);
        } else {
            cache.put(key, value);
            logger.debug("Subscriber added to cache with key:{} value:{}", key, value);
        }
    }

    @Override
    public void remove(Integer key) {
        cache.remove(key);
        logger.info("Removing Subscriber from cache with key:{}", key);
    }

    @Override
    public Subscriber get(Integer key) {
        logger.info("Getting Subscriber from cache with key:{}", key);
        return Optional.ofNullable(cache.get(key)).orElse(null);
    }

    @Override
    public void clear() {
        cache.clear();
        logger.info("Subscriber cache cleared !!");
    }

    @Override
    public long size() {
        return cache.entrySet().stream().count();
    }

    public List<Subscriber> getAllCacheData() {
        return cache.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }
}