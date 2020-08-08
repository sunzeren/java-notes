package com.sun.demo.current.echo;

import java.io.*;
import java.net.Socket;

/**
 * Author by Sun, Date on 2020/8/4.
 * PS: Not easy to write code, please indicate.
 */
public class EchoClientTest {
    public static void main(String[] args) {
        try {
            //创建一个Socket对象 指定要连接的服务器：IP+端口号
            Socket socket = new Socket("localhost", 8080);
            //获取Socket的输出输入流
            PrintStream ps = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //写数据
            ps.println("hello,这里是客户端发送的信息");
            ps.flush();//刷新
            //读取服务器返回的信息
            String info = br.readLine();
            System.out.println("服务器返回的信息：" + info);

            ps.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
