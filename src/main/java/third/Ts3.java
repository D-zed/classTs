package third;

public class Ts3 {

   private Integer  aaa;

    public Ts3(Integer aaa) {
        this.aaa = aaa;
    }

    public  Ts3 add(int a){
        this.aaa+=a;
        return  new Ts3(aaa);

    } public  Ts3 sub(int a){
        this.aaa-=a;
        return  new Ts3(aaa);

    } public  Ts3 muti(int a){
        this.aaa*=a;
        return  new Ts3(aaa);

    } public  Ts3 dis(int a){
        this.aaa/=a;
        return  new Ts3(aaa);

    }
    public  Ts3 qiuyu(int a){
        this.aaa%=a;
        return  new Ts3(aaa);

    }public  Ts3 weiyi(int a,String logo){
        if ("<<".equals(logo)){

            this.aaa<<=a;
        }if (">>".equals(logo)){

            this.aaa<<=a;
        }
        return  new Ts3(aaa);

    }

    public Integer getAaa() {
        return aaa;
    }
}
