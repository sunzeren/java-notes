package com.sun.demo.io.sql;

import com.google.common.collect.Iterables;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Author by Sun, Date on 2020/2/22.
 * PS: Not easy to write code, please indicate.
 * 构建sql脚本
 */
public class BuilderSql {


    public static void main(String[] args) throws IOException {
        String outputFile = "C:\\Users\\admin\\Desktop\\exception\\sql\\2018";
        // 是否强制创建目录
        FileUtils.forceMkdir(new File(outputFile));

        // 以月为周期
        exportOfDateCycle(outputFile);
    }

    /**
     * 以日期周期为维度来生成导出sql
     *
     * @param outputFile
     * @throws IOException
     */
    private static void exportOfDateCycle(String outputFile) throws IOException {
        ClassPathResource resource = new ClassPathResource("file/MCD_STORE_IDS");
        List<String> sourceDataList = IOUtils.readLines(resource.getInputStream(), "UTF-8");

        LocalDateTime start = LocalDateTime.of(2018, 7, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2018, 8, 1, 0, 0, 0);

        // 查询一年份的数据
        for (int i = 0; i < 6; i++) {
            String startTime = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String endTime = end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String sqlTemplate = "SELECT order_id,exception_type,exception_note,exception_time FROM order_exception \n" +
                    "WHERE exception_time >= '" + startTime + "'\n" +
                    "AND exception_time < '" + endTime + "'\n" +
                    "AND store_id in (#{storeIds});";
            String[] variableArrays = Arrays.array("#{storeIds}");
            String resultSql = paddedPartitionHandel(sourceDataList, sqlTemplate, variableArrays);

            IOUtils.write(resultSql, new FileOutputStream(String.format("%s\\%s.sql", outputFile, start.format(DateTimeFormatter.ofPattern("yyyyMMdd")))), "UTF-8");
            start = start.plusMonths(1);
            end = end.plusMonths(1);
        }
    }

    /**
     * 分片替换处理
     *
     * @param sourceDataList 数据源
     * @param sqlTemplate
     * @param tags
     * @return
     */
    private static String paddedPartitionHandel(List<String> sourceDataList, String sqlTemplate, String[] tags) {

        Iterable<List<String>> splitDataList = Iterables.paddedPartition(sourceDataList, 100);
        ArrayList<String> resultSqlList = new ArrayList<>(1024);
        for (List<String> splitData : splitDataList) {
            ArrayList<String> replaceData = Lists.newArrayList(StringUtils.join(splitData, ","));
            replaceMark(replaceData, sqlTemplate, tags, resultSqlList);
        }
        return StringUtils.join(resultSqlList, "\n");
    }

    private static void replaceMark(List<String> sourceDataList, String sqlTemplate, String[] tags, List<String> resultSqlList) {

        for (String data : sourceDataList) {
            if (StringUtils.isEmpty(data)) {
                continue;
            }
            if (data.startsWith("-")) {
                continue;
            }
            String[] infos = Arrays.array(data);
            if (Arrays.isNullOrEmpty(infos)) {
                continue;
            }
            for (int i = 0; i < infos.length; i++) {
                String info = infos[i];
                // 替换数组出现空字符拼接的问题
                if (StringUtils.contains(info, ",,")) {
                    info = Pattern.compile(",,+").matcher(info).replaceAll("");
                }
                String tag = tags[i];
                String realSql = sqlTemplate
                        .replace(tag, info);
                resultSqlList.add(realSql);
            }
        }
    }


    /**
     * 查找文本中的不同信息
     */
    @Test
    public void findDifferent() throws IOException {

        ClassPathResource resource = new ClassPathResource("file/excel待导入经销商信息");
        ClassPathResource exist = new ClassPathResource("file/存在的经销商");
        List<String> companyList = IOUtils.readLines(resource.getInputStream(), "UTF-8");
        List<String> existList = IOUtils.readLines(exist.getInputStream(), "UTF-8");

        for (String code : companyList) {
            if (!existList.contains(code)) {
                System.out.println(code);
            }
        }
    }
}
