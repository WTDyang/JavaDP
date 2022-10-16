package creationalPatterns;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 单例
 *
 * @author WTDYang
 * @date 2022/10/15
 */
@SuppressWarnings("all")
public class SingletonPattern {
    public static void main(String[] args) {
        Long begin = new Date().getTime();
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
        System.out.println(set.size()+"个实例\n耗时："+(new Date().getTime() - begin));
    }
}

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
enum EnumSingleton {
    INSTANCE;
    public EnumSingleton getInstance(){
        return INSTANCE;
    }
}