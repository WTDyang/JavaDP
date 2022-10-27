package structuralPatterns;

import java.util.List;

/**
 * 组合模式
 *
 * @author WTDYang
 * @date 2022/10/27
 */
public class CompositePattern {
    public static void main(String[] args) {

    }
}

/**
 * 部门
 *
 * @author WTDYang
 * @date 2022/10/27
 */
interface Department{
    void add();
    void remove();
    int getCount();
}

/**
 * 部门
 *
 * @author WTDYang
 * @date 2022/10/27
 */
class Dept implements Department{

    List<Department> children;
    @Override
    public void add() {

    }

    @Override
    public void remove() {

    }

    @Override
    public int getCount() {
        int count  = 0;
        for (Department child : children) {
            count += child.getCount();
        }
        return count;
    }
}