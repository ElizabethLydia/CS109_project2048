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
            String filePath = "data.txt";//保存的文件名
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));//写入文件
            //不想覆盖掉用BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true));
            for (int i = 0; i < model.getNumbers().length; i++) {//遍历整个面板
                for (int j = 0; j < model.getNumbers()[i].length; j++) {//遍历整个面板
                    writer.write(String.valueOf(model.getNumbers()[i][j]));//将整个面板的数字写入文件
                    writer.write(" ");  // 使用空格分隔每个元素
                }
                writer.newLine();  // 换行
            }

            writer.close();//关闭文件
        } catch (IOException e) {//捕获异常
            e.printStackTrace();//打印异常
        }
    }
    public void loadGame() {//加载游戏
        try {//捕获异常
            String filePath = "data.txt";//文件名
            BufferedReader reader = new BufferedReader(new FileReader(filePath));//读取文件

            List<int[]> rows = new ArrayList<>();//创建一个数组列表
            String line;//创建一个字符串
            while ((line = reader.readLine()) != null) {//读取文件
                String[] values = line.trim().split(" ");//将读取的文件分割成字符串数组
                int[] row = Arrays.stream(values).mapToInt(Integer::parseInt).toArray();//将字符串数组转换成整型数组
                rows.add(row);//将整型数组添加到数组列表
            }

            int[][] restoredArray = new int[rows.size()][];//创建一个二维数组
            for (int i = 0; i < rows.size(); i++) {//遍历数组列表
                restoredArray[i] = rows.get(i);//将数组列表的元素添加到二维数组
            }
            int xcount = restoredArray.length;//获取二维数组的长度
            int ycount = restoredArray[0].length;//获取二维数组的长度
            this.model = new GridNumber(xcount, ycount);//创建一个新的面板
            this.model.setNumbers(restoredArray);//设置面板的数字
            this.view=new GamePanel((int) (frame.getHeight() * 0.8),xcount,ycount);//创建一个新的面板
            this.view.updateGridsNumber();//更新面板的数字
            reader.close();//关闭文件
        } catch (IOException e) {//捕获异常
            e.printStackTrace();//打印异常
        }
    }

}
