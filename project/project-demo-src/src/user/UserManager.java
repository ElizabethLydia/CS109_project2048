package user;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, String> users = new HashMap<>();
    //这个类是用来管理用户的类,这个类的作用是用来管理用户的登录、注册等操作,这个类的实例化是在Main中进行的
    //这个类中有一个Map类型的users变量,用来存储用户的用户名和密码,通过用户名来判断用户是否存在,通过密码来验证用户是否登录成功
    //HashMap是一个散列表，它存储的内容是键值对(key-value)映射,HashMap类实现了Map接口,HashMap类中有一个内部类Entry,Entry类实现了Map.Entry接口,Entry类中有两个成员变量key和value,分别表示键和值。

    //判断用户是否存在,如果用户存在,则返回true,否则返回false
    public boolean isUserExists(String username) {
        return users.containsKey(username);
    }

    //在注册用户的dialog中,调用这个方法,如果用户不存在,则成功注册用户,并将用户的用户名和密码存储到users中,如果用户存在,则提示用户用户名已存在
    public void registerUser(String username, String password) {
        if (!isUserExists(username)) {//判断用户是否存在,如果用户不存在,则告知用户注册成功,并将用户的用户名和密码存储到users中
            JOptionPane.showMessageDialog(null, "User registered successfully!");
            users.put(username, password);//将用户的用户名和密码存储到users中,这个users是一个Map类型的变量,用来存储用户的用户名和密码,实际存储的是键值对(key-value)映射
            System.out.println("User registered successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Username already exists! Please try again with a different username.");//如果用户存在,则提示用户用户名已存在，提醒用户重新输入用户名;
            //让用户重新输入用户名
            RegisterView registerView = new RegisterView(null, this);
            registerView.setVisible(true);
            System.out.println("Username already exists!");
        }
    }

    //验证用户是否存在,并且验证用户的密码是否正确，如果用户存在且密码正确，则返回true，否则返回false
    public boolean validateUser(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}