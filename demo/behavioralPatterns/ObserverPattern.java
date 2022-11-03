package behavioralPatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 *
 * @author WTDYang
 * @date 2022/11/03
 */
public class ObserverPattern {
    public static void main(String[] args) {
        Subject subject = new Subject(1);
        // 定义观察者
        new SpecificObserver(subject);

        // 模拟数据变更，这个时候，观察者们的 update 方法将会被调用
        subject.setState(2);
    }
}
abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
class Subject {

    private List<Observer> observers = new ArrayList<Observer>();
    private int state;

    public Subject(int state) {
        this.state = state;
    }

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
class SpecificObserver extends Observer {

    // 在构造方法中进行订阅主题
    public SpecificObserver(Subject subject) {
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