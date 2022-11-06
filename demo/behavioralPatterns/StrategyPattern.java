package behavioralPatterns;

/**
 * 策略模式
 *
 * @author WTDYang
 * @date 2022/11/06
 */
public class StrategyPattern {
    public static void main(String[] args) {
        Shop shop1 = new Shop(new ElderCard());
        shop1.pay(100);
        Shop shop2 = new Shop(new ChildrenCard());
        shop2.pay(100);
        Shop shop3 = new Shop(new CommonCard());
        shop3.pay(100);

        Shop shop = new Shop(money -> {
            System.out.printf("lambda支付---支付%d元 %n",money);
        });
        shop.pay(100);
    }
}
interface Strategy {
    void pay(int money);
}
class ElderCard implements Strategy{

    @Override
    public void pay(int money) {
        System.out.printf("老年人免费---支付%d元 %n",0);
    }
}
class CommonCard implements Strategy{
    @Override
    public void pay(int money) {
        System.out.printf("无优惠---支付%d元 %n",money);
    }
}
class ChildrenCard implements Strategy{

    @Override
    public void pay(int money) {
        System.out.printf("儿童半折---支付%d元 %n",money >> 1);
    }
}
class Shop {
    private Strategy strategy;

    public Shop(Strategy strategy){
        this.strategy = strategy;
    }

    public void pay(int money){
       strategy.pay(money);
    }
}