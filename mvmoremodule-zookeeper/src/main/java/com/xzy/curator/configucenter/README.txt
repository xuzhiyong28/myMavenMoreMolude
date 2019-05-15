基于zk的配置中心
设计思路:建立一个持久节点configuration为根节点，根节点下的路径为key，value为对应的值,结构如下
/configuration
 -- /jdbc_username
 ------ value : root
 -- /jdbc_password
 ------ value : 123456
 -- / jdbc_driver
 ------ value : com.mysql.jdbc.Driver
程序启动时候执行 DefaultConfigurationCenter.initCuratorClient() 将所有配置加载到configMap静态变量中，当其他客户端操作
更改配置时，监听器监听到会将新的配置加载到configMap