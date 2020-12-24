package mpc.objTest;

/**
 * 等待唤醒
 *
 * @author: 孟鹏程
 * @date: 2020/12/24 10:30
 */
public class WaitNotifyTest {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        new Thread(() -> {
            System.out.println("线程A等待获取lock锁");
            synchronized (lock) {
                try {
                    System.out.println("线程A获取了lock锁");
                    Thread.sleep(1000);
                    System.out.println("线程A将要运行lock.wait()方法进行等待");
                    lock.wait();
                    System.out.println("线程A等待结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            System.out.println("线程B等待获取lock锁");
            synchronized (lock) {
                try {
                    System.out.println("线程B获取了lock锁");
                    Thread.sleep(1000);
                    System.out.println("线程B将要运行lock.nofity()方法进行等待");
                    lock.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        /**
         * 3.wait,notify,notifyAll必须在同步代码块使用,否则报错:IllegalMonitorStateException
         */
        Object everyWhere = new Object();
//        everyWhere.wait();

        new Thread(() -> {
            System.out.println("线程C等待获取everyWhere锁");
            synchronized (everyWhere) {
                try {
                    System.out.println("线程C将要everyWhere.wait(5000)等待开始," + System.currentTimeMillis());
                    everyWhere.wait(5000, 1000);
                    System.out.println("线程C将要everyWhere.wait(5000)等待结束," + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    /**
     * sleep和wait
     * 1.来自不同的类Thread和Object
     * 2.最主要的是sleep没有释放锁,而wait释放了锁,是得其他线程可以使用同步控制块或方法.
     * 3.wait,notify,notifyAll只能在同步控制方法或者同步控制块使用,而sleep可以在任何地方使用
     */
}
