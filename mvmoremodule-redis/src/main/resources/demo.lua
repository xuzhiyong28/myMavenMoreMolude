redis.call("SET","myName","xuzhiyong")
local myName = redis.call("GET","myName")
return myName