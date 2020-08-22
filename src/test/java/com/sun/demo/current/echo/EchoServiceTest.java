package com.sun.demo.current.echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * Author by Sun, Date on 2020/8/4.
 * PS: Not easy to write code, please indicate.
 */
public class EchoServiceTest {

    private static final int port = 8080;

    public static void main(String[] args) throws IOException {
        // 传统io
        echoIO();
        // nio
        // echoNio();
    }

    private static void echoIO() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            OutputStream outputStream = accept.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);

            Scanner scanner = new Scanner(inputStream, "UTF-8");
            printWriter.println("hello ! enter 'exist' to exist");

            boolean done = false;
            while (!done && scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                System.out.println(nextLine);
                printWriter.println("echo:" + nextLine);
                if (nextLine.trim().equalsIgnoreCase("exist")) {
                    printWriter.println("Bye Bye !");
                    done = true;
                }
            }

            printWriter.write("--form sun");
            accept.close();
        }

    }

    public static void echoNio() throws IOException {

        final ServerSocketChannel ssc =
                ServerSocketChannel.open().bind(new InetSocketAddress(port));
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
                        sc.write(ByteBuffer.wrap("这里是服务端:[\r\n".getBytes()));
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
