package lwf.excode.multhread;

//父子类也可以锁重入

class Parent {
    public int i = 10;

    public synchronized void runParent() {
        try {
            i--;
            System.out.println("Parent>>>>i=" + i);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Child extends Parent {
    public synchronized void runChild(){
        try {
            while(i>0){
                i--;
                System.out.println("Child>>>>i="+i);
                Thread.sleep(100);
                this.runParent();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}

public class DemoThread06ParentChild {
    public static void main(String[] args) {
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                Child sub=new Child();
                sub.runChild();
            }
        });
        t1.start();
    }

}
