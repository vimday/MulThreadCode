package com.lwf.juc.c_023singleton;

public enum SingletonENUM {
    INSTANCE;
    public int i = 0;
    public void leaveTheBuilding() {
        i++;
        System.out.println("我现在是单例，这是我第"+i+"次调用！！");
    }

    // 调用这样子调用
    public static void main(String[] args) {
        for(int z = 0 ; z<10 ;z++){
            SingletonENUM  demo = SingletonENUM.INSTANCE;
            demo .leaveTheBuilding();
        }
    }
}


//public enum SingletonENUM{
//    INSTANCE;
//    public void doSomething(){
//        System.out.println("do......");
//    }
//
//    public static void main(String[] args) {
//        SingletonENUM.INSTANCE.doSomething();
//    }
//}

