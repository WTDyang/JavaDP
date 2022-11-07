package behavioralPatterns;

/**
 * 模板模式
 *
 * @author WTDYang
 * @date 2022/11/07
 */
public class TemplatePattern {
    public static void main(String[] args) {
        new WesternStyleFood().dinner();
        new ChineseFood().dinner();
    }
}
abstract class Cook{
    abstract void washingVegetables();
    abstract void cooking();
    abstract void platter();

    final void dinner(){
        washingVegetables();
        cooking();
        platter();
    }
}
class ChineseFood extends Cook{

    @Override
    void washingVegetables() {
        System.out.println("胡萝卜、肉丁");
    }

    @Override
    void cooking() {
        System.out.println("爆炒");
    }

    @Override
    void platter() {
        System.out.println("出锅");
    }
}
class WesternStyleFood extends Cook{

    @Override
    void washingVegetables() {
        System.out.println("牛排、土豆");
    }

    @Override
    void cooking() {
        System.out.println("煎、炸");
    }

    @Override
    void platter() {
        System.out.println("装盘");
    }
}