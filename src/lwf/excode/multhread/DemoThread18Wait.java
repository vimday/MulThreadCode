package lwf.excode.multhread;

import java.util.ArrayList;
import java.util.List;

public class DemoThread18Wait {

    private volatile List<String> list=new ArrayList<>();
    private Object lock=new Object();

    public void put(){
        synchronized (lock){
            for (int i=0;i<10;i++){
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                list.add("A");
                System.out.println("线程"+Thread.currentThread().getName()+"添加第"+i+"个元素");
                if(list.size()==5){
                    lock.notify();//并不释放锁
                    System.out.println("线程"+Thread.currentThread().getName()+"发出通知(notify)....");
                }
            }
        }

    }

    public void get(){
        synchronized (lock){
            try {
                System.out.println("线程"+Thread.currentThread().getName()+"业务处理，发现需要的数据没准备好，发起等待");
                System.out.println("线程"+Thread.currentThread().getName()+"wait");
                lock.wait();//wait释放锁
                System.out.println("线程"+Thread.currentThread().getName()+"被唤醒");
                list.forEach(s->{
                    System.out.println("线程"+Thread.currentThread().getName()+"获取元素："+s);
                });


            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final DemoThread18Wait dt= new DemoThread18Wait();
        new Thread(new Runnable() {
            @Override
            public void run() {
                dt.get();
            }
        },"t1").start();

        Thread.sleep(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dt.put();
            }
        },"t2").start();
    }
}
