package com.heima;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestEventLoop {
    private static final Logger log = LoggerFactory.getLogger(TestEventLoop.class);
    public static void main(String[] args) {
        EventLoopGroup group=new NioEventLoopGroup(2);
        group.next().submit(()-> {

            log.info("ok");
        });
    }
}
