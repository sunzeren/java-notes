package com.sun.demo.base;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.WeakHashMap;

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


    @Test
    public void weakHashMapTest() {
        // 初始化3个“弱键”
        String w1 = new String("one");
        String w2 = new String("two");
        String w3 = new String("three");
        // 新建WeakHashMap
        Map<String, String> wMap = new WeakHashMap<>();
        // 添加键值对
        wMap.put(w1, "w1");
        wMap.put(w2, "w2");
        wMap.put(w3, "w3");

        // 打印出wmap
        System.out.printf("\nwMap:%s\n", wMap);

        // containsKey(Object key) :是否包含键key
        System.out.printf("contains key two : %s\n", wMap.containsKey("two"));
        System.out.printf("contains key five : %s\n", wMap.containsKey("five"));

        // containsValue(Object value) :是否包含值value
        System.out.printf("contains value 0 : %s\n", wMap.containsValue("w1"));

        // remove(Object key) ： 删除键key对应的键值对
        wMap.remove("three");

        System.out.printf("wMap: %s\n", wMap);


        // ---- 测试 WeakHashMap 的自动回收特性 ----

        // 将w1设置null。
        // 这意味着“弱键”w1再没有被其它对象引用，调用gc时会回收WeakHashMap中与“w1”对应的键值对
        w1 = null;
        // 内存回收。这里，会回收WeakHashMap中与“w1”对应的键值对
        System.gc();

        //region 因为gc的执行是异步的，所以等待一段时间
        try {
            Thread.sleep(1000 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //endregion

        // 遍历WeakHashMap
        Iterator<Map.Entry<String, String>> iter = wMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> en = iter.next();
            System.out.printf("next : %s - %s\n", en.getKey(), en.getValue());
        }
        // 打印WeakHashMap的实际大小
        System.out.printf(" after gc WeakHashMap size:%s\n", wMap.size());
    }


    @Test
    public void treeMap() {

        // 运行发现, TreeMap 默认情况下(可以通过构造器传入Comparator)是以 Key的排序来排序的,
        // 如 data.firstKey()会返回 Key最小的一个键
        TreeMap<Object, Object> data = new TreeMap<>();

        data.put(2, "1");
        data.put(5, "2");
        data.put(12, "我是最大的");
        data.put(0, "key为0的Value");
        data.put(1, "5");
        data.put(-1, "我是最小的");

        // 将会返回 Key最小的键值
        System.out.println(data.get(data.firstKey()));

    }
}
