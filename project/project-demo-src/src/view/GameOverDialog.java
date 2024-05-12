package view;

import controller.GameController;

import javax.swing.*;
import java.awt.*;

public class GameOverDialog extends JDialog {

    private int currentScore;
    private int highScore;
    private int step;
    GameController controller;

    public GameOverDialog(JFrame parent, String gameOverMessage, int currentScore, int highScore, int step, GameController controller) {
        super(parent, "Game Over", true); // 模态对话框
        this.currentScore = currentScore;
        this.highScore = highScore;
        this.controller = controller;
        this.step = step;
        this.setSize(300, 450); // 对话框大小
        this.setLayout(new BorderLayout()); // 使用 BorderLayout
        this.setLocationRelativeTo(parent); // 居中显示
        this.controller = controller;
        this.setResizable(false); // 不可调整大小

        // 添加游戏结束的消息
        JLabel messageLabel = new JLabel(gameOverMessage);
        this.add(messageLabel, BorderLayout.NORTH);

        // 添加配图，这里假设您有一个图像文件 "gameover.png"
        JLabel imageLabel = new JLabel(new ImageIcon("project/project-demo-src/src/view/gameIsOver.png"));
        //修改图片的大小
        ImageIcon icon = (ImageIcon) imageLabel.getIcon();
        Image img = icon.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT);
        imageLabel.setIcon(new ImageIcon(img));
        // 设置图片的位置
        this.add(imageLabel, BorderLayout.NORTH);//将图片添加到对话框中,并设置位置

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(3, 1));
        this.add(scorePanel, BorderLayout.CENTER);

        // 添加步数显示
        JLabel stepLabel = new JLabel("Step: " + step);
        // 设置字体颜色
        stepLabel.setFont(new Font("Arial", Font.BOLD, 20));
        //设置位置
        scorePanel.add(stepLabel);

        // 添加得分显示
        JLabel scoreLabel = new JLabel("Current Score: " + currentScore);
        // 设置字体颜色
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        //设置位置
        scorePanel.add(scoreLabel);


        // 添加历史最高分显示
        JLabel highScoreLabel = new JLabel("High Score: " + highScore);
        // 设置字体颜色
        highScoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        //设置位置
        scorePanel.add(highScoreLabel);


        // 添加按钮，允许玩家选择重新开始或回到主页
        JPanel buttonPanel = new JPanel();
        JButton playAgainButton = new JButton("Play Again");
        JButton homeButton = new JButton("Back to Home");
        buttonPanel.add(playAgainButton);
        buttonPanel.add(homeButton);
        this.add(buttonPanel, BorderLayout.SOUTH);


        // 为按钮添加事件监听器
        playAgainButton.addActionListener(e -> {
            // 触发新的游戏或重置当前游戏的逻辑
            this.dispose(); // 关闭对话框
            // 这里应该调用新游戏或重置游戏的方法
            controller.restartGame();

        });
        homeButton.addActionListener(e -> {
            // 触发回到主页的逻辑
            this.dispose(); // 关闭对话框
            // 这里应该调用回到主页的方法
        });

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // 对话框关闭时销毁
    }
}