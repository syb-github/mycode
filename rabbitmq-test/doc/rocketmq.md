1、参考地址
https://blog.csdn.net/m0_60517827/article/details/130228560

2、安装rocketmq和启动  window  （启动两个组件 name server  和 broker）

配置环境变量
ROCKETMQ_HOME

    D:\java\soft\rocketmq-all-5.2.0-bin-release

启动RecoketMQ

    启动name server：
    start mqnamesrv.cmd   

启动broker

    start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true



