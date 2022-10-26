package structuralPatterns;

/**
 * 桥接模式
 *
 * @author WTDYang
 * @date 2022/10/26
 */
public class BridgePattern {
    public static void main(String[] args) {
        Sale physicalStore = new PhysicalStore();
        PepsiCola pepsiCola = new PepsiCola(physicalStore);
        pepsiCola.saleCola();
        Sale networkStore = new NetworkStore();
        CocaCola cocaCola = new CocaCola(networkStore);
        cocaCola.saleCola();

    }
}
interface Sale{
    void saleCola();
}
abstract class Cola{
    Sale sale;
    public Cola(Sale sale){
        this.sale = sale;
    }

    public void saleCola(){
        sale.saleCola();
    }

}
class PepsiCola extends Cola{

    public PepsiCola(Sale sale) {
        super(sale);
    }
    public void saleCola(){
        System.out.print("百事可乐：");
        super.saleCola();
    }
}
class CocaCola extends Cola{

    public CocaCola(Sale sale) {
        super(sale);
    }
    public void saleCola(){
        System.out.print("可口可乐：");
        super.saleCola();
    }

}
class PhysicalStore implements Sale{

    @Override
    public void saleCola() {
        System.out.println("通过实体店售出");
    }
}
class NetworkStore implements  Sale{

    @Override
    public void saleCola() {
        System.out.println("通过网店售出");
    }
}