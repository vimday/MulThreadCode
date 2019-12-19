package lwf.excode.multhread;

public class DemoThread04 {
    private String name = "zhangsan";
    private String address="daxing";

    public synchronized void setVal(String name,String address){
        this.name=name;
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        this.address=address;

        System.out.println("setVal最终结果：username = "+name+", address = " + address);
    }

    //加锁防止脏读
    public synchronized void getVal(){
        System.out.println("getVal：username = "+name+", address = " + address);
    }

    public static void main(String[] args) throws InterruptedException {
        final DemoThread04 dt4=new DemoThread04();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                dt4.setVal("lisi","changping");
            }
        });
        t1.start();
        Thread.sleep(1000);
        dt4.getVal();
    }

}
