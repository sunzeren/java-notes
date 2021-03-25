package com.sun.demo.io.log.task;

import com.sun.demo.io.log.MsgHandel;
import com.sun.demo.io.log.bean.PlatformLogReq;
import com.sun.demo.io.log.bean.PlatformLogRsp;
import com.sun.demo.io.log.bean.PlatformLogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 扫描日志
 */
public class RunnableTask implements Runnable {
    protected static final Logger log = LoggerFactory.getLogger(RunnableTask.class);
    protected static final RestTemplate restTemplate = new RestTemplate();


    protected final PlatformLogType platformLogType;
    protected final PlatformLogReq logReq;
    protected final MsgHandel msgHandel;

    public RunnableTask(PlatformLogType platformLogType, PlatformLogReq logReq, MsgHandel msgHandel) {
        this.platformLogType = platformLogType;
        this.logReq = logReq;
        this.msgHandel = msgHandel;
    }

    @Override
    public void run() {
        log.info("扫描心跳: 请求参数:{}", logReq.toString());

        PlatformLogRsp logRsp = doRequest();
        if (logRsp == null) {
            System.err.println("中台日志解析异常!");
            return;
        }
        handelRsp(logRsp);
    }

    protected void handelRsp(PlatformLogRsp logRsp) {
        PlatformLogRsp.DataBean data = logRsp.getData();
        for (PlatformLogRsp.DataBean.RowsBean row : data.getRows()) {
            msgHandel.handelMsg(platformLogType, logReq, row);
        }
    }

    protected PlatformLogRsp doRequest() {
        return restTemplate.getForObject(platformLogType.getValue().concat(logReq.getUrlParameters()), PlatformLogRsp.class);
    }
}