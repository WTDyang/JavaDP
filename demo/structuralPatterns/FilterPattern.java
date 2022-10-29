package structuralPatterns;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 过滤模式
 *
 * @author WTDYang
 * @date 2022/10/29
 */
public class FilterPattern {
    public static void main(String[] args) {
        List<People> list = new ArrayList<>();
        list.add(new People("小明",20,1));
        list.add(new People("小李",15,1));
        list.add(new People("小蓝",28,1));
        list.add(new People("小红",11,1));
        list.add(new People("小王",21,0));
        list.add(new People("小强",27,0));

        System.out.println("参选人有：");
        list.forEach(people -> {
            System.out.print(people+" ");
        });
        System.out.println("\n过滤年龄");
        Filter ageFilter = new AgeFilter();
        list = ageFilter.filter(list);
        list.forEach(people -> {
            System.out.print(people+" ");
        });
        System.out.println("\n过滤性别");
        Filter sexFilter = new SexFilter();
        list = sexFilter.filter(list);
        list.forEach(people -> {
            System.out.print(people+" ");
        });
    }
}
class People{
    String name;
    int age;
    int sex;
    public People(String name, int age, int sex){
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

}
interface Filter{
    List<People> filter(List<People> peopleList);
}
class SexFilter implements Filter{

    @Override
    public List<People> filter(List<People> peopleList) {
        return  peopleList.stream().filter(people -> people.sex == 1).collect(Collectors.toList());
    }
}
class AgeFilter implements Filter{

    @Override
    public List<People> filter(List<People> peopleList) {
        return  peopleList.stream().filter(people -> people.age <= 26 && people.age >= 18).collect(Collectors.toList());
    }
}