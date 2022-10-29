package structuralPatterns;

/**
 * 门面模式
 *
 * @author WTDYang
 * @date 2022/10/29
 */
public class FacadePattern {
    public static void main(String[] args) {
        ColorPen colorPen = new ColorPen();
        colorPen.redDraw();
        colorPen.greenDraw();
    }
}
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
interface Pen{
    void draw();
}
class RedPen implements Pen{

    @Override
    public void draw() {
        System.out.println("红笔画画");
    }
}
class BluePen implements Pen{

    @Override
    public void draw() {
        System.out.println("蓝笔画画");
    }
}
class GreenPen implements Pen{

    @Override
    public void draw() {
        System.out.println("绿笔画画");
    }
}
