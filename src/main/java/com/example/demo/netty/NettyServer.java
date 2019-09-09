package com.example.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.util.Date;
//http://www.sohu.com/a/272879207_463994
public class NettyServer {
    /**
     * 每个 Boss NioEventLoop 循环执行的任务包含 3 步:
     * 轮询 Accept 事件。
     * 处理 Accept I/O 事件，与 Client 建立连接，生成 NioSocketChannel，并将 NioSocketChannel 注册到某个 Worker NioEventLoop 的 Selector 上。
     * 处理任务队列中的任务，runAllTasks。任务队列中的任务包括用户调用 eventloop.execute 或 schedule 执行的任务，或者其他线程提交到该 eventloop 的任务。
     * @param args
     */
    /**
     * 每个 Worker NioEventLoop 循环执行的任务包含 3 步：
     * 轮询 Read、Write 事件。
     * 处理 I/O 事件，即 Read、Write 事件，在 NioSocketChannel 可读、可写事件发生时进行处理。
     * 处理任务队列中的任务，runAllTasks。
     * @param args
     */
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boos = new NioEventLoopGroup();//接收新线程
        NioEventLoopGroup worker = new NioEventLoopGroup();//负责读取数据的线程，及业务处理
        //组装NioEventLoopGroup
        serverBootstrap.group(boos, worker)
                //设置channel类型为NIO类型
                .channel(NioServerSocketChannel.class)
                //设置链接配置参数
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                //配置入站出站事件handler
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        //配置入站出站事件handler
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                ctx.channel().eventLoop().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        //用户程序自定义的普通任务
                                    }
                                });

                                System.out.println(msg);
                            }
                        });
                    }
                })
                //绑定端口
                .bind(8000)
                .addListener(future -> {
                   if (future.isSuccess()){
                       System.out.println(new Date()+":端口绑定成功");
                   }else {
                       System.out.println("端口绑定失败");
                   }
                });
    }
}
