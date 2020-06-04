package third;

public class Tsts {

    public static void main(String[] args) {
        Ts3 ts3 = new Ts3(33);

                System.out.println("加法"+ts3.add(4).getAaa());
                System.out.println("减法"+ts3.sub(4).getAaa());
                System.out.println("乘法"+ts3.muti(4).getAaa());
                System.out.println("除法"+ts3.dis(4).getAaa());
        System.out.println("位移"+ts3.weiyi(2,"<<").getAaa());
        System.out.println("位移"+ts3.weiyi(2,">>").getAaa());
        System.out.println("求余"+ts3.qiuyu(4).getAaa());

        int x=5;
        int y=6;
        int z;
        z=x>y? ++x:y++;
        System.out.println("x"+x+"y"+y+"z"+z);

    }
}
