package controller;

import model.GridNumber;
import view.GameFrame;
import view.GamePanel;

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
        System.out.println("Do restart game here");
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

}
