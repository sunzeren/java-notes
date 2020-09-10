package com.sun.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Author by Sun, Date on 2018/9/3.
 * PS: Not easy to write code, please indicate.
 */
@AllArgsConstructor
@Data
public class SitProjectEntity {
    private String id;
    private Integer sort;
    private String url;
    private String backgroundUrl;
    private String header;
    private String content;
    private String footer;
}
