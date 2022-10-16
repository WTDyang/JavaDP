package creationalPatterns;
public class FactoryPattern{
    public static void main(String[] args) {
        //选择工厂
        ComputerFactory computerFactory1 = new AMDFactory();
        Computer computer1 = computerFactory1.getComputer(2.4, 16, 2);
        System.out.println(computer1);

        //选择工厂
        ComputerFactory computerFactory2 = new IntelFactory();
        Computer computer2 = computerFactory2.getComputer(2.9, 8, 1);
        System.out.println(computer2);
    }
}
//工厂类：
/**
 * 电脑工厂
 *
 * @author WTDYang
 * @date 2022/10/14
 */


interface ComputerFactory {
    public Computer getComputer(Double cpu,Integer ram,Integer hdd);
}

/**
 * amdfactory
 * amd工厂
 *
 * @author WTDYang
 * @date 2022/10/14
 */
class AMDFactory implements ComputerFactory{

    @Override
    public Computer getComputer(Double cpu,Integer ram,Integer hdd) {
        return new AMDComputer(cpu,ram,hdd);
    }
}

/**
 * 英特尔工厂
 *
 * @author WTDYang
 * @date 2022/10/14
 */
class IntelFactory implements ComputerFactory{

    @Override
    public Computer getComputer(Double cpu,Integer ram,Integer hdd) {
        return new IntelComputer(cpu,ram,hdd);
    }
}

//产品类：

class Computer {
    public Double cpu;
    public Integer ram;
    public Integer hdd;
    public Computer(Double cpu,Integer ram,Integer hdd) {
        this.cpu = cpu;
        this.ram = ram;
        this.hdd = hdd;
    }
}


class AMDComputer extends Computer{

    public AMDComputer(Double cpu, Integer ram, Integer hdd) {
        super(cpu, ram, hdd);
    }
    public String toString() {
        return "AMD computer{" +  "cpu=" + cpu + "GHz, ram=" + ram + "GB, hdd=" + hdd + "TB}";
    }
}
class IntelComputer extends Computer {

    public IntelComputer(Double cpu, Integer ram, Integer hdd) {
        super(cpu, ram, hdd);
    }
    public String toString() {
        return "Intel computer{" +  "cpu=" + cpu + "GHz, ram=" + ram + "GB, hdd=" + hdd + "TB}";
    }
}

