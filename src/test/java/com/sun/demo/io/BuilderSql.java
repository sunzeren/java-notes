package com.sun.demo.io;

import com.google.common.collect.Iterables;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author by Sun, Date on 2020/2/22.
 * PS: Not easy to write code, please indicate.
 * 构建sql脚本
 */
public class BuilderSql {

    // sql模板

    public static void main(String[] args) throws IOException {
        String outputFile = "C:\\Users\\admin\\Desktop\\result.sql";

        ClassPathResource resource = new ClassPathResource("file/temp");
        List<String> sourceDataList = IOUtils.readLines(resource.getInputStream(), "UTF-8");

        String resultSql = paddedPartitionHandel(sourceDataList);

        IOUtils.write(resultSql, new FileOutputStream(outputFile), "UTF-8");
    }

    /**
     * 分片替换处理
     *
     * @param sourceDataList 数据源
     * @return
     */
    private static String paddedPartitionHandel(List<String> sourceDataList) {
        String sqlTemplate = "SELECT SUM(order_send_diff_time) as '配送总时长',count(order_id) as '总订单数' FROM report_order_summary WHERE store_id in (#{storeIds})AND expected_finish_time >= '2019-07-01'AND expected_finish_time < '2019-08-01' ;";
        String[] tags = Arrays.array("#{storeIds}");

        Iterable<List<String>> splitData = Iterables.paddedPartition(sourceDataList, 5);
        ArrayList<String> resultSqlList = new ArrayList<>(1024);
        for (List<String> spitData : splitData) {
            replaceMark(Lists.newArrayList(StringUtils.join(spitData, ",")), sqlTemplate, tags, resultSqlList);
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
