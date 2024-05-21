package controller;

import model.GridNumber;
import view.*;

import javax.swing.*;
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
                TimingGameFrame timingGameFrame = (TimingGameFrame) frame;//判断是否为计时模式，如果是则停止计时器，并创建新的计时游戏窗口，否则创建新的游戏窗口
                timingGameFrame.timer.stop();
                timingGameFrame.dispose(); // 关闭当前窗口
                TimingGameFrame newGameFrame = new TimingGameFrame(view.getXCOUNT(), view.getYCOUNT(), frame.user, 60); // 创建新的游戏窗口
                newGameFrame.setVisible(true); // 显示新的游戏窗口
                // 停止定时器
            } else if (frame instanceof AIGameFrame) {
                AIGameFrame AiGameFrame = (AIGameFrame) frame;//判断是否为计时模式，如果是则停止计时器，并创建新的计时游戏窗口，否则创建新的游戏窗口
                AiGameFrame.timer.stop();
                AiGameFrame.dispose(); // 关闭当前窗口
                AIGameFrame newGameFrame = new AIGameFrame(view.getXCOUNT(), view.getYCOUNT(), frame.user); // 创建新的游戏窗口
                newGameFrame.setVisible(true); // 显示新的游戏窗口
                // 停止定时器
            } else{
                this.frame.dispose(); // 关闭当前窗口
                GameFrame newGameFrame = new GameFrame(view.getXCOUNT(), view.getYCOUNT(), frame.user); // 创建新的游戏窗口
                newGameFrame.setVisible(true); // 显示新的游戏窗口
            }
        } else if (result == JOptionPane.NO_OPTION) {
            // 用户选择“取消”，则不执行任何操作
            // 什么都不做，或者可以在这里添加一些取消操作的逻辑（如果有的话）
        }
    }

    //todo: add other methods such as loadGame, saveGame...

    public void saveGame() {
        frame.user.xCount = model.getNumbers().length;
        frame.user.yCount = model.getNumbers()[0].length;
        for (int i = 0; i < model.getNumbers().length; i++) {
            for (int j = 0; j < model.getNumbers()[i].length; j++) {
                frame.user.numbers[i][j] = model.getNumbers()[i][j];
            }
        }
        frame.user.score = model.getScore();
        frame.user.step = view.getSteps();
        if (frame instanceof TimingGameFrame) {
            frame.user.time = ((TimingGameFrame) frame).timeLeft;
        }
        frame.user.userManager.updateUser();
    }

    public void loadGame() {
        if (frame.user.time == 0) {
            GameFrame newGameFrame = new GameFrame(frame.user.xCount, frame.user.yCount, frame.user); // 创建新的游戏窗口
            newGameFrame.setVisible(true); // 显示新的游戏窗口
            newGameFrame.getGamePanel().getModel().setNumbers(frame.user.numbers); // 设置棋盘状态
            // 设置分数
            newGameFrame.getGamePanel().getModel().setScore(frame.user.score);
            // 设置步数
            newGameFrame.getGamePanel().getModel().setStep(frame.user.step);
            // 更新分数和步数显示
            newGameFrame.getGamePanel().setScoreLabel(newGameFrame.getGamePanel().getModel().getScore());
            newGameFrame.getGamePanel().setStepLabel(newGameFrame.getGamePanel().getModel().getStep());
            // 更新游戏面板以显示加载的状态
            newGameFrame.getGamePanel().updateGridsNumber();
        } else {
            TimingGameFrame newGameFrame = new TimingGameFrame(frame.user.xCount, frame.user.yCount, frame.user, frame.user.time); // 创建新的计时游戏窗口
            newGameFrame.setVisible(true); // 显示新的游戏窗口
            newGameFrame.getGamePanel().getModel().setNumbers(frame.user.numbers); // 设置棋盘状态
            // 设置分数
            newGameFrame.getGamePanel().getModel().setScore(frame.user.score);
            // 设置步数
            newGameFrame.getGamePanel().getModel().setStep(frame.user.step);
            // 更新分数和步数显示
            newGameFrame.getGamePanel().setScoreLabel(newGameFrame.getGamePanel().getModel().getScore());
            newGameFrame.getGamePanel().setStepLabel(newGameFrame.getGamePanel().getModel().getStep());
            // 更新游戏面板以显示加载的状态
            newGameFrame.getGamePanel().updateGridsNumber();
        }
        frame.dispose(); // 关闭当前窗口

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
            System.out.println("调用对象是 ChildClass1。");
        }
        // 调用父类的方法
    }

    public void setVolume() {
    }

    public void returnToHomePage() {

    }

    public void exit() {
        if (frame.user != null) {
            saveGame();//退出游戏时保存游戏
        }
        System.exit(0);
    }

    public void gotoHome() {
        if (frame.user != null) {
            // 跳转到主页
            Menu2 menu2 = new Menu2(frame.user);
            menu2.setVisible(true);
            frame.dispose();
        } else {
            Menu1 menu = new Menu1();
            menu.setVisible(true);
            frame.dispose();
        }

    }
}