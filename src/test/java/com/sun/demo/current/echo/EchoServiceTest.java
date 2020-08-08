package com.sun.demo.current.echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Author by Sun, Date on 2020/8/4.
 * PS: Not easy to write code, please indicate.
 */
public class EchoServiceTest {
    public static void main(String[] args) throws IOException {
        echo();
    }

    public static void echo() throws IOException {

        final ServerSocketChannel ssc =
                ServerSocketChannel.open().bind(new InetSocketAddress(8080));
        //处理请求
        try {
            while (true) {
                // 接收请求
                SocketChannel sc = ssc.accept();
                // 每个请求都创建一个线程
                new Thread(() -> {
                    try {
                        // 读Socket
                        ByteBuffer rb = ByteBuffer
                                .allocateDirect(1024 * 2);
                        sc.read(rb);
                        //模拟处理请求
                        Thread.sleep(2000);
                        // 写Socket
                        ByteBuffer wb =
                                (ByteBuffer) rb.flip();
                        sc.write(ByteBuffer.wrap("这里是服务端:[".getBytes()));
                        sc.write(wb);
                        sc.write(ByteBuffer.wrap("]".getBytes()));
                        // 关闭Socket
                        sc.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } finally {
            ssc.close();
        }
    }

}
