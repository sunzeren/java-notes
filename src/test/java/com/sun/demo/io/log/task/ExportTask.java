package com.sun.demo.io.log.task;

import com.sun.demo.io.log.MsgHandel;

/**
 * 导出任务
 */
public class ExportTask implements Runnable {

    private final MsgHandel msgHandel;

    public ExportTask(MsgHandel msgHandel) {
        this.msgHandel = msgHandel;
    }

    @Override
    public void run() {
        msgHandel.exportMsg();
    }
}