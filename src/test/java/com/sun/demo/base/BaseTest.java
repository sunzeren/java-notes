package com.sun.demo.base;

import org.junit.Test;

/**
 * @Author: ZeRen.
 * @Date: 2019/10/7 15:05
 */
public class BaseTest {

    @Test
    public void nullEqualsTest() {
        System.out.println("".equals(null));
        System.out.println(null == this);
        System.out.println(null == null);
    }
}
