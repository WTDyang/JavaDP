package creationalPatterns;

/**
 * 构建器
 *
 * @author WTDYang
 * @date 2022/10/16
 */
public class Builder {
    public static void main(String[] args) {
        User user = User.builder()
                .name("ounces")
                .password("123")
                .email("123@ounces.com")
                .build();
        System.out.println(user);

    }
}
class User {
    private String name;
    private String password;
    private String email;

    //由于后面会用到全参构造器，但是我们并不想客户端直接调用我们的构造器构建对象，因此将全参构造器设为私有
    private User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }


    //构造器可以使用一个静态内部类，他的属性应当与所要构造的类相同，如此才能接收到全部参数
    public static class UserBuilder {
        private String name;
        private String password;
        private String email;
        //私有化构造器
        private UserBuilder() {
        }

        // 我们需要链式调用，因此需要返回this，也就是返回一个指向这个对象本身的指针
        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        //build()方法，将接收到的参数赋予新构建的对象
        //我们可以在build方法中加入一些参数校验规则。
        public User build() {
            if (name == null || password == null) {
                throw new RuntimeException("用户名和密码必填");
            }
            return new User(name,password,email);
        }
    }

    //我们使用userBuilder方法隐藏了new UserBuilder()，是的代码构造器在被使用的时候更加优雅
    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
