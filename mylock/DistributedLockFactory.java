package com.redisstudy.redis7.mylock;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;

/**
 * @auther zzyy
 * @create 2022-10-18 18:53
 */
@Component
public class DistributedLockFactory
{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private String lockName;
    private String uuidValue;

    public DistributedLockFactory() {
        this.uuidValue = IdUtil.simpleUUID();//UU
    }

    public Lock getDistributedLock(String lockType)
    {
        if(lockType == null) {
            return null;
        }
        if(lockType.equalsIgnoreCase("REDIS")){
            // redis锁
            lockName = "RedisLock";
            return new RedisDistributedLock(stringRedisTemplate,lockName, uuidValue);
        } else if(lockType.equalsIgnoreCase("ZOOKEEPER")){
            // Zookeeper锁
            // return new ZookeeperDistributedLock();
            return null;
        } else if(lockType.equalsIgnoreCase("MYSQL")){
            // Mysql锁
            return null;
        }
        return null;
    }
}