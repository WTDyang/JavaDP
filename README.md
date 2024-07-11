123456
<meta name="robots" content="noindex">

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

### 行为型模式

这些设计模式特别关注对象之间的通信。

### 具体分类如下：

```
设计模式
│
│    
├─ 创建型模式（Creational Patterns）
│		├─ 工厂模式（Factory Pattern）√
│		├─ 抽象工厂模式（Abstract Factory Pattern）√
│		├─ 单例模式（Singleton Pattern）√
│		├─ 建造者模式（Builder Pattern）√
│    	└─ 原型模式（Prototype Pattern）√
│    
├─ 结构型模式（Structural Patterns）
│		├─ 适配器模式（Adapter Pattern）√
│		├─ 桥接模式（Bridge Pattern）√
│		├─ 代理模式（Proxy Pattern）√
│		├─ 组合模式（Composite Pattern）√
│		├─ 装饰器模式（Decorator Pattern）√
│		├─ 外观模式（Facade Pattern）√
│		├─ 享元模式（Flyweight Pattern）√
│    	└─ 过滤器模式（Filter、Criteria Pattern）√
│    
└─ 行为型模式（Behavioral Patterns）
		├─ 责任链模式（Chain of Responsibility Pattern）√
		├─ 命令模式（Command Pattern）√	
		├─ 解释器模式（Interpreter Pattern）√
		├─ 迭代器模式（Iterator Pattern）√
		├─ 中介者模式（Mediator Pattern）√
		├─ 备忘录模式（Memento Pattern）√
		├─ 观察者模式（Observer Pattern）√
		├─ 状态模式（State Pattern）√
		├─ 空对象模式（Null Object Pattern）√
		├─ 策略模式（Strategy Pattern）√
		├─ 模板模式（Template Pattern）√
		└─ 访问者模式（Visitor Pattern） √ 
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



## 创建型模式

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

**优点**：

- 符合依赖抽象原则
- 简化调用者可以方便知道产品族

**缺点**

- 产品族难扩展，修改一个产品需要所有工厂都完成扩展
- 增加了系统的抽象性和理解难度；

### 单例模式

单例模式是最重要一个设计模式之一，他简单、常考但易错

单例模式实现的类**负责创建自己的对象**，同时保证只有一个对象被创建。并且这个类提供了一种**访问**其唯一的对象的方式，在使用中不需要实例化对象，而是直接访问类创建出来的那一个唯一对象。

如此保证了全局只有一个实例对象，避免了对象的频繁创建与销毁，可以很好地节省系统资源。

值得一提的是，在spring中，创建spring bean的默认方式就是单例模式。

#### 懒汉模式

懒汉模式最为简单，但是同时他并不能保证线程安全。也就是当多个线程同时创建时，有可能会创建多个实例对象。

```java
class LazySingleton {
    private static LazySingleton instance;
    // 将 new LazySingleton() 堵死,这是单例模式的精髓所在，将构造器私有化，外界就无法进行自由创建实例了。
    private LazySingleton() {
    }
    // 创建私有静态实例，如果这个类第一次使用的时候就会进行创建。
    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
```

客户端调用：

```java
 public static void main(String[] args) {
        Set<LazySingleton> set = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(100);
                    set.add(LazySingleton.getInstance());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
        System.out.println(set.size());
    }
```

最终输出可能不是1（结果具有随机性），可见其不并不是线程安全的。

#### 懒汉模式改良

```java
class LazySingleton {
    private static LazySingleton instance;
    // 将 new LazySingleton() 堵死,这是单例模式的精髓所在，将构造器私有化，外界就无法进行自由创建实例了。
    private LazySingleton() {
    }
    // 创建私有静态实例，如果这个类第一次使用的时候就会进行创建。
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
```

改良后的懒汉模式在 getInstance()方法上加入了 synchronized ，从而保证了线程安全。但是如此效率大幅下降。

#### 饿汉模式

饿汉模式的实例对象在类加载的时候就被初始化

```java
class HungrySingleton {
    private static HungrySingleton instance = new HungrySingleton();
    private HungrySingleton (){}
    public static HungrySingleton getInstance() {
        return instance;
    }
}
```

但是如果调用类的静态方法，也会引起实例对象的初始化，以至于造成不必要的对象被创建，造成资源浪费。

```java
class HungrySingleton {
    private static HungrySingleton instance = new HungrySingleton();
    private HungrySingleton (){}
    public static HungrySingleton getInstance() {
        return instance;
    }
    
    public static Date getData(){
        return new Date();
    }
}
```

比如我们在类中加入了一个方法是查新现在的时间。如果我们只调用getDate类，我们并不希望创建一个实例对象，但是仍然被创建了。

> 如果不能理解为什么调用静态方法的时候也会引起实例对象的初始化，可以看一下jvm类的加载过程。
>
> 简言之调用类的静态方法将会引起类的加载，在此过程中对类的静态成员变量进行初始化。

#### 饱汉模式

饱汉模式可以很好地保障线程安全。

```java
class FullSingleton {
        private FullSingleton() {}
        // 注意此除加入了volatile，保证变量的可见性，这也在初始化的时候其他线程调用就可以及时知道实例对象是否完成了初始化
        private static volatile FullSingleton instance = null;

