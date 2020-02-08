package com.sun.demo.export;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.*;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Author by Sun, Date on 2020/2/8.
 * PS: Not easy to write code, please indicate.
 * 测试导出word文件
 */
public class ExportWord {

    /**
     * 简单导出
     */
    @Test
    public void exportWordSimple() {
        Resource resource = new ClassPathResource("file/template.docx");
        String outPath = "C:\\Users\\Sun\\Desktop\\out_template.docx";
        //核心API采用了极简设计，只需要一行代码
        try {
            XWPFTemplate.compile(resource.getInputStream()).render(new HashMap<String, Object>() {{
                put("title", "Poi-tl 模板引擎,这是title变量");
            }}).writeToFile(outPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("生成doc文件失败");
        }
        System.out.println("已生成docx文件至:" + outPath);
    }

    /**
     * 综合导出 word
     *
     * @throws IOException
     */
    @Test
    public void exportComplexTest() throws IOException {
        Resource resource = new ClassPathResource("file/complex_template.docx");
        String outPath = "C:\\Users\\Sun\\Desktop\\complex_template.docx";

        RowRenderData header = RowRenderData.build(new TextRenderData("ff9800", "姓名"), new TextRenderData("ff9800", "学历"));

        RowRenderData row0 = RowRenderData.build("张三", "研究生");
        RowRenderData row1 = RowRenderData.build("李四", "博士");
        RowRenderData row2 = RowRenderData.build("王五", "博士后");

        XWPFTemplate.compile(resource.getInputStream()).render(new HashMap<String, Object>() {{
            put("table", new MiniTableRenderData(header, Arrays.asList(row0, row1, row2)));
            put("author", new TextRenderData("010101", " Sun"));
            put("introduce", "http://www.deepoove.com");
            put("link", new HyperLinkTextRenderData("website.", "http://www.deepoove.com"));
            put("feature", new NumbericRenderData(new ArrayList<TextRenderData>() {
                {
                    add(new TextRenderData("Plug-in grammar"));
                    add(new TextRenderData("Supports word text, header..."));
                    add(new TextRenderData("Not just templates, but also style templates"));
                }
            }));
        }}).writeToFile(outPath);
    }

}
