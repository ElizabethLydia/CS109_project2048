package view;

import controller.GameController;
import user.User;
import util.Create;
import util.RoundedPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame implements Create {//整个游戏的窗口

    private GameController controller;
    public User user;
    private JButton restartBtn;
    private JButton magicBtn;
    private JButton undoBtn;

//    private JButton loadBtn;
//    private JButton deadBtn;

    protected GamePanel gamePanel;

    private JLabel titleLabel;//标题2048

    private JLabel stepLabel;
    private JLabel scoreLabel;
    protected JLabel HighestScoreLabel;
    protected GameMenu gameMenu;
    private JPanel scorePanel;
    private JPanel stepPanel;
    private JPanel HighestScorePanel;

    private JPanel buttonPanel;
    private JButton leftBtn;
    private JButton rightBtn;
    private JButton upBtn;
    private JButton downBtn;

    public GameFrame(int xcount, int ycount, User user) {
        this.setTitle("2024 CS109 Project Demo");//窗口名称
        this.setLayout(null);
        /*在 JFrame 窗口中设置绝对定位布局（Absolute Positioning Layout）的方法。
        通过调用这个方法，并将布局管理器设置为 null，你可以自由地使用绝对坐标来定位和放置窗口中的组件，而不受默认布局管理器的影响。*/
        this.setSize(800, 650);
        this.setResizable(false);//设置窗口大小不可变
        createGameMenu();//创建游戏菜单,这个是在窗口上方的菜单栏,怎么让他显示出来？？？？
        //可以通过setJMenuBar()方法将菜单栏添加到窗口中
        this.user = user;

        this.titleLabel = createLabel("2048", new Font("Verdana", Font.BOLD, 80), new Point(35, 20), 240, 80, this, 0x463627);
        getContentPane().setBackground(new Color(0xF6ECDF));//设置窗口背景颜色

        gamePanel = new GamePanel((int) (500 * 0.8), xcount, ycount);//这行代码设置了游戏面板的大小，以及游戏4*4、5*5的大小之后要改
        gamePanel.setLocation(500 / 15, 500 / 15 + 100);
        this.add(gamePanel);

        this.controller = new GameController(gamePanel, gamePanel.getModel(), this);
        gamePanel.setController(controller);//建立gamePanel中所得到的controller值与controller的联系,这个是为了让gamePanel中的controller和GameFrame中的controller保持一致
        //Controller是用来控制游戏的，所以gamePanel中的controller和GameFrame中的controller应该保持一致

        if (user != null) {
            HighestScorePanel = createPanel(418, 30, 110, 70, false);
            this.HighestScoreLabel = createLabel("<html>Highest:<br>0  </html>", new Font("Arial", Font.BOLD, 22), new Point(10, 10), 180, 50, this, 0xF1EDEA);
            //<html>Step:<br> 0 </html>这个是为了让step和score在同一行显示
            HighestScorePanel.setComponentZOrder(HighestScoreLabel, 0);
        }

        scorePanel = createPanel(550, 30, 100, 70, false);
        stepPanel = createPanel(670, 30, 100, 70, false);

        this.controller = new GameController(gamePanel, gamePanel.getModel(), this);
        gamePanel.setController(controller);

        this.stepLabel = createLabel("<html>Step:<br> 0 </html>", new Font("Arial", Font.BOLD, 22), new Point(10, 10), 180, 50, this, 0xF1EDEA);
        this.scoreLabel = createLabel("<html>Score:<br>0  </html>", new Font("Arial", Font.BOLD, 22), new Point(10, 10), 180, 50, this, 0xF1EDEA);

        this.restartBtn = createButtonWithIcon("Restart", new Point(560, 130), 50, 50, this);
        this.undoBtn = createButtonWithIcon("Undo", new Point(630, 130), 50, 50, this);
        this.magicBtn = createButtonWithIcon("Magic", new Point(700, 130), 50, 50, this);

//        this.loadBtn = createButton("Load", new Point(500, 220), 110, 50, this);
//        this.deadBtn = createButton("Dead", new Point(500, 290), 110, 50, this);


        buttonPanel = createPanel(450, 330, 320, 210, true);
        this.leftBtn = createButtonWithIcon("Left", new Point(15, 110), 90, 90, this);
        this.rightBtn = createButtonWithIcon("Right", new Point(215, 110), 90, 90, this);
        this.upBtn = createButtonWithIcon("Up", new Point(115, 10), 90, 90, this);
        this.downBtn = createButtonWithIcon("Down", new Point(115, 110), 90, 90, this);
        buttonPanel.setComponentZOrder(leftBtn, 0);//设置按钮的层次,0表示最底层，这个是为了让按钮显示在panel上
        buttonPanel.setComponentZOrder(rightBtn, 0);
        buttonPanel.setComponentZOrder(upBtn, 0);
        buttonPanel.setComponentZOrder(downBtn, 0);
        //要把button显示在panel上

        gamePanel.setStepLabel(stepLabel);//建立gamePanel中所得到的step值与stepLabel的联系
        gamePanel.setScoreLabel(scoreLabel);//建立gamePanel中所得到的score值与scoreLabel的联系
        stepPanel.setComponentZOrder(stepLabel, 0);//设置层次，0表示最底层
        scorePanel.setComponentZOrder(scoreLabel, 0);

        this.magicBtn.addActionListener(e -> {
            gamePanel.doMagic();
            gamePanel.requestFocusInWindow();
        });

        this.restartBtn.addActionListener(e ->

        {//给按钮添加监听器,当按钮被点击时，执行以下restartGame()方法
            controller.restartGame();
            gamePanel.requestFocusInWindow();//当重启按钮被点击时，游戏将重新开始并且游戏面板会请求焦点，以便启用键盘事件监听，为玩家提供交互操作的功能。
        });

//        this.loadBtn.addActionListener(e -> {
//            String string = JOptionPane.showInputDialog(this, "Input path:");
//            System.out.println(string);
//            gamePanel.requestFocusInWindow();//启用键盘事件监听？？？？
//        });

        this.undoBtn.addActionListener(e -> {
            gamePanel.doUndo();
            gamePanel.requestFocusInWindow();
        });
//        this.deadBtn.addActionListener(e -> {
//            gamePanel.dead();
//            gamePanel.requestFocusInWindow();
//        });

        this.leftBtn.addActionListener(e ->

        {
            gamePanel.doMoveLeft();
            gamePanel.requestFocusInWindow();
        });

        this.rightBtn.addActionListener(e ->

        {
            gamePanel.doMoveRight();
            gamePanel.requestFocusInWindow();
        });

        this.upBtn.addActionListener(e ->

        {
            gamePanel.doMoveUp();
            gamePanel.requestFocusInWindow();
        });

        this.downBtn.addActionListener(e ->

        {
            gamePanel.doMoveDown();
            gamePanel.requestFocusInWindow();
        });

//        //todo: add other button here
        this.

                setLocationRelativeTo(null);//调用这个方法后，窗口将在屏幕的中心位置显示，而不是默认的窗口初始位置（通常是在左上角）

        this.

                addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        // 弹出一个带有“确定”和“取消”按钮的确认窗口
                        controller.exit();
                    }
                });
    }

    private void createGameMenu() {
        gameMenu = new GameMenu(this, user);
        this.setJMenuBar(gameMenu);//将菜单栏添加到窗口中
    }


    public GameController getController() {
        return controller;
    }

    public JPanel createPanel(int x, int y, int width, int height, boolean isOpaque) {
        RoundedPanel panel = new RoundedPanel(new Color(0xbbada0), x, y, width, height, this, isOpaque);
        panel.setLayout(null);
        this.add(panel);
        return panel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
