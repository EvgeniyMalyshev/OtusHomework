package otus;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import otus.cache.CacheEngine;
import otus.cache.CacheEngineImp;
import otus.cache.MyElement;
import otus.cache.SlowDataSrc;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class CacheTest {
    private static Logger log = LoggerFactory.getLogger(CacheTest.class);

    @Test
    public void eternalCacheTest()
    {
        int size = 6;
        CacheEngine<Integer, String> cache = new CacheEngineImp<>(size, 0, 0, true);

        for (int i = 0; i < 10; i++) {
            cache.put(new MyElement<>(i, "String: " + i));
        }
        Assert.assertNotNull(cache);

        for (int i = 0; i < 10; i++) {
            MyElement<Integer, String> element = cache.get(i);
            log.info("String for " + i + ": " + (element != null ? element.getValue() : "null"));
        }

        log.info("Cache hits: " + cache.getHitCount());
        log.info("Cache misses: " + cache.getMissCount());

        cache.dispose();
    }

    @Test
    public void lifeCacheTest() throws InterruptedException
    {
        int size = 6;
        CacheEngine<Integer, String> cache = new CacheEngineImp<>(size, 1000, 0, false);

        for (int i = 0; i < size; i++) {
            cache.put(new MyElement<>(i, "String: " + i));
        }
        Assert.assertNotNull(cache);

        for (int i = 0; i < size; i++) {
            MyElement<Integer, String> element = cache.get(i);
            log.info("String for " + i + ": " + (element != null ? element.getValue() : "null"));
        }

        log.info("Cache hits: " + cache.getHitCount());
        log.info("Cache misses: " + cache.getMissCount());

        Thread.sleep(1000);

        for (int i = 0; i < size; i++) {
            MyElement<Integer, String> element = cache.get(i);
            log.info("String for " + i + ": " + (element != null ? element.getValue() : "null"));
        }

        log.info("Cache hits: " + cache.getHitCount());
        log.info("Cache misses: " + cache.getMissCount());

        cache.dispose();
    }

    @Test
    public void workSpeedTest(){
        int size = 5;
        CacheEngine<Integer, String> cache = new CacheEngineImp<>(size, 0, 0, false);

        for (int i = 0; i < 10; i++) {
            cache.put(new MyElement<>(i, "String: " + i));
        }
        Assert.assertNotNull(cache);

        for (int i = 0; i < 10; i++) {
            MyElement<Integer, String> element = cache.get(i);
            if(element == null){ SlowDataSrc.getValue(i); }

            log.info("String for " + i + ": " + (element != null ? element.getValue() : "null"));
        }


    }



}
