package ${package}.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;


@Slf4j
public class DistributedLock {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static ConcurrentHashMap<String, String> localCache = new ConcurrentHashMap<>();

    public boolean tryLock(String key, long expire) {
        try {
            String uuid = UUID.randomUUID().toString();
            localCache.put(key, uuid);
            Boolean result = redisTemplate.opsForValue().setIfAbsent(key, uuid, expire, TimeUnit.SECONDS);
            return result != null && result;
        } catch (Exception e) {
            log.error("set redis occured an exception", e);
        }
        return false;
    }

    public boolean releaseLock(String key) {
        // 释放锁的时候，有可能因为持锁之后方法执行时间大于锁的有效期，此时有可能已经被另外一个线程持有锁，所以不能直接删除
        String localCacheValue = localCache.get(key);
        try {
            String oldValue = redisTemplate.opsForValue().get(key);
            if(StringUtils.isEmpty(oldValue)){
                return true;
            }
            if(oldValue.equals(localCacheValue)){
                redisTemplate.delete(key);
                localCache.remove(key);
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            log.error("release lock occured an exception", e);
        }
        return false;
    }

}
