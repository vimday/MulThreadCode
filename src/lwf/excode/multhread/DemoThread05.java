package lwf.excode.multhread;

//锁重入
public class DemoThread05 {
    public synchronized  void run1(){
        System.out.println(Thread.currentThread().getName()+">run1...");
        run2();
    }

    public synchronized void run2() {
        System.out.println(Thread.currentThread().getName()+">run2...");
    }

    public static void main(String[] args) {
        final DemoThread05 dt5=new DemoThread05();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                dt5.run1();
            }
        });
        thread.start();

    }
}
