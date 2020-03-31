package com.lwf.juc.c_023singleton;

public class SingletonDCL {
    private static  volatile SingletonDCL INSTANCE;//必须要加volatile 防止指令重排
    private SingletonDCL(){}

    public static SingletonDCL getINSTANCE(){
        //可能会有其他的业务逻辑
        if(INSTANCE==null){
            synchronized (SingletonDCL.class){
                if(INSTANCE==null){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE=new SingletonDCL();
                }

            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            new Thread(()-> System.out.println(SingletonDCL.getINSTANCE())).start();
        }
    }
}
