package com.sun.demo.io.log;

import com.sun.demo.io.log.bean.PlatformLogReq;
import com.sun.demo.io.log.bean.PlatformLogRsp;
import com.sun.demo.io.log.bean.PlatformLogType;

public interface MsgHandel {

    /**
     * 信息处理
     */
    void handelMsg(PlatformLogType platformLogType, PlatformLogReq logReq, PlatformLogRsp.DataBean.RowsBean row);

    /**
     * 导出信息数据
     */
    void exportMsg();

    /**
     * 应用程序关闭的钩子
     */
    default void closeApplicationEvent() {
        System.out.println("应用程序退出!");
    };
}
