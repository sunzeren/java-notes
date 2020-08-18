package com.sun.demo.io.nio;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

/**
 * Author by Sun, Date on 2020/8/18.
 * PS: Not easy to write code, please indicate.
 */
public class FileChannelTest {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        long crcValue = checksumMappedFile(Paths.get("C:\\Users\\Sun\\Desktop\\TEST.txt"));
        long end = System.currentTimeMillis();
        System.out.println("结果:" + crcValue + "共耗时:" + (end - start) + "毫秒");
    }

    public static long checksumMappedFile(Path path) throws IOException {
        try (FileChannel fileChannel = FileChannel.open(path)) {
            CRC32 crc = new CRC32();
            int length = (int) fileChannel.size();
            // 将此通道的文件直接映射到内存中
            MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, length);
            for (int p = 0; p < length; p++) {
                byte c = buffer.get(p);
                crc.update(c);
            }
            return crc.getValue();
        }
    }
}
