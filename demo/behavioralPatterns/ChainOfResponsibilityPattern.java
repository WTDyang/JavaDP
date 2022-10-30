package behavioralPatterns;

import java.util.Scanner;

/**
 * 责任链模式
 *
 * @author WTDYang
 * @date 2022/10/30
 */
public class ChainOfResponsibilityPattern {
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
}
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