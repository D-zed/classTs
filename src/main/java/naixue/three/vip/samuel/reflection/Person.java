package naixue.three.vip.samuel.reflection;

import java.lang.reflect.Method;

public  class Person {

    static {
        System.out.println("hahahahahah...");
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void study(){
        System.out.println("good good study day day up。。。");
    }

    public static void main(String[] args) throws Exception{
        //获取Class对象
        Class<Person> clazz = Person.class;
        //获得类的结构
        System.out.println(clazz.getSuperclass());
        System.out.println(clazz.getClassLoader());
        System.out.println(clazz.getPackage());
        System.out.println(clazz.getName());

        //动态创建对象
        Person person = clazz.newInstance();

        //调用方法
        Method studyMethod = clazz.getDeclaredMethod("study");
        studyMethod.invoke(person);

    }
}
