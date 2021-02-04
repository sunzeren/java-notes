package com.sun.demo.io.log;

import com.sun.demo.io.log.bean.PlatformLogReq;
import com.sun.demo.io.log.bean.PlatformLogRsp;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScanLog {

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final CustomizableThreadFactory threadFactory = new CustomizableThreadFactory("scan-log-");
    private static final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1, threadFactory);

    private static final Logger log = LoggerFactory.getLogger(ScanLog.class);


    public static void main(String[] args) {
        // 请求参数
        LocalDateTime startTime = LocalDateTime.of(2021, 2, 5, 0, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2021, 2, 5, 23, 59, 59, 999);
        PlatformLogReq logReq = PlatformLogReq.getInstance(startTime, endTime, PlatformLogReq.SysTypeEnum.DMS_MCD);
        logReq.setLogLevel(PlatformLogReq.LogLevelEnum.ERROR);
        logReq.setPageSize(30);
        // message Handel
        MsgHandel msgHandel = new ErrorMsgHandel(
                String.format("C:\\Users\\admin\\Desktop\\scanLog(%s-%s)",
                        startTime.format(DateTimeFormatter.ofPattern("MMddHHmm")),
                        endTime.format(DateTimeFormatter.ofPattern("MMddHHmm"))));

        scheduledThreadPool.scheduleAtFixedRate(new RunnableTask(PlatformLogType.BUSINESS_LOG, logReq, msgHandel), 1, 10, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleAtFixedRate(new ExportTask(msgHandel), 15, 60, TimeUnit.SECONDS);

    }

    public static class RunnableTask implements Runnable {

        private final PlatformLogType platformLogType;
        private final PlatformLogReq logReq;
        private final MsgHandel msgHandel;

        public RunnableTask(PlatformLogType platformLogType, PlatformLogReq logReq, MsgHandel msgHandel) {
            this.platformLogType = platformLogType;
            this.logReq = logReq;
            this.msgHandel = msgHandel;
        }

        @Override
        public void run() {
            log.info("扫描心跳: 请求参数:{},", logReq.toString());

            PlatformLogRsp logRsp = restTemplate.getForObject(platformLogType.getValue().concat(logReq.getUrlParameters()), PlatformLogRsp.class);

            if (logRsp == null) {
                System.err.println("中台日志解析异常!");
                return;
            }
            PlatformLogRsp.DataBean data = logRsp.getData();
            for (PlatformLogRsp.DataBean.RowsBean row : data.getRows()) {
                msgHandel.handelMsg(platformLogType, logReq, row);
            }
        }
    }

    public static class ExportTask implements Runnable {

        private final MsgHandel msgHandel;

        public ExportTask(MsgHandel msgHandel) {
            this.msgHandel = msgHandel;
        }

        @Override
        public void run() {
            msgHandel.exportMsg();
        }
    }


    @Getter
    public enum PlatformLogType {
        // 请求日志 URL
        GATEWAY_LOG("https://log.can-dao.com/log/rsplog", "网关日志"),
        // 业务日志 URL
        BUSINESS_LOG("https://log.can-dao.com/log/syslog", "业务日志"),

        ;

        PlatformLogType(String value, String memo) {
            this.value = value;
            this.memo = memo;
        }

        private final String value;
        private final String memo;

        public String getValue() {
            return value;
        }
    }
}
