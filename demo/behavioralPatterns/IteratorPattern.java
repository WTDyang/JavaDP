package behavioralPatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * 迭代器模式
 *
 * @author WTDYang
 * @date 2022/11/01
 */
public class IteratorPattern {
    public static void main(String[] args) {
        StudentHub studentHub = new StudentHub();
        studentHub.appendStudent(new Student("小赵"));
        studentHub.appendStudent(new Student("小钱"));
        studentHub.appendStudent(new Student("小孙"));
        studentHub.appendStudent(new Student("小李"));

        StudentIterator studentIterator = studentHub.getStudentIterator();
        while (studentIterator.hasNext()){
            System.out.println(studentIterator.next());
        }
    }
}
class Student{
    String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name: "+name;
    }
}
interface Iterator{
    boolean hasNext();
    Student next();
}
class StudentIterator implements Iterator{
    private List<Student> list;
    private int position = 0;

    public StudentIterator(List<Student> list) {
        this.list = list;
    }
    @Override
    public boolean hasNext() {
        return position < list.size();
    }

    @Override
    public Student next() {
        Student currentStudent = list.get(position);
        position ++;
        return currentStudent;
    }
}
interface Students {
    void appendStudent(Student student);

    void removeStudent(Student student);

    StudentIterator getStudentIterator();
}
class StudentHub implements Students {
    /**
     * 存储元素列表
     */
    private List<Student> list = new ArrayList<Student>();

    @Override
    public void appendStudent(Student student) {
        this.list.add(student);
    }

    @Override
    public void removeStudent(Student student) {
        this.list.remove(student);
    }

    @Override
    public StudentIterator getStudentIterator() {
        return new StudentIterator(list);
    }
}
