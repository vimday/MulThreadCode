package lwf.excode.multhread;

import javax.sound.midi.Soundbank;

public class DemoThread22NotifyAll {

    public synchronized void run1(){
        System.out.println("进入run1方法..");
        this.notifyAll();
        //this.notify();
        System.out.println("run1执行完毕，通知完毕..");
    }

    public synchronized void run2(){
        try {
            System.out.println("进入run2方法..");
            this.wait();
            System.out.println("run2执行完毕..");
//            this.notify();
//            System.out.println("run2发出通知..");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public synchronized void run3(){
        try {
            System.out.println("进入run3方法..");
            this.wait();
            System.out.println("run3执行完毕..");
//            this.notify();
//            System.out.println("run3发出通知..");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final DemoThread22NotifyAll dt=new DemoThread22NotifyAll();
        new Thread(new Runnable() {
            @Override
            public void run() {
                dt.run2();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                dt.run3();
            }
        }).start();

        Thread.sleep(1000L);
        new Thread(new Runnable() {
            @Override
            public void run() {
                dt.run1();
            }
        }).start();
    }

}
