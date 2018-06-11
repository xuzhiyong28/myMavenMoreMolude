package com.IO.pipedStream;/**
 * Created by Administrator on 2018-05-10.
 */

import java.io.PipedOutputStream;

/**
 * @author xuzhiyong
 * @createDate 2018-05-10-17:16
 */
public class Sender extends Thread {

    //管道输出流对象.它和“管道输入流(PipedInputStream)”对象绑定.从而可以将数据发送给“管道输入流”的数据，然后用户可以从“管道输入流”读取数据
    private PipedOutputStream out = new PipedOutputStream();

    public PipedOutputStream getOut() {
        return out;
    }

    private void writeShortMessage(){
        String strInfo = "在Zookeeper中，znode是一个跟Unix文件系统路径相似的节点，可以往这个节点存储或获取数据。如果在创建znode时Flag设置为EPHEMERAL，那么当创建这个znode的节点和Zookeeper失去连接后，这个znode将不再存在在Zookeeper里，Zookeeper使用Watcher察觉事件信息。当客户端接收到事件信息，比如连接超时、节点数据改变、子节点改变，可以调用相应的行为来处理数据。Zookeeper的Wiki页面展示了如何使用Zookeeper来处理事件通知，队列，优先队列，锁，共享锁，可撤销的共享锁，两阶段提交那么Zookeeper能做什么事情呢，简单的例子：假设我们有20个搜索引擎的服务器(每个负责总索引中的一部分的搜索任务)和一个总服务器(负责向这20个搜索引擎的服务器发出搜索请求并合并结果集)，一个备用的总服务器(负责当总服务器宕机时替换总服务器)，一个web的cgi(向总服务器发出搜索请求)。搜索引擎的服务器中的15个服务器提供搜索服务，5个服务器正在生成索引。这20个搜索引擎的服务器经常要让正在提供搜索服务的服务器停止提供服务开始生成索引，或生成索引的服务器已经把索引生成完成可以提供搜索服务了。使用Zookeeper可以保证总服务器自动感知有多少提供搜索引擎的服务器并向这些服务器发出搜索请求，当总服务器宕机时自动启用备用的总服务器";
        try{
            out.write(strInfo.getBytes());
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        writeShortMessage();
    }
}
