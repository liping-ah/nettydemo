package com.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Netty服务端.
 *
 * @author Mrx
 * @since 2021/1/27
 */
public class NettyServer {
    /**
     * 设置服务端端口
     */
    private static final int PORT = 6666;
    /*
     * 通过nio方式来接收连接和处理连接
     * EventLoopGroup是用来处理IO操作的多线程事件循环器
     */
    /**
     * bossGroup 用来接收进来的连接（并没有处理请求）
     */
    NioEventLoopGroup bossGroup = new NioEventLoopGroup();
    /**
     * workerGroup 用来处理已经被接收的连接
     */
    NioEventLoopGroup workGroup = new NioEventLoopGroup();

    /**
     * 开启服务线程，netty通过ServerBootstrap启动服务端。
     * Netty创建全部都是实现自AbstractBootstrap。
     * 客户端的是Bootstrap，服务端的则是ServerBootstrap。
     **/
    public void start() {
        try {
            //启动 NIO 服务的辅助启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    //配置 Channel (异步的服务器端 TCP Socket 连接)
                    .channel(NioServerSocketChannel.class)
                    //服务端过滤器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel socketChannel) throws Exception {
                            // 服务端业务逻辑
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                            // 解决拆包问题
                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            // 获取数据的结果为string类型,解码和编码，应和客户端一致
                            socketChannel.pipeline().addLast(new StringEncoder());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定端口，开始接收进来的连接
            ChannelFuture f = serverBootstrap.bind(PORT).sync();
            System.out.println("通讯服务启动成功：" + PORT);
            // 等待服务器 socket 关闭。
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.err.println("通讯服务启动失败：" + PORT);
        } finally {
            //关闭EventLoopGroup，释放掉所有资源包括创建的线程
            try {
                bossGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                workGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start();
    }
}
