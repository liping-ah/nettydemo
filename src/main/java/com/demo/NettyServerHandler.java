package com.demo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 服务端请求处理Handler.
 *
 * @author Mrx
 * @since 2021/1/27
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    /**
     * 接受消息，返回信息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        // 收到消息直接打印输出
        System.out.println("服务端接受的消息: " + msg);
        //另外一种方式，直接把ByteBuf转换成utf-8编码的字符串，输出也是一样
//      String result = msg.toString(CharsetUtil.UTF_8);
        byte[] result = new byte[msg.readableBytes()];
        // msg中存储的是ByteBuf类型的数据，把数据读取到byte[]中
        msg.readBytes(result);
        String string= new String(result);
        System.out.println("Client said:" + string);
//        // 释放资源，这行很关键（添加这行会抛出异常IllegalReferenceCountException: refCnt: 0, decrement: 1）
//        msg.release();


        // 向客户端发送消息
        String response = "hello client! take chenghua avenue!";
        // 在当前场景下，发送的数据须转换成ByteBuf数组
        ByteBuf buff = ctx.alloc().buffer(4 * response.length());
        buff.writeBytes(response.getBytes());
        ctx.writeAndFlush(buff);
//        ctx.writeAndFlush(Unpooled.copiedBuffer(response, CharsetUtil.UTF_8));
    }
}
