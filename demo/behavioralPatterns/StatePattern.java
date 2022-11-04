package behavioralPatterns;

/**
 * 状态模式
 *
 * @author WTDYang
 * @date 2022/11/04
 */
public class StatePattern {
    public static void main(String[] args) {
        Context context = new Context("小明");
        context.doAction();
        context.setState(new FullState());
        context.doAction();
    }
}

/**
 * 上下文
 *
 * @author WTDYang
 * @date 2022/11/04
 */
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
interface State {
    public void doAction(Context context);
}

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