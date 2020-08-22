package com.sun.demo.io.socket;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Author by Sun, Date on 2020/8/18.
 * PS: Not easy to write code, please indicate.
 */
public class SocketTest {

    private static final String host = "baidu.com";
    private static final int port = 443;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(host, port);
        // 此步骤会打开连接,直至服务器响应,如服务器暂不可用,则上述代码会一直等待
        // 可使用以下方法,来创建socket
        //region 可超时的 socket创建方式
        Socket socketTimeOut = new Socket();
        socketTimeOut.connect(new InetSocketAddress(host, port));
        socketTimeOut.setSoTimeout(1000 * 2);
        //endregion

        InputStream inputStream = socket.getInputStream();
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        if (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
    }

    @Test
    public void getAllByHostName() throws UnknownHostException {
        InetAddress[] addresses = InetAddress.getAllByName(host);
        for (int i = 0; i < addresses.length; i++) {
            InetAddress address = addresses[i];
            System.out.println("address = " + address);
        }
    }
}
