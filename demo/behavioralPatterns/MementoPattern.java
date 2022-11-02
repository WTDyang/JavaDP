package behavioralPatterns;

import java.util.Stack;

/**
 * 备忘录模式
 *
 * @author WTDYang
 * @date 2022/11/02
 */
public class MementoPattern {
    public static void main(String[] args) {
        DocumentCopyStack documentCopyStack = new DocumentCopyStack();

        Document document = new Document();
        document.update("版本1");
        System.out.println(document.getContext());
        documentCopyStack.add(document.save());
        document.update("版本2");
        System.out.println(document.getContext());
        documentCopyStack.add(document.save());
        document.update("版本3");
        System.out.println(document.getContext());
        documentCopyStack.add(document.save());

        document.rollBack(documentCopyStack.get());
        System.out.println(document.getContext());
        document.rollBack(documentCopyStack.get());
        System.out.println(document.getContext());
        document.rollBack(documentCopyStack.get());
        System.out.println(document.getContext());

    }
}

/**
 * 文件快照
 *
 * @author WTDYang
 * @date 2022/11/02
 */
class DocumentCopy{
    private String context;

    public DocumentCopy(String context) {
        this.context = context;
    }
    public String getContext(){
        return this.context;
    }
}

/**
 * 文档快照栈
 *
 * @author WTDYang
 * @date 2022/11/02
 */
class DocumentCopyStack{
    Stack<DocumentCopy> stack = new Stack<>();
    void add(DocumentCopy documentCopy){
        stack.push(documentCopy);
    }
    DocumentCopy get(){
        return stack.pop();
    }

}
/**
 * 文档
 *
 * @author WTDYang
 * @date 2022/11/02
 */
class Document {
    private String context = "";

    public String getContext(){
        return context;
    }
    public void update(String s){
        this.context = s;
    }
    public DocumentCopy save(){
        return new DocumentCopy(context);
    }
    public void rollBack(DocumentCopy documentCopy){
        this.context = documentCopy.getContext();
    }

}
