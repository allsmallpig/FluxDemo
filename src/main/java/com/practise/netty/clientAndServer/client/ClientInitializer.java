package com.practise.netty.clientAndServer.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Set;

/**
 * @Filename: FluxDemo /com.example.demo.clientAndServer
 * @Description:
 * @Author: Tiezhu.Li@paat.com/804082784@qq.com
 * @Version: 1.0
 * @CreateTime: 2019/6/17 14:15
 * @History:
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        Selector open = Selector.open();
        if (open.isOpen()) {
            Set<SelectionKey> keys = open.keys();
            for (SelectionKey key : keys) {
                SelectableChannel channel = key.channel();
                channel.register(open,5);
            }
        }
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("lengthFieldBasedFrameDecoder",
                new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 2, 0, 2));
        pipeline.addLast("lengthFieldPrepender", new LengthFieldPrepender(3));
        pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));

        pipeline.addLast(new MyClientHandler());
    }
}
