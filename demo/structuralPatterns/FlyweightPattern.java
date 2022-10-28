package structuralPatterns;

import java.util.HashMap;

/**
 * 享元模式
 *
 * @author WTDYang
 * @date 2022/10/28
 */
public class FlyweightPattern {
    public static void main(String[] args) {
        BicycleFactory bicycleFactory = BicycleFactory.getBicycleFactory();
        ISharedBike bicycle = bicycleFactory.getBike("bicycle");
        bicycle.ride();

    }
}
interface ISharedBike{
    void ride();
}

class Bicycle implements ISharedBike{

    @Override
    public void ride() {
        System.out.println("骑自行车");
    }
}
class ElectricVehicle implements ISharedBike{

    @Override
    public void ride() {
        System.out.println("骑电动车");
    }
}
class BicycleFactory{
        private BicycleFactory() {}
        private static volatile BicycleFactory instance = null;
        private static HashMap<String,ISharedBike> bikePool;

        public static BicycleFactory getBicycleFactory() {
            if (instance == null) {
                synchronized (BicycleFactory.class) {
                    if (instance == null) {
                        instance = new BicycleFactory();
                        bikePool = new HashMap<>(2);
                        bikePool.put("bicycle",new Bicycle());
                        bikePool.put("electricVehicle",new ElectricVehicle());
                    }
                }
            }
            return instance;
        }

        public ISharedBike getBike(String type){
            if("electricVehicle".equals(type)){
                return bikePool.get("electricVehicle");
            }
            return bikePool.get("bicycle");
        }
}