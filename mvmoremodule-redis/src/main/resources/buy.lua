local buyNum = ARGV[1]
local goodsKey = KEYS[1]
local goodsNum = redis.call('get',goodsKey)
if tonumber(goodsNum) >= tonumber(buyNum) then
    redis.call('decrby',goodsKey,buyNum)
    return buyNum
else
    return '0'
end