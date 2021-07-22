import java.util.concurrent.Semaphore;

public class Foo {
    Semaphore semaphoreFun2 = new Semaphore(0);
    Semaphore semaphoreFun3 = new Semaphore(0);

    public void first(){
        System.out.print("first");
        semaphoreFun2.release();
    }
    public void second(){
        try {
            semaphoreFun2.acquire();
            System.out.print("second");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        semaphoreFun3.release();
    }
    public void third(){
        try {
            semaphoreFun3.acquire();
            System.out.print("third");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Foo foo = new Foo();
        Thread thread = new Thread(foo::first);
        Thread thread2 = new Thread(foo::second);
        Thread thread3 = new Thread(foo::third);

        thread3.start();
        thread.start();
        thread2.start();
    }
}
