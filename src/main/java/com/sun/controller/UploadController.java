package com.sun.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;

/**
 * @Author: ZeRen.
 * @Date: 2019/9/24 15:07
 * 上传文件测试控制器
 * <p>
 * 当上传文件时程序接收到请求时,会先把文件保存在计算机硬盘中的临时文件夹中,当文件被读取后,则会删除该文件
 * 如此项目虽为SpringBoot但内嵌应用服务器为TomCat,上传文件请求会保存至如下目录(可能系统不同目录不一致,运行项目的系统为Windows7)
 * C:\Users\admin\AppData\Local\Temp\tomcat.1165940721791455434.8080\work\Tomcat\localhost\ROOT
 * 为如上目录, 其中 tomcat.1165940721791455434 后面的数字应该是一个时间戳 后面加端口, 数字每次启动时不一样,所以主要目录为C:\Users\admin\AppData\Local\Temp
 * 如想查看上传的文件保存在哪个目录,则可以打断点查看 HttpServletRequest 中的 Parts集合对象,并查看其中Part
 * 如此项目Part的实现类为 ApplicationPart 中的 location 属性,其为private属性,所以如需查看需要使用debug或反射
 * </p>
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    @PostMapping(value = "/uploadOneFile")
    @ApiOperation(value = "上传一个文件", notes = "上传一个文件,需请求的表单域为file,才可读取文件")
    public void upload(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            List<String> readLines = IOUtils.readLines(inputStream, Charset.defaultCharset());
            readLines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @PostMapping(value = "/upload-request")
    @ApiOperation(value = "通过Request对象读取文件流", notes = "上传一个文件")
    public void upload_2(HttpServletRequest request) {
        System.out.println("====== 上传文件大小: " + request.getContentLength() + "字节 ======");
        try {
            Collection<Part> parts = request.getParts();
            for (Part part : parts) {
                try (InputStream inputStream = part.getInputStream()) {
                    List<String> readLines = IOUtils.readLines(inputStream, Charset.defaultCharset());
                    readLines.forEach(System.out::println);
                }
                System.out.println();
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }


    @GetMapping(value = "/find")
    @ApiOperation(value = "文件查找", notes = "查找一个文件")
    public String find(String fileName) {
        System.out.println("fileName = " + fileName);
        return "";
    }


}
