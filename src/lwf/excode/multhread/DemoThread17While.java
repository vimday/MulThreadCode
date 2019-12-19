package lwf.excode.multhread;

import java.util.ArrayList;
import java.util.List;

public class DemoThread17While {
    private volatile List<String> list=new ArrayList<>();
    private volatile boolean canGet=false;
    public void put(){
        for (int i=0;i<10;i++){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            list.add("A");
            System.out.println("线程"+Thread.currentThread().getName()+"添加第"+i+"个元素");
            if(i==5){
                canGet=true;
                System.out.println("线程"+Thread.currentThread().getName()+"发出通知");
            }
        }
    }

    public void get(){
        while (true){
            if(canGet){
                list.forEach(s->{
                    System.out.println("线程"+Thread.currentThread().getName()+"获取元素："+s);
                });
                break;
            }
        }
    }

    public static void main(String[] args) {
        final DemoThread17While dt=new DemoThread17While();
        new Thread(new Runnable() {
            @Override
            public void run() {
                dt.put();
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                dt.get();
            }
        },"t2").start();
    }

}
