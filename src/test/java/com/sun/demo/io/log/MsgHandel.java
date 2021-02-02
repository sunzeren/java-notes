package com.sun.demo.io.log;

import com.sun.demo.io.log.bean.PlatformLogReq;
import com.sun.demo.io.log.bean.PlatformLogRsp;

public interface MsgHandel {

    /**
     * 信息处理
     */
    void handelMsg(ScanLog.PlatformLogType platformLogType, PlatformLogReq logReq, PlatformLogRsp.DataBean.RowsBean row);

    /**
     * 导出信息数据
     */
    void exportMsg();
}
