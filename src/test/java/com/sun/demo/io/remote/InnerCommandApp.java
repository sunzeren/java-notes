package com.sun.demo.io.remote;

import ch.ethz.ssh2.Connection;

/**
 * @Author: ZeRen.
 * @Date: 2020/5/13 9:55
 */
public class InnerCommandApp {
    public static void main(String[] args) {

        final Connection connection = RemoteCommandUtil.login("xxx", "root", "xxx");
        try {
            final String executeResult = RemoteCommandUtil.execute(connection, "nginx -s stop");
            final String executeResult2 = RemoteCommandUtil.execute(connection, "nginx");
        } finally {
            connection.close();
        }
    }
}
