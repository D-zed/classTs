package tss;

public class D424 {
    public static void main(String[] args) {
        int i=0;
      //先是i直接入栈 然后 本身值++（自增无需入栈此时i==1）  然后 ++i加这个时候 i=2然后入栈 然后 i再入栈
       int aa= (i++)+(++i)+i;
        System.out.println(aa);
    }
}
