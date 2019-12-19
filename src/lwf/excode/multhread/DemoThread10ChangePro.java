package lwf.excode.multhread;


class Person{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class DemoThread10ChangePro {
    private Person person = new Person();

    public void changeUser(String name,int age){
        synchronized (person){
            System.out.println("线程"+Thread.currentThread().getName()+"开始"+person);
            //person=new Person();//打开注释会存在线程安全问题
            person.setAge(age);
            person.setName(name);
            System.out.println("线程"+Thread.currentThread().getName()+"修改为"+person);
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("线程"+Thread.currentThread().getName()+"结束"+person);
        }
    }

    public static void main(String[] args) {
        final DemoThread10ChangePro dt =new DemoThread10ChangePro();
        new Thread(new Runnable() {
            @Override
            public void run() {
                dt.changeUser("xiaobai",99);
            }
        },"t1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                dt.changeUser("xiaohei",100);
            }
        },"t2").start();
    }

}
