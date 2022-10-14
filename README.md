# Java 设计模式

## 设计模式介绍

设计模式是对实际工作中写的各种代码进行抽象总结，其中广为人知的便是

```
Gang of Four
(
	GoF
)
```

他们将设计模式分为 23 种，其中包括三大类

### 创建型模式（Creational Patterns）

这些设计模式提供了一种在创建对象的同时隐藏创建逻辑的方式，而不是使用 new 运算符直接实例化对象。这使得程序在判断针对某个给定实例需要创建哪些对象时更加灵活。

### 结构型模式（Structural Patterns）

这些设计模式关注类和对象的组合。继承的概念被用来组合接口和定义组合对象获得新功能的方式。

### 行为型模式（Behavioral Patterns）

这些设计模式特别关注对象之间的通信。

### 具体分类如下：

```
设计模式
│
│    
├─ 创建型模式（Creational Patterns）
│		├─ 工厂模式（Factory Pattern）
│		├─ 抽象工厂模式（Abstract Factory Pattern）
│		├─ 单例模式（Singleton Pattern）
│		├─ 建造者模式（Builder Pattern）
│    	└─ 原型模式（Prototype Pattern）
│    
├─ 结构型模式（Structural Patterns）
│		├─ 适配器模式（Adapter Pattern）
│		├─ 桥接模式（Bridge Pattern）
│		├─ 过滤器模式（Filter、Criteria Pattern）
│		├─ 组合模式（Composite Pattern）
│		├─ 装饰器模式（Decorator Pattern）
│		├─ 外观模式（Facade Pattern）
│		├─ 享元模式（Flyweight Pattern）
│    	└─ 代理模式（Proxy Pattern）
│    
└─ 行为型模式（Behavioral Patterns）
		├─ 责任链模式（Chain of Responsibility Pattern）
		├─ 命令模式（Command Pattern）	
		├─ 解释器模式（Interpreter Pattern）
		├─ 迭代器模式（Iterator Pattern）
		├─ 中介者模式（Mediator Pattern）
		├─ 备忘录模式（Memento Pattern）
		├─ 观察者模式（Observer Pattern）
		├─ 状态模式（State Pattern）
		├─ 空对象模式（Null Object Pattern）
		├─ 策略模式（Strategy Pattern）
		├─ 模板模式（Template Pattern）
		└─ 访问者模式（Visitor Pattern）  
```

### 设计模式的六大原则

设计模式中应当遵循六大原则

**1、开闭原则（Open Close Principle）**

开闭原则的意思是：**对扩展开放，对修改关闭**。在程序需要进行拓展的时候，不能去修改原有的代码，实现一个热插拔的效果。简言之，是为了使程序的扩展性好，易于维护和升级。想要达到这样的效果，我们需要使用接口和抽象类，后面的具体设计中我们会提到这点。

**2、里氏代换原则（Liskov Substitution Principle）**

里氏代换原则是面向对象设计的基本原则之一。 里氏代换原则中说，任何基类可以出现的地方，子类一定可以出现。LSP 是继承复用的基石，只有当派生类可以替换掉基类，且软件单位的功能不受到影响时，基类才能真正被复用，而派生类也能够在基类的基础上增加新的行为。里氏代换原则是对开闭原则的补充。实现开闭原则的关键步骤就是抽象化，而基类与子类的继承关系就是抽象化的具体实现，所以里氏代换原则是对实现抽象化的具体步骤的规范。

**3、依赖倒转原则（Dependence Inversion Principle）**

这个原则是开闭原则的基础，具体内容：针对接口编程，依赖于抽象而不依赖于具体。

**4、接口隔离原则（Interface Segregation Principle）**

这个原则的意思是：使用多个隔离的接口，比使用单个接口要好。它还有另外一个意思是：降低类之间的耦合度。由此可见，其实设计模式就是从大型软件架构出发、便于升级和维护的软件设计思想，它强调降低依赖，降低耦合。

**5、迪米特法则，又称最少知道原则（Demeter Principle）**

最少知道原则是指：一个实体应当尽量少地与其他实体之间发生相互作用，使得系统功能模块相对独立。

**6、合成复用原则（Composite Reuse Principle）**

合成复用原则是指：尽量使用合成/聚合的方式，而不是使用继承。



## 建造者模式

### 工厂模式

工厂模式是一种建造模式，可以避免用户在任意new对象的条件下完成实例的创建。

#### 简单工厂模式

```java
public class FoodFactory {
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
```

如上述代码所示，当我们需要制作食物的时候，就可以调用`FoodFactory`的静态方法，创造对应的食物。

> 根据单一职责原则，通常XxxFactory的职责就是创造Xxx，比如FoodFactory的职责就是创造Food。

#### 工厂模式

虽然简单工厂模式可以解决一些问题，但是可以看到它仍有一些问题。

比如如果我要做一台电脑。电脑制作过程中可以输入参数比如CPU性能，内存性能，硬盘性能等等。

但是在此之前，我首先要考虑，我的电脑使用AMD厂商还是Intel厂商。

因此我们可以创建一个AMD工厂和一个Intel工厂，他们都可以继承一个computer工厂的父类。

```java
public interface ComputerFactory {
    public Computer getComputer(Double cpu,Integer ram,Integer hdd);
}
```

```java
class AMDFactory implements ComputerFactory{

    @Override
    public Computer getComputer(Double cpu,Integer ram,Integer hdd) {
        return new AMDComputer(cpu,ram,hdd);
    }
}
```

```java
class IntelFactory implements ComputerFactory{

    @Override
    public Computer getComputer(Double cpu,Integer ram,Integer hdd) {
        return new IntelComputer(cpu,ram,hdd);
    }
}
```

我们看客户端调用的时候的输出：

```java
class client{
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

AMD computer{cpu=2.4GHz, ram=16GB, hdd=2TB}
Intel computer{cpu=2.9GHz, ram=8GB, hdd=1TB}
```

**类关系如图：**

![image-20221014165447837](README.assets/image-20221014165447837.png)

**一个典型的例子**

我们使用日志工厂的时候，首先要指定日志工厂的实现类。

比如FileLogFactory 和 KafkaLogFactory，他们都继承了 LogFactory接口。

### 抽象工厂模式

抽象工厂模式是对工厂模式地一个拓展。

在引入抽象工厂模式的开始，首先介绍产品族的概念，比如华为手机、华为耳机、华为手表都是一共产品族的产品。苹果手机、苹果耳机、苹果手表都是另一产品族的产品。我们可以认为相同产品族的产品的适配性更好，甚至不是统一产品族的产品可以无法相互兼容使用，因而在客户端调用的时候我们希望客户端可以方便地创造同一族的产品。

因而可以在工厂上进行拓展，使得每一个工厂都可以实现同一族不同产品的建造。

![image-20221014195819380](README.assets/image-20221014195819380.png)

客户端：

```java
public class AbstractFactory {
    public static void main(String[] args) {
        PhoneFactory phoneFactory = new HuaweiFactory();
        System.out.printf(phoneFactory.getPone().toString());
        System.out.printf(phoneFactory.getHeadset().toString());
        System.out.printf(phoneFactory.getWatch().toString());
    }
}
```

工厂：

```java
interface PhoneFactory{
    Phone getPone();
    Headset getHeadset();
    Watch getWatch();
}
```

```java
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
```

```java
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
```

**优劣分析**

优点：

- 符合依赖抽象原则
- 简化调用者可以方便知道产品族

## 缺点

- 产品族难扩展，修改一个产品需要所有工厂都完成扩展
- 增加了系统的抽象性和理解难度；