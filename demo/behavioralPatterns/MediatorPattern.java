package behavioralPatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * 中介者模式
 *
 * @author WTDYang
 * @date 2022/11/01
 */
public class MediatorPattern {
    public static void main(String[] args) {
        ChatUser zhao = new ChatUser("小赵");
        ChatUser qian = new ChatUser("小钱");
        ChatUser sun = new ChatUser("小孙");
        ChatUser li = new ChatUser("小李");

        ChatRoom chatRoom = new ChatRoom();
        chatRoom.addUser(zhao);
        chatRoom.addUser(qian);
        chatRoom.addUser(sun);
        chatRoom.addUser(li);

        chatRoom.broadcast(zhao,"你们好啊");

    }
}
class ChatUser{
    String name;

    public ChatUser(String name) {
        this.name = name;
    }
}
class ChatRoom{
    List<ChatUser> users = new ArrayList<>();
    void addUser(ChatUser user){
        users.add(user);
    }
    void broadcast(ChatUser user,String message){
        users.forEach(user1 -> {
            if(user1.name != user.name){
                System.out.println(user.name+"->"+user1.name+":"+message);
            }
        });
    }
}
