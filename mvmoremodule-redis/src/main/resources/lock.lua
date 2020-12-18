-- KEYS[1] 表示锁名称
-- ARGV[2] 表示线程ID
-- ARGV[1] 表示存活时间
-- 如果线程ID不存在在redis中，则表示第一次加锁
if (redis.call('exists', KEYS[1]) == 0) then
  redis.call('hset', KEYS[1], ARGV[2], 1);
  redis.call('pexpire', KEYS[1], ARGV[1]);
  return nil;
end;
-- 判断 线程ID是否存在，如果存在表示还是原来的线程来加锁，则值+1，并重新设置超时时间
if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then
    redis.call('hincrby', KEYS[1], ARGV[2], 1);
    redis.call('pexpire', KEYS[1], ARGV[1]);
    return nil;
end;
return redis.call('pttl', KEYS[1]);