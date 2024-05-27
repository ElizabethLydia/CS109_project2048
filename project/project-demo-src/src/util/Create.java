package util;//util包本身是用来存放工具类的，所以这个接口应该放在util包下
/*
给JFrame和JDialog各创建createButton和createLabel方法
 */
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public interface Create {//创建按钮和标签
    default JButton createButton(String name, Point location, int width, int height,Container parent) {
        //这是一个默认方法，可以直接调用
        //创建按钮,按钮名称，位置，宽度，高度，父窗口,返回按钮
        JButton button = new JButton(name);//创建一个按钮
        button.setLocation(location);//设置按钮位置
        button.setSize(width, height);//设置按钮大小
        parent.add(button);//将按钮添加到父窗口
        return button;//返回按钮
    }

    default JLabel createLabel(String name, Font font, Point location, int width, int height,Container parent,int color) {
        //创建标签,标签名称，字体，位置，宽度，高度，父窗口,返回标签
        JLabel label = new JLabel(name);
        label.setFont(font);
        Color c= new Color(0x8F725E);//设置label的背景颜色，这里是浅灰色
        label.setForeground(new Color(color));
        label.setLocation(location);
        label.setSize(width, height);
        parent.add(label);
        return label;
    }
    default JButton createButtonWithIcon(String name, Point location, int width, int height, Container parent) {
        ImageIcon icon=null;
        switch (name) {
            case "Undo":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/undo.png");
                break;
            case "Restart":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/restart.png");
                break;
            case "Magic":
                icon =new ImageIcon("project/project-demo-src/src/util/pictures/magic.png");
                break;
            case "Left":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/left.png");//这里的路径是相对路径，所以要加上"project/project-demo-src/src/util/pictures/"
                break;
            case "Right":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/right.png");
                break;
            case "Up":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/up.png");
            break;
            case "Down":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/down.png");
                break;
            case "StartGame":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/startGame.png");
                break;
            case "LoadGame":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/loadGame.png");
                break;
            case "Settings":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/settings.png");
                break;
            case "Exit":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/exit.png");
                break;
            case "4x4":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/4x4.png");
                break;
            case "5x5":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/5x5.png");
                break;
            case "Timing":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/timing.png");
                break;
            case "AI":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/ai.png");
                break;
            case "Return":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/return.png");
                break;
            case "usersLogin":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/usersLogin.png");
                break;
            case "visitorsLogin":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/visitorsLogin.png");
                break;
            case "playAgain":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/playAgain.png");
                break;
            case "home":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/home.png");
                break;
            case"Login":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/login.png");
                break;
            case "Register":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/register.png");
                break;
            case "Cancel":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/cancel.png");
                break;
            case "RemoveUser":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/RemoveUser.png");
                break;
            case "ChangeBgm":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/ChangeBgm.png");
                break;
            case "VolumeUp":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/VolumeUp.png");
                break;
            case "VolumeDown":
                icon = new ImageIcon("project/project-demo-src/src/util/pictures/VolumeDown.png");
                break;
        }
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(img);
        JButtonWithIcon button = new JButtonWithIcon(resizedIcon);
        button.setLocation(location);//设置按钮位置
        button.setSize(width, height);//设置按钮大小
        parent.add(button);//将按钮添加到父窗口
        return button;//返回按钮
    }
}
