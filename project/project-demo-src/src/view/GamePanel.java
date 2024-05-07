package view;

import model.GridNumber;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GamePanel extends ListenerPanel {
    private final int XCOUNT;
    private final int YCOUNT;
    private GridComponent[][] grids;
    private GridNumber model;
    private JLabel stepLabel;
    private int steps;
    private static int GRID_SIZE;

    public GamePanel(int size, int xcount, int ycount) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        this.setSize(size, size);
        this.XCOUNT = xcount;
        this.YCOUNT = ycount;
        this.GRID_SIZE = size / (Math.max(XCOUNT, YCOUNT));
        this.grids = new GridComponent[XCOUNT][YCOUNT];
        this.model = new GridNumber(XCOUNT, YCOUNT);
        initialGame();

    }

    public GridNumber getModel() {
        return model;
    }

    public void initialGame() {
        this.steps = 0;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                grids[i][j] = new GridComponent(i, j, model.getNumber(i, j), this.GRID_SIZE);
                grids[i][j].setLocation(j * GRID_SIZE, i * GRID_SIZE);
                this.add(grids[i][j]);
            }
        }
        model.printNumber();//check the 4*4 numbers in game
        this.repaint();
    }

    public void updateGridsNumber() {
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                grids[i][j].setNumber(model.getNumber(i, j));
            }
        }
        repaint();
    }


    /**
     * Implement the abstract method declared in ListenerPanel.
     * Do move right.
     */
    @Override
    public void doMoveRight() {//记得改现在是4*4的情况
        int[][] initialArray = new int[XCOUNT][YCOUNT];
        for (int i = 0; i < initialArray.length; i++) {
            for (int j = 0; j < initialArray[i].length; j++) {
                initialArray[i][j] = model.getNumber(i, j);
            }
        }
        this.model.moveRight();
        int[][] lastArray = new int[XCOUNT][YCOUNT];
        for (int i = 0; i < lastArray.length; i++) {
            for (int j = 0; j < lastArray[i].length; j++) {
                lastArray[i][j] = model.getNumber(i, j);
            }
        }
        if (this.checkValidMove(initialArray, lastArray)) {
            System.out.println("Click VK_RIGHT");
            this.afterMove();
            this.model.addRandomNumber();
            this.updateGridsNumber();
        } else {
            System.out.println("Unable to move right,try another direction");
            if (checkIfEnded(lastArray) == true) {
                System.out.println("game is over");
                //结束游戏界面
            }
        }
    }

    @Override
    public void doMoveLeft() {
        int[][] initialArray = new int[XCOUNT][YCOUNT];
        for (int i = 0; i < initialArray.length; i++) {
            for (int j = 0; j < initialArray[i].length; j++) {
                initialArray[i][j] = model.getNumber(i, j);
            }
        }
        this.model.moveLeft();
        int[][] lastArray = new int[XCOUNT][YCOUNT];
        for (int i = 0; i < lastArray.length; i++) {
            for (int j = 0; j < lastArray[i].length; j++) {
                lastArray[i][j] = model.getNumber(i, j);
            }
        }
        if (this.checkValidMove(initialArray, lastArray)) {
            System.out.println("Click VK_LEFT");
            this.afterMove();
            this.model.addRandomNumber();
            this.updateGridsNumber();
        } else {
            System.out.println("Unable to move left,try another direction");
            if (checkIfEnded(lastArray) == true) {
                System.out.println("game is over");
                //结束游戏界面
            }
        }
    }

    @Override
    public void doMoveUp() {
        int[][] initialArray = new int[XCOUNT][YCOUNT];
        for (int i = 0; i < initialArray.length; i++) {
            for (int j = 0; j < initialArray[i].length; j++) {
                initialArray[i][j] = model.getNumber(i, j);
            }
        }
        this.model.moveUp();
        int[][] lastArray = new int[XCOUNT][YCOUNT];
        for (int i = 0; i < lastArray.length; i++) {
            for (int j = 0; j < lastArray[i].length; j++) {
                lastArray[i][j] = model.getNumber(i, j);
            }
        }
        if (this.checkValidMove(initialArray, lastArray)) {
            System.out.println("Click VK_UP");
            this.afterMove();
            this.model.addRandomNumber();
            this.updateGridsNumber();
        } else {
            System.out.println("Unable to move up,try another direction");
            if (checkIfEnded(lastArray) == true) {
                System.out.println("game is over");
                //结束游戏界面
            }
        }
    }

    @Override
    public void doMoveDown() {
        int[][] initialArray = new int[XCOUNT][YCOUNT];
        for (int i = 0; i < initialArray.length; i++) {
            for (int j = 0; j < initialArray[i].length; j++) {
                initialArray[i][j] = model.getNumber(i, j);
            }
        }
        this.model.moveDown();
        int[][] lastArray = new int[XCOUNT][YCOUNT];
        for (int i = 0; i < lastArray.length; i++) {
            for (int j = 0; j < lastArray[i].length; j++) {
                lastArray[i][j] = model.getNumber(i, j);
            }
        }
        if (this.checkValidMove(initialArray, lastArray)) {
            System.out.println("Click VK_DOWN");
            this.afterMove();
            this.model.addRandomNumber();
            this.updateGridsNumber();
        } else {
            System.out.println("Unable to move down,try another direction");
            if (checkIfEnded(lastArray) == true) {
                System.out.println("game is over");
                //结束游戏界面
            }
        }
    }

    //判断是否是有效移动
    public boolean checkValidMove(int[][] initialArray, int[][] lastArray) {
        boolean check = false;
        for (int i = 0; i < initialArray.length; i++) {
            for (int j = 0; j < initialArray[i].length; j++) {
                if (initialArray[i][j] != lastArray[i][j]) {
                    check = true;
                }
            }
        }
        return check;
    }

    //判断游戏是否终止
    public boolean checkIfEnded(int[][] lastArray) {
        boolean check = false;
        this.model.moveRight();
        this.model.moveLeft();
        this.model.moveUp();
        this.model.moveDown();
        int[][] afterMoveArray = new int[lastArray.length][lastArray[0].length];
        for (int i = 0; i < afterMoveArray.length; i++) {
            for (int j = 0; j < afterMoveArray[i].length; j++) {
                afterMoveArray[i][j] = this.model.getNumber(i, j);
            }
        }
        if (!areGridsEqual(lastArray, afterMoveArray)) {
            check = false;
        } else check = true;
        return check;
    }

    private boolean areGridsEqual(int[][] lastArray, int[][] afterMoveArray) {
        for (int i = 0; i < lastArray.length; i++) {
            for (int j = 0; j < lastArray[i].length; j++) {
                if (lastArray[i][j] != afterMoveArray[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void afterMove() {//用于步数加1
        this.steps++;
        this.stepLabel.setText(String.format("Step: %d", this.steps));
    }


    public void setStepLabel(JLabel stepLabel) {//用于设置步数
        this.stepLabel = stepLabel;
    }
}