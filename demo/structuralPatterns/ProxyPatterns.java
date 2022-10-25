package structuralPatterns;

/**
 * 代理模式
 *
 * @author WTDYang
 * @date 2022/10/25
 */
public class ProxyPatterns {
    public static void main(String[] args) {
        Landlord landlord = new Landlord();
        Intermediary intermediary = new Intermediary(landlord);
        intermediary.saleHouse(100,1);
    }
}

interface SaleHouse{
    /**
     * 出售房子
     *
     * @param price   价格
     * @param houseId 房子id
     */
    void saleHouse(int price,int houseId);
}

/**
 * 房东
 *
 * @author WTDYang
 * @date 2022/10/25
 */
class Landlord implements SaleHouse{

    @Override
    public void saleHouse(int price, int houseId) {
        System.out.printf("以%d的价格卖出%d号房子\n",price,houseId);
    }
}

/**
 * 中介
 *
 * @author WTDYang
 * @date 2022/10/25
 */
class Intermediary implements SaleHouse{

    private SaleHouse landlord;
    public Intermediary(SaleHouse landlord){
        this.landlord = landlord;
    }
    @Override
    public void saleHouse(int price, int houseId) {
        System.out.println("在中介上的帮助下开始交易");
        landlord.saleHouse(price,houseId);
        System.out.println("在中介的帮助下交易完成");
    }
}