        public static FullSingleton getInstance() {
            if (instance == null) {
                // 加锁 对类进行加锁，效果是一旦发现实例对象未初始化，那么立刻锁住对象，保证初始化未完成之前的线程只能有序进行操作。
                synchronized (FullSingleton.class) {
                    /* 为什么这里也需要进行判断？ 在上一行的代码进行加锁保证了不同线程对此处的有序进行，如果不判断null，那么放进来
                    的线程将会依次对实例进行初始化，因此判断一个null，就可以保证只有第一个进来的线程可以进行初始化。*/
                    if (instance == null) {
                        instance = new FullSingleton();
                    }
                }
            }
            return instance;
        }
}
```

#### 静态内部类

```java
class StaticSingleton {
    //类的内部存在一个静态内部类，这个内部类初始化过程中会进行实例对象的初始化
    private static class SingletonHolder {
        private static final StaticSingleton INSTANCE = new StaticSingleton();
    }
    private StaticSingleton (){}
    public static final StaticSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
```

这个方式利用了类加载机制，保证了只有一个线程可以进程线程初始化。

相较于饱汉模式，实现更加简单，但是这种方式只适用于静态域的情况。

#### 枚举类模式

```java
enum EnumSingleton {
    INSTANCE;
    public EnumSingleton getInstance(){
        return INSTANCE;
    }
}
```

类加载模式实现简单、线程安全，只是不支持懒加载

但是他并没有广泛使用开。

### 建造者模式

建造者模式也是一种非常常用的设计模式，它提供了一种创建对象的优雅解决方案。

他的常见形式为创建者对象进行链式编程进行属性赋值，最后build产生对象。

例如：

```java
User user =  User.builder()
		.name()
		.password()
		.email()
		.build();
```

我们可以理解为，将一道复杂的菜品一步一步添加佐料，最终一起放到炒勺里面爆炒出锅。

**代码示例如下：**
客户端调用：

```java
public class Builder {
    public static void main(String[] args) {
        User user = User.builder()
                .name("ounces")
                .password("123")
                .email("123@ounces.com")
                .build();
        System.out.println(user);
    }
}
```

实现建造者模式的类：

```java
class User {
    private String name;
    private String password;
    private String email;

    //由于后面会用到全参构造器，但是我们并不想客户端直接调用我们的构造器构建对象，因此将全参构造器设为私有
    private User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }


    //构造器可以使用一个静态内部类，他的属性应当与所要构造的类相同，如此才能接收到全部参数
    public static class UserBuilder {
        private String name;
        private String password;
        private String email;
        //私有化构造器
        private UserBuilder() {
        }

        // 我们需要链式调用，因此需要返回this，也就是返回一个指向这个对象本身的指针
        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        //build()方法，将接收到的参数赋予新构建的对象
        //我们可以在build方法中加入一些参数校验规则。
        public User build() {
            if (name == null || password == null) {
                throw new RuntimeException("用户名和密码必填");
            }
            return new User(name,password,email);
        }
    }

