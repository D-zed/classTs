package zhouyang.mianshi;

public class FirstProblem {

    public static void int1(int a){
        a=300;
    }

    public static void str1(String string){
        string="秀秀";
    }

    public static void person1(Person person){
        person.setName("修改");
    }

    public static void main(String[] args) {
        int a=200;

        String string="aa";

        Person person = new Person("dddd");

        int1(1);
        //a不会改
        System.out.println(a);
        str1(string);
        //string不会改 因为他是不变的用而这个string指向的就是当前栈帧的变量，而这个变量的值不会变
        System.out.println(string);
        person1(person);
        //person由于person1方法操作的真实的对象与main中的 person指向是一个东西所以这个会被修改
        System.out.println(person.getName());
    }


}
