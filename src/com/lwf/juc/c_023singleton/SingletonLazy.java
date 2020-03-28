package com.lwf.juc.c_023singleton;

/**
 * 懒汉模式 线程不安全
 */
public class SingletonLazy {
    private static SingletonLazy INSTACNE;
    private SingletonLazy(){}
    //解决方法可以加上synchronized 但这样锁得粒度太大->DCL
    public static /*synchronized*/ SingletonLazy getInstance(){
        if(INSTACNE==null){
            try {
                Thread.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            INSTACNE =new SingletonLazy();
        }
        return INSTACNE;
    }

    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            new Thread(()-> System.out.println(SingletonLazy.getInstance())).start();
        }
    }
}
