package view;
/*
ChooseSize类是一个对话框，用于选择游戏的大小
 */

import util.Create;

import javax.swing.*;
import java.awt.*;

public class ChooseGamemode extends JDialog implements Create {
//JDialog是用来创建对话框的

    private JButton Btn4x4;
    private JButton Btn5x5;
    private JButton BtnLimited;
    //建议还添加提示信息，用于提示用户选择游戏大小
    //建议还添加一个自定义按钮，用于自定义游戏大小
    private JLabel titleLabel;

    public ChooseGamemode(JFrame parent) {//JFrame parent是父窗口
        super(parent, "Choose the Size of the Game", true);
        //modal为true时，表示对话框是模态的，即用户必须先关闭对话框才能回到父窗口
        this.setLayout(null);//使用linear布局,这句话说的是这个对话框的布局方式是null
        this.setSize(500, 400);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);//设置关闭操作，这个对话框关闭时，只关闭这个对话框
        this.setLocationRelativeTo(parent);// 方法会将子窗口相对于父窗口进行定位，通常是使子窗口显示在父窗口的中央位置。
        //待调整位置
        this.titleLabel = createLabel("Choose the Size of the Game", new Font("serif", Font.ITALIC, 50), new Point(480, 50), 180, 50, this,0xF3E0D1);

        //创建一个标签，标签名称，字体，位置，宽度，高度，父窗口
        this.Btn4x4 = createButton("4x4", new Point(100, 150), 100, 50, this);
        this.Btn5x5 = createButton("5x5", new Point(100, 250), 100, 50, this);
        this.BtnLimited = createButton("Limited", new Point(100, 300), 100, 50, this);
        this.Btn4x4.addActionListener(e -> {//给按钮添加监听器,当按钮被点击时，执行以下操作
            GameFrame gameFrame = new GameFrame(4,4);//需要改GameFrame的constructor以传入4*4的值
            gameFrame.setVisible(true);
            this.dispose();
            parent.dispose();
        });
        this.Btn5x5.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(5,5);
            gameFrame.setVisible(true);
            this.dispose();
            parent.dispose();
        });
        this.BtnLimited.addActionListener(e -> {//待写计时模式的方法
            GameFrame gameFrame = new GameFrame(4,4);
            gameFrame.setVisible(true);
            this.dispose();
            parent.dispose();
        });
        parent.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
