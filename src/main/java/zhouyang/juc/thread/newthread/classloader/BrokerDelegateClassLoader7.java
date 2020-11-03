package zhouyang.juc.thread.newthread.classloader;

/**
 * 自定义类加载器  灵活使用
 * 问题为什么不同的类加载器加载的类之间能够互相访问 因为双亲委派的关系
 *
 *  每个类加载器会有一个列表记录判断该类是否将本类加载器作为初始类加载器就算没有真正加载也会添加  所以低级的类加载器是可以访问上级类加载器的类的
 *  书中 174页记录
 *
 *  类的卸载
 *     jvm规定了一个Class只有在满足下面三个条件的时候才会被GC回收，也就是类被卸载
 *     该类的所有实例被回收
 *     加载该类的ClassLoader 被回收
 *     该类的class实例没有在其他地方被引用
 * @author dzd
 */
public class BrokerDelegateClassLoader7 extends MyClassLoader5{


    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        // 1 根据类的全路径名确保每一个类多线程下只能加载一次
        synchronized (getClassLoadingLock(name)){
            // 2 到已加载的缓存中查看是否加载
            Class<?> klass = findLoadedClass(name);
            // 3 如果缓存中没有吧内加载的类则再次加载
            if (klass==null){
                // 4 如果是 java. javax 则尝试用 系统类加载器
                if (name.startsWith("java.")||name.startsWith("javax")){
                    try {
                        klass = getSystemClassLoader().loadClass(name);
                    }catch (Exception e){
                        //ignore
                    }
                }else {
                    // 5 否则使用我们的自定义类加载器
                    try {
                        //
                        klass=this.findClass(name);
                    }catch (Exception e){

                    }
                    // 6 如果自定义类加载器没有成功 则使用父加载器
                    if (klass == null) {
                        if (getParent() != null){

                            klass=getParent().loadClass(name);
                        }else {
                            klass=getSystemClassLoader().loadClass(name);
                        }
                    }
                }
            }
            // 7
            if (klass==null){
                throw new ClassNotFoundException("没找到类"+name);
            }
            if (resolve){
                resolveClass(klass);
            }
            return klass;
        }
    }
}