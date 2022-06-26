package com.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * netty客户端.
 *
 * @author Mrx
 * @since 2021/1/27
 */
public class NettyClient {
    /**
     * ip地址
     */
    public static final String HOST = "127.0.0.1";
    /**
     * 端口
     */
    public static final int PORT = 6666;
    /*
     * 通过nio方式来接收连接和处理连接
     * EventLoopGroup是用来处理IO操作的多线程事件循环器
     */
    /**
     * workerGroup 用来处理已经被接收的连接
     */
    NioEventLoopGroup workGroup = new NioEventLoopGroup();

    public void connect(String host, int port) throws Exception {
        try {
            Bootstrap b = new Bootstrap();
            b.group(workGroup);
//配置Channel (异步的客户端 TCP Socket 连接)
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel socketChannel) throws Exception {
                    // 客户端业务逻辑
                    socketChannel.pipeline().addLast(new NettyClientHandler());
                    // 解决拆包问题
                    socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                    // 获取数据的结果为string类型, 解码和编码，应和服务端一致
                    socketChannel.pipeline().addLast(new StringEncoder());
                }
            });

            // Start the client.
            ChannelFuture future = b.connect(host, port).sync();
//消息在这里发送，处理器那边接受服务端返回的消息
            String str="Hello Server! to erxian bridge";
            future.channel().writeAndFlush(str);
            System.out.println("客户端发送数据:"+str);
            // Wait until the connection is closed.
            future.channel().closeFuture().sync();
        } finally {
            //关闭EventLoopGroup，释放掉所有资源包括创建的线程
            try {
                workGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        NettyClient client = new NettyClient();
        client.connect(HOST, PORT);
    }
}

