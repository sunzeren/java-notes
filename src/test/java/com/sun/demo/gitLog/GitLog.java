package com.sun.demo.gitLog;

import lombok.Data;

import java.util.Date;

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
public class GitLog {
    // 提交版本号
    private String commitNo;
    // 作者
    private String author;
    // 日期
    private Date time;
    // 提交注释
    private String remark;

}
