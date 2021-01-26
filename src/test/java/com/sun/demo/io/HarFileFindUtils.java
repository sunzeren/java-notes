package com.sun.demo.io;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class HarFileFindUtils {

    private Set<String> results = new HashSet<>();
    private List<String> lines;


    @Before
    public void init() throws IOException {
        FileInputStream inputStream = new FileInputStream("E:\\work\\project\\my-github\\java-notes\\src\\test\\java\\com\\sun\\demo\\base\\temp.json");
        lines = IOUtils.readLines(inputStream, Charset.defaultCharset());
    }

    /**
     * HTTPArchive 格式文件 (.har) 解析关键字
     * 筛选数据
     * 过滤 黑名单数据
     * 倒叙后输出
     */
    @Test
    public void findKeywords() {
        // 需要过滤的信息
        List<String> blacklist = Lists.newArrayList(
                "/order-service/app/list",
                "/horseman-service/app/heartBeat",
                "/horseman-service/app/getOrdersV3",
                "/horseman-service/app/bindBackStoreByRider",
                "/horseman-service/app/validateVersion",
                "/report-service/app/unusualDetectionSum",
                "/horseman-service/app/getConfigInfo"
        );

        // 标准版
        //        String host = "http://139.9.120.173:4000";
        // 麦中版
        String host = "http://119.3.64.126:4000";

        String regx = "(\"url\": \")(.*)(\",)";

        Pattern patternRex = Pattern.compile(regx);
        for (String line : lines) {
            if (StringUtils.isNotEmpty(line) && line.contains(host)) {
                Matcher matcher = patternRex.matcher(line);
                while (matcher.find()) {
                    String result = StringUtils.remove(matcher.group(2), host);
                    if (blacklist.contains(result)) {
                        continue;
                    }
                    results.add(result);
                }
            }
        }

        results.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

    }


    @Test
    public void findData() {
        String regx = "\\\\\"data\\\\\":.*?([(?=])";

//        范围查找
//        String regx = "(?<=\\{token\\=\\').+(?=', horseman=)";
//        String regx = "\\\\\"data\\\\\":.*?(?<=])";
        Pattern patternRex = Pattern.compile(regx);
        for (String line : lines) {
            Matcher matcher = patternRex.matcher(line);
            while (matcher.find()) {
                results.add(matcher.group());
            }
        }
        System.out.println(results);
    }


    @Test
    public void diff() throws IOException {
        String filePath1 = "E:\\work\\project\\my-github\\java-notes\\src\\test\\java\\com\\sun\\demo\\base\\mcdRequestURI";
        String filePath2 = "E:\\work\\project\\my-github\\java-notes\\src\\test\\java\\com\\sun\\demo\\base\\nomoreRequestURI";

        FileInputStream mcdInput = new FileInputStream(filePath1);
        FileInputStream commonInput = new FileInputStream(filePath2);
        List<String> mcdLines = IOUtils.readLines(mcdInput, Charset.defaultCharset()).stream().distinct().collect(Collectors.toList());
        List<String> generalLines = IOUtils.readLines(commonInput, Charset.defaultCharset()).stream().distinct().collect(Collectors.toList());

        Collection<String> diffList = CollectionUtils.subtract(mcdLines, generalLines);
        diffList.forEach(System.out::println);
    }
}
