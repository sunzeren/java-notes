package com.sun.demo.current.echo;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * Author by Sun, Date on 2020/8/4.
 * PS: Not easy to write code, please indicate.
 */
public class EchoClientTest {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 8080);
                //获取Socket的输出输入流
                PrintStream ps = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            //创建一个Socket对象 指定要连接的服务器：IP+端口号


            //写数据
            ps.println("hello,这里是客户端发送的信息");
            ps.flush();//刷新
            //读取服务器返回的信息
            System.out.println("服务器返回的信息：");
            List<String> readLines = IOUtils.readLines(br);
            for (String readLine : readLines) {
                System.out.println(readLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
