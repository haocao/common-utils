package com.common.utils.generator.redis;

public interface IdGenerator {

  /**
   * 生成分布式自增主键
   *
   */
  Long generateId(String flag);
}
