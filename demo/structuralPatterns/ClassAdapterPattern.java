package structuralPatterns;

import java.util.Date;

/**
 * 类适配器模式
 *
 * @author WTDYang
 * @date 2022/10/23
 */
public class ClassAdapterPattern {
    public static void main(String[] args) {
        new DateAdapter().printDate();
    }
}
class DateInfo{
    public static String getDate(){
      return new Date().toString();
   }
}
interface Adapter{
    void printDate();
}
class DateAdapter extends DateInfo implements Adapter{

    @Override
     public void printDate() {
        System.out.println(
                "当前时间为："
                +getDate()
        );
    }
}