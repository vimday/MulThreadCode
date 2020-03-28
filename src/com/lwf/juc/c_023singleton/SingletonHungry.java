package com.lwf.juc.c_023singleton;
/**
 * 饿汉式单例
 * 类加载到内存后，就实例化一次单例，jvm来保证线程安全
 * 缺点：类只要加载就会完成实例化
 * Class.forName("")
 */
public class SingletonHungry {
    private static final SingletonHungry INSTANCE =new SingletonHungry();
    private SingletonHungry(){};

    public static SingletonHungry getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) {
        SingletonHungry m1= SingletonHungry.getInstance(),m2= SingletonHungry.getInstance();
        System.out.println(m1==m2);
    }
}
