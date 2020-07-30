package com.sun.demo.current.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * Author by Sun, Date on 2020/7/29.
 * PS: Not easy to write code, please indicate.
 * 以下来自官方示例
 * StampedLock的文档，差不多是StampedLock的最佳实现模板了
 */
public class StampedLockTest {
    final StampedLock sl = new StampedLock();
    private int x, y;

    //计算到原点的距离
    int distanceFromOrigin() {
        // 乐观读
        long stamp = sl.tryOptimisticRead();
        // 读入局部变量，
        // 读的过程数据可能被修改
        int curX = x, curY = y;
        //判断执行读操作期间，
        //是否存在写操作，如果存在，
        //则sl.validate返回false
        if (!sl.validate(stamp)) {
            // 升级为悲观读锁
            stamp = sl.readLock();
            try {
                curX = x;
                curY = y;
            } finally {
                //释放悲观读锁
                sl.unlockRead(stamp);
            }
        }
        return (int) Math.sqrt(curX * curX + curY * curY);
    }

}
