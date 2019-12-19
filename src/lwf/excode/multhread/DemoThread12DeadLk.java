package lwf.excode.multhread;

public class DemoThread12DeadLk {

    private Object lock1=new Object();
    private Object lock2=new Object();

    public void execute1(){
        synchronized (lock1){
            System.out.println("线程"+Thread.currentThread().getName()+"获得lock1执行execute1");
            try {
                Thread.sleep(2000);
                //加上下面这句如果不catch相应的异常则会释放lock1
                //int i=10/0;
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            synchronized (lock2){
                System.out.println("线程"+Thread.currentThread().getName()+"获得lock2执行execute1");

            }
        }
    }

    public void execute2(){
        synchronized (lock2){
            System.out.println("线程"+Thread.currentThread().getName()+"获得lock2执行execute2 ");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            synchronized (lock1){
                System.out.println("线程"+Thread.currentThread().getName()+"获得lock1执行execute2");

            }
        }
    }

    public static void main(String[] args) {
        final DemoThread12DeadLk dt=new DemoThread12DeadLk();
        new Thread(new Runnable() {
            @Override
            public void run() {
                dt.execute1();
            }
        },"t1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                dt.execute2();
            }
        },"t2").start();
    }

}
