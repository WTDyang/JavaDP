package structuralPatterns;

/**
 * 装饰器模式
 *
 * @author WTDYang
 * @date 2022/10/27
 */
public class DecoratorPattern {
    public static void main(String[] args) {
        Dustbin dustbin = new RefuseBin();
        DustbinWithWheel electricDustbin = new ElectricDustbin(dustbin);
        electricDustbin.runToCollect();
        electricDustbin.collectGarbage();
    }
}

/**
 * 垃圾箱
 *
 * @author WTDYang
 * @date 2022/10/27
 */
interface Dustbin{
    void collectGarbage();
}

class RefuseBin implements Dustbin{

    @Override
    public void collectGarbage() {
        System.out.println("垃圾桶收集垃圾");
    }
}
abstract class DustbinWithWheel implements Dustbin{
    public Dustbin dustbin;
    public DustbinWithWheel(Dustbin dustbin){
        this.dustbin = dustbin;
    }
    public abstract void runToCollect();
}
class ElectricDustbin extends DustbinWithWheel{

    public ElectricDustbin(Dustbin dustbin){
        super(dustbin);
    }
    @Override
    public void collectGarbage() {
        dustbin.collectGarbage();
    }

    @Override
    public void runToCollect() {
        System.out.println("电动小马达来喽~");
    }
}