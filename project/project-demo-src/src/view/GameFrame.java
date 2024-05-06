package view;

import controller.GameController;
import util.ColorMap;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {//整个游戏的窗口

    private GameController controller;
    private JButton restartBtn;
    private JButton loadBtn;

    private JLabel stepLabel;
    private GamePanel gamePanel;

    public GameFrame(int width, int height) {
        this.setTitle("2024 CS109 Project Demo");//窗口名称
        this.setLayout(null);
        /*在 JFrame 窗口中设置绝对定位布局（Absolute Positioning Layout）的方法。
        通过调用这个方法，并将布局管理器设置为 null，你可以自由地使用绝对坐标来定位和放置窗口中的组件，而不受默认布局管理器的影响。*/
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        gamePanel = new GamePanel((int) (this.getHeight() * 0.8), 4, 4);//这行代码设置了游戏面板的大小，以及游戏4*4、5*5的大小之后要改
        gamePanel.setLocation(this.getHeight() / 15, this.getWidth() / 15);
        this.add(gamePanel);

        this.controller = new GameController(gamePanel, gamePanel.getModel(),this);
        this.restartBtn = createButton("Restart", new Point(500, 150), 110, 50);
        this.loadBtn = createButton("Load", new Point(500, 220), 110, 50);
        this.stepLabel = createLabel("Start", new Font("serif", Font.ITALIC, 22), new Point(480, 50), 180, 50);
        gamePanel.setStepLabel(stepLabel);//建立gamePanel中所得到的step值与stepLabel的联系

        this.restartBtn.addActionListener(e -> {
            controller.restartGame();
            gamePanel.requestFocusInWindow();//当重启按钮被点击时，游戏将重新开始并且游戏面板会请求焦点，以便启用键盘事件监听，为玩家提供交互操作的功能。
        });
        this.loadBtn.addActionListener(e -> {
            String string = JOptionPane.showInputDialog(this, "Input path:");
            System.out.println(string);
            gamePanel.requestFocusInWindow();//启用键盘事件监听？？？？
        });
        //todo: add other button here
        this.setLocationRelativeTo(null);//调用这个方法后，窗口将在屏幕的中心位置显示，而不是默认的窗口初始位置（通常是在左上角）。
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//当用户点击窗口关闭按钮时，应用程序将会执行退出操作，关闭所有相关的资源，然后退出应用程序
    }


    private JButton createButton(String name, Point location, int width, int height) {
        JButton button = new JButton(name);
        button.setLocation(location);
        button.setSize(width, height);
        this.add(button);
        return button;
    }

    private JLabel createLabel(String name, Font font, Point location, int width, int height) {
        JLabel label = new JLabel(name);
        label.setFont(font);
        label.setLocation(location);
        label.setSize(width, height);
        this.add(label);
        return label;
    }

}
