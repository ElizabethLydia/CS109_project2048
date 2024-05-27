package view;

import user.User;
import util.Create;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Menu2 extends JFrame implements Create {//选择游戏第二个主界面
    private JButton startBtn;
    private JButton loadGameBtn;
    private JButton settingsBtn;
    private JButton returnBtn;

    public Menu2(User user,Menu1 mainMenu) {
        this.setTitle("2024 CS109 Project Demo");
        this.setLayout(null);
        this.setSize(500, 700);
        this.setResizable(false);
        getContentPane().setBackground(new Color(0xF6ECDF));//设置窗口背景颜色
        JLabel imageLabel = new JLabel(new ImageIcon("project/project-demo-src/src/util/pictures/title/2048title.png"));
        //修改图片的大小
        ImageIcon icon = (ImageIcon) imageLabel.getIcon();
        Image img = icon.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setBounds(0, 0, 500, 300);
        this.add(imageLabel);
        this.startBtn = createButtonWithIcon("StartGame", new Point(120, 300), 250, 70, this);
        this.loadGameBtn = createButtonWithIcon("LoadGame", new Point(120, 400), 250, 70, this);
        this.settingsBtn = createButtonWithIcon("Settings", new Point(120, 500), 80, 80, this);
        this.returnBtn = createButtonWithIcon("Return", new Point(290, 500), 80, 80, this);
        //还要setVisible(true)和setVisible(false)
        this.startBtn.addActionListener(e -> {
            ChooseGamemode chooseSize = new ChooseGamemode(this,user,mainMenu);
            chooseSize.setVisible(true);
        });
        this.loadGameBtn.addActionListener(e -> {
            if (user.time==0) {
                GameFrame newGameFrame = new GameFrame(user.xCount, user.yCount,user,mainMenu); // 创建新的游戏窗口
                newGameFrame.setVisible(true); // 显示新的游戏窗口
                newGameFrame.getGamePanel().getModel().setNumbers(user.numbers); // 设置棋盘状态
                // 设置分数
                newGameFrame.getGamePanel().getModel().setScore(user.score);
                // 设置步数
                newGameFrame.getGamePanel().getModel().setStep(user.step);
                // 更新分数和步数显示
                newGameFrame.getGamePanel().setScoreLabel(newGameFrame.getGamePanel().getModel().getScore());
                newGameFrame.getGamePanel().setStepLabel(newGameFrame.getGamePanel().getModel().getStep());
                // 更新游戏面板以显示加载的状态
                newGameFrame.getGamePanel().updateGridsNumber();
            } else {
                TimingGameFrame newGameFrame = new TimingGameFrame(user.xCount, user.yCount, user,user.time,mainMenu); // 创建新的计时游戏窗口
                newGameFrame.setVisible(true); // 显示新的游戏窗口
                newGameFrame.getGamePanel().getModel().setNumbers(user.numbers); // 设置棋盘状态
                // 设置分数
                newGameFrame.getGamePanel().getModel().setScore(user.score);
                // 设置步数
                newGameFrame.getGamePanel().getModel().setStep(user.step);
                // 更新分数和步数显示
                newGameFrame.getGamePanel().setScoreLabel(newGameFrame.getGamePanel().getModel().getScore());
                newGameFrame.getGamePanel().setStepLabel(newGameFrame.getGamePanel().getModel().getStep());
                // 更新游戏面板以显示加载的状态
                newGameFrame.getGamePanel().updateGridsNumber();
            }
                this.dispose(); // 关闭当前窗口
        });
        this.settingsBtn.addActionListener(e -> {
            mainMenu.settings.setUser(user);
            mainMenu.settings.setFrame(this);
            mainMenu.settings.setVisible(true);
        });
        this.returnBtn.addActionListener(e -> {
            mainMenu.setVisible(true);
            this.dispose();
        });
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
