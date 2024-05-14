package view;

import util.Create;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Menu extends JFrame implements Create {
    private JButton startBtn;
    private JButton loadGameBtn;
    private JButton settingsBtn;
    private JButton exitBtn;

    public Menu() {
        this.setTitle("2024 CS109 Project Demo");
        this.setLayout(null);
        this.setSize(500, 700);
        this.setResizable(false);
        getContentPane().setBackground(new Color(0xF6ECDF));//设置窗口背景颜色
        JLabel imageLabel = new JLabel(new ImageIcon("project/project-demo-src/src/util/pictures/2048title.png"));
        //修改图片的大小
        ImageIcon icon = (ImageIcon) imageLabel.getIcon();
        Image img = icon.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setBounds(0, 0, 500, 300);
        this.add(imageLabel);
        this.startBtn = createButtonWithIcon("StartGame", new Point(120, 300), 250, 70, this);
        this.loadGameBtn = createButtonWithIcon("LoadGame", new Point(120, 400), 250, 70, this);
        this.settingsBtn = createButtonWithIcon("Settings", new Point(120, 500), 80, 80, this);
        this.exitBtn = createButtonWithIcon("Exit", new Point(290, 500), 80, 80, this);
        //还要setVisible(true)和setVisible(false)
        this.startBtn.addActionListener(e -> {
            ChooseGamemode chooseSize = new ChooseGamemode(this);
            chooseSize.setVisible(true);
        });
        this.loadGameBtn.addActionListener(e -> {
            try {
                //创建一个文件读取器,读取文件savegame.txt,如果文件不存在会抛出异常
                BufferedReader reader = new BufferedReader(new FileReader("savegame.txt"));
                //读取文件的每一行
                String line;
                System.out.println("you have loaded the game");

                // 首先读取棋盘的大小
                line = reader.readLine();
                String[] sizeParts = line.split(" ");
                int xCount = Integer.parseInt(sizeParts[0]);
                int yCount = Integer.parseInt(sizeParts[1]);
                int[][] numbers = new int[xCount][yCount];

                // 接下来读取棋盘上的数字
                for (int i = 0; i < xCount; i++) {
                    line = reader.readLine();
                    String[] numberStrings = line.split(",");
                    for (int j = 0; j < yCount; j++) {
                        numbers[i][j] = Integer.parseInt(numberStrings[j]);
                    }
                }
                GameFrame newGameFrame = new GameFrame(xCount, yCount); // 创建新的游戏窗口
                newGameFrame.setVisible(true); // 显示新的游戏窗口
                newGameFrame.getGamePanel().getModel().setNumbers(numbers); // 设置棋盘状态

                // 读取得分
                line = reader.readLine();
                String[] scoreParts = line.split(": ");
                newGameFrame.getGamePanel().getModel().setScore(Integer.parseInt(scoreParts[1]));

                // 读取步数
                line = reader.readLine();
                String[] stepParts = line.split(": ");
                newGameFrame.getGamePanel().getModel().setStep(Integer.parseInt(stepParts[1]));

                // 更新分数和步数显示
                newGameFrame.getGamePanel().setScoreLabel(newGameFrame.getGamePanel().getModel().getScore());
                newGameFrame.getGamePanel().setStepLabel(newGameFrame.getGamePanel().getModel().getStep());
                // 更新游戏面板以显示加载的状态
                newGameFrame.getGamePanel().updateGridsNumber();
                reader.close();
            } catch (IOException | NumberFormatException event) {
                event.printStackTrace();
            }
        });
        this.settingsBtn.addActionListener(e -> {
            Settings settings = new Settings();
            settings.setVisible(true);
        });
        this.exitBtn.addActionListener(e -> {
            System.exit(0);
        });
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
