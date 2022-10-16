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