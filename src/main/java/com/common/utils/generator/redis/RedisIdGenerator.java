package com.common.utils.generator.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisIdGenerator implements IdGenerator {

  private JedisPool jedisPool;

  private String host = "localhost";

  private int port = 6379;

  public RedisIdGenerator() {
    JedisPoolConfig config = new JedisPoolConfig();
    config.setMaxTotal(10);
    config.setMaxIdle(10);
    config.setTestOnBorrow(true);
    jedisPool = new JedisPool(config, host, port);
  }

  @Override
  public Long generateId(String flag) {
    try (Jedis jedis = jedisPool.getResource()) {
      return jedis.incr(flag);
    }
  }
}
