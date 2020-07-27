package zhouyang.juc;

import com.alibaba.fastjson.JSON;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicReferenceDemo {


    //原子引用demo 原子类操作引用变量
    public static void main(String[] args) {
        //如何解决ABA问题
        User zc = new User("zc","25");
        User ls = new User("ls","22");
        AtomicReference<User> atomicReference=new AtomicReference<>(zc);
        System.out.println(atomicReference.compareAndSet(zc,ls)+"--"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(zc,zc)+"--"+atomicReference.get().toString());



    }
}

class User {
    String name;
    String age;

    public User() {
    }

    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
