package com.sun.demo.io.log;

import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.alibaba.excel.EasyExcel;
import com.sun.demo.io.log.bean.PlatformLogReq;
import com.sun.demo.io.log.bean.PlatformLogRsp;
import com.sun.demo.io.log.bean.PlatformLogType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ErrorMsgHandel implements MsgHandel {

    public static final List<String> whiteList = new ArrayList<>();
    public static final Set<PlatformLogRsp.DataBean.RowsBean> errorSet = new ConcurrentHashSet<>();

    private static long lastHashCode = 0;

    static {
        //region 白名单
//        whiteList.add("(请求异常)同步配送状态到上游:orderId");
//        whiteList.add("转派第三方骑手公司失败");
//        whiteList.add("新增订单坐标记录异常 orderId=");
//        whiteList.add("BusinessException");
//        whiteList.add("IOS推送");
        //endregion
    }

    private final String exportPath;

    public ErrorMsgHandel(String exportPath) {
        this.exportPath = exportPath;
    }


    public static boolean maybeIsError(String msg) {
        for (String whiteMsg : whiteList) {
            if (msg.contains(whiteMsg)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void handelMsg(PlatformLogType platformLogType, PlatformLogReq logReq, PlatformLogRsp.DataBean.RowsBean row) {
        if (logReq.getLogLevel() == PlatformLogReq.LogLevelEnum.ERROR && ErrorMsgHandel.maybeIsError(row.getMsg())) {
            if (maybeIsError(row.getMsg()) && errorSet.add(row)) {
                System.err.println(row.simpleText());
            }
        } else {
//                    System.out.println(row.simpleText());
        }
    }

    @Override
    public synchronized void exportMsg() {
        if (errorSet.isEmpty()) {
            return;
        }

        if (errorSet.hashCode() == lastHashCode) {
            return;
        }
        exportErrorData();
        lastHashCode = errorSet.hashCode();
    }

    private void exportErrorData() {
        System.out.println("触发异常信息导出!");
        List<PlatformLogRsp.DataBean.RowsBean> rowsList = new ArrayList<>(errorSet);
        rowsList.sort(Comparator.comparing(PlatformLogRsp.DataBean.RowsBean::getCreateTime));

        String fileName = exportPath + "-errorData.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, PlatformLogRsp.DataBean.RowsBean.class)
                .sheet("错误日志")
                .doWrite(rowsList);

    }
}
