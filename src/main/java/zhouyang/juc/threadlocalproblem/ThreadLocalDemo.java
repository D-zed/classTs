package zhouyang.juc.threadlocalproblem;

/**
 * @author dzd
 */
public class ThreadLocalDemo {

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal=new ThreadLocal();
        threadLocal.set("hh");

        /**
         * map进行set的时候 执行了这段代码
         * tab[i] = new Entry(key, value);
         * Entry的构造如此 其继承了虚引用 这个方法相当于 将key用 虚引用包装了一下 所以我们的key 也就是一个虚引用的东西了 而这个key也就是我们定义的 threadLocal对象 所以说这个threadLocal
         * 很有可能就被gc掉了 但是值却一直在 也就导致了 内存泄漏  极少程多也就成了大问题了
         *
         * 问题发生时机 threadLocal对象被回收了 值还在 ，
         *
         * static class Entry extends WeakReference<ThreadLocal<?>> {
         *
         *Object value;
         *
         *Entry(ThreadLocal < ? > k, Object v){
         *super(k);
         *value = v;
         *}
         *}
         *
         * 使用remove即可清空 对应的值 和 key为null的值
         *
         */

    }
}
