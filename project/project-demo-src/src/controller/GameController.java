package controller;

import model.GridNumber;
import view.*;

import javax.swing.*;
import java.io.*;
//导入音乐播放器
//import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.Player;


public class GameController {
    public GamePanel view;
    private GridNumber model;
    public GameFrame frame;
//    private Player player; // 用于播放音乐的 Player 对象
//    private boolean isMusicPlaying = true; // 音乐播放状态

    public GameController(GamePanel view, GridNumber model, GameFrame frame) {
        this.view = view;
        this.model = model;
        this.frame = frame;

        Timer timer = new Timer(60000, e -> saveGame()); // 每60秒保存一次
        timer.start(); // 启动定时器
    }

    public void restartGame() {
        // 弹出一个带有“确定”和“取消”按钮的确认窗口
        int result = JOptionPane.showConfirmDialog(
                frame,//这句话的意思是将窗口放在frame上
                "Do you want to restart the game?",
                "Restart Game",
                JOptionPane.YES_NO_OPTION,//弹出一个带有“确定”和“取消”按钮的确认窗口
                JOptionPane.QUESTION_MESSAGE//如果用户点击了“确定”按钮，将会返回 JOptionPane.YES_OPTION，如果用户点击了“取消”按钮，将会返回 JOptionPane.NO_OPTION
        );

        // 根据用户的选择执行操作
        if (result == JOptionPane.YES_OPTION) {
            // 用户选择“确定”重启游戏
            if (frame instanceof TimingGameFrame) {
                TimingGameFrame timingGameFrame = (TimingGameFrame) frame;
                timingGameFrame.timer.stop();
                timingGameFrame.dispose(); // 关闭当前窗口
                TimingGameFrame newGameFrame = new TimingGameFrame(view.getXCOUNT(), view.getYCOUNT(),60); // 创建新的游戏窗口
                newGameFrame.setVisible(true); // 显示新的游戏窗口
                // 停止定时器
            }else {
                this.frame.dispose(); // 关闭当前窗口
                GameFrame newGameFrame = new GameFrame(view.getXCOUNT(), view.getYCOUNT()); // 创建新的游戏窗口
                newGameFrame.setVisible(true); // 显示新的游戏窗口
            }
        } else if (result == JOptionPane.NO_OPTION) {
            // 用户选择“取消”，则不执行任何操作
            // 什么都不做，或者可以在这里添加一些取消操作的逻辑（如果有的话）
        }
    }

    //todo: add other methods such as loadGame, saveGame...

    public void saveGame() {
        try {
            //创建一个文件写入器,文件名为savegame.txt,如果文件不存在会自动创建,如果文件存在会覆盖原文件
            BufferedWriter writer = new BufferedWriter(new FileWriter("savegame.txt"));
            //首先写入棋盘的大小
            writer.write(model.getNumbers().length + " " + model.getNumbers()[0].length);
            //换行
            writer.newLine();
            System.out.println("you have saved the game");
            //接下来写入棋盘上的数字
            for (int i = 0; i < model.getNumbers().length; i++) {
                for (int j = 0; j < model.getNumbers()[i].length; j++) {
                    writer.write(String.valueOf(model.getNumbers()[i][j]));
                    if (j < model.getNumbers()[i].length - 1) {
                        writer.write(","); // 用逗号分隔数字
                    }
                }
                writer.newLine(); // 每行结束后换行
            }
            //写入得分和步数
            writer.write("Score: " + model.getScore());
            writer.write("\nStep: " + view.getSteps());
            if (frame instanceof TimingGameFrame) {//判断是否为计时模式
                TimingGameFrame timingGameFrame = (TimingGameFrame) frame;
                writer.write("\nTime: " + timingGameFrame.timeLeft); // 是则增加一行时间
            }
            writer.close();
        } catch (IOException e) {//捕获IO异常
            e.printStackTrace();
        }
    }

    public void loadGame() {
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
            int score;
            int step;
            // 接下来读取棋盘上的数字
            for (int i = 0; i < xCount; i++) {
                line = reader.readLine();
                String[] numberStrings = line.split(",");
                for (int j = 0; j < yCount; j++) {
                    numbers[i][j] = Integer.parseInt(numberStrings[j]);
                }
            }
            // 读取得分
            line = reader.readLine();
            String[] scoreParts = line.split(": ");
            score = Integer.parseInt(scoreParts[1]);
            // 读取步数
            line = reader.readLine();
            String[] stepParts = line.split(": ");
            step = Integer.parseInt(stepParts[1]);
            //读取时间
            line = reader.readLine();

            if (line ==null) {
                GameFrame newGameFrame = new GameFrame(xCount, yCount); // 创建新的游戏窗口
                newGameFrame.setVisible(true); // 显示新的游戏窗口
                newGameFrame.getGamePanel().getModel().setNumbers(numbers); // 设置棋盘状态
                // 设置分数
                newGameFrame.getGamePanel().getModel().setScore(score);
                // 设置步数
                newGameFrame.getGamePanel().getModel().setStep(step);
                // 更新分数和步数显示
                newGameFrame.getGamePanel().setScoreLabel(newGameFrame.getGamePanel().getModel().getScore());
                newGameFrame.getGamePanel().setStepLabel(newGameFrame.getGamePanel().getModel().getStep());
                // 更新游戏面板以显示加载的状态
                newGameFrame.getGamePanel().updateGridsNumber();
            } else {
                String[] timeParts = line.split(": ");
                int time = Integer.parseInt(timeParts[1]);
                TimingGameFrame newGameFrame = new TimingGameFrame(xCount, yCount, time); // 创建新的计时游戏窗口
                newGameFrame.setVisible(true); // 显示新的游戏窗口
                newGameFrame.getGamePanel().getModel().setNumbers(numbers); // 设置棋盘状态
                // 设置分数
                newGameFrame.getGamePanel().getModel().setScore(score);
                // 设置步数
                newGameFrame.getGamePanel().getModel().setStep(step);
                // 更新分数和步数显示
                newGameFrame.getGamePanel().setScoreLabel(newGameFrame.getGamePanel().getModel().getScore());
                newGameFrame.getGamePanel().setStepLabel(newGameFrame.getGamePanel().getModel().getStep());
                // 更新游戏面板以显示加载的状态
                newGameFrame.getGamePanel().updateGridsNumber();
            }
            reader.close();
            frame.dispose(); // 关闭当前窗口

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void playMusic() {
//        try {
//            FileInputStream fileInputStream = new FileInputStream("music.mp3");
//            player = new Player(fileInputStream);
//            new Thread(() -> {
//                try {
//                    player.play();
//                } catch (JavaLayerException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        } catch (FileNotFoundException | JavaLayerException e) {
//            e.printStackTrace();
//        }
    }
    public void callParentMethod(GameFrame object) {
        if (object instanceof TimingGameFrame) {
            System.out.println("调用对象是 ChildClass1");
        }
        // 调用父类的方法
    }

    public void setVolume() {
    }

    public void returnToHomePage() {

    }

    public void exit() {
        saveGame();//退出游戏时保存游戏
        System.exit(0);
    }

    public void gotoHome() {
        // 跳转到主页
        Menu2 menu2 = new Menu2();
        menu2.setVisible(true);
        frame.dispose();
        /*ChooseGamemode chooseGamemode = new ChooseGamemode(frame);
        chooseGamemode.setVisible(true);*/

    }
}

