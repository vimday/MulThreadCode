package lwf.excode.multhread;

public class DemoThread09ChangeRef {
    private String lock="lock handler";

    private void method(){
        synchronized (lock){
            try {
                System.out.println("当前线程： "+Thread.currentThread().getName()+"开始");
                //锁的引用被改变，其他线程可获得锁，导致并发问题
                lock = "change lock handler";
                Thread.sleep(2000);
                System.out.println("当前线程： "+Thread.currentThread().getName()+"结束");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final DemoThread09ChangeRef changeLock=new DemoThread09ChangeRef();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        },"thread1");
        thread.start();
        Thread.sleep(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                changeLock.method();
            }
        },"thread2").start();

    }
}
