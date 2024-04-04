redis 
1、报错：ERR Client sent AUTH, but no password is set

redis cmd -》 redis-cli -> config set requirepass "123456" -> auth 123456

2、lua脚本  用于处理redis缓存数据导致原子性
eval "return redis.call('set', KEYS[1], ARGV[1])" 1 name name-value
eval 执行脚本关键字
KEYS[1] 脚本中第一个参数key  lua脚本的下标从1开始  与java不同
ARGV[1] 脚本中第一个参数值
1 代表一个参数

