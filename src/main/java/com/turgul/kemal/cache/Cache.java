package com.turgul.kemal.cache;

import com.turgul.kemal.model.Subscriber;

/**
 * @author kemalturgul
 *
 */
public interface Cache {

    void add(Integer key, Subscriber value);

    void remove(Integer key);

    Subscriber get(Integer key);

    void clear();

    long size();

}
