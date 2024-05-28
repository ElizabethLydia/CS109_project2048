package view;

import controller.GameController;
import util.Create;

import javax.swing.*;
import java.awt.*;

public class GameOverDialog extends JDialog implements Create {

    private int Score;
    private int highestScore;
    private int Step;
    GameController controller;

    public GameOverDialog(GameFrame parent, String gameOverMessage, int currentScore, int highScore, int step, GameController controller) {
        super(parent, "Game Over", true); // 模态对话框
        this.Score = currentScore;
        this.highestScore = highScore;
        this.controller = controller;
        this.Step = step;
        this.setSize(500, 700); // 对话框大小
        this.setLayout(null);
        this.setLocationRelativeTo(parent); // 居中显示
        this.controller = controller;
        this.setResizable(false); // 不可调整大小
        getContentPane().setBackground(new Color(0xF6ECDF));//设置窗口背景颜色


        JLabel imageLabel = new JLabel(new ImageIcon("project/project-demo-src/src/util/pictures/title/GameOver.png"));
        //修改图片的大小
        ImageIcon icon = (ImageIcon) imageLabel.getIcon();
        Image img = icon.getImage().getScaledInstance(500, 280, Image.SCALE_DEFAULT);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setBounds(0, 0, 500, 260);
        this.add(imageLabel);

        JLabel endLabel = new JLabel(new ImageIcon("project/project-demo-src/src/util/pictures/title/EndLabel.png"));
        //修改图片的大小
        ImageIcon icon2 = (ImageIcon) endLabel.getIcon();
        Image img2 = icon2.getImage().getScaledInstance(420, 250, Image.SCALE_DEFAULT);
        endLabel.setIcon(new ImageIcon(img2));
        endLabel.setBounds(0, 260, 500, 250);
        this.add(endLabel);


        JLabel stepLabel = createLabel("Step:         "+Step, new Font("Arial", Font.BOLD, 40), new Point(100, 15), 500, 70,this,0x545454);
        endLabel.setComponentZOrder(stepLabel, 0);

        JLabel scoreLabel = createLabel("Score:       "+Score, new Font("Arial", Font.BOLD, 40), new Point(100, 90), 500, 70,this,0x545454);
        endLabel.setComponentZOrder(scoreLabel, 0);

        JLabel highScoreLabel = createLabel("Highest:    "+highestScore, new Font("Arial", Font.BOLD, 40), new Point(100, 165), 500, 70,this,0x545454);
        endLabel.setComponentZOrder(highScoreLabel, 0);


        // 添加按钮，允许玩家选择重新开始或回到主页
        JButton playAgainButton = createButtonWithIcon("playAgain", new Point(120, 510), 80, 80, this);
        JButton homeButton = createButtonWithIcon("home", new Point(290, 510), 80, 80, this);
        // 为按钮添加事件监听器
        playAgainButton.addActionListener(e -> {
            // 触发新的游戏或重置当前游戏的逻辑
            // 这里应该调用新游戏或重置游戏的方法
            // 弹出一个带有“确定”和“取消”按钮的确认窗口
            int result = JOptionPane.showConfirmDialog(
                    this,//这句话的意思是将窗口放在frame上
                    "Do you want to restart the game?",
                    "Restart Game",
                    JOptionPane.YES_NO_OPTION,//弹出一个带有“确定”和“取消”按钮的确认窗口
                    JOptionPane.QUESTION_MESSAGE//如果用户点击了“确定”按钮，将会返回 JOptionPane.YES_OPTION，如果用户点击了“取消”按钮，将会返回 JOptionPane.NO_OPTION
            );

            // 根据用户的选择执行操作
            if (result == JOptionPane.YES_OPTION) {
                // 用户选择“确定”重启游戏
                if (controller.frame instanceof TimingGameFrame) {
                    TimingGameFrame timingGameFrame = (TimingGameFrame) controller.frame;
                    timingGameFrame.dispose();
                    TimingGameFrame newGameFrame = new TimingGameFrame(controller.view.getXCOUNT(), controller.view.getYCOUNT(),controller.frame.user,60,parent.menu1); // 创建新的游戏窗口
                    newGameFrame.setVisible(true); // 显示新的游戏窗口
                    this.dispose(); // 关闭当前窗口
                    // 停止定时器
                }else {
                    controller.frame.dispose(); // 关闭当前窗口
                    GameFrame newGameFrame = new GameFrame(controller.view.getXCOUNT(), controller.view.getYCOUNT(),controller.frame.user,parent.menu1); // 创建新的游戏窗口
                    newGameFrame.setVisible(true); // 显示新的游戏窗口
                    this.dispose(); // 关闭当前窗口
                }
            } else if (result == JOptionPane.NO_OPTION) {
                // 用户选择“取消”，则不执行任何操作
                // 什么都不做，或者可以在这里添加一些取消操作的逻辑（如果有的话）
            }
        });
        homeButton.addActionListener(e -> {
            // 触发回到主页的逻辑
            //弹出一个带有“确定”和“取消”按钮的确认窗口
            int result = JOptionPane.showConfirmDialog(
                    parent,//这句话的意思是将窗口放在frame上
                    "Do you want to go back to the home page?",
                    "Back to Home",
                    JOptionPane.YES_NO_OPTION,//弹出一个带有“确定”和“取消”按钮的确认窗口
                    JOptionPane.QUESTION_MESSAGE//如果用户点击了“确定”按钮，将会返回 JOptionPane.YES_OPTION，如果用户点击了“取消”按钮，将会返回 JOptionPane.NO_OPTION
            );
            if (result == JOptionPane.YES_OPTION) {
                GameOverDialog.this.dispose(); // 关闭当前窗口
                controller.gotoHome();
                // 这里应该调用回到主页的方法
            }else{
                this.dispose();
            }
        });

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // 对话框关闭时销毁
    }
}