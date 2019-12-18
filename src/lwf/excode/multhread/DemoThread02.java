package lwf.excode.multhread;

public class DemoThread02 {
    private static int cnt=0;
    public synchronized static void add(){
        cnt++;
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":cnt="+cnt);
    }

    public static void main(String[] args) {
        final DemoThread02 t1=new DemoThread02();
        final DemoThread02 t2=new DemoThread02();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                t1.add();
            }
        },"thread1");
        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                t2.add();
            }
        },"thread2");
        thread1.start();
        thread2.start();

    }
}
