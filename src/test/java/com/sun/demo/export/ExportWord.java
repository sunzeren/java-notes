package com.sun.demo.export;

import com.deepoove.poi.XWPFTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;

/**
 * Author by Sun, Date on 2020/2/8.
 * PS: Not easy to write code, please indicate.
 * 测试导出word文件
 */
public class ExportWord {

    public static void main(String[] args) {
        Resource resource = new ClassPathResource("file/template.docx");
        String outPath = "C:\\Users\\Sun\\Desktop\\out_template.docx";
        //核心API采用了极简设计，只需要一行代码
        try {
            XWPFTemplate.compile(resource.getInputStream()).render(new HashMap<String, Object>() {{
                put("title", "Poi-tl 模板引擎");
            }}).writeToFile(outPath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("生成doc文件失败");
        }
        System.out.println("已生成docx文件至:" + outPath);
    }

}
