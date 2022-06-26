package com.test;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class NettyServer {
    public static void main(String[] args) {
        //引导类，引导我们进行服务端的启动工作
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        //监听端口，创建新连接的线程组
        NioEventLoopGroup boos=new NioEventLoopGroup();
        //表示处理每一条连接的数据读写的线程组
        NioEventLoopGroup worker=new NioEventLoopGroup();
        serverBootstrap
                //给引导类配置两大线程，这个引导类的线程模型也就定型了
                .group(boos,worker)
                //指定我们服务端的IO模型为NIO，NioServerSocketChannel是对NIO类型的连接的抽象，类似于ServerSocket
                .channel(NioServerSocketChannel.class)
                //指定在服务端启动过程中的一些逻辑，通常情况下我们用不着这个方法
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception {
                        System.out.println("服务端启动中...");
                    }
                })
                //定义后续每条连接的数据读写，业务处理逻辑，NioSocketChannel是Netty对NIO类型的连接的抽象，类似于Socket
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                //输出从客户端发送过来的消息
                                System.out.println("收到的  "+msg);
                                ctx.writeAndFlush("received xxxx");
                            }
                        });
                    }
                })
                //绑定一个8010端口启动
                .bind(28011)
                //监听端口是否绑定成功
                .addListener(new GenericFutureListener<Future<? super Void>>() {
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if(future.isSuccess()){
                            System.out.println("端口绑定成功！");
                        }else{
                            System.out.println("端口绑定失败！");
                        }
                    }
                });

    }
}
