package naixue.three.vip.samuel.generic.bridge;

public class SubClass implements SuperClass<String> {
    @Override
    public String m01(String param) {
        return param + "---";
    }
}
