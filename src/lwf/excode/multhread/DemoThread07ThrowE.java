package lwf.excode.multhread;

public class DemoThread07ThrowE {

    private int i=0;

    public synchronized void run(){
        while (true){
            i++;
            System.out.println(Thread.currentThread().getName()+"-run>i="+i);

            try {
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            //int t=i/0;
            if(i==10){
                throw new RuntimeException();
            }
        }
    }

    public synchronized void get(){
        System.out.println(Thread.currentThread().getName()+"-get>i="+i);
    }

    public static void main(String[] args) throws InterruptedException {
        final DemoThread07ThrowE dt7=new DemoThread07ThrowE();

        new Thread(new Runnable() {
            @Override
            public void run() {
                dt7.run();
            }
        },"t1").start();

        //保证t1先执行
        Thread.sleep(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dt7.get();
            }
        },"t2").start();

    }

}