    //我们使用userBuilder方法隐藏了new UserBuilder()，是的代码构造器在被使用的时候更加优雅
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
```

**代码分析：**

建造者模式可以通过一个静态内部类，因为静态内部类可以在外部类没有实例化的条件下进行实例化。

基本思路为：首先实例化一个静态内部类UserBuilder对象，接下来链式赋值，最后使用 build()方法完成实例化。

链式编程的一个精髓就是返回.this，如此不断将操作后的对象返回并进行下一步操作，从而完成链式操作。

**日常应用**

Lombok的@builder注解便可完成上述一堆代码

```java
@Builder
class LombokUser{
    private String name;
    private String password;
    private String email;
}
```

**特点分析**

首先建造者模式的链式写法使得对字段赋值集中在一起，使代码简洁易懂。不然将会在代码中出现一堆`xxxSet()`方法。

同时可以在build()方法中进行一些参数校验，可以强调出一些必填字段让调用者感知。

### 原型模式

原型模式非常简单，他做的是通过已有对象在保证性能的前提下创建新的对象，拷贝对象。

在Java中就是实现 Cloneable接口，重写clone()方法。

#### 浅拷贝

首先存在一个类叫做Peo，浅拷贝的类中存在这个类的引用类型参数

```java
class Peo{
    public String name;
    public Peo(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Peo{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

代码中如此实现：

```java
class ShallowClone implements Cloneable{
    public int type;
    public Peo peo;
    public ShallowClone(int type,Peo peo){this.type = type;this.peo = peo;}
    public Object Clone(){
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }

    @Override
    public String toString() {
        return "ShallowClone{" +
                "type=" + type +
                ", peo='" + peo + '\'' +
                '}';
    }
}
```

客户端调用

```java
public class PrototypePattern {
    public static void main(String[] args) {
        ShallowClone shallowClone = new ShallowClone(1,new Peo("num1"));
        ShallowClone shallowClone1 = (ShallowClone) shallowClone.Clone();
        shallowClone1.type = 2;
        shallowClone1.peo.name = "num2";
        System.out.println(shallowClone);
        System.out.println(shallowClone1);
    }
}
```

结果为：

```
ShallowClone{type=1, peo='Peo{name='num2'}'}
ShallowClone{type=2, peo='Peo{name='num2'}'}
```

可以看到普通数据类型的属性没有问题，但是对克隆后对象的引用类型的属性的修改却引起了原型对象的修改，这是因为两个对象的引用类型属性引用了同一个对象。

为了避免这种情况就需要了深拷贝。

#### 深拷贝

```java
class DeepClone implements Cloneable{
    public int type;
    public Peo peo;
    public DeepClone(int type,Peo peo){this.type = type;this.peo = peo;}
    public Object Clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "DeepClone{" +
                "type=" + type +
                ", peo='" + peo + '\'' +
                '}';
    }
}
```

如此客户端调用：


```java
DeepClone deepClone = new DeepClone(1,new Peo("num1"));
DeepClone deepClone1 = (DeepClone)deepClone.Clone();
deepClone1.type = 2;
deepClone1.peo.name = "num2";
System.out.println(deepClone);
System.out.println(deepClone1);
```

输出结果为：

```
DeepClone{type=1, peo='Peo{name='num1'}'}
DeepClone{type=2, peo='Peo{name='num2'}'}

```

和预计结果相同，克隆后的对象属性改变均为影响模型对象。

但是这个前提是Peo类实现了Cloneable接口，重写了clone()方法

#### 序列化与反序列化

实现类可以实现Serializable接口，并在克隆方法中实现序列化与反序列化操作。

在单纯在clone中很少使用。但是我们经常做的是将类序列化后存放在redis等缓存中。

当再次用到这个对象的时候就进行反序列化得到新的对象。

这个世界上也是原型模式的一个应用



使用序列化的方式方式比较繁琐，这里只是研究设计模式概念便不深入研究。读者可以自行搜索。

## 结构型模式

### 适配器模式

#### 对象适配器

适配器模式是解决接口转化的问题。

就好像是日常生活中使用的插口转化器

![image-20221017171819580](README.assets/image-20221017171819580.png)

如上所示，假如我们需要使用USB接口的移动硬盘，但是我们的手机只有一个Type-C的接口，现在我们就要使用一个适配器解决接口转化的问题。

**示例代码**：

首先定义一个接口，例如USB接口，如果某个类实现了USB接口便可认为这个类有一个USB的接口来实现USB功能（传输数据）

```java
interface USB{
    public void transferData();
}
```

接下来创建一个类实现这个接口，例如我们有一个u盘 ，u盘上面恰好有一个usb的接口

```java
class USBDrive implements USB{

    @Override
    public void transferData() {
        System.out.println("U盘正在传输数据");
    }
}
```

然后定义适配器

```java
class USBTypeCAdapter{
    private USB usb;
    public USBTypeCAdapter(USBDrive usb){
        this.usb = usb;
    }
    @Override
    public void transferData() {
        usb.transferData();
    }
}
```

客户端调用：

```java
public class AdapterPattern {
    public static void main(String[] args) {
        USBDrive usbDrive = new USBDrive();
        USBTypeCAdapter usbTypeCAdapter = new USBTypeCAdapter(usbDrive);
        usbTypeCAdapter.transferData();
    }
}
```

可以看到，我们最后使用的是usbTypeCAdapter的数据传输，而不是直接使用usbDriver的传输

同时我们可以改造一下，令usbTypeCAdapter实现一个 叫做TypeC的接口。

```java
class USBTypeCAdapter implements TypeC{...}
```

客户端使用TypeC类型引用接收对象的引用

```java
 public static void main(String[] args) {
        USBDrive usbDrive = new USBDrive();
        TypeC usbTypeCAdapter =  new USBTypeCAdapter(usbDrive);
        usbTypeCAdapter.transferData();

    }
```

我们更可以直观发现，现在我们客户端可以认为使用typeC的接口而不是Usb的接口了

**如上，我们将一个对象通过适配器的包装，可以通过这个对象类没有实现的接口进行调用**

#### 类适配器模式

对象适配器通常将实例化对象进行包装，但是类适配器并不需要进行示例化，而是在类上直接进行包装。

那么我们思考如果完成这种功能呢？
我们知道类的继承，自类可以继承父类的功能（除非final修饰），如果我们定义了一个子类（适配器类），他继承了适配器需要的接口（需求接口），并且继承了父类，那么他就是父类的一个适配器类了。因为我们在重写接口方法的时候，可以直接调用父类的方法，相当于将父类的方法进行了进一步封装。

**我们来看一个示例**

首先定义一个适配器

```java
interface Adapter{
    void printDate();
}
```

接下来定义需要被适配器修饰的类，也就是上述的父类

```java
class DateInfo{
    public static String getDate(){
      return new Date().toString();
   }
}
```

接下来完成适配器类

```java
class DateAdapter extends DateInfo implements Adapter{

    @Override
     public void printDate() {
        System.out.println(
                "当前时间为："
                +getDate()
        );
    }
}
```

客户端调用

```java
public class ClassAdapterPattern {
    public static void main(String[] args) {
        new DateAdapter().printDate();
    }
}
```

**类适配器与对象适配器的比较**

我们看起来，对象适配器仿佛是将一个个示例的对象进行包装。而类适配器是静态地在类中使用方法进行包装。

在平时仍然是对象适配器的会更为灵活与广泛。

#### 默认适配器

默认适配器最为简单，封装性稍差，但是最好理解。

默认适配器实际上无法认为是一个真正的适配器，因为他只是对需要实现的接口进行了简易化包装。

他的适配器更像是对于用户来说，复杂的接口实现对用户来说更加友善了，但是对于机器来说效果不大。

这里引用[一篇优秀文章](https://juejin.cn/post/6844903695667167240)的例子进行说明

---



我们希望使用`Appache commons-io `包中的 `FileAlterationListener`对文件进行监控

但是此接口定义了十分多的抽象方法，如果我们直接使用此接口，必须实现他的所有方法，无法避免地会造成代码大量地臃肿。

```java
public interface FileAlterationListener {
    void onStart(final FileAlterationObserver observer);
    void onDirectoryCreate(final File directory);
    void onDirectoryChange(final File directory);
    void onDirectoryDelete(final File directory);
    void onFileCreate(final File file);
    void onFileChange(final File file);
    void onFileDelete(final File file);
    void onStop(final FileAlterationObserver observer);
}
```

但是我们往常只希望使用他的创建和删除文件地两个功能，于是我们可以定义如下一个适配器，在这个适配器中，实现了上述地复杂接口，但是所有的方法均置为空方法。当我们需要再使用这个接口的时候，只需要继承这个抽象的适配器对象就可以了，这样就不需要实现所有的方法了。

```java
public class FileAlterationListenerAdaptor implements FileAlterationListener {

    public void onStart(final FileAlterationObserver observer) {
    }

    public void onDirectoryCreate(final File directory) {
    }

    public void onDirectoryChange(final File directory) {
    }

    public void onDirectoryDelete(final File directory) {
    }

    public void onFileCreate(final File file) {
    }

    public void onFileChange(final File file) {
    }

    public void onFileDelete(final File file) {
    }

    public void onStop(final FileAlterationObserver observer) {
    }
}
```

当我们使用的时候，直接继承这个适配器类，选择进行方法地重写。

```java
public class FileMonitor extends FileAlterationListenerAdaptor {
    public void onFileCreate(final File file) {
        // 文件创建
        doSomething();
    }

    public void onFileDelete(final File file) {
        // 文件删除
        doSomething();
    }
}
```



### 代理模式

代理模式顾名思义，是依托别人去完成以一项自己无法完成的认为，或者让被人帮助自己去完成一项任务。

我们在日常中最长遇到的问题就是网络代理。当我们无法连接到某网段的网络的时候，就可以摆脱一个代理服务器，代理服务器可以帮我们进行数据的转发和初步校验。

代理模式 的一个典型例子就是租客和租户的模式。我们知道我们经常需要找中介去买房子或者卖房子。我们研究卖房子这一种行为的时候。我们可以认为房东自己可以卖房子，但是他的人脉等资源少于中介，所以去找中介帮忙。

因此中介需要实现和房东相同的饿功能接口，所以房东和中介需要实现相同的接口。

看一下代码：

**售房接口**

```java
interface SaleHouse{
    void saleHouse(int price,int houseId);
}
```

**房东**

```java
class Landlord implements SaleHouse{

    @Override
    public void saleHouse(int price, int houseId) {
        System.out.printf("以%d的价格卖出%d号房子\n",price,houseId);
    }
}
```

**中介**

```java
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
```

**客户端**

```java
public static void main(String[] args) {
    Landlord landlord = new Landlord();
    Intermediary intermediary = new Intermediary(landlord);
    intermediary.saleHouse(100,1);
}
```

**JAVA中的代理**

使用java的java.lang.reflect包下提供了一个Proxy类和一个InvocationHandler接口可以实现动态代理

同时也可以使用Spring AOP完成动态代理

### 桥接模式

桥接模式是 降低代码耦合性，减少代码量的一个优秀的解决方案。

我们考虑一个情况

假如我们现在有可口可乐和百事可乐两家 Cola公司，他们现在都可以提供生产Cola的服务。但是现在都没有销售的能力。

如果我们给他们销售的功能，就需要在两家公司分别重构代码，如果是100家公司，就需要重构100份代码，这显然不是我们希望看到的。

于是我们将 Cola公司的原职能和销售职能抽离出来，形成两个业务。

 Cola公司建立一个抽象类，在类中有一个销售接口的示例对象。我们可以通过这个对象提供的销售业务进行 Cola的销售 。

而实现不同的销售方式（如线上销售和线下销售）就只需要分别实现销售接口，并将示例对象传递到 Cola对象中即可了。



这样抽象类中的示例引用和接口之间就形成了一个**桥梁**



 **代码如下**:

**销售接口**

```java
interface Sale{
    void saleCola();
}
```

 **Cola抽象类**

```java
abstract class Cola{
    Sale sale;
    public Cola(Sale sale){
        this.sale = sale;
    }

    public void saleCola(){
        sale.saleCola();
    }

}
```

**Cola实现类**

```java
class PepsiCola extends Cola{

    public PepsiCola(Sale sale) {
        super(sale);
    }
    public void saleCola(){
        System.out.print("百事可乐：");
        super.saleCola();
    }
}
```



```java
class CocaCola extends Cola{

    public CocaCola(Sale sale) {
        super(sale);
    }
    public void saleCola(){
        System.out.print("可口可乐：");
        super.saleCola();
    }

}
```



**销售实现类**

```java
class PhysicalStore implements Sale{

    @Override
    public void saleCola() {
        System.out.println("通过实体店售出");
    }
}
```



```java
class NetworkStore implements  Sale{

    @Override
    public void saleCola() {
        System.out.println("通过网店售出");
    }
}
```

**客户端**

```java
public static void main(String[] args) {
    Sale physicalStore = new PhysicalStore();
    PepsiCola pepsiCola = new PepsiCola(physicalStore);
    pepsiCola.saleCola();
    Sale networkStore = new NetworkStore();
    CocaCola cocaCola = new CocaCola(networkStore);
    cocaCola.saleCola();

}
```

### 组合模式

组合模式比较好理解，我们日常生活中的层级结构多半是树形结构，如企业的各个部门，形成了以总公司到最下面各个小组之间的树形结构，如果我们要统计本公司总的人数，可以每个部门分别进行统计，然后不断上传，直到总公司便可以得出结果。

此时我们直到他们都可以统计人口、删减部门，因此可以定义一个统一接口进行规范。

**定义接口**

```java
interface Department{
    void add();
    void remove();
    int getCount();
}
```

**实现类**

```java
class Dept implements Department{

    List<Department> children;
    @Override
    public void add() {

    }

    @Override
    public void remove() {

    }

    @Override
    public int getCount() {
        int count  = 0;
        for (Department child : children) {
            count += child.getCount();
        }
        return count;
    }
}
```

一般类中均有add(node)、remove(node)、getChildren()方法。因为这种模式比较简单，此处不多赘述。

### 装饰器模式

装饰器和代理模式很像，不同在于在代理模式中，我们着重去实现接口定义的方法，也就是对原方法进行功能增强。但是在装饰器模式中，我们着重于去扩展新的小功能。

举一个小栗子

我们现在存在一个垃圾桶，现在有收垃圾这一个功能，现在我们希望对他进行一些改造，希望他有一个能够跑来跑去的功能，这样就方便我们随时随地都可以丢垃圾了。

于是我们做了一个大箱子，将垃圾桶蹲到箱子里面，子啊箱子的外面安装了一些小轮子，这样垃圾桶就可以跑起来了，并且并没有对垃圾桶的原本结构进行改造，没有在他的身上钻孔，只是将他完整的放到了一个箱子里面。这样，那个箱子就是我们说的装饰器。

并且这个新的装置还是有收垃圾的功能的，并且这个功能就是原本的垃圾桶实现的，因此仍需要实现垃圾桶接口。

并且为了方便扩展，我们将这个类定义为一个抽象类。

**垃圾桶接口**

```java
interface Dustbin{
    void collectGarbage();
}
```

**垃圾桶实现类**

```java
class RefuseBin implements Dustbin{

    @Override
    public void collectGarbage() {
        System.out.println("垃圾桶收集垃圾");
    }
}
```

**带轮子的抽象类**

```java
abstract class DustbinWithWheel implements Dustbin{
    public Dustbin dustbin;
    public DustbinWithWheel(Dustbin dustbin){
        this.dustbin = dustbin;
    }
    public abstract void runToCollect();
}
```

**带轮子垃圾桶的实现类**

```java
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
```

### 享元模式

运用共享技术有效地支持大量细粒度的对象。顾名思义就是一个共享模式，例如共享单车。

他的本质是一个池技术，比如线程池、数据库连接池等等等

如果我们要建立一个共享单车，首先要创建一个单车接口，因此单车包括自行车和电动车等，均需要进行分别创建。

然后我们创建一个共享单车工厂，所有的共享单车均需要从此处取出，同时工厂应当使用单例模式。

**单车接口**

```java
interface ISharedBike{
    void ride();
}
```

**自行车和电动车**

```java
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
```

**单车工厂**

```java
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
```

**客户端**

```java
public static void main(String[] args) {
    BicycleFactory bicycleFactory = BicycleFactory.getBicycleFactory();
    ISharedBike bicycle = bicycleFactory.getBike("bicycle");
    bicycle.ride();

}
```

### 门面模式

门面模式又称为外观模式，我们最常见到的案例就是SLF4J,

门面模式隐藏了系统的复杂性，为客户端提供了一个简单的统一的简单接口

例如我们要用不同的笔写字

首先我们要定义不同颜色的类

**接口类**

```java
interface ColorPen{
    void draw();
}
```

**颜色实现类**

```java
class RedPen implements ColorPen{

    @Override
    public void draw() {
        System.out.println("红笔画画");
    }
}
class BluePen implements ColorPen{

    @Override
    public void draw() {
        System.out.println("蓝笔画画");
    }
}
class GreenPen implements ColorPen{

    @Override
    public void draw() {
        System.out.println("绿笔画画");
    }
}
```

你们我们如果先用红笔画再用绿笔画会怎么样？

```java
RedPen red = new RedPen();
red.draw();
GreenPen green = new GreenPen();
green.draw();
```

客户端要自己去选择实现类，再去实例化、调用方法，太麻烦了！我们如何进行简化呢？

我们建立一个彩笔类

```java
class ColorPen{
    private RedPen redPen;
    private BluePen bluePen;
    private GreenPen greenPen;
    public ColorPen(){
        redPen = new RedPen();
        bluePen = new BluePen();
        greenPen = new GreenPen();
    }

    public void greenDraw(){
        greenPen.draw();
    }
    public void blueDraw(){
        bluePen.draw();
    }
    public void redDraw(){
        redPen.draw();
    }
}
```

客户端再次调用

```java
public static void main(String[] args) {
    ColorPen colorPen = new ColorPen();
    colorPen.redDraw();
    colorPen.greenDraw();
}
```

是不是简单多了

### 过滤器模式

过滤器模式（Filter Pattern）又称标准模式（Criteria Pattern），顾名思义通过一步一步的过滤，形成一层层的标准

比如我们要设计一个软件，它可以将plmm都挑选出来

于是我们首先定义一个参与人的类（为了方便，此除不再私有化成员属性）

```java
class People{
    String name;
    enum sex{
        MALE,
        FEMALE
    };
    int age;
}
```

再定义一个过滤器的接口类

```java
interface Filter{
    List<People> filter(List<People> peopleList);
}
```

实现一个过滤性别的类

```java
class SexFilter implements Filter{

    @Override
    public List<People> filter(List<People> peopleList) {
        return  peopleList.stream().filter(people -> {
            return people.sex == 1;
        }).collect(Collectors.toList());
    }
}
```

再实现一个年龄过滤器

```java
class AgeFilter implements Filter{

    @Override
    public List<People> filter(List<People> peopleList) {
        return  peopleList.stream().filter(people -> {
            return people.age <= 26;
        }).collect(Collectors.toList());
    }
}
```

现在客户端调用

```java
public static void main(String[] args) {
    List<People> list = new ArrayList<>();
    list.add(new People("小明",20,1));
    list.add(new People("小李",15,1));
    list.add(new People("小蓝",28,1));
    list.add(new People("小红",11,1));
    list.add(new People("小王",21,0));
    list.add(new People("小强",27,0));

    System.out.println("参选人有：");
    list.forEach(people -> {
        System.out.print(people+" ");
    });
    System.out.println("\n过滤年龄");
    Filter ageFilter = new AgeFilter();
    list = ageFilter.filter(list);
    list.forEach(people -> {
        System.out.print(people+" ");
    });
    System.out.println("\n过滤性别");
    Filter sexFilter = new SexFilter();
    list = sexFilter.filter(list);
    list.forEach(people -> {
        System.out.print(people+" ");
    });
}
```

调用结果为：

```
参选人有：
People{name='小明', age=20, sex=1} People{name='小李', age=15, sex=1} People{name='小蓝', age=28, sex=1} People{name='小红', age=11, sex=1} People{name='小王', age=21, sex=0} People{name='小强', age=27, sex=0} 
过滤年龄
People{name='小明', age=20, sex=1} People{name='小王', age=21, sex=0} 
过滤性别
People{name='小明', age=20, sex=1} 
```

## 行为型模式

### 责任链模式

责任链模式，顾名思义室将执行责任任务的各个组件组装成链，客户端只需要直到这个链的头结点，就可以完成整个任务，客户端对其是不感知的，各个责任组件间依次调用。避免了大量的if else 语句，做到了低耦合的效果。

并且当工作流程发生变化的时候，只需要动态更改调用次序，不需要队客户端进行更改，利于迭代，满足了开闭原则。

责任链模式是一个种行为模式，过滤器模式是一种结构模式。责任链模式通常采用过滤器模式的方式进行实现。例如Java web的过滤器就是一种责任链模式。

这里举出一个责任链模式是示例

首先肯定是要定义一个责任链的抽象类，抽象类包含一个成员属性为下一个环节的实现类。并且包含setNext和getNext方法进行动态调整责任链。同时一个抽象方法handleRequest表示具体实现类的主要处理方法。

```java
abstract class Handler{
    private Handler next;
    public void setNext(Handler next) {
        this.next=next;
    }
    public Handler getNext() {
        return next;
    }
    public abstract boolean handleRequest(String request);
}
```

接下来我们要实现 一个能够返回判断否有数字的具体类和小写字母的具体类

```java
class NumHandler extends Handler{

    @Override
    public boolean handleRequest(String request) {
            boolean flag = false;
            for(int i = 0;i < request.length();i++)
            {
                if( request.charAt(i) <='9' &&  request.charAt(i)>='0'){
                    flag = true;
                }
            }
        if(getNext()!=null){
            flag = flag && getNext().handleRequest(request);
        }
        return flag;
    }
}
class CharHandler extends Handler{

    @Override
    public boolean handleRequest(String request) {
        boolean flag = false;
        for(int i = 0;i < request.length();i++)
        {
            if( request.charAt(i) <='z' && request.charAt(i) >= 'a'){
                flag = true;
            }
        }
        if(getNext()!=null){
            flag = flag && getNext().handleRequest(request);
        }
        return flag;
    }
}
```

客户端调用

```java
public static void main(String[] args) {
    Handler handler1 = new NumHandler();
    Handler handler2 = new CharHandler();
    handler1.setNext(handler2);
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()){
        String s = scanner.nextLine();
        boolean b = handler1.handleRequest(s);
        System.out.print(s);
        if(b){
            System.out.println("符合要求");
        }
        else {
            System.out.println("不符合要求");
        }
    }

}
```

### 命令模式

命令模式将原本绑定在一起的视图层逻辑层分离开。命令模式为视图层进行方法的包装，由此视图层只需要调用控制类，就可以定制化完成系列化的指令而不需要在视图层上面添加逻辑代码

首先建立一个命令的抽象接口

```java
interface Command{
    void execute();
}
```

定义不同的具体实现类

```java
class SingCommand implements Command{
    People people;

    public SingCommand(People people) {
        this.people = people;
    }

    @Override
    public void execute() {
        people.sing();
    }
}
class DanceCommand implements Command{
    People people;

    public DanceCommand(People people) {
        this.people = people;
    }

    @Override
    public void execute() {
        people.dance();
    }
}
class RapCommand implements Command{
    People people;

    public RapCommand(People people) {
        this.people = people;
    }

    @Override
    public void execute() {
        people.rap();
    }
}
class BasketballCommand implements Command{
    People people;

    public BasketballCommand(People people) {
        this.people = people;
    }

    @Override
    public void execute() {
        people.basketBall();
    }
}
```

由上可知，具体的实现是一个people实现的

```java
class People{
    void sing(){
        System.out.println("唱");
    }
    void dance(){
        System.out.println("跳");
    }
    void rap(){
        System.out.println("RAP");
    }
    void basketBall(){
        System.out.println("篮球");
    }
}
```

我们要将复杂的业务逻辑包装起来供客户端调用

命令的包装类如下

```java
class Controller{
    List<Command> list = new ArrayList<>();
    void addCommand(Command command){
        list.add(command);
    }
    void play(){
        list.forEach(command -> command.execute());
    }
}
```

客户端调用

```java
public static void main(String[] args) {
    People CXK = new People();
    Controller controller = new Controller();
    controller.addCommand(new SingCommand(CXK));
    controller.addCommand(new DanceCommand(CXK));
    controller.addCommand(new RapCommand(CXK));
    controller.addCommand(new BasketballCommand(CXK));
    controller.play();
}
```

### 解释器模式

解释器模式就是解释一段特定文本中的含义。

在实际中使用的极少

使用的是菜鸟教程中的例子

**创建一个表达式接口**

```java
interface Expression {
    public boolean interpret(String context);
}
```

**创建实现了上述接口的实体类**

```java
class TerminalExpression implements Expression {

    private String data;

    public TerminalExpression(String data){
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        if(context.contains(data)){
            return true;
        }
        return false;
    }
}
```

```java
class OrExpression implements Expression {

    private Expression expr1 = null;
    private Expression expr2 = null;

    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        return expr1.interpret(context) || expr2.interpret(context);
    }
}
```

```java
class AndExpression implements Expression {

    private Expression expr1 = null;
    private Expression expr2 = null;

    public AndExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        return expr1.interpret(context) && expr2.interpret(context);
    }
}
```

***InterpreterPatternDemo* 使用 *Expression* 类来创建规则，并解析它们**

```java
class InterpreterPatternDemo {

    //规则：Robert 和 John 是男性
    public static Expression getMaleExpression(){
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    //规则：Julie 是一个已婚的女性
    public static Expression getMarriedWomanExpression(){
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }

}
```

**客户端调用**

```java
public static void main(String[] args) {
    Expression isMale = InterpreterPatternDemo.getMaleExpression();
    Expression isMarriedWoman = InterpreterPatternDemo.getMarriedWomanExpression();
    System.out.println("John is male? " + isMale.interpret("John"));
    System.out.println("Julie is a married women? "
            + isMarriedWoman.interpret("Married Julie"));
}
```

### 迭代器模式

在不暴露聚合对象内部信息的情况下完成对对象的顺序访问

主要有四部分组成

- 抽象迭代器（Iterator）：通常包含 hasNext()、next() 等方法，规定访问和遍历聚合元素的接口。

- 抽象聚合（Aggregate）角色：自己定义的存储大量元素的集合类，通常内部使用List或Set等存储元素。

- 具体聚合（ConcreteAggregate）角色：实现抽象聚合类，应当可以返回一个具体迭代器的实例。

- 具体迭代器（Concretelterator）角色：迭代器接口的实现类。

元素类

```java
class Student{
    String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name: "+name;
    }
}
```

抽象迭代器

```java
interface Iterator{
    boolean hasNext();
    Student next();
}
```

抽象聚合

```java
interface Students {
    void appendStudent(Student student);

    void removeStudent(Student student);

    StudentIterator getStudentIterator();
}
```

具体聚合

```java
class StudentHub implements Students {
    /**
     * 存储元素列表
     */
    private List<Student> list = new ArrayList<Student>();

    @Override
    public void appendStudent(Student student) {
        this.list.add(student);
    }

    @Override
    public void removeStudent(Student student) {
        this.list.remove(student);
    }

    @Override
    public StudentIterator getStudentIterator() {
        return new StudentIterator(list);
    }
}
```

具体迭代器

```java
class StudentIterator implements Iterator{
    private List<Student> list;
    private int position = 0;

    public StudentIterator(List<Student> list) {
        this.list = list;
    }
    @Override
    public boolean hasNext() {
        return position < list.size();
    }

    @Override
    public Student next() {
        Student currentStudent = list.get(position);
        position ++;
        return currentStudent;
    }
}
```

客户端

```java
public static void main(String[] args) {
    StudentHub studentHub = new StudentHub();
    studentHub.appendStudent(new Student("小赵"));
    studentHub.appendStudent(new Student("小钱"));
    studentHub.appendStudent(new Student("小孙"));
    studentHub.appendStudent(new Student("小李"));

    StudentIterator studentIterator = studentHub.getStudentIterator();
    while (studentIterator.hasNext()){
        System.out.println(studentIterator.next());
    }
}
```

### 中介者模式

中介模式目的在于多个对象之间的通信。将网状结构转化为星形结构。降低系统耦合度

比如我们要实现一个聊天室

聊天室的服务器就是一个中介，将消息散播给各个绑定的对象

用户

```java
class ChatUser{
    String name;

    public ChatUser(String name) {
        this.name = name;
    }
}
```

聊天室



```java
class ChatRoom{
    List<ChatUser> users = new ArrayList<>();
    void addUser(ChatUser user){
        users.add(user);
    }
    void broadcast(ChatUser user,String message){
        users.forEach(user1 -> {
            if(user1.name != user.name){
                System.out.println(user.name+"->"+user1.name+":"+message);
            }
        });
    }
}
```

客户端

```java
public static void main(String[] args) {
    ChatUser zhao = new ChatUser("小赵");
    ChatUser qian = new ChatUser("小钱");
    ChatUser sun = new ChatUser("小孙");
    ChatUser li = new ChatUser("小李");

    ChatRoom chatRoom = new ChatRoom();
    chatRoom.addUser(zhao);
    chatRoom.addUser(qian);
    chatRoom.addUser(sun);
    chatRoom.addUser(li);

    chatRoom.broadcast(zhao,"你们好啊");

}
```

### 备忘录模式

备忘录模式使用比较少。简而言之就是保存对象快照，并可以完成对快照的恢复功能

实现中有基本几个类：文档类，也就是要保存的文件；快照类，也就是文件保存的形式，基本成员变量中要有文档的内容，并且可以额外加一些时间，名称之类的；快照库，用来保存快照

文档类

```java
class Document {
    private String context = "";

    public String getContext(){
        return context;
    }
    public void update(String s){
        this.context = s;
    }
    public DocumentCopy save(){
        return new DocumentCopy(context);
    }
    public void rollBack(DocumentCopy documentCopy){
        this.context = documentCopy.getContext();
    }

}
```

快照类

```java
class DocumentCopy{
    private String context;

    public DocumentCopy(String context) {
        this.context = context;
    }
    public String getContext(){
        return this.context;
    }
}
```

快照库

```java
class DocumentCopyStack{
    Stack<DocumentCopy> stack = new Stack<>();
    void add(DocumentCopy documentCopy){
        stack.push(documentCopy);
    }
    DocumentCopy get(){
        return stack.pop();
    }

}
```

客户端

```java
public static void main(String[] args) {
    DocumentCopyStack documentCopyStack = new DocumentCopyStack();

    Document document = new Document();
    document.update("版本1");
    System.out.println(document.getContext());
    documentCopyStack.add(document.save());
    document.update("版本2");
    System.out.println(document.getContext());
    documentCopyStack.add(document.save());
    document.update("版本3");
    System.out.println(document.getContext());
    documentCopyStack.add(document.save());

    document.rollBack(documentCopyStack.get());
    System.out.println(document.getContext());
    document.rollBack(documentCopyStack.get());
    System.out.println(document.getContext());
    document.rollBack(documentCopyStack.get());
    System.out.println(document.getContext());

}
```

### 观察者模式

观察者模式就是订阅发布模式，通常使用生产者消费者模型进行解决

如今可以使用消息队列进行实现，也可以使用jdk的java.util.Observable 和 java.util.Observer 这两个类进行辅助

发布订阅似乎更容易被大家所接受，顾名思义就是很多读者订阅了一个报刊，当这个报刊更新的时候，他会通知所有的读者进行阅读。

首先定义一个订阅者接口，满足这个接口的人就可以订阅发布者的内容了

```java
abstract class Observer {
   protected Subject subject;
   public abstract void update();
}
```

其中 Subject代表的是一个发布者，对应订阅来说就是订阅的一个主题

```java
class Subject {

   private List<Observer> observers = new ArrayList<Observer>();
   private int state;

   public int getState() {
      return state;
   }

   public void setState(int state) {
      this.state = state;
      // 数据已变更，通知观察者们
      notifyAllObservers();
   }

   public void attach(Observer observer){
      observers.add(observer);        
   }

   // 通知观察者们
   public void notifyAllObservers(){
      for (Observer observer : observers) {
         observer.update();
      }
   }     
}
```

接下来定义一个订阅者的类

```java
class BinaryObserver extends Observer {

      // 在构造方法中进行订阅主题
    public BinaryObserver(Subject subject) {
        this.subject = subject;
        // 通常在构造方法中将 this 发布出去的操作一定要小心
        this.subject.attach(this);
    }

      // 该方法由主题类在数据变更的时候进行调用
    @Override
    public void update() {
        String result = Integer.toBinaryString(subject.getState());
        System.out.println("订阅的数据发生变化：" + result);
    }
}
```

客户端进行调用

```java
public static void main(String[] args) {
    Subject subject = new Subject(1);
    // 定义观察者
    new SpecificObserver(subject);

    // 模拟数据变更，这个时候，观察者们的 update 方法将会被调用
    subject.setState(2);
}
```

结果为：

```
订阅的数据发生变化：10
```

### 状态模式

状态模式是一种消除if else语句的方法。核心在于给要控制的类一个状态类。可以根据改变状态来控制类的行为。

首先定义状态接口

```java
interface State {
    public void doAction(Context context);
}
```

实现几个具体接口类

```java
class HungryState implements State{

    @Override
    public void doAction(Context context) {
        System.out.println(context.toString()+"很饿");
    }
}
class FullState implements State{

    @Override
    public void doAction(Context context) {
        System.out.println(context.toString()+"饱了");
    }
}
```

使用状态模式的类

```java
class Context {
    private State state;
    private String name;
    public Context(String name) {
        this.name = name;
        //默认策略
        state = new HungryState();
    }

    @Override
    public String toString() {
        return "Context{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setState(State state) {
        this.state = state;
    }
    public State getState() {
        return this.state;
    }
    public void doAction() {
        this.state.doAction(this);
    }
}
```

客户端调用

```java
public static void main(String[] args) {
    Context context = new Context("小明");
    context.doAction();
    context.setState(new FullState());
    context.doAction();
}
```

### 空对象模式

(示例来源于菜鸟教程)

空对象模式就是使用一个空对象类来代表这里的是一个空对象。这样做的目的是如果出现了一个空对象，也可以有一个默认的方法。

于是我们先定义一个抽象类，他是行为对象（真实行为对象和空行为对象）的父类

```java
abstract class AbstractCustomer {
    protected String name;
    public abstract boolean isNil();
    public abstract String getName();
}
```

接下来定义一个真实行为类

```java
class RealCustomer extends AbstractCustomer {
 
   public RealCustomer(String name) {
      this.name = name;    
   }
   
   @Override
   public String getName() {
      return name;
   }
   
   @Override
   public boolean isNil() {
      return false;
   }
}
```

还有一个空行为对象类

```java
class NullCustomer extends AbstractCustomer {
 
   @Override
   public String getName() {
      return "Not Available in Customer Database";
   }
 
   @Override
   public boolean isNil() {
      return true;
   }
}
```

那么如果产生这两种不太的对象呢，他们是同一个工厂生产的

```java
class CustomerFactory {
   
   public static final String[] names = {"Rob", "Joe", "Julie"};
 
   public static AbstractCustomer getCustomer(String name){
      for (int i = 0; i < names.length; i++) {
         if (names[i].equalsIgnoreCase(name)){
            return new RealCustomer(name);
         }
      }
      return new NullCustomer();
   }
}
```

客户端进行调用

```java
public static void main(String[] args) {
 
      AbstractCustomer customer1 = CustomerFactory.getCustomer("Rob");
      AbstractCustomer customer2 = CustomerFactory.getCustomer("Bob");
      AbstractCustomer customer3 = CustomerFactory.getCustomer("Julie");
      AbstractCustomer customer4 = CustomerFactory.getCustomer("Laura");
 
      System.out.println("Customers");
      System.out.println(customer1.getName());
      System.out.println(customer2.getName());
      System.out.println(customer3.getName());
      System.out.println(customer4.getName());
   }
```

```
Customers
Rob
Not Available in Customer Database
Julie
Not Available in Customer Database
```

### 策略模式

策略模式看起来和状态模式是很像的。但是他们的关注点不同，在状态模式看来，更加关注他什么状态去做什么，但是策略模式更加关注他的行为应该是什么。

策略模式的使用频度真的非常的高

策略模式，是行为类在执行某行为的时候，方法通过客户端进行指定。

如jdk创建线程池的时候，需要指定一个拒绝策略。它我们就是在调用创建线程池工厂的时候进行指定的。

现在我们自己来设计一个策略模式

首先完成一个策略接口

```java
interface Strategy {
    void pay(int money);
}
```

接下来实现几个策略

```java
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
```

实现一个使用这个行为的具体类

```java
class Shop {
    private Strategy strategy;

    public Shop(Strategy strategy){
        this.strategy = strategy;
    }

    public void pay(int money){
       strategy.pay(money);
    }
}
```

客户端调用 

```java
Shop shop1 = new Shop(new ElderCard());
shop1.pay(100);
Shop shop2 = new Shop(new ChildrenCard());
shop2.pay(100);
Shop shop3 = new Shop(new CommonCard());
shop3.pay(100);
```

老年人免费---支付0元 
儿童半折---支付50元 
无优惠---支付100元 

甚至可以使用lambda表达式简化

```java
Shop shop = new Shop(money -> {
    System.out.printf("lambda支付---支付%d元 %n",money);
});
shop.pay(100);
```

lambda支付---支付100元 

### 模板模式

模板模式，就是父类通过一个final的方法，定义一个模板，子类来实现父类的抽象方法来实现整体流程行为的定制化

首先定义抽象父类

```java
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
```

定义实现类

```java
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
```

客户端

```java
public static void main(String[] args) {
    new WesternStyleFood().dinner();
    new ChineseFood().dinner();
}
```

### 访问者模式

访问者模式是最复杂的一个设计模式

他是一直为了实现数据结构与数据分类、实现简单化升级的一直设计模式

可以降低系统耦合度

他是实现系统访问抽象组件

所有首先定义抽象组件接口

```java

interface ComputerPart {
    public void accept();
}
```

接下来定义具体实现类

```java
class CPU  implements ComputerPart {

    void run(){
        System.out.println("计算：");
    }
    @Override
    public void accept() {
        run();
    }
}
```

```java
class RAM implements ComputerPart {
    String info;
    public RAM(String info){
        this.info = info;
    }
    @Override
    public void accept() {
        System.out.println(info);
    }
}
```

定义一个访问者接口

```java
interface ComputerPartVisitor {
    public void visit(CPU cpu);
    public void visit(RAM ram);
}
```

实体类实现接口

```java
class ComputerPartDisplayVisitor implements ComputerPartVisitor {

    @Override
    public void visit(CPU cpu) {
        cpu.accept();
    }

    @Override
    public void visit(RAM ram) {
        ram.accept();
    }
}
```

客户端调用

```java
public static void main(String[] args) {
    ComputerPartDisplayVisitor computerPartDisplayVisitor = new ComputerPartDisplayVisitor();
    computerPartDisplayVisitor.visit(new CPU());
    computerPartDisplayVisitor.visit(new RAM("1+1=2"));
}
```

计算：
1+1=2



**本段完结！**
