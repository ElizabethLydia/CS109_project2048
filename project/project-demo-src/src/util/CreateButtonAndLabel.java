package util;//util包本身是用来存放工具类的，所以这个接口应该放在util包下
/*
给JFrame和JDialog各创建createButton和createLabel方法
 */
import javax.swing.*;
import java.awt.*;

public interface CreateButtonAndLabel {//创建按钮和标签
    default JButton createButton(String name, Point location, int width, int height,JDialog parent) {
        //这是一个默认方法，可以直接调用
        //创建按钮,按钮名称，位置，宽度，高度，父窗口,返回按钮
        JButton button = new JButton(name);//创建一个按钮
        button.setLocation(location);//设置按钮位置
        button.setSize(width, height);//设置按钮大小
        parent.add(button);//将按钮添加到父窗口
        return button;//返回按钮
    }
    default JButton createButton(String name, Point location, int width, int height,JFrame parent) {//重载
        //创建按钮,按钮名称，位置，宽度，高度，父窗口,返回按钮,JFrame parent不理解
        JButton button = new JButton(name);
        button.setLocation(location);
        button.setSize(width, height);
        parent.add(button);//将按钮添加到父窗口
        return button;
    }
    default JLabel createLabel(String name, Font font, Point location, int width, int height,JDialog parent) {
        //创建标签,标签名称，字体，位置，宽度，高度，父窗口,返回标签
        JLabel label = new JLabel(name);
        label.setFont(font);
        label.setLocation(location);
        label.setSize(width, height);
        parent.add(label);
        return label;
    }
    default JLabel createLabel(String name, Font font, Point location, int width, int height,JFrame parent) {
        //创建标签,标签名称，字体，位置，宽度，高度，父窗口,返回标签
        JLabel label = new JLabel(name);
        label.setFont(font);
        label.setLocation(location);
        label.setSize(width, height);
        parent.add(label);
        return label;
    }
}
