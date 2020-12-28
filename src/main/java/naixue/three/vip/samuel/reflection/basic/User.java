package naixue.three.vip.samuel.reflection.basic;


import naixue.three.vip.samuel.reflection.Person;

/**
 * @author samuel
 * @version 1.0
 * @since jdk8
 */
public class User  extends Person {

    public  static String nationalty;
    public String idCard;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void play(){
        System.out.println("user play method...");
    }


    private User(String idCard, String address) {
        this.idCard = idCard;
        this.address = address;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "idCard='" + idCard + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    /**
     *
     * @param a 加数一
     * @param b 加数二
     * @return 返回两个加数的和
     */
    public int add(int a,int b){
        return a+b;
    }
}
