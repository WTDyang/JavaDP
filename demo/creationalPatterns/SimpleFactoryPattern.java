package creationalPatterns;

/**
 * 简单工厂
 *
 * @author WTDYang
 * @date 2022/10/14
 */
public class SimpleFactoryPattern{
    public static void main(String[] args) {
        Food food = SimpleFactory.makeFood("Noodle");
        food.end();
    }
}
class SimpleFactory {
    public static Food makeFood(String name) {
        if (name.equals("Noodle")) {
            Food noodle = new Noodle();
            noodle.salt();
            return noodle;
        } else if (name.equals("Dumplings")) {
            Food chicken = new Dumplings();
            chicken.vinegar();
            return chicken;
        } else {
            return null;
        }
    }
}
interface Food{
    public void salt();
    public void vinegar();
    public void end();
}
class Noodle implements Food{
    public void salt(){
        System.out.println("面条放盐");
    }
    public void vinegar(){
        System.out.println("面条放醋");
    }

    public void end() {
        System.out.println("面条出餐");
    }

}
class Dumplings implements Food{
    public void vinegar(){
        System.out.println("饺子放醋");
    }
    public void salt(){
        System.out.println("饺子放盐");
    }
    public void end() {
        System.out.println("饺子出餐");
    }
}