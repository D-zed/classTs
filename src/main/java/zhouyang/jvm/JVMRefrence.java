package zhouyang.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class JVMRefrence {

    /**
     * 强软弱虚引用
     * 强引用我们使用最为常见
     * 强引用 就算oom也不会gc 死都不收
     * 软引用 内存不够则回收
     * 弱引用 内存够不够，只要发生gc就会回收
     * jvm 参数 -Xmx10m -Xms10m
     *
     *
     * 软引用案例 可以缓存图片使得gc的时候可以被gc掉
     */



    public void hardReference() throws InterruptedException {
        //强软弱虚
        JVMMetaSpace jvmMetaSpace = new JVMMetaSpace("dd");
        //直接复制则就是强引用
        JVMMetaSpace jvmMetaSpace2 = jvmMetaSpace;
        jvmMetaSpace = null;
        System.gc();
        TimeUnit.SECONDS.sleep(4);
        System.out.println(jvmMetaSpace2.name + "---" + jvmMetaSpace);
    }


    public static void main(String[] args) {
        //内存足够的时候的软引用
        // softRefMemEnough();
        //内存不足时候的软引用
        softRefMemNotEnough();

        //弱引用
      //  weakRefMem();

        //weakHashMap的key被回收了,则这个值也就被移除了
      /*  WeakHashMap<Integer,String> weakHashMap=new WeakHashMap();
        Integer key=new Integer(1);
        String value="aaa";
        weakHashMap.put(key,value);
        System.out.println(weakHashMap);
        key=null;
        System.gc();
        System.out.println(weakHashMap+"---"+weakHashMap.size());*/

        //引用队列
       // refQueue();

        //虚引用
      //  phantomRefMem();
    }

    public static void softRefMemEnough() {
        //当没有发生内存不足的情况时软引用gc不会回收
        Object o = new Object();
        SoftReference<Object> objectSoftReference = new SoftReference<>(o);
        System.out.println(o);
        System.out.println(objectSoftReference.get());
        o = null;
        System.gc();
        System.out.println(o);
        System.out.println(objectSoftReference.get());
    }

    public static void softRefMemNotEnough() {
        //当没有发生内存不足的情况时软引用gc不会回收
        Object o = new Object();
        SoftReference<Object> objectSoftReference = new SoftReference<>(o);
        System.out.println(o);
        System.out.println(objectSoftReference.get());
        o = null;
        try {
            //增加内存占用使得内存不足触发gc
            byte[] bb = new byte[1024 * 1024 * 20];
        } finally {
            System.out.println(o);
            System.out.println(objectSoftReference.get());
        }

    }

    public static void weakRefMem() {
        //当没有发生内存不足的情况时软引用gc不会回收
        Object o = new Object();
        WeakReference<Object> objectSoftReference = new WeakReference<>(o);
        System.out.println(o);
        System.out.println(objectSoftReference.get());
       // o = null;
        try {
            //增加内存占用使得内存不足触发gc
            byte[] bb = new byte[1024 * 1024 * 20];
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(o);
            System.out.println(objectSoftReference.get());
        }

    }

    /**
     * 虚引用 如果一个对象存在虚引用那么他就相当于没有引用，任何时候都会被回收 且虚引用不能单独使用也不能通过它访问对象
     * 必须和引用队列联合使用
     */
    public static void phantomRefMem() {
        //当没有发生内存不足的情况时软引用gc不会回收
        Object o = new Object();
        ReferenceQueue<Object> objectReferenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> objectSoftReference = new PhantomReference<>(o,objectReferenceQueue);
        System.out.println("object对象是---"+o);
        System.out.println("objectSoftReference是---"+objectSoftReference.get()+"--objectSoftReference是"+objectSoftReference);
        System.out.println("objectReferenceQueue是---"+objectReferenceQueue.poll());
        o = null;
        try {
            //增加内存占用使得内存不足触发gc
            System.gc();
        } finally {
            System.out.println("object对象是---"+o);
            System.out.println("objectSoftReference是---"+objectSoftReference.get());
            System.out.println("objectReferenceQueue是---"+objectReferenceQueue.poll());
        }
    }


    /**
     * 引用队列在虚引用发生了gc的情况下，加入了引用队列
     *
     *
     */
    public static void refQueue() {
        //当没有发生内存不足的情况时软引用gc不会回收
        Object o = new Object();
        ReferenceQueue<Object> objectReferenceQueue = new ReferenceQueue<>();
        WeakReference<Object> objectSoftReference = new WeakReference<>(o,objectReferenceQueue);
        System.out.println("object对象是---"+o);
        System.out.println("objectSoftReference是---"+objectSoftReference.get()+"--objectSoftReference是"+objectSoftReference);
        System.out.println("objectReferenceQueue是---"+objectReferenceQueue.poll());
        o = null;
        try {
            //增加内存占用使得内存不足触发gc
            System.gc();
        } finally {
            System.out.println("object对象是---"+o);
            System.out.println("objectSoftReference是---"+objectSoftReference.get());
            System.out.println("objectReferenceQueue是---"+objectReferenceQueue.poll());
        }
    }
}