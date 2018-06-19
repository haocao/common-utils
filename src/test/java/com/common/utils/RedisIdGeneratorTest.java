package com.common.utils;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.common.utils.generator.redis.IdGenerator;
import com.common.utils.generator.redis.RedisIdGenerator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RedisIdGeneratorTest {

    /**
     * ID生成器
     */
    private IdGenerator redisIdGenerator = new RedisIdGenerator();

    @Test
    public void testGeneratorId() throws Exception {
      int totalThreads = 20;
      String key = "order_" + System.currentTimeMillis();
      ExecutorService executorService = Executors.newFixedThreadPool(totalThreads);
      CountDownLatch countDownLatch = new CountDownLatch(totalThreads);
      for (int i = 0; i < totalThreads; i++) {
        executorService.execute(() -> {
          countDownLatch.countDown();
        });
      }
      countDownLatch.await();
      assertThat(redisIdGenerator.generateId(key), is(totalThreads + 1L));
    }
}
