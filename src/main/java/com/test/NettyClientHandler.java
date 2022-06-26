package com.test;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public  class NettyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("客户端接受的消息: " + msg);
        byte[] result = new byte[msg.readableBytes()];
        msg.readBytes(result);
        System.out.println("Server said:" + new String(result));
//添加这行会抛出异常IllegalReferenceCountException: refCnt: 0, decrement: 1
//        result.release();
        super.channelActive(ctx);
        System.out.println("连接关闭! ");
        super.channelInactive(ctx);
    }
}