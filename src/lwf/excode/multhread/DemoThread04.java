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

    public void getVal(){
        System.out.println("getVal：username = "+name+", address = " + address);
    }

}
