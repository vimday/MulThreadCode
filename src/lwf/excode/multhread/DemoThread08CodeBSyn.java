package lwf.excode.multhread;


//不同类型锁之间互不干扰
public class DemoThread08CodeBSyn {
    public void run1() {
        synchronized (this) {
            try {
                System.out.println(Thread.currentThread().getName() + ">当前对象锁..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run2() {
        synchronized (DemoThread08CodeBSyn.class) {
            try {
                System.out.println(Thread.currentThread().getName() + ">类锁..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Object objectLock = new Object();

    public void run3() {
        synchronized (objectLock) {
            try {
                System.out.println(Thread.currentThread().getName() + ">任意对象锁..");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //测试方法

    public static void test(final int type) {
        if (type == 1)
            System.out.println("当前对象锁测试..");
        else if (type == 2)
            System.out.println("类锁测试..");
        else if (type == 3)
            System.out.println("任意对象锁测试..");
        final DemoThread08CodeBSyn dt1 = new DemoThread08CodeBSyn();
        final DemoThread08CodeBSyn dt2 = new DemoThread08CodeBSyn();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                if(type==1)
                    dt1.run1();
                else if(type==2)
                    dt1.run2();
                else
                    dt1.run3();
            }
        },"t1");
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                if(type==1)
                    dt1.run1();
                else if(type==2)
                    dt2.run2();
                else
                    dt1.run3();
            }
        },"t2");
        t1.start();
        t2.start();
    }

    public static void main(String[] args) throws InterruptedException {
        //test(1);
        //test(2);
        //test(3);
        final DemoThread08CodeBSyn dt1=new DemoThread08CodeBSyn();
        final DemoThread08CodeBSyn dt2=new DemoThread08CodeBSyn();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                dt1.run2();
            }
        },"t1");
        t1.start();
        Thread.sleep(100);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dt2.run1();
            }
        },"t2").start();


    }

}
