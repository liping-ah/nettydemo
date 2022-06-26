package com.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.SocketTimeoutException;
import java.util.Date;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap=new Bootstrap();
        NioEventLoopGroup group=new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new StringEncoder());
                        channel.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                                                       @Override
                                                       public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                                          // super. (ctx, msg);
                                                           System.out.println(msg);
                                                       }
                                                   }
                        );

                    }
                });
        //获取连接通道
        Channel channel=bootstrap.connect("127.0.0.1",28011).channel();

            //循环向服务端发送消息
            channel.writeAndFlush(new Date()+":hello Jetty!").sync();

    }




}
