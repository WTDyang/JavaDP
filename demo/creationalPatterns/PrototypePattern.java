package creationalPatterns;

/**
 * 原型模式
 *
 * @author WTDYang
 * @date 2022/10/16
 */
@SuppressWarnings("all")
public class PrototypePattern {
    public static void main(String[] args) {
//        浅拷贝
//        ShallowClone shallowClone = new ShallowClone(1,new Peo("num1"));
//        ShallowClone shallowClone1 = (ShallowClone) shallowClone.clone();
//        shallowClone1.type = 2;
//        shallowClone1.peo.name = "num2";
//        System.out.println(shallowClone);
//        System.out.println(shallowClone1);
//        深拷贝
        DeepClone deepClone = new DeepClone(1,new Peo("num1"));
        DeepClone deepClone1 = (DeepClone)deepClone.Clone();
        deepClone1.type = 2;
        deepClone1.peo.name = "num2";
        System.out.println(deepClone);
        System.out.println(deepClone1);
    }

}

class DeepClone implements Cloneable{
    public int type;
    public Peo peo;
    public DeepClone(int type,Peo peo){this.type = type;this.peo = peo;}
    public Object Clone(){
        DeepClone clone = null;
        try {
            clone = (DeepClone) super.clone();
            clone.peo = (Peo) clone.peo.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }

    @Override
    public String toString() {
        return "DeepClone{" +
                "type=" + type +
                ", peo='" + peo + '\'' +
                '}';
    }
}


class ShallowClone implements Cloneable{
    public int type;
    public Peo peo;
    public ShallowClone(int type,Peo peo){this.type = type;this.peo = peo;}
    public Object clone(){
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
class Peo implements Cloneable{
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
    public Object clone(){
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return clone;
    }
}