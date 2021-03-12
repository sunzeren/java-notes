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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Author by Sun, Date on 2020/2/22.
 * PS: Not easy to write code, please indicate.
 * 构建sql脚本
 */
public class BuilderSql {


    public static void main(String[] args) throws IOException {
        String outputFile = "C:\\Users\\admin\\Desktop\\3-12安宇上海门店提前关单\\sql";
        // 是否强制创建目录
        FileUtils.forceMkdir(new File(outputFile));

        // 以月为周期
//        exportOfDateCycle(outputFile);

        // 批量生成sql
        exportOfTemplateName(outputFile);
    }

    /**
     * 以日期周期为维度来生成导出sql
     *
     * @param outputFile
     * @throws IOException
     */
    private static void exportOfDateCycle(String outputFile) throws IOException {
        ClassPathResource resource = new ClassPathResource("file/MCD_HORSEMAN_IDS");
        List<String> sourceDataList = IOUtils.readLines(resource.getInputStream(), "UTF-8");

        LocalDateTime start = LocalDateTime.of(2018, 7, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2019, 6, 30, 0, 0, 0);

        // 查询一年份的数据
//        for (int i = 0; i < 6; i++) {
//            String startTime = start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            String endTime = end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String sqlTemplate = "SELECT horseman_id,horseman_name,avg(delivery_total_time / order_number_total) as '日平均配送时长' \n" +
                    "FROM horseman_statistics_report \n" +
                    "WHERE horseman_id in (#{horsemanIds})\n" +
                    "AND token_id IN(10,24,25,56,61,62,63,64,66,261)\n" +
                    "AND select_type = 0 AND specific_time < '2021-01-01 00:00:00'\n" +
                    "GROUP BY horseman_id;\n";
            String[] variableArrays = Arrays.array("#{horsemanIds}");
            String resultSql = paddedPartitionHandel(sourceDataList, sqlTemplate, variableArrays);

            IOUtils.write(resultSql, new FileOutputStream(String.format("%s\\%s.sql", outputFile, "HorsemanDayAvg")), "UTF-8");
//            start = start.plusMonths(1);
//            end = end.plusMonths(1);
//        }
    }

    /**
     *  构建 source.size() 条sql,并将 source.get() 置入模板变量中
     */
    private static void exportOfTemplateName(String outputFile) throws IOException {
        ClassPathResource resource = new ClassPathResource("file/MCD_HORSEMAN_IDS");
        List<String> sourceDataList = IOUtils.readLines(resource.getInputStream(), "UTF-8");

        String sqlTemplate = "SELECT\n" +
                "\torder_id AS 'dms订单id',\n" +
                "\torder_no AS '订单编号',\n" +
                "\tstore_name AS '门店名称',\n" +
                "\treceiver_address AS '配送地址',\n" +
                "\thorseman_name AS '骑手名称',\n" +
                "\tmark_exception_reason AS '标记异常原因',\n" +
                "\tcompel_finish_reason AS '强制完成原因',\n" +
                "\tclose_order_distance AS '关单距离',\n" +
                "\tCONCAT( receiver_lng, ',', receiver_lng ) AS '收货地址坐标',\n" +
                "\tclose_lng AS '关单经度',\n" +
                "\tclose_lat AS '关单纬度',\n" +
                "\tdelivery_distance AS '配送距离' \n" +
                "FROM\n" +
                "\treport_order_summary_#{storeId} \n" +
                "WHERE\n" +
                "\texpected_finish_time >= '2021-02-01 00:00:00' \n" +
                "\tAND expected_finish_time < '2021-03-01 00:00:00' \n" +
                "\tAND is_advance_close_order = 2;";
        String[] variableArrays = Arrays.array("#{storeId}");

        List<String> resultLis = new ArrayList<>();
        for (String data : sourceDataList) {
            replaceMark(Collections.singletonList(data), sqlTemplate, variableArrays, resultLis);
        }
        IOUtils.write(StringUtils.join(resultLis, "\r\n"), new FileOutputStream(String.format("%s\\%s.sql", outputFile, "订单明细")), "UTF-8");
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

        Iterable<List<String>> splitDataList = Iterables.paddedPartition(sourceDataList, 300);
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
