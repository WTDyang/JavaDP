package creationalPatterns;

public class AbstractFactory {
    public static void main(String[] args) {
        PhoneFactory phoneFactory = new HuaweiFactory();
        System.out.printf(phoneFactory.getPone().toString());
        System.out.printf(phoneFactory.getHeadset().toString());
        System.out.printf(phoneFactory.getWatch().toString());
    }
}
interface PhoneFactory{
    Phone getPone();
    Headset getHeadset();
    Watch getWatch();
}
class HuaweiFactory implements PhoneFactory{

    @Override
    public Phone getPone() {
        return new Phone("mate60");
    }

    @Override
    public Headset getHeadset() {
        return new Headset("FreeBuds");
    }

    @Override
    public Watch getWatch() {
        return new Watch("GT3");
    }
}
class AppleFactory implements PhoneFactory{
    @Override
    public Phone getPone() {
        return new Phone("iphone");
    }

    @Override
    public Headset getHeadset() {
        return new Headset("AirPods");
    }

    @Override
    public Watch getWatch() {
        return new Watch("Apple Watch");
    }
}
class Phone{String name;

    public Phone(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name+"\n";
    }
}
class Headset{String name;

    public Headset(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name+"\n";
    }
}
class Watch{String name;

    public Watch(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name+"\n";
    }
}