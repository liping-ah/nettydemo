package com.nettypdf;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class EchoServer {
    public static void main(String[] args)throws Exception {
        new EchoServer().start();
    }
    public void start()throws Exception{
        final EchoServerHandler serverHandler=new EchoServerHandler();
        EventLoopGroup group=new NioEventLoopGroup();
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        try {
            serverBootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(6666))
                    .childHandler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(serverHandler);
                        }
                    });
            ChannelFuture future=serverBootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully().sync();
        }

    }
}
