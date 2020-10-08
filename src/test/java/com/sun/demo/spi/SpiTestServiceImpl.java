package com.sun.demo.spi;

public class SpiTestServiceImpl implements SpiTestService {


    @Override
    public void hello() {
        System.out.println(" is test ");
    }
}
