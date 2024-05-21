package user;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<User, String> users = new HashMap<>();
    //这个类是用来管理用户的类,这个类的作用是用来管理用户的登录、注册等操作,这个类的实例化是在Main中进行的
    //这个类中有一个Map类型的users变量,用来存储用户的用户名和密码,通过用户名来判断用户是否存在,通过密码来验证用户是否登录成功
    //HashMap是一个散列表，它存储的内容是键值对(key-value)映射,HashMap类实现了Map接口,HashMap类中有一个内部类Entry,Entry类实现了Map.Entry接口,Entry类中有两个成员变量key和value,分别表示键和值。

    public UserManager() {
        try {
            //创建一个文件读取器,读取文件savegame.txt,如果文件不存在会抛出异常
            BufferedReader reader = new BufferedReader(new FileReader("DataField.txt"));
            int lineNumber = 0;
            //读取文件的每一行
            String line;
            System.out.println("loading data...");
            while ((line = reader.readLine()) != null) {
                User user = new User(this);
                String[] nameParts = line.split(":");
                user.name =nameParts[1];
                //第二行为密码
                line = reader.readLine();
                String[] passwordParts = line.split(":");
                String password = passwordParts[1];
                //最高分
                line = reader.readLine();
                String[] HighestScoreParts = line.split(":");
                user.HighestScore= Integer.parseInt(HighestScoreParts[1]);
                //时间模式最高分
                line = reader.readLine();
                String[] TimeModeHighestScoreParts = line.split(":");
                user.TimeModeHighestScore= Integer.parseInt(TimeModeHighestScoreParts[1]);
                //棋盘大小
                line = reader.readLine();
                String[] sizeParts = line.split(" ");
                user.xCount = Integer.parseInt(sizeParts[0]);
                user.yCount = Integer.parseInt(sizeParts[1]);
                //棋盘
                user.numbers = new int[user.xCount][user.yCount];
                for (int i = 0; i < user.xCount; i++) {
                    line = reader.readLine();
                    String[] numberStrings = line.split(",");
                    for (int j = 0; j < user.yCount; j++) {
                        user.numbers [i][j] = Integer.parseInt(numberStrings[j]);
                    }
                }
                if(user.xCount ==4){
                    reader.readLine();
                }
                //读出本局分数
                line = reader.readLine();
                String[] ScoreParts = line.split(":");
                user.score= Integer.parseInt(ScoreParts[1]);
                //读出本局步数
                line = reader.readLine();
                String[] StepParts = line.split(":");
                user.step= Integer.parseInt(StepParts[1]);
                //读出本局时间（如果有的话）
                line = reader.readLine();
                if(!line.isEmpty()){
                    String[] TimeParts = line.split(":");
                    user.time= Integer.parseInt(TimeParts[1]);
                }
                reader.readLine();
                user.indexLine = lineNumber;
                lineNumber = lineNumber + 14;//依据username在txt文件中行数-1来确定整个用户的位置
                //将用户名和密码存储到users中
                users.put(user, password);
            }
            reader.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
    //判断用户是否存在,如果用户存在,则返回true,否则返回false
    public boolean isUserExists(String username) {
        for (User user : users.keySet()) {
            if (user.name.equals(username)) {
                return true;
            }
        }
        return false;
    }

    //在注册用户的dialog中,调用这个方法,如果用户不存在,则成功注册用户,并将用户的用户名和密码存储到users中,如果用户存在,则提示用户用户名已存在
    public void registerUser(String username, String password) {
        if (!isUserExists(username)) {//判断用户是否存在,如果用户不存在,则告知用户注册成功,并将用户的用户名和密码存储到users中
            User newuser = new User(this);
            newuser.name = username;
            newuser.indexLine =users.size()*14;
            JOptionPane.showMessageDialog(null, "User registered successfully!Return to login page to login.");
            users.put(newuser, password);//将用户的用户名和密码存储到users中,这个users是一个Map类型的变量,用来存储用户的用户名和密码,实际存储的是键值对(key-value)映射
            updateUser();
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
    public User validateUser(String username, String password) {
        boolean exists = false;
        User thisuser=null;
        for (User user : users.keySet()) {
            if (user.name.equals(username)) {
                exists=true;
                thisuser=user;
            }
        }
        if(exists && users.get(thisuser).equals(password)){
            return thisuser;
        }
        return null;
    }
    public void updateUser() {
        try {
            //创建一个文件读取器,读取文件savegame.txt,如果文件不存在会抛出异常
            BufferedWriter writer = new BufferedWriter(new FileWriter("DataField.txt"));
            //读取文件的每一行
            for (User user : users.keySet()) {
                //第一行为用户名
                writer.write("UseName:" + user.name + "\n");
                //第二行为密码
                writer.write("Password:" + users.get(user) + "\n");
                //最高分
                writer.write("HighestScore:" + user.HighestScore + "\n");
                //时间模式最高分
                writer.write("TimeModeHighestScore:" + user.TimeModeHighestScore + "\n");
                //首先写入棋盘的大小
                writer.write(user.xCount + " " + user.yCount + "\n");
                //接下来写入棋盘上的数字
                for (int i = 0; i < user.xCount; i++) {
                    for (int j = 0; j < user.yCount; j++) {
                        writer.write(String.valueOf(user.numbers[i][j]));
                        if (j < user.yCount - 1) {
                            writer.write(","); // 用逗号分隔数字
                        }
                    }
                    writer.newLine(); // 每行结束后换行
                }
                if(user.xCount ==4){
                    writer.newLine();
                }
                //本局分数
                writer.write("Score:" + user.score + "\n");
                //本局步数
                writer.write("Step:" + user.step + "\n");
                //本局时间（如果有的话）
                writer.write("Time:" + user.time + "\n");
                writer.write("********"+"\n");
            }
            writer.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}