package lwf.excode.multhread;

class User{
    private String name;
    private String pass;
    public User(String name,String pass){
        this.name=name;
        this.pass=pass;
    }

    public synchronized void  set(String name,String pass){
        this.name=name;
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        this.pass=pass;
        System.out.println(Thread.currentThread().getName()+"-name="+this.name+"pass="+this.pass);
    }
}

class UserServlet{
    private User user;

    public UserServlet(){
        user=new User("张三","123456");
    }

    public void setPass(String name,String pass){
        user.set(name,pass);
    }

}


public class DemoThread00 {
    public static void main(String[] args) {

        //匿名内部类引用外部对象需要用final声明
        final UserServlet us= new UserServlet();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("11111111111");
                us.setPass("李四","77777");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("222222222222222");
                us.setPass("王五","88888");
            }
        }).start();
    }
}
