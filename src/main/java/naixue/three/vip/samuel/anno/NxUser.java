package naixue.three.vip.samuel.anno;

/**
 * 注解三个作用
 * 1、编译检查
 * 2、生成文档
 * 3、代码分析
 * @author  samuel
 * @version 1.0
 * @since jdk8
 */
public class NxUser {

    private int id;
    private String name;

    @Override
    public String toString() {
        return "NxUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     *
     * @param a 加数1
     * @param b 加数2
     * @return  两个加数的和
     */
    public int add(int a,int b){
        return a+b;
    }
}
