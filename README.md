# redis_distributed_lock
手写redis分布式锁

实现了可重入和自动续期。

### 使用方法
```java
Lock redisLock = distributedLockFactory.getDistributedLock("redis");
redisLock.lock();
try
{
  // ......
} finally {
  redisLock.unlock();
}
```
