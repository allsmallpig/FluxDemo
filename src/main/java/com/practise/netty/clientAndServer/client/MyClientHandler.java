package com.practise.netty.clientAndServer.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Filename: FluxDemo /com.example.demo.clientAndServer
 * @Description:
 * @Author: Tiezhu.Li@paat.com/804082784@qq.com
 * @Version: 1.0
 * @CreateTime: 2019/6/17 14:16
 * @History:
 */
public class MyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
        System.out.println(msg);
        ctx.channel().writeAndFlush("to Server: 草泥马");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(System.currentTimeMillis() + "...........");
        ctx.channel().writeAndFlush("来自于客户端的问候！");
        System.out.println("client channel Active...");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println(System.currentTimeMillis() + "...........");
        System.out.println("client hanlder added...");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println(System.currentTimeMillis() + "...........");
        System.out.println("client channel register...");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client channel inactive...");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client channel unregister...");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}