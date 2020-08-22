package com.sun.demo.io.serialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Date;

/**
 * Author by Sun, Date on 2020/8/22.
 * PS: Not easy to write code, please indicate.
 * 对象序列化测试类
 */
public class ObjectSerializeTest {

    private static final String sourcePath = "C:\\Users\\Sun\\Desktop\\data.txt";
    private static final String targetPath = "C:\\Users\\Sun\\Desktop\\data-target.txt";

    /**
     * 从文件中读取对象流并保存对象至硬盘
     *
     * @throws IOException
     */
    @Test
    public void write() throws IOException {
        try (
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(targetPath));
        ) {
            byte[] dataBytes = IOUtils.toByteArray(new FileInputStream(sourcePath));
            objectOutputStream.writeObject(new ByteDataModel("这是测试文件", new Date(), dataBytes));
            objectOutputStream.flush();
        }
    }

    /**
     * 从已保存的文件流中读取对象
     *
     * @throws IOException
     */
    @Test
    public void read() throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(targetPath))) {
            Object obj = objectInputStream.readObject();
            ByteDataModel model = ByteDataModel.class.cast(obj);
            System.out.println(model);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 拓展:
     * 此为从 已知的 数据数组 中读取数据并保存至本地
     *
     * @throws IOException
     */
    @Test
    public void writeFileOfByteData() throws IOException {
        byte[] data = {35, 35, 32, 106, 97, 118, 97, 32, -72, -9, -76, -13, -80, -26, -79, -66, 13, 10, 13, 10, 13, 10, 13, 10, 62, 32, -60, -65, -57, -80, 106, 97, 118, 97, -77, -10, -75, -67, -63, -53, 106, 100, 107, 32, 49, 52, 32, 40, 50, 48, 49, 57, 46, 52, 41, 13, 10, 62, 13, 10, 62, 32, 106, 100, 107, 49, 53, -67, -85, -45, -38, 50, 48, 50, 48, 46, 57, -44, -62, -42, -48, -47, -82, -73, -94, -78, -68, 13, 10, 13, 10, 13, 10, 13, 10, 124, 32, -80, -26, -79, -66, 32, 32, 32, 32, 32, 32, 32, 124, 32, -60, -22, -73, -35, 32, 124, 32, -52, -40, -48, -44, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 13, 10, 124, 32, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 32, 124, 32, 45, 45, 45, 45, 32, 124, 32, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 32, 124, 13, 10, 124, 32, 49, 46, 48, 32, 32, 32, 32, 32, 32, 32, 32, 124, 32, 49, 57, 57, 54, 32, 124, 32, -45, -17, -47, -44, -79, -66, -55, -19, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 13, 10, 124, 32, 49, 46, 49, 32, 32, 32, 32, 32, 32, 32, 32, 124, 32, 49, 57, 57, 55, 32, 124, 32, -60, -38, -78, -65, -64, -32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 13, 10, 124, 32, 49, 46, 50, 32, 32, 32, 32, 32, 32, 32, 32, 124, 32, 49, 57, 57, 56, 32, 124, 32, 115, 116, 114, 105, 99, 116, 102, 112, -48, -34, -54, -50, -73, -5, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 13, 10, 124, 32, 49, 46, 51, 32, 32, 32, 32, 32, 32, 32, 32, 124, 32, 50, 48, 48, 48, 32, 124, 32, -50, -34, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 13, 10, 124, 32, 49, 46, 52, 32, 32, 32, 32, 32, 32, 32, 32, 124, 32, 50, 48, 48, 50, 32, 124, 32, -74, -49, -47, -44, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 13, 10, 124, 32, 53, 46, 48, -93, -88, 49, 46, 53, -93, -87, 32, 124, 32, 50, 48, 48, 52, 32, 124, 32, -73, -70, -48, -51, -64, -32, -93, -84, -95, -80, 102, 111, 114, 32, 101, 97, 99, 104, -95, -79, -47, -83, -69, -73, -93, -84, -65, -55, -79, -28, -44, -86, -78, -50, -54, -3, -93, -84, -41, -44, -74, -81, -41, -80, -49, -28, -93, -84, -44, -86, -54, -3, -66, -35, -93, -84, -61, -74, -66, -39, -93, -84, -66, -78, -52, -84, -75, -68, -56, -21, 32, 124, 13, 10, 124, 32, 54, -93, -88, 49, 46, 54, -93, -87, 32, 32, 32, 124, 32, 50, 48, 48, 54, 32, 124, 32, -50, -34, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 13, 10, 124, 32, 55, -93, -88, 49, 46, 55, -93, -87, 32, 32, 32, 124, 32, 50, 48, 49, 49, 32, 124, 32, -69, -7, -45, -38, -41, -42, -73, -5, -76, -82, -75, -60, 115, 119, 105, 116, 99, 104, -93, -84, -41, -22, -54, -81, -78, -39, -41, -9, -73, -5, -93, -88, 60, 62, 44, -68, -76, -73, -70, -48, -51, -93, -87, 44, -74, -2, -67, -8, -42, -58, -41, -42, -61, -26, -63, -65, -93, -84, -46, -20, -77, -93, -76, -90, -64, -19, -72, -60, -67, -8, 32, 124, 13, 10, 124, 32, 56, -93, -88, 49, 46, 56, -93, -87, 32, 32, 32, 124, 32, 50, 48, 49, 52, 32, 124, 32, 108, 97, 109, 98, 100, 97, -79, -19, -76, -17, -54, -67, -93, -84, -80, -4, -70, -84, -60, -84, -56, -49, -73, -67, -73, -88, -75, -60, -67, -45, -65, -38, -93, -84, -63, -9, -70, -51, -56, -43, -58, -38, 47, -54, -79, -68, -28, -65, -30, 40, 83, 116, 114, 101, 97, 109, 44, 76, 111, 99, 97, 108, 68, 97, 116, 101, 84, 105, 109, 101, 41, 32, 124, 13, 10, 124, 32, 57, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 32, 50, 48, 49, 55, 32, 124, 32, -60, -93, -65, -23, -49, -75, -51, -77, -93, -84, 74, 83, 104, 101, 108, 108, -93, -88, -67, -69, -69, -91, -54, -67, -79, -32, -77, -52, -69, -73, -66, -77, -93, -87, 44, 112, 114, 105, 118, 97, 116, 101, 32, 109, 101, 116, 104, 111, 100, -67, -45, -65, -38, -42, -48, -65, -55, -54, -71, -45, -61, -53, -67, -45, -48, -73, -67, -73, -88, 32, 124, 13, 10, 124, 32, 49, 48, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 32, 50, 48, 49, 56, 32, 124, 32, 118, 97, 114, 44, -66, -42, -78, -65, -79, -28, -63, -65, -51, -58, -74, -49, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 13, 10, 124, 32, 49, 49, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 32, 50, 48, 49, 56, 32, 124, 32, -41, -42, -73, -5, -76, -82, -78, -39, -41, -9, -68, -45, -57, -65, -93, -88, -68, -76, -74, -32, -63, -53, -78, -29, -78, -39, -41, -9, -73, -30, -41, -80, -93, -87, -93, -84, 90, 71, 67, -65, -55, -55, -20, -53, -11, -75, -51, -47, -45, -77, -39, -64, -84, -69, -8, -54, -43, -68, -81, -58, -9, -93, -84, 32, 124, 13, 10, 124, 32, 49, 50, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 32, 50, 48, 49, 56, 32, 124, 32, -44, -10, -57, -65, 115, 119, 105, 116, 99, 104, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 13, 10, 124, 32, 49, 51, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 32, 50, 48, 49, 56, 32, 124, 32, -44, -10, -57, -65, 115, 119, 105, 116, 99, 104, 44, -42, -89, -77, -42, 121, 105, 101, 108, 100, -71, -40, -68, -4, -41, -42, -93, -84, -50, -60, -79, -66, -65, -23, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 13, 10, 124, 32, 49, 52, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 32, 50, 48, 49, 57, 32, 124, 32, 114, 101, 99, 111, 114, 100, 32, -71, -40, -68, -4, -41, -42, -93, -84, 115, 119, 105, 116, 99, 104, -44, -10, -57, -65, -93, -88, 49, 50, -80, -26, -79, -66, -93, -87, -41, -86, -50, -86, -43, -3, -54, -67, -80, -26, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 13, 10, 124, 32, 49, 53, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 32, 50, 48, 50, 48, 32, 124, 32, 50, 48, 50, 48, 46, 57, -44, -62, -42, -48, -47, -82, -73, -94, -78, -68, -93, -84, -76, -3, -74, -88, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 124, 13, 10};
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Sun\\Desktop\\data-readOfByteArray.txt");
        fileOutputStream.write(data);
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    @Data
    @AllArgsConstructor
    public static class ByteDataModel implements Serializable {
        String title;
        Date createTime;
        byte[] data;

        @Override
        public String toString() {
            return "ByteDataModel{" +
                    "title='" + title + '\'' +
                    ", createTime=" + DateFormatUtils.format(createTime, "yyyy-MM-dd HH:mm:ss") +
                    ", data=" + Arrays.toString(data) +
                    '}';
        }
    }
}
