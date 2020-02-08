package com.sun.demo.gitLog;

import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.RowRenderData;
import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author by Sun, Date on 2020/2/8.
 * PS: Not easy to write code, please indicate.
 * 一条Git提交记录的对象
 * eg:
 * commit f185022eb9a93c3390f1a2f46d6409ed284b2f26
 * Author: sunzeren <honeyze@163.com>
 * Date:   Sat Feb 8 15:01:58 2020 +0800
 * <p>
 * 增加Spring,注解,xml的BeanFactory的依赖注入,依赖查找相关解析
 */
@Data
public class WeekReport {

    // header
    private static final RowRenderData headList = RowRenderData.build("编号", "工作内容", "开始时间", "完成时间", "进度情况");

    // 标题
    private String title;
    // 公司名称
    private String companyName;
    // 姓名
    private String name;
    // 岗位
    private String job;
    // 周报开始时间
    private String reportStartDate;
    // 周报结束时间
    private String reportEndDate;
    // 汇报对西昂
    private String reportTo;
    // 汇报内容
    private MiniTableRenderData content;

    private WeekReport() {
    }

    public static WeekReport builder(List<GitLog> contentList) {
        WeekReport weekReport = new WeekReport();
        weekReport.setTitle("天光科技工作周报");
        weekReport.setCompanyName("广州天光信息科技有限公司");
        weekReport.setName("孙泽仁");
        weekReport.setJob("Java开发工程师");
        weekReport.setReportStartDate(DateFormatUtils.format(DateUtils.addDays(new Date(), -7), "yyyy年MM月dd日"));
        weekReport.setReportEndDate(DateFormatUtils.format(new Date(), "yyyy年MM月dd日"));
        weekReport.setReportTo("李文博");

        weekReport.setContent(converter(contentList));
        return weekReport;
    }

    private static MiniTableRenderData converter(List<GitLog> contentList) {
        List<RowRenderData> renderDataList = new ArrayList<>();
        for (int i = 0; i < contentList.size(); i++) {
            GitLog gitLog = contentList.get(i);
            String time = DateFormatUtils.format(gitLog.getTime(), "MM月dd日");
            RowRenderData renderData = RowRenderData.build(String.valueOf(i + 1), gitLog.getRemark(), time, time, "已完成");
            renderDataList.add(renderData);
        }
        return new MiniTableRenderData(headList, renderDataList);
    }
}
