package com.sun.demo.io.log.task;

import com.sun.demo.io.log.MsgHandel;
import com.sun.demo.io.log.bean.PlatformLogReq;
import com.sun.demo.io.log.bean.PlatformLogRsp;
import com.sun.demo.io.log.bean.PlatformLogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 分页导出任务
 * 分页查询数据后,汇总导出
 */
public class PageExportTask extends RunnableTask {

    private static final Logger log = LoggerFactory.getLogger(PageExportTask.class);

    public PageExportTask(PlatformLogType platformLogType, PlatformLogReq logReq, MsgHandel msgHandel) {
        super(platformLogType, logReq, msgHandel);
    }

    @Override
    protected void handelRsp(PlatformLogRsp logRsp) {
        PlatformLogRsp.DataBean data = logRsp.getData();

        // 当前页
        int page = data.getPage();
        // 总页数
        int pages = data.getPages();
        // 总数据行数
        int total = data.getTotal();

        log.info("分页导出,处理进度 页数:{}/{} 总数据量:{}", page, pages, total);

        // 处理导出
        super.handelRsp(logRsp);

        // 未达到最后一页则继续处理
        if (page < pages) {
            nextPageHandel();
        } else {
            msgHandel.exportMsg();
        }
    }


    /**
     * 处理下一页
     */
    private void nextPageHandel() {
        logReq.setPageNow(logReq.getPageNow() + 1);
        super.run();
    }
}