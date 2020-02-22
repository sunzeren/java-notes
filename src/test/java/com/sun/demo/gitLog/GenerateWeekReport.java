package com.sun.demo.gitLog;

import com.deepoove.poi.XWPFTemplate;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author by Sun, Date on 2020/1/10.
 * PS: Not easy to write code, please indicate.
 * 根据git提交日志 生成周报
 * 1. git提交日志转换为 数组对象
 * //TODO Sun 2020/2/8 根据数组对象生成doc文件
 */
public class GenerateWeekReport {

    @Test
    public void gitLog() throws IOException {
        // 获取git日志字符
        File path = new File("E:\\work\\eskyray\\有技财务测评\\youjicaishui\\src");
        final String logString = this.getGitLogStr(path);
        // 转换为每行记录
        List<String> logOfOneRecordList = this.converterToLineList(logString);
        // 转换为对象集合
        List<GitLog> gitLogs = this.converterToObject(logOfOneRecordList);
        System.out.println("gitLogs = " + gitLogs);

        this.generateWeeklyReport(gitLogs, "C:\\Users\\Sun\\Desktop\\");
    }

    /**
     * 生成周报
     *
     * @param gitLogs
     * @param filePath
     */
    private void generateWeeklyReport(List<GitLog> gitLogs, String filePath) {
        Resource resource = new ClassPathResource("file/week-report.docx");
        String fileName = "周报";
        String outPath = filePath + fileName + ".docx";

        try {
            WeekReport model = WeekReport.builder(gitLogs);
            XWPFTemplate.compile(resource.getInputStream()).render(model).writeToFile(outPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转换为每行单独的集合
     *
     * @param logString 00
     * @return
     */
    private List<String> converterToLineList(String logString) {
        final List<String> logLineList = Stream.of(logString.split("\n")).collect(Collectors.toList());
        List<String> logOfOneRecordList = new ArrayList<>();
        StringBuilder lineTemp = new StringBuilder();
        for (int i = 0; i < logLineList.size(); i++) {
            String temp = logLineList.get(i);
            boolean isNewLine = i != 0 && temp.startsWith("commit");
            if (isNewLine) {
                logOfOneRecordList.add(lineTemp.toString());
                lineTemp = new StringBuilder();
            }
            lineTemp.append(temp);
            lineTemp.append("\n");
        }
        return logOfOneRecordList;
    }

    /**
     * 获取 当前所在目录的git 日志字符
     *
     * @param path 运行目录
     * @return git 日志
     * @throws IOException
     */
    private String getGitLogStr(File path) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        final Process exec = runtime.exec("git log", null, path);
        return IOUtils.toString(exec.getInputStream(), StandardCharsets.UTF_8);
    }

    /**
     * 将字符转换为 对象
     *
     * @param recordList
     * @return
     */
    private List<GitLog> converterToObject(List<String> recordList) {
        List<GitLog> gitLogList = new ArrayList<>();
        for (String record : recordList) {
            GitLog gitLog = new GitLog();
            for (String line : record.split("\n")) {
                if (line.isEmpty()) {
                    continue;
                }
                if (line.startsWith("commit")) {
                    gitLog.setCommitNo(StringUtils.replace(line, "commit ", ""));
                } else if (line.startsWith("Author")) {
                    gitLog.setAuthor(StringUtils.replace(line, "Author:", ""));
                } else if (line.startsWith("Date")) {
                    gitLog.setTime(strParseToDate(line));
                } else {
                    gitLog.setRemark(StringUtils.trimToEmpty(line));
                }
            }
            gitLogList.add(gitLog);
        }
        return gitLogList;
    }

    /**
     * 日期转换 为Date
     *
     * @param dateStr
     * @return
     */
    private Date strParseToDate(String dateStr) {
        dateStr = StringUtils.trimToEmpty(StringUtils.replace(dateStr, "Date:", ""));
        try {
            return DateUtils.parseDate(dateStr, Locale.ENGLISH, "EEE MMM d HH:mm:ss yyyy ZZZZ");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
