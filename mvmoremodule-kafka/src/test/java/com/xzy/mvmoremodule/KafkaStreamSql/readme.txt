kafka流处理
许多Kafka用户通过管道来处理数据，有多个阶段： 从Kafka topic中消费原始输入数据，然后聚合，
修饰或通过其他方式转化为新的topic， 以供进一步消费或处理。
例如，一个推荐新闻文章的处理管道可以从RSS订阅源抓取文章内容并将其发布到“文章”topic;
然后对这个内容进行标准化或者重复的内容， 并将处理完的文章内容发布到新的topic;
最终它会尝试将这些内容推荐给用户。
这种处理管道基于各个topic创建实时数据流图。
从0.10.0.0开始，在Apache Kafka中，Kafka Streams 可以用来执行上述的数据处理，它是一个轻量但功能强大的流处理库

模拟数据流向
mysql日志提交到kafka ---> kafaka通过流处理（做业务逻辑处理）--->重新入mysql