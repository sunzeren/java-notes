package com.sun.demo.request.crawler;

import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author: ZeRen.
 * @Date: 2020/4/26 10:47
 */
public class GenerateHtmlUtils {

    private static String BODY_MARK = "${body}";
    private static String TITLE_MARK = "${title}";
    private final static String TEMPLATE_HTML = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title> " + TITLE_MARK + " </title>\n" +
            "</head>\n" +
            "<body>\n" +
            BODY_MARK +
            "</body>\n" +
            "</html>\n";


    public static void generate(String body, String filePath, String title) {
        final String html = TEMPLATE_HTML.replace(TITLE_MARK, title).replace(BODY_MARK, body);

        final FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(filePath);
            IOUtils.write(html, fileOutputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("生成html文件失败!");
        }
    }
}
