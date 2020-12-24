package java.lang;

/**
 * 类层级结构的根,都是以Object为超类,所有对象数组都实现这个类的方法.
 * Object类是所有类的父类,包括自己写的类
 *
 * @author unascribed
 * @see java.lang.Class
 * @since JDK1.0
 */
public class Object {

    /**
     * native:JNI Java Native Interface
     * java品台和本地C代码进行互操作的API,关键字告诉编译器(JVM)调用的是该方法在外部定义
     */
    private static native void registerNatives();

    static {
        registerNatives();
    }

    /**
     * 返回此Object的运行时类
     */
    public final native Class<?> getClass();


    /**
     * 返回对象的hashCode值.用来将对象的存储地址进行映射
     * hashCode常规协定是:
     * 1.在java应用程序执行期间,在对同一对象多次调用hashCode方法时,必须一致地返回相同的整数,
     * 前提是将对象进行equals比较时所用的信息没有被修改.
     * 从某一应用程序的一次执行到同一应用程序的另一次执行,该整数无须保持一致.
     * 2.如果根据equals方法,两个对象是相等的,那么对这两个对象中的每个对象调用hashCode方法必须生成相同的整数结果
     * 3.如果根据equals方法,两个对象不相等,不要求hashCode生成不同的整数结果.
     * 4.程序员应该意识到,为不相等的对象生成不同整数结果可以提高哈希表的性能.
     *
     * @return
     */
    public native int hashCode();


    /**
     * 此方法实现了非null的Object之间的等价关系.
     * 最具辨识能力的对等关系:两个非null引用值引用同一个对象,则返回true.这里比较的是对象的内存地址,String比较的是String的值.
     * 当equals方法被重写,hashCode也要重写.
     * 遵循该方法的规则:两个对象如果使用equals相等,则他们的hashCode也相等
     *
     * @param obj 另一个对象
     * @return 是否相等
     */
    public boolean equals(Object obj) {
        return (this == obj);
    }

    /**
     * clone方法,用于对象的复制
     *
     * @return
     * @throws CloneNotSupportedException
     */
    protected native Object clone() throws CloneNotSupportedException;

    /**
     * 返回该对象的字符串表示
     * getClass().getName();获取字节码文件的对应全路径名,例如java.lang.Object
     * Integer.toHexString(hashCode);将哈希值转成16进制格式的字符串.
     *
     * @return
     */
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    /**
     * 唤醒在此对象监视器上等待的单个线程
     */
    public final native void notify();

    /**
     * 唤醒在此对象监视器上等待的所有线程
     */
    public final native void notifyAll();

    /**
     * 1.使用wait方法会释放锁,使得其他线程可以使用同步控制块或者方法.
     * 2.在其他线程调用此对象的notify()或者notifyAll()方法前,导致当前线程等待.
     * 换句话说,次方法的行为就好像他仅执行wait(0)调用一样.
     * 3.当前线程必须拥有此对象监视器(WaitNotifyTest中15-20行之剑的关系).该线程发布对此监视器的所有权并等待,
     * 直到其他线程通过调用notify()或者notifyAll()方法通知在此对象的监视器上等待的线程醒来,
     * 然后该线程将等到重新获得对监视器的所有权才能继续执行.
     *
     * @param timeout 等待的毫秒数
     * @throws InterruptedException 中断异常
     */
    public final native void wait(long timeout) throws InterruptedException;

    /**
     * 在其他线程调用此对象的notify方法或者notifyAll方法,或者超过指定的时间量前,导致当前线程等待
     *
     * @param timeout 等待的毫秒数
     * @param nanos   额外时间
     * @throws InterruptedException 中断异常
     */
    public final void wait(long timeout, int nanos) throws InterruptedException {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                    "nanosecond timeout value out of range");
        }

        if (nanos > 0) {
            timeout++;
        }
        wait(timeout);
    }

    /**
     * 如果 timeout 为0,则不考虑实际时间,在获得通知前该线程将一直等待.
     *
     * @throws InterruptedException 中断异常
     */
    public final void wait() throws InterruptedException {
        wait(0);
    }

    /**
     * 当垃圾回收器确定不存在对该对象的更多引用时,由对象的垃圾回收器调用此方法
     *
     * @throws Throwable
     */
    protected void finalize() throws Throwable {
    }
}
