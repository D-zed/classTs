package naixue.three.vip.samuel.generic.effect;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * 保证了类型安全
 * 避免了强转
 * 提高代码重复使用率
 * @param <T>
 */
public class Reuse<T extends Comparable>{

    public Integer compaerTo(T t1,T t2){
        return t1.compareTo(t2);
    }

    @Test
    public void testComara(){
        Integer n1 =123;
        Integer n2= 234;
        Reuse<Integer> res = new Reuse<>();

        assertTrue(res.compaerTo(n1,n2)==-1);


        String str1 = "abc";
        String str2 = "cba";
        Reuse<String> strres = new Reuse<>();
        assertTrue(strres.compaerTo(str1,str2)==-1);

    }

}
