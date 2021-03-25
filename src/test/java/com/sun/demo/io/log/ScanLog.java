package com.sun.demo.io.log;

import com.sun.demo.io.log.bean.PlatformLogReq;
import com.sun.demo.io.log.bean.PlatformLogType;
import com.sun.demo.io.log.task.ExportTask;
import com.sun.demo.io.log.task.PageExportTask;
import com.sun.demo.io.log.task.RunnableTask;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScanLog {

    private static final CustomizableThreadFactory threadFactory = new CustomizableThreadFactory("scan-log-");
    private static final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);

    private static final Logger log = LoggerFactory.getLogger(ScanLog.class);


    public static void main(String[] args) {
        // 请求参数
        LocalDateTime startTime = LocalDateTime.of(2021, 3, 25, 0, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2021, 3, 25, 23, 59, 59, 999);
        PlatformLogReq logReq = PlatformLogReq.getInstance(startTime, endTime, PlatformLogReq.SysTypeEnum.DMS_MCD);
        logReq.setLogLevel(PlatformLogReq.LogLevelEnum.ERROR);
        logReq.setPageSize(200);
        // message Handel
        MsgHandel msgHandel = new ErrorMsgHandel(
                String.format("C:\\Users\\admin\\Desktop\\scanLog(%s-%s)",
                        startTime.format(DateTimeFormatter.ofPattern("MMddHHmm")),
                        endTime.format(DateTimeFormatter.ofPattern("MMddHHmm"))));

        scheduledThreadPool.scheduleAtFixedRate(new RunnableTask(PlatformLogType.BUSINESS_LOG, logReq, msgHandel), 1, 10, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleAtFixedRate(new ExportTask(msgHandel), 15, 60, TimeUnit.SECONDS);
        Runtime.getRuntime().addShutdownHook(new Thread(msgHandel::closeApplicationEvent));
    }


    @Test
    public void 分页导出Excel() {
        // 请求参数
        LocalDateTime startTime = LocalDateTime.of(2021, 3, 25, 0, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2021, 3, 25, 23, 59, 59, 999);
        PlatformLogReq logReq = PlatformLogReq.getInstance(startTime, endTime, PlatformLogReq.SysTypeEnum.DMS_MCD);
        logReq.setLogLevel(PlatformLogReq.LogLevelEnum.ERROR);
        logReq.setPageSize(10);
        // message Handel
        MsgHandel msgHandel = new ErrorMsgHandel(
                String.format("C:\\Users\\admin\\Desktop\\scanLog(%s-%s)",
                        startTime.format(DateTimeFormatter.ofPattern("MMddHHmm")),
                        endTime.format(DateTimeFormatter.ofPattern("MMddHHmm"))));

        new PageExportTask(PlatformLogType.BUSINESS_LOG, logReq, msgHandel).run();
    }
}
