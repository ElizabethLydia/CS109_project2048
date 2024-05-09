package view;

import controller.GameController;
import util.Create;
import util.RoundedPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameFrame extends JFrame implements Create {//整个游戏的窗口

    private GameController controller;
    private JButton restartBtn;
    private JButton loadBtn;
    private JButton undoBtn;

    private GamePanel gamePanel;

    private JLabel titleLabel;//标题2048

    private JLabel stepLabel;
    private JLabel scoreLabel;
    private JLabel highestScoreLabel;


    private JPanel scorePanel;
    private JPanel stepPanel;
    private JPanel highestScorePanel;

    private JPanel buttonPanel;
    private JButton leftBtn;
    private JButton rightBtn;
    private JButton upBtn;
    private JButton downBtn;

    private BufferedImage backgroundImage;


    public GameFrame(int width, int height) {
        this.setTitle("2024 CS109 Project Demo");//窗口名称
        this.setLayout(null);
        /*在 JFrame 窗口中设置绝对定位布局（Absolute Positioning Layout）的方法。
        通过调用这个方法，并将布局管理器设置为 null，你可以自由地使用绝对坐标来定位和放置窗口中的组件，而不受默认布局管理器的影响。*/
        this.setSize(800, 600);
        this.setResizable(false);
        this.titleLabel = createLabel("2048", new Font("Verdana", Font.BOLD, 80), new Point(35, 20), 240, 80,this,0x463627);

        gamePanel = new GamePanel((int) (500 * 0.8), 4, 4);//这行代码设置了游戏面板的大小，以及游戏4*4、5*5的大小之后要改
        gamePanel.setLocation(500/ 15, 500 / 15+100);
        this.add(gamePanel);

        highestScorePanel = createPanel(428, 30, 100, 70,false);
        scorePanel = createPanel(550, 30, 100, 70,false);
        stepPanel = createPanel(670, 30, 100, 70,false);

        this.controller = new GameController(gamePanel, gamePanel.getModel(), this);
        this.restartBtn = createButton("Restart", new Point(500, 150), 110, 50, this);
        this.loadBtn = createButton("Load", new Point(500, 220), 110, 50, this);
        this.stepLabel = createLabel("<html>Step:<br> 0 </html>", new Font("Arial", Font.BOLD, 22), new Point(10, 10), 180, 50,this,0xF1EDEA);
        this.scoreLabel = createLabel("<html>Score:<br>0  </html>", new Font("Arial", Font.BOLD, 22), new Point(10, 10), 180, 50,this,0xF1EDEA);
        //<html>Step:<br> 0 </html>这个是为了让step和score在同一行显示
        this.undoBtn = createButton("Undo", new Point(500, 290), 110, 50,this);//这个可以创建一个和load，restart一样形式的按钮
        /*undoBtn = createButton("", new Point(500, 290), 110, 50, this); // 使用 createButton 方法创建按钮
        ImageIcon undoIcon = new ImageIcon("D:\\code\\javasepro\\project\\project\\project-demo-src\\src\\view\\undo.jpg"); // 替换为你的图像路径
        undoBtn.setIcon(undoIcon);// 设置按钮的图标
        //获取图标的宽度和高度
        int width1 = undoIcon.getIconWidth();
        int height1 = undoIcon.getIconHeight();
        //设置按钮的大小
        undoBtn.setSize(width1, height1);
        // 添加撤销按钮到游戏窗口
        this.add(undoBtn);*/
        buttonPanel = createPanel(450, 330, 320, 210,true);

        this.leftBtn = createButton("Left", new Point(15, 110), 90, 90, this);
        this.rightBtn = createButton("Right", new Point(215, 110), 90, 90, this);
        this.upBtn = createButton("Up", new Point(115, 10), 90, 90, this);
        this.downBtn = createButton("Down", new Point(115, 110), 90, 90, this);
        buttonPanel.setComponentZOrder(leftBtn, 0);//设置按钮的层次,0表示最底层，这个是为了让按钮显示在panel上
        buttonPanel.setComponentZOrder(rightBtn, 0);
        buttonPanel.setComponentZOrder(upBtn, 0);
        buttonPanel.setComponentZOrder(downBtn, 0);
        //要把button显示在panel上

        gamePanel.setStepLabel(stepLabel);//建立gamePanel中所得到的step值与stepLabel的联系
        gamePanel.setScoreLabel(scoreLabel);//建立gamePanel中所得到的score值与scoreLabel的联系
        stepPanel.setComponentZOrder(stepLabel, 0);//设置层次，0表示最底层
        scorePanel.setComponentZOrder(scoreLabel, 0);

        this.restartBtn.addActionListener(e -> {//给按钮添加监听器,当按钮被点击时，执行以下restartGame()方法
            controller.restartGame();
            gamePanel.requestFocusInWindow();//当重启按钮被点击时，游戏将重新开始并且游戏面板会请求焦点，以便启用键盘事件监听，为玩家提供交互操作的功能。
        });

        this.loadBtn.addActionListener(e -> {
            String string = JOptionPane.showInputDialog(this, "Input path:");
            System.out.println(string);
            gamePanel.requestFocusInWindow();//启用键盘事件监听？？？？
        });

        this.undoBtn.addActionListener(e -> {
            gamePanel.doUndo();
            gamePanel.requestFocusInWindow();
        });

        this.leftBtn.addActionListener(e -> {
            gamePanel.doMoveLeft();
            gamePanel.requestFocusInWindow();
                });

        this.rightBtn.addActionListener(e -> {
            gamePanel.doMoveRight();
            gamePanel.requestFocusInWindow();
        });

        this.upBtn.addActionListener(e -> {
            gamePanel.doMoveUp();
            gamePanel.requestFocusInWindow();
        });

        this.downBtn.addActionListener(e -> {
            gamePanel.doMoveDown();
            gamePanel.requestFocusInWindow();
        });

        /*try {
            backgroundImage = ImageIO.read(new File("D:\\hanson\\壁纸\\1.jpeg"));  // 读取图片
        } catch (IOException e) {
            e.printStackTrace();
        }*/


//        //todo: add other button here
        this.setLocationRelativeTo(null);//调用这个方法后，窗口将在屏幕的中心位置显示，而不是默认的窗口初始位置（通常是在左上角）。
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//当用户点击窗口关闭按钮时，应用程序将会执行退出操作，关闭所有相关的资源，然后退出应用程序
    }

    public GameController getController() {
        return controller;
    }
    public JPanel createPanel(int x,int y, int width, int height,boolean isOpaque) {
        RoundedPanel panel = new RoundedPanel(new Color(0xbbada0),x, y, width, height,this,isOpaque);
        panel.setLayout(null);
        this.add(panel);
        return panel;
    }
    /*public void paint(Graphics g) {
        super.paint(g);
        if(backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);  // 绘制图片
        }
    }*/


}
