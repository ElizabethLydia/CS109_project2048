package controller;

import model.GridNumber;
import view.GameFrame;
import view.GamePanel;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This class is used for interactive with JButton in GameFrame.
 */
public class GameController {
    private GamePanel view;
    private GridNumber model;
    private GameFrame frame;



    public GameController(GamePanel view, GridNumber model, GameFrame frame) {
        this.view = view;
        this.model = model;
        this.frame = frame;

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
            this.frame.dispose(); // 关闭当前窗口
            GameFrame newGameFrame = new GameFrame(view.getXCOUNT(), view.getYCOUNT()); // 创建新的游戏窗口
            newGameFrame.setVisible(true); // 显示新的游戏窗口
        } else if (result == JOptionPane.NO_OPTION) {
            // 用户选择“取消”，则不执行任何操作
            // 什么都不做，或者可以在这里添加一些取消操作的逻辑（如果有的话）
        }
    }

    //todo: add other methods such as loadGame, saveGame...
    public void saveGame() {
        try {
            String filePath = "data.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            //不想覆盖掉用BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true));
            for (int i = 0; i < model.getNumbers().length; i++) {
                for (int j = 0; j < model.getNumbers()[i].length; j++) {
                    writer.write(String.valueOf(model.getNumbers()[i][j]));
                    writer.write(" ");  // 使用空格分隔每个元素
                }
                writer.newLine();  // 换行
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadGame(String filePath) {
        try {
//            String filePath = "data.txt";
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            List<int[]> rows = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.trim().split(" ");
                int[] row = Arrays.stream(values).mapToInt(Integer::parseInt).toArray();
                rows.add(row);
            }

            int[][] restoredArray = new int[rows.size()][];
            for (int i = 0; i < rows.size(); i++) {
                restoredArray[i] = rows.get(i);
            }
            int xcount = restoredArray.length;
            int ycount = restoredArray[0].length;
            this.model = new GridNumber(xcount, ycount);
            this.model.setNumbers(restoredArray);
            this.view = new GamePanel((int) (frame.getHeight() * 0.8), xcount, ycount);
            this.view.updateGridsNumber();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setVolume() {
    }

    public void returnToHomePage() {
    }

    public void loadGame() {
    }

    public void exit() {
        System.exit(0);
    }
}
