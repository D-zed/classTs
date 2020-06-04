package aexample;

public class A {
    class  Dog{
        private String name;
        private int age;
        private int step;
        Dog(String s,int a){
            name=s;
            age=a;
            step=0;
        }
        public void run(Dog dog){
            dog.step++;
        }

    }
    public static void main(String[] args) {
        A a = new A();
        Dog tom = a.new Dog("tom", 3);
        tom.step=25;
        tom.run(tom);
        System.out.println(tom.step);
    }
}
