package com.practise.netty.clientAndServer.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Filename: FluxDemo /com.example.demo.clientAndServer
 * @Description:
 * @Author: Tiezhu.Li@paat.com/804082784@qq.com
 * @Version: 1.0
 * @CreateTime: 2019/6/17 14:14
 * @History:
 */
public class MyHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + ":" + msg);
        ctx.channel().writeAndFlush("from server: 草泥马");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println(System.currentTimeMillis() + "********");
        System.out.println("server handler added**********");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println(System.currentTimeMillis() + "********");
        System.out.println("server channel register****");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(System.currentTimeMillis() + "********");
        System.out.println("server channel actieve****");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}