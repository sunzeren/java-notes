package com.sun.demo.current.lock;

import com.alibaba.dubbo.remoting.exchange.support.DefaultFuture;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * 此类为 Guarded Suspension(守卫,挂起)  模式 的典型实现
 * 其本质上是一种等待唤醒机制的实现
 *
 *
 *
 * <p>
 * Guarded Suspension 模式也常被称作 Guarded Wait 模式、Spin Lock 模式（因为使用了 while 循环去等待），这些名字都很形象，
 * 不过它还有一个更形象的非官方名字：多线程版本的 if
 *
 * <p>
 * 官方案例实现可参考
 * {@link DefaultFuture}
 *
 * @param <T> 需要被保护的对象
 */
public class GuardedObject<T> {
    //保存所有GuardedObject
    final static Map<Object, GuardedObject> gos = new ConcurrentHashMap<>();
    final Lock lock = new ReentrantLock();
    final Condition done = lock.newCondition();
    final int timeout = 2;
    //受保护的对象
    T obj;

    //静态方法创建GuardedObject
    static <K> GuardedObject create(K key) {
        GuardedObject go = new GuardedObject();
        gos.put(key, go);
        return go;
    }

    static <K, T> void fireEvent(K key, T obj) {
        GuardedObject go = gos.remove(key);
        if (go != null) {
            go.onChanged(obj);
        }
    }

    //获取受保护对象
    T get(Predicate<T> p) {
        lock.lock();
        try {
            //MESA管程推荐写法
            while (!p.test(obj)) {
                done.await(timeout, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        //返回非空的受保护对象
        return obj;
    }

    //事件通知方法
    void onChanged(T obj) {
        lock.lock();
        try {
            this.obj = obj;
            done.signalAll();
        } finally {
            lock.unlock();
        }
    }
}