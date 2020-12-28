package naixue.three.vip.samuel.anno.fruit;


import naixue.three.vip.samuel.anno.ColorEnum;

public class Apple {

    @FruitName("Apple")
    private String appleName;

    @FruitColor(fruitColor = ColorEnum.RED)
    private String appeColor;

    @FruitProvider(id=1,name="山东富士")
    private String appleProvider;

    public String getAppleName() {
        return appleName;
    }

    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }

    public String getAppeColor() {
        return appeColor;
    }

    public void setAppeColor(String appeColor) {
        this.appeColor = appeColor;
    }

    public String getAppleProvider() {
        return appleProvider;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }
}
