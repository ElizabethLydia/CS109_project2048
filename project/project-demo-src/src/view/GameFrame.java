package view;

import controller.GameController;
import util.CreateButtonAndLabel;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame implements CreateButtonAndLabel {//整个游戏的窗口

    private GameController controller;
    private JButton restartBtn;
    private JButton loadBtn;
    private JButton undoBtn;

    private JLabel stepLabel;
    private JLabel scoreLabel;
    private GamePanel gamePanel;


    public GameFrame(int width, int height) {
        this.setTitle("2024 CS109 Project Demo");//窗口名称
        this.setLayout(null);
        /*在 JFrame 窗口中设置绝对定位布局（Absolute Positioning Layout）的方法。
        通过调用这个方法，并将布局管理器设置为 null，你可以自由地使用绝对坐标来定位和放置窗口中的组件，而不受默认布局管理器的影响。*/
        this.setSize(width, height);
        gamePanel = new GamePanel((int) (this.getHeight() * 0.8), 4, 4);//这行代码设置了游戏面板的大小，以及游戏4*4、5*5的大小之后要改
        gamePanel.setLocation(this.getHeight() / 15, this.getWidth() / 15);
        this.add(gamePanel);

        this.controller = new GameController(gamePanel, gamePanel.getModel(), this);
        this.restartBtn = createButton("Restart", new Point(500, 150), 110, 50, this);
        this.loadBtn = createButton("Load", new Point(500, 220), 110, 50, this);
        this.stepLabel = createLabel("Start", new Font("serif", Font.ITALIC, 22), new Point(480, 50), 180, 50, this);
        this.scoreLabel = createLabel("Score", new Font("serif", Font.ITALIC, 22), new Point(480, 90), 180, 50, this);
////        this.undoBtn = createButton("Undo", new Point(500, 290), 110, 50,this);//这个可以创建一个和load，restart一样形式的按钮
        undoBtn = createButton("", new Point(500, 290), 110, 50, this); // 使用 createButton 方法创建按钮
        ImageIcon undoIcon = new ImageIcon("D:\\code\\javasepro\\project\\project\\project-demo-src\\src\\view\\undo.jpg"); // 替换为你的图像路径
        undoBtn.setIcon(undoIcon);// 设置按钮的图标
        //获取图标的宽度和高度
        int width1 = undoIcon.getIconWidth();
        int height1 = undoIcon.getIconHeight();
        //设置按钮的大小
        undoBtn.setSize(width1, height1);
        // 添加撤销按钮到游戏窗口
        this.add(undoBtn);
        gamePanel.setStepLabel(stepLabel);//建立gamePanel中所得到的step值与stepLabel的联系
        gamePanel.setScoreLabel(scoreLabel);//建立gamePanel中所得到的score值与scoreLabel的联系

        this.restartBtn.addActionListener(e -> {//给按钮添加监听器,当按钮被点击时，执行以下restartGame()方法
            controller.restartGame();
            gamePanel.requestFocusInWindow();//当重启按钮被点击时，游戏将重新开始并且游戏面板会请求焦点，以便启用键盘事件监听，为玩家提供交互操作的功能。
        });
        this.loadBtn.addActionListener(e -> {
            String string = JOptionPane.showInputDialog(this, "Input path:");
            System.out.println(string);
            gamePanel.requestFocusInWindow();//启用键盘事件监听？？？？
        });
        undoBtn.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(GameFrame.this, "Do you want to undo the last move?", "Undo Move", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                // 撤销游戏状态
                if (!gamePanel.getEachArray().isEmpty()) {
                    gamePanel.getEachArray().remove(gamePanel.getEachArray().size() - 1);
                    gamePanel.getEachScore().remove(gamePanel.getEachScore().size() - 1);
                }

                // 更新分数和步数标签
//                gamePanel.setScore(gamePanel.getEachScore().get(gamePanel.getEachScore().size() - 1));
//                gamePanel.setStep(gamePanel.getStep() - 1);
                gamePanel.setScoreLabel(scoreLabel);
                gamePanel.setStepLabel(stepLabel);

                // 重新绘制游戏面板
                gamePanel.repaint();
                //目前问题是游戏无法继续进行，因为撤销后的游戏面板无法再次获得焦点，无法再次进行键盘操作
                //解决方法：在撤销后，重新获取焦点
                gamePanel.requestFocusInWindow();
            } else {
                // 用户选择不撤销，不执行任何操作
            }
        });
//        //todo: add other button here
        this.setLocationRelativeTo(null);//调用这个方法后，窗口将在屏幕的中心位置显示，而不是默认的窗口初始位置（通常是在左上角）。
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//当用户点击窗口关闭按钮时，应用程序将会执行退出操作，关闭所有相关的资源，然后退出应用程序
    }

    public GameController getController() {
        return controller;
    }


}
