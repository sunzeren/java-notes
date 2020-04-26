package com.sun.demo.request.crawler;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 爬虫任务表
 */
@ToString
@Getter
@Setter
public class CrawlerTask implements Serializable {

    //任务ID
    private String id;

    //路径
    private String link;

    //标题
    private String title;

    //来源
    private String source;

    //样式
    private String style;

    //内容
    private String content;

}
