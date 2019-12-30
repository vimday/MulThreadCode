package lwf.excode.multhread;


class  ShellThread extends Thread {
    static /*volatile*/ int ticketsNum=0;//不加锁的话volatile也不能保证线程安全 ，volatile只有 可见性与禁止指令重排两种作用
    //volatile并不具有原子性

    Object lock;
    ShellThread(String name,Object lock){
        super(name);
        this.lock=lock;
    }

    @Override
    public void run() {
        while (true){
            synchronized (lock) {
                if (ticketsNum < 1000) {
                    //ticketsNum++;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("%s线程正在卖出第%d张票\n", Thread.currentThread().getName(), ++ticketsNum);
                } else
                    break;
            }
        }
    }
}
public class SellTickets {
    public static void main(String[] args) {
        Object lock=new Object();
        ShellThread t1=new ShellThread("窗口1",lock);
        ShellThread t2=new ShellThread("窗口2",lock);
        ShellThread t3=new ShellThread("窗口3",lock);
        ShellThread t4=new ShellThread("窗口4",lock);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
