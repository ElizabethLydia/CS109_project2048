package view;
/*
ChooseSize类是一个对话框，用于选择游戏的大小
 */

import user.User;
import util.Create;

import javax.swing.*;
import java.awt.*;

public class ChooseGamemode extends JDialog implements Create {
//JDialog是用来创建对话框的

    private JButton Btn4x4;
    private JButton Btn5x5;
    private JButton BtnTiming;
    private JButton BtnAi;
    //建议还添加提示信息，用于提示用户选择游戏大小
    //建议还添加一个自定义按钮，用于自定义游戏大小

    public ChooseGamemode(JFrame parent, User user) {
        //JFrame parent是父窗口，User user是用户信息，将这两个参数传入到构造函数中
        super(parent, "Choose the Size of the Game", true);
        //modal为true时，表示对话框是模态的，即用户必须先关闭对话框才能回到父窗口
        this.setLayout(null);//使用linear布局,这句话说的是这个对话框的布局方式是null
        this.setSize(500, 500);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);//设置关闭操作，这个对话框关闭时，只关闭这个对话框
        this.setLocationRelativeTo(parent);// 方法会将子窗口相对于父窗口进行定位，通常是使子窗口显示在父窗口的中央位置。
        getContentPane().setBackground(new Color(0xF6ECDF));//设置窗口背景颜色
        JLabel imageLabel = new JLabel(new ImageIcon("project/project-demo-src/src/util/pictures/choose.png"));
        ImageIcon icon = (ImageIcon) imageLabel.getIcon();
        Image img = icon.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setBounds(0, 0, 500, 250);
        this.add(imageLabel);
        this.Btn4x4 = createButtonWithIcon("4x4", new Point(100, 270), 120, 60, this);
        this.Btn5x5 = createButtonWithIcon("5x5", new Point(280, 270), 120, 60, this);
        this.BtnTiming = createButtonWithIcon("Timing", new Point(100, 350), 120, 60, this);
        this.BtnAi = createButtonWithIcon("AI", new Point(280, 350), 120, 60, this);
        this.Btn4x4.addActionListener(e -> {//给按钮添加监听器,当按钮被点击时，执行以下操作
            GameFrame gameFrame = new GameFrame(4,4,user);
            //需要改GameFrame的constructor以传入4*4的值，user是用户信息，他是这里的父窗口，所以传入到这里，实现了父窗口和子窗口的关联
            //如果是游客模式，user为null，则不需要传入user，直接传入4,4即可，所以这么写不会产生问题
            gameFrame.setVisible(true);
            this.dispose();
            parent.dispose();
        });
        this.Btn5x5.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(5,5,user);
            gameFrame.setVisible(true);
            //让界面显示在正中间
            this.dispose();
            parent.dispose();
        });
        this.BtnTiming.addActionListener(e -> {//待写计时模式的方法
            TimingGameFrame gameFrame = new TimingGameFrame(4,4,user,60);
            gameFrame.setVisible(true);
            this.dispose();
            parent.dispose();
        });
        this.BtnAi.addActionListener(e -> {//待写ai模式的方法
            AIGameFrame gameFrame = new AIGameFrame(4,4,user);
            gameFrame.setVisible(true);
            this.dispose();
            parent.dispose();
        });
        parent.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
