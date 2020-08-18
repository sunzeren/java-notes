package com.sun.demo.current.stm;

@FunctionalInterface
public interface TxnRunnable {
    void run(Txn txn);
}
