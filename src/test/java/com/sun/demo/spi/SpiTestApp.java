package com.sun.demo.spi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * spi
 * service-provider interface
 * 即 服务提供者接口
 * 1。在META-INF 目录中建立 services 目录
 * 2。建立需要实现接口的  全限定名，为文件名称，内容为需要实现的接口实现类的全限定名
 */
@Controller
@RequestMapping(value = "test")
public class SpiTestApp {

    @RequestMapping("/spi")
    @ResponseBody
    public void test() {
        ServiceLoader<SpiTestService> testServices = ServiceLoader.load(SpiTestService.class);
        Iterator<SpiTestService> iterator = testServices.iterator();
        while (iterator.hasNext()) {
            iterator.next().hello();
        }
    }

}
