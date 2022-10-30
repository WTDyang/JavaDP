package behavioralPatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令模式
 *
 * @author WTDYang
 * @date 2022/10/30
 */
public class CommandPattern {
    public static void main(String[] args) {
        People CXK = new People();
        Controller controller = new Controller();
        controller.addCommand(new SingCommand(CXK));
        controller.addCommand(new DanceCommand(CXK));
        controller.addCommand(new RapCommand(CXK));
        controller.addCommand(new BasketballCommand(CXK));
        controller.play();
    }
}
interface Command{
    void execute();
}
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
class Controller{
    List<Command> list = new ArrayList<>();
    void addCommand(Command command){
        list.add(command);
    }
    void play(){
        list.forEach(command -> command.execute());
    }
}
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