package behavioralPatterns;

/**
 * 访问者模式
 *
 * @author WTDYang
 * @date 2022/11/08
 */
public class VisitorPattern {
    public static void main(String[] args) {
        ComputerPartDisplayVisitor computerPartDisplayVisitor = new ComputerPartDisplayVisitor();
        computerPartDisplayVisitor.visit(new CPU());
        computerPartDisplayVisitor.visit(new RAM("1+1=2"));
    }
}
interface ComputerPart {
    public void accept();
}
class CPU  implements ComputerPart {

    void run(){
        System.out.println("计算：");
    }
    @Override
    public void accept() {
        run();
    }
}
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
interface ComputerPartVisitor {
    public void visit(CPU cpu);
    public void visit(RAM ram);
}
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