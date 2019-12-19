package lwf.excode.multhread;


import java.util.ArrayList;
import java.util.List;

class MQueue{
    private List<String> list=new ArrayList<>();
    private int maxSize;

    private Object lock =new Object();

    public MQueue(int maxSize){
        this.maxSize=maxSize;
        System.out.println("线程"+Thread.currentThread().getName()+"已被初始化长度为"+this.maxSize+"的队列");
    }

    public void put(String element){
        synchronized (lock){
            if(this.list.size()==this.maxSize){
                try {
                    System.out.println("线程"+Thread.currentThread().getName()+"当前队列已满put等待...");
                    lock.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            this.list.add(element);
            System.out.println("线程"+Thread.currentThread().getName()+"向队列中加入元素:"+element);
            lock.notifyAll();
        }
    }

    public String take(){
        synchronized (lock){
            if(this.list.size()==0){
                try {
                    System.out.println("线程"+Thread.currentThread().getName()+"队列为空take等待...");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String result=list.get(0);
            list.remove(0);
            System.out.println("线程"+Thread.currentThread().getName()+"获取数据："+result);
            lock.notifyAll();
            return result;
        }
    }
}

public class DemoThread20Exe  {
    public static void main(String[] args) {
        final MQueue queue=new MQueue(5);

        //此处队列为空 线程main take等待，就执行不到下面的t1-t4线程了
        //queue.take();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queue.put("1");
                queue.put("2");
                queue.put("3");
                queue.put("4");
                queue.put("5");
                queue.put("6");
            }
        },"t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queue.put("11");
                queue.put("21");
                queue.put("31");
                queue.put("41");
                queue.put("51");
                queue.put("61");
            }
        },"t2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queue.take();
                queue.take();
                queue.take();
                queue.take();
                queue.take();
            }
        },"t3").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                queue.take();
                queue.take();
                queue.take();
                queue.take();
                queue.take();
            }
        },"t4").start();
    }

}